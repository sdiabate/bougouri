package com.bougouri.timetable.app.web.controller;

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

import com.bougouri.timetable.app.web.model.ProfessionalModel;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.service.IBasicDaoService;
import com.bougouri.timetable.business.service.IBusinessService;
import com.bougouri.timetable.business.service.Exception.BusinessException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("timetable/")
public class TimetableController {
	
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
	public ResponseEntity<Result> registerProfessional(@RequestBody final ProfessionalModel professionalModel) throws BusinessException {
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
}
