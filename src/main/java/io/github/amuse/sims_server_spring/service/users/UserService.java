package io.github.amuse.sims_server_spring.service.users;

import io.github.amuse.sims_server_spring.dto.users.UserInfoReqDto;
import io.github.amuse.sims_server_spring.dto.users.UserInfoResDto;

import java.util.List;

public interface UserService {
    UserInfoResDto createUser(UserInfoReqDto reqData);
    public List<UserInfoResDto> getUserList(int startAt, int maxResult);
    public UserInfoResDto getUser(Long userKey);
    public UserInfoResDto updateUser(Long userKey,UserInfoReqDto reqData);
    public String deleteUser(Long userKey);
}
