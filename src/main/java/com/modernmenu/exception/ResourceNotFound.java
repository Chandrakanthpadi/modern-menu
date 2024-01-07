package com.modernmenu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException {

  public ResourceNotFound(String string) {
    super(string);
  }

  private static final long serialVersionUID = 1L;

}
