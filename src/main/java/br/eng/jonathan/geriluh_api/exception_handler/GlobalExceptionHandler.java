package br.eng.jonathan.geriluh_api.exception_handler;

import br.eng.jonathan.geriluh_api.exception_handler.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;


	/**
	 * Trata erros de {@link NotFoundException} rastreadas na API.
	 * @author Jonathan Nascimento
	 * @since 1.0
	 * @serialData 2024-11-28
	 * @return {@link ResponseEntityExceptionHandler}
	 */
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> notFoundException(NotFoundException ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(
				OffsetDateTime.now(),
				HttpStatus.NOT_FOUND.getReasonPhrase(),
				Arrays.asList(ex.getMessage()),
				request.getDescription(false));

		return handleExceptionInternal(ex, exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}
