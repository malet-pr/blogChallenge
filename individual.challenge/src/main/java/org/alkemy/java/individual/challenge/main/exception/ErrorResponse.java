package org.alkemy.java.individual.challenge.main.exception;

import java.util.Date;
import java.util.List;

public class ErrorResponse {

    private int status;
    private String message;
    private Date timestamp;
    private List<String> errors;
    
    ErrorResponse() {}

    ErrorResponse(String message){
        this.message = message;
    }

	public ErrorResponse(int status, String message, Date timestamp, List<String> errors) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.errors = errors;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
    
    

}
