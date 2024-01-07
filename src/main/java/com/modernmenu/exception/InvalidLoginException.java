package com.modernmenu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Invalid Login")
public class InvalidLoginException extends RuntimeException {

  private static final long serialVersionUID = -3967667122798196945L;

}
