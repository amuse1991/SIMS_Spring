package io.github.amuse.sims_server_spring.domain.users;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_key", updatable = false)
    private Long userKey;

    private String userId;
    private String password;
    private String userName;
    private String dept;
    private String position;
    private String email;
    private String phone;

    @Builder
    public Users(String userId, String password, String userName, String dept, String position, String email, String phone) {
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
        return "Users{" +
                "userKey=" + userKey +
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
