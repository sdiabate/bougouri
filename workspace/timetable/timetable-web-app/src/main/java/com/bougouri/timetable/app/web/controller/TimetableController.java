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
import com.bougouri.timetable.app.web.model.HolidayModel;
import com.bougouri.timetable.app.web.model.ProfessionalModel;
import com.bougouri.timetable.app.web.model.WorkingDayList;
import com.bougouri.timetable.business.model.Appointment;
import com.bougouri.timetable.business.model.Holiday;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.model.WorkingDay;
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
	public ResponseEntity<Result> makeAppointment(@RequestBody final AppointmentModel appointmentModel) throws Exception {
		final Appointment appointment = appointmentModel.to(new Appointment());
		appointment.setProfessional(businessService.findProfessional(appointmentModel.getProfessionalId()));
		businessService.makeAppointment(appointmentModel.getProfessionalId(), appointment, LocalDateTime.now());
		return new ResponseEntity<>(new Result(appointmentModel), HttpStatus.OK);
	}

	@PostMapping(value = "cancelAppointment", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> cancelAppointment(@RequestBody final AppointmentModel appointmentModel) throws Exception {
		final Optional<Appointment> appointment = businessService.cancelAppointment(appointmentModel.getProfessionalId(), LocalDateTime.parse(appointmentModel.getDate(), DATE_TIME_FORMATTER));
		final AppointmentModel appointmentModelToReturn = new AppointmentModel();
		appointment.ifPresent(a -> appointmentModelToReturn.from(a));
		return new ResponseEntity<>(new Result(appointment), HttpStatus.OK);
	}
	
	@PostMapping(value = "scheduleHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> scheduleHoliday(@RequestBody final HolidayModel holidayModel) throws Exception {
		final LocalDateTime startDateTime = LocalDateTime.parse(holidayModel.getStartDateTime(), DATE_TIME_FORMATTER);
		final LocalDateTime endDateTime = LocalDateTime.parse(holidayModel.getEndDateTime(), DATE_TIME_FORMATTER);
		final Holiday holiday = new Holiday(startDateTime, endDateTime);
		final Holiday scheduledHoliday = businessService.scheduleHoliday(holidayModel.getProfessionalId(), holiday, LocalDateTime.now());
		return new ResponseEntity<>(new Result(holidayModel.from(scheduledHoliday)), HttpStatus.OK);
	}

	@PostMapping(value = "cancelHoliday", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> cancelHoliday(@RequestBody final HolidayModel holidayModel) throws Exception {
		final Optional<Holiday> cancelledHoliday = businessService.cancelHoliday(holidayModel.getProfessionalId(), LocalDateTime.parse(holidayModel.getStartDateTime(), DATE_TIME_FORMATTER));
		final HolidayModel holidayModelToReturn = new HolidayModel();
		cancelledHoliday.ifPresent(h -> holidayModelToReturn.from(h));
		return new ResponseEntity<>(new Result(holidayModelToReturn), HttpStatus.OK);
	}
	
	@PostMapping(value = "defineWorkingDays", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> defineWorkingDays(@RequestBody final WorkingDayList workingDayList) throws Exception {
		final List<WorkingDay> workingDays = workingDayList.getWorkingDayModels().stream().map(workingDayModel -> workingDayModel.to(new WorkingDay())).collect(Collectors.toList());
		businessService.defineWorkingDays(workingDayList.getProfessionalId(), workingDays);
		return new ResponseEntity<>(new Result(null), HttpStatus.OK);
	}
	
	@GetMapping(value = "getOnGoingAppointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getOnGoingAppointments(final long professionalId) throws Exception {
		final List<AppointmentModel> appointmentModels = businessService.getOnGoingAppointments(professionalId, LocalDateTime.now()).stream().map(a -> new AppointmentModel().from(a)).collect(Collectors.toList());
		return new ResponseEntity<>(new Result(appointmentModels), HttpStatus.OK);
	}
	
	@GetMapping(value = "getOnGoingHolidays", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getOnGoingHolidays(final long professionalId) throws Exception {
		final List<HolidayModel> holidayModels = businessService.getOnGoingHolidays(professionalId, LocalDateTime.now()).stream().map(h -> new HolidayModel().from(h)).collect(Collectors.toList());
		return new ResponseEntity<>(new Result(holidayModels), HttpStatus.OK);
	}
	
}
