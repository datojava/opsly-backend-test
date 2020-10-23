package com.opsly.newsfeed.errors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opsly.newsfeed.entities.ErrorResponse;
import com.opsly.newsfeed.entities.ErrorType;

@ControllerAdvice
public class OpslyExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger log = LogManager.getLogger(getClass());

	@Autowired
	private ObjectMapper objMapper;

	@ExceptionHandler(value = { OpslyException.class })
	protected ResponseEntity<Object> handle(Exception ex, WebRequest request) throws JsonProcessingException {

		log.info("Handling exception : {}, message = {}", ex.getClass().getName(), ex.getMessage());

		ErrorResponse response = new ErrorResponse();

		response.setCode(ErrorType.SOMETHING_WENT_WRONG.getCode());
		response.setMessage(ErrorType.SOMETHING_WENT_WRONG.message());

		if (ex instanceof OpslyException) {

			OpslyException opsEx = ((OpslyException) ex);

			ErrorType errType = opsEx.getErrorType();

			if (errType != null) {
				response.setMessage(errType.message());
				response.setCode(errType.getCode());
			}

		}

		return handleExceptionInternal(ex, objMapper.writeValueAsString(response), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}
}
