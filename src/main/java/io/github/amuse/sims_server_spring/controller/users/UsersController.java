package io.github.amuse.sims_server_spring.controller.users;

import io.github.amuse.sims_server_spring.dto.users.UserInfoReqDto;
import io.github.amuse.sims_server_spring.dto.users.UserInfoResDto;
import io.github.amuse.sims_server_spring.service.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController {

    private UserServiceImpl userService;

    @PostMapping("/api/users")
    public UserInfoResDto createUser(@RequestBody UserInfoReqDto reqData){
        return userService.createUser(reqData);
    }

    @GetMapping("/api/users")
    public List<UserInfoResDto> getUserList(@RequestParam(required = false, defaultValue = "0") int startAt,
                                           @RequestParam(required = false, defaultValue = "50") int maxResult){
        return userService.getUserList(startAt,maxResult);
    }

    @GetMapping("/api/users/{userKey}")
    public UserInfoResDto getUser(@PathVariable Long userKey){
        return userService.getUser(userKey);
    }

    @PutMapping("/api/users/{userKey}")
    public UserInfoResDto updateUser(@PathVariable Long userKey,
                           @RequestBody UserInfoReqDto reqData){
        return userService.updateUser(userKey,reqData);
    }

    @DeleteMapping("/api/users/{userKey}")
    public String deleteUser(@PathVariable Long userKey){
        return userService.deleteUser(userKey);
    }
}
