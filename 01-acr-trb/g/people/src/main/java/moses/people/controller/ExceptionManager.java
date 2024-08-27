package moses.people.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import moses.people.PersonNotFoundException;

@ControllerAdvice
public class ExceptionManager {

  @ExceptionHandler(PersonNotFoundException.class)
  public ResponseEntity<String> handleNotFound(RuntimeException exception) {
    return ResponseEntity
      .status(HttpStatus.NOT_FOUND)
      .body(exception.getMessage()
    );
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
    return ResponseEntity
      .status(HttpStatus.BAD_REQUEST)
      .body(exception.getMessage()
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception exception) {
    return ResponseEntity
      .status(HttpStatus.INTERNAL_SERVER_ERROR)
      .body(exception.getMessage());
  }

  @ExceptionHandler(Throwable.class)

  public ResponseEntity<String> handleThrowable(Throwable exception) {
    return ResponseEntity
      .status(HttpStatus.BAD_GATEWAY)
      .body(exception.getMessage());
  }
}