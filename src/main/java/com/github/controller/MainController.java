package com.github.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.github.exception.BusinessException;
import com.github.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  @GetMapping(value = "/testExceptionHandler", produces = APPLICATION_JSON_VALUE)
  public Response testExceptionHandler(
        @RequestParam(required = false, defaultValue = "false") boolean exception)
      throws BusinessException {
    if (exception) {
      throw new BusinessException("BusinessException in testExceptionHandler");
    }
    return new Response("OK");
  }

  @ExceptionHandler(BusinessException.class)
//  @ExceptionHandler({BusinessException.class, ServiceException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Response handleException(BusinessException e) {
    return new Response(e.getMessage());
  }
}
