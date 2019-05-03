package io.github.amuse.sims_server_spring.advice.users;

import io.github.amuse.sims_server_spring.exceptions.users.DuplicateUserIdException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class UsersExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicateUserIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String DuplicateUserIdHandler(DuplicateUserIdException ex){
        log.error(ex.getClass()+" : "+ex.getMessage());
        return ex.getMessage();
    }
}
