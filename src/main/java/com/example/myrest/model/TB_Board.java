package com.example.myrest.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class TB_Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;
    private String content;

//    게시판에다가 시용자 정보를 넣어 주는 것 user의 id와 board의 user_id와 연결되는 것
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
//    @JoinColumn(name="user_id", referencedColumnName = "id")
    private TB_User user;


}
