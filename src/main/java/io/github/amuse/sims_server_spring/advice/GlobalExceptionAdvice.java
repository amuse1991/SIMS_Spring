package io.github.amuse.sims_server_spring.advice;

import io.github.amuse.sims_server_spring.exceptions.json.ParsingJsonToStringException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotFoundHandler(EntityNotFoundException ex){
        log.error(ex.getClass()+" : "+ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ParsingJsonToStringException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String ParsingJsonToStringHandler(ParsingJsonToStringException ex){
        log.error(ex.getClass()+" : "+ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String IllegalArgumentHandler(IllegalArgumentException ex){
        log.error(ex.getClass()+" : "+ex.getMessage());
        return ex.getMessage();
    }
}
