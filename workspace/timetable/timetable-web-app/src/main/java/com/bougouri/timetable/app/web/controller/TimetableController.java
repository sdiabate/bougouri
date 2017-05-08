package com.bougouri.timetable.app.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bougouri.timetable.app.web.model.AppointmentModel;
import com.bougouri.timetable.app.web.model.ProfessionalModel;
import com.bougouri.timetable.app.web.model.WorkingDayModel;
import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.service.IBasicDaoService;
import com.bougouri.timetable.business.service.IBusinessService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("timetable/")
public class TimetableController {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d[d]/M[M]/yyyy H[H]:m[m]:s[s]");

	// private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private IBusinessService businessService;

	@Autowired
	private IBasicDaoService daoService;

	@ApiOperation(value = "registerProfessional", nickname = "registerProfessional")
	@ApiParam(name = "professionalModel", value = "Professional", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ProfessionalModel.class), @ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@PostMapping(value = "registerProfessional", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> registerProfessional(@RequestBody final ProfessionalModel professionalModel) throws Exception {
		final Professional professional = new Professional();
		professionalModel.to(professional);
		businessService.registerProfessional(professional);
		return new ResponseEntity<>(new Result(professionalModel), HttpStatus.OK);
	}

	@GetMapping(value = "professionalList", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getAllProfessionals() {
		final Result result = new Result(daoService.getAll(Professional.class).stream().map(p -> new ProfessionalModel().from(p)).collect(Collectors.toList()));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping(value = "makeAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> makeAppointment(final long professionalId, final AppointmentModel appointmentModel) throws Exception {
		final Appointment appointment = appointmentModel.to(new Appointment());
		appointment.setProfessional(businessService.findProfessional(professionalId));
		businessService.makeAppointment(professionalId, appointment, LocalDateTime.now());
		return new ResponseEntity<>(new Result(appointmentModel), HttpStatus.OK);
	}
	
	@PostMapping(value = "cancelAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> cancelAppointment(final long professionalId, final String dateTime) throws Exception {
		final Optional<Appointment> appointment = businessService.cancelAppointment(professionalId, LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER));
		final AppointmentModel appointmentModel = new AppointmentModel();
		appointment.ifPresent(a -> appointmentModel.from(a));
		return new ResponseEntity<>(new Result(appointment), HttpStatus.OK);
	}

	@PostMapping(value = "scheduleHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> scheduleHoliday(final long professionalId, final String startDateTime, final String endDateTime) throws Exception {
		throw new IllegalAccessException("Not implemented");
	}
	
	@PostMapping(value = "scheduleHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> cancelHoliday(final long professionalId, final String startDateTime, final String endDateTime) throws Exception {
		throw new IllegalAccessException("Not implemented");
	}

	@PostMapping(value = "scheduleHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> defineWorkingDays(final long professionalId, final List<WorkingDayModel> workingDays) throws Exception {
		throw new IllegalAccessException("Not implemented");
	}

	@GetMapping(value = "scheduleHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getOnGoingAppointments(final long professionalId) throws Exception {
		throw new IllegalAccessException("Not implemented");
	}

	@GetMapping(value = "scheduleHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getOnGoingHolidays(final long professionalId) throws Exception {
		throw new IllegalAccessException("Not implemented");
	}

}
