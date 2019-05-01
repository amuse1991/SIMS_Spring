package io.github.amuse.sims_server_spring.domain.users;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="user")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UserCode", updatable = false)
    private Long userCode;

    @Column(name = "UserId")
    private String userId;

    @Column(name="Password")
    private String password;

    @Column(name = "Name")
    private String userName;

    @Column(name="Dept")
    private String dept;

    @Column(name="Position")
    private String position;

    @Column(name="Email")
    private String email;

    @Column(name="Phone")
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
