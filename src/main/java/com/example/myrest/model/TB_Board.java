package com.example.myrest.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
public class  TB_Board extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;
    private String content;


    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @CreationTimestamp
    @LastModifiedDate
    private LocalDateTime modifiedDate;


    //    게시판에다가 시용자 정보를 넣어 주는 것 user의 id와 board의 user_id와 연결되는 것
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private TB_User user;


}
