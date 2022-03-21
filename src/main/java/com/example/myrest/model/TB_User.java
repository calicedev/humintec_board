package com.example.myrest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TB_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "이름을 다시 입력해주세요.")
    private String username;
    private String email;
    private String password;
    private Integer birthday;
    private String phone;
    private Boolean enabled;
//    private String rawPassword;


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    //유저에 해당하는 role이 roles에 담기게 된다.
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    //    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
    private List<TB_Board> boards = new ArrayList<>();

}
