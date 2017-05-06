package com.bougouri.timetable.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bougouri.timetable.app.web.model.ProfessionalModel;
import com.bougouri.timetable.business.model.Professional;
import com.bougouri.timetable.business.service.Exception.BusinessException;
import com.bougouri.timetable.business.service.impl.BusinessService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class Controller {

	// private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private BusinessService businessService;

	@ApiOperation(value = "registerProfessional", nickname = "registerProfessional")
	@ApiParam(name = "professionalModel", value = "Profesional", required = true)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ProfessionalModel.class), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProfessionalModel> registerProfessional(@RequestBody final ProfessionalModel professionalModel) throws BusinessException {
		final Professional professional = new Professional();
		professionalModel.to(professional);
		businessService.registerProfessional(professional);
		return new ResponseEntity<>(professionalModel, HttpStatus.OK);
	}
}
