package io.github.amuse.sims_server_spring.controller.users;

import io.github.amuse.sims_server_spring.dto.users.UserInfoReqDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UsersController {

    @PostMapping("/users")
    public void createUser(@RequestBody UserInfoReqDto reqData){

    }

    @GetMapping("/users")
    public void getAllUser(){

    }

    @GetMapping("/users/{userKey}")
    public void getUser(@PathVariable String userKey){

    }

    @PutMapping("/users/{userKey}")
    public void updateUser(@PathVariable String userKey,
                           @RequestBody UserInfoReqDto reqData){
    }

    @DeleteMapping("/users/{userKey}")
    public void deleteUser(@PathVariable String userKey){

    }
}
