package com.modernmenu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class ResourceAlreadyExists extends RuntimeException {

  public ResourceAlreadyExists(String string) {
    super(string);
  }

  private static final long serialVersionUID = 1L;

}
