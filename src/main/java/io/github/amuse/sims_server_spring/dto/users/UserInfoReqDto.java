package io.github.amuse.sims_server_spring.dto.users;

import lombok.*;

@Getter
@Setter
public class UserInfoReqDto {
    private Long userCode;
    private String userId;
    private String password;
    private String userName;
    private String dept;
    private String position;
    private String email;
    private String phone;

    @Builder
    public UserInfoReqDto(Long userCode, String userId, String password, String userName, String dept, String position, String email, String phone) {
        this.userCode = userCode;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.dept = dept;
        this.position = position;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserInfoReqDto{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", dept='" + dept + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
