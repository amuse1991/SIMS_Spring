package io.github.amuse.sims_server_spring.service.users;

import io.github.amuse.sims_server_spring.domain.users.Users;
import io.github.amuse.sims_server_spring.domain.users.UsersRepository;
import io.github.amuse.sims_server_spring.dto.users.UserInfoReqDto;
import io.github.amuse.sims_server_spring.dto.users.UserInfoResDto;
import io.github.amuse.sims_server_spring.exceptions.users.DuplicateUserIdException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UsersRepository usersRepository;

    @Override
    public UserInfoResDto createUser(UserInfoReqDto reqData) {
        String userId = reqData.getUserId();
        // 사용자 id 중복검사
        if(usersRepository.existsByUserId(userId)){
            throw new DuplicateUserIdException(userId);
        }

        Users newUser = Users.builder()
                .userId(userId)
                .password(reqData.getPassword())
                .userName(reqData.getUserName())
                .dept(reqData.getDept())
                .position(reqData.getPosition())
                .email(reqData.getEmail())
                .phone(reqData.getPhone())
                .build();

        usersRepository.save(newUser);

        return UserInfoResDto.builder()
                .userId(newUser.getUserId())
                .userName(newUser.getUserName())
                .dept(newUser.getDept())
                .position(newUser.getPosition())
                .email(newUser.getEmail())
                .phone(newUser.getPhone())
                .build();
    }

    @Override
    public List<UserInfoResDto> getUserList(int startAt, int maxResult) {
        List<UserInfoResDto> resList = new ArrayList<>();
        List<Users> users = usersRepository.findAll(); // TODO:페이징
        users.forEach(user->{
            resList.add(UserInfoResDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .dept(user.getDept())
                .position(user.getPosition())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build());
        });
        return resList;
    }

    @Override
    public UserInfoResDto getUser(Long userKey) {
        Users user = usersRepository.findById(userKey)
                .orElseThrow(()->new EntityNotFoundException("can't find user " + userKey));
        return UserInfoResDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .dept(user.getDept())
                .position(user.getPosition())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public UserInfoResDto updateUser(Long userKey, UserInfoReqDto reqData) {
        Users user = usersRepository.findById(userKey)
                .orElseThrow(()->new EntityNotFoundException("can't find user " + userKey));
        user.setPassword(reqData.getPassword());
        user.setUserName(reqData.getUserName());
        user.setDept(reqData.getDept());
        user.setPosition(reqData.getPosition());
        user.setEmail(reqData.getEmail());
        user.setPhone(reqData.getPhone());

        return UserInfoResDto.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .dept(user.getDept())
                .position(user.getPosition())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public String deleteUser(Long userKey) {
        if(!usersRepository.existsById(userKey)){
            throw new EntityNotFoundException();
        }
        usersRepository.deleteById(userKey);
        return userKey.toString();
    }
}
