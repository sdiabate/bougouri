package com.bougouri.timetable.app.services;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.bougouri.timetable.app.services.controller.Result;
import com.bougouri.timetable.app.services.model.ProfessionalModel;
import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.TimeSlot;
import com.bougouri.timetable.business.model.Weekday;
import com.bougouri.timetable.business.model.WorkingDay;
import com.bougouri.timetable.business.service.Exception.ExceptionType;
import com.bougouri.timetable.business.service.impl.BasicDaoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebServiceStarter.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @WebMvcTest(TimetableController.class)
@ActiveProfiles("dev")
public class TimetableControllerTest {

	@Autowired
	protected BasicDaoService daoService;

	@Autowired
	private TestRestTemplate template;

	@LocalServerPort
	private int port;

	@After
	public void cleanDatabase() {
		daoService.deleteAll(Professional.class);
		daoService.deleteAll(Appointment.class);
	}

	@Test
	public void professionalRegistrationTest() {
		// Register a professional
		final Professional professional = new Professional("sdi", "pwdpwdpwdpwd", "Seydou", "KONE", "Gynecologue", "");
		final ResponseEntity<Result> createProfessionalResponse = template.postForEntity(getUrl("registerProfessional"), new ProfessionalModel().from(professional), Result.class);
		Assertions.assertThat(createProfessionalResponse.getStatusCode().value()).isEqualTo(200);
		Assertions.assertThat(toSingleObject(createProfessionalResponse.getBody(), ProfessionalModel.class).to(new Professional()).getLogin()).isEqualTo("sdi");

		// check that the professional has been successfully registered
		final ResponseEntity<Result> getAllProfessionalResponse = template.getForEntity(getUrl("professionalList"), Result.class);
		Assertions.assertThat(getAllProfessionalResponse.getStatusCode().value()).isEqualTo(200);
		Assertions.assertThat(toListObject(getAllProfessionalResponse.getBody(), ProfessionalModel.class).size()).isEqualTo(1);
	}

	@Test
	public void professionalRegistrationExceptionTest() {
		// Register a professional with first name empty
		final Professional professional = new Professional("sdi", "pwdpwdpwdpwd", "", "KONE", "Gynecologue", "");
		final ResponseEntity<Result> createProfessionalResponse = template.postForEntity(getUrl("registerProfessional"), new ProfessionalModel().from(professional), Result.class);
		Assertions.assertThat(createProfessionalResponse.getStatusCode().value()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
		Assertions.assertThat(createProfessionalResponse.getBody().getExceptionCode()).isEqualTo(ExceptionType.INVALID_PROPERTY_VALUE.name());
		Assertions.assertThat(createProfessionalResponse.getBody().getExceptionMessage()).containsSequence("firstName");

		// check that the professional has NOT been registered
		final ResponseEntity<Result> getAllProfessionalResponse = template.getForEntity(getUrl("professionalList"), Result.class);
		Assertions.assertThat(getAllProfessionalResponse.getStatusCode().value()).isEqualTo(200);
		Assertions.assertThat(toListObject(getAllProfessionalResponse.getBody(), ProfessionalModel.class).size()).isEqualTo(0);
	}

	@Test
	public void defineWorkingDaysTest() {
		final Professional professional = new Professional("sdi", "pwdpwdpwdpwd", "Seydou", "KONE", "Gynecologue", "");
		final ResponseEntity<Result> createProfessionalResponse = template.postForEntity(getUrl("registerProfessional"), new ProfessionalModel().from(professional), Result.class);
		Assertions.assertThat(createProfessionalResponse.getStatusCode().value()).isEqualTo(200);
		Assertions.assertThat(toSingleObject(createProfessionalResponse.getBody(), ProfessionalModel.class).to(new Professional()).getLogin()).isEqualTo("sdi");
		
		// TODO Create an register the working days
	}

	protected List<WorkingDay> createWorkingDays() {
		return Stream.of(Weekday.values()).filter(weekday -> weekday != Weekday.SUNDAY).map(weekday -> createWorkingDay(weekday)).collect(Collectors.toList());
	}

	private WorkingDay createWorkingDay(final Weekday weekday) {
		final WorkingDay workingDay = new WorkingDay(weekday);
		workingDay.getTimeSlots().add(new TimeSlot(LocalTime.of(8, 0), LocalTime.of(8, 30)));
		workingDay.getTimeSlots().add(new TimeSlot(LocalTime.of(8, 30), LocalTime.of(9, 0)));
		return workingDay;
	}

	private String getUrl(final String service) {
		return String.format("http://localhost:%s/timetable/professional/%s", port, service);
	}
	
	private <T> T toSingleObject(final Result result, final Class<T> clazz) {
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(result.getData(), clazz);
	}

	private <T> List<T> toListObject(final Result result, final Class<T> clazz) {
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(result.getData(), mapper.getTypeFactory().constructCollectionLikeType(List.class, clazz));
	}
	
}
