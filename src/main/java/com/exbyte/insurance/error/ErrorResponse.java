package com.exbyte.insurance.error;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {
	
	private String message;
	private String code;
	private int status;
	private List<FieldError> errors = new ArrayList<>();
	
	@Builder
	public ErrorResponse(String message, String code, int status, List<FieldError> errors) {
		this.message = message;
		this.code = code;
		this.status = status;
		this.errors = errors;
	}
	
	@Getter
	public static class FieldError{
		private String field;
		private String value;
		private String reason;
		
		@Builder
		public FieldError(String field, String value, String reason) {
			this.field= field;
			this.value = value;
			this.reason = reason;
		}
	}
	
}
