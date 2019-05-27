package io.github.amuse.sims_server_spring.domain.users;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name="user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userCode;
    @Column(updatable = false)
    @Setter(AccessLevel.NONE) private String userId;
    private String password;
    private String userName;
    private String dept;
    private String position;
    private String email;
    private String phone;
    private Long role;

    @Builder
    public Users(String userId, String password, String userName, String dept, String position, String email, String phone, Long role) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.dept = dept;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userCode=" + userCode +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", dept='" + dept + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
