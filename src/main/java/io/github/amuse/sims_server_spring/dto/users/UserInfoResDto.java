package io.github.amuse.sims_server_spring.dto.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResDto {
    private Long userCode;
    private String userId;
    private String userName;
    private String dept;
    private String position;
    private String email;
    private String phone;

    @Builder
    public UserInfoResDto(Long userCode, String userId, String userName, String dept, String position, String email, String phone) {
        this.userCode = userCode;
        this.userId = userId;
        this.userName = userName;
        this.dept = dept;
        this.position = position;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserInfoResDto{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", dept='" + dept + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
