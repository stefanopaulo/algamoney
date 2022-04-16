package com.stefano.api.awm.backend.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final MessageSource messageSource;
	
	public AlgamoneyExceptionHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}



	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msgUser = messageSource.getMessage("requisicao.invalida", null, LocaleContextHolder.getLocale());
		String msgDeveloper = ex.getCause().toString();
		
		return handleExceptionInternal(ex, new Error(msgUser, msgDeveloper), headers, status, request);
	}
	
	public static class Error {
		private String msgUser;
		private String msgDeveloper;

		public Error(String msgUser, String msgDeveloper) {
			this.msgUser = msgUser;
			this.msgDeveloper = msgDeveloper;
		}
		
		public String getMsgUser() {
			return msgUser;
		}
		
		public String getMsgDeveloper() {
			return msgDeveloper;
		}
	}

}
