package com.tdl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ToDoNoteNotFoundException extends RuntimeException {
	public ToDoNoteNotFoundException(String exception) {
		super(exception);
	}
}
