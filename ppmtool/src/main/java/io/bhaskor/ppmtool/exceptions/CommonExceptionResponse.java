package io.bhaskor.ppmtool.exceptions;

public class CommonExceptionResponse {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CommonExceptionResponse(String message) {
		this.message = message;
	}
}
