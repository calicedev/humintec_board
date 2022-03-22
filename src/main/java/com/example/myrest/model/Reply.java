package com.example.myrest.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 120)
    String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boardId")
    private TB_Board board;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private TB_User user; // 작성자


    public void save(TB_Board board) {
        this.board = board;
        this.user = user;
    }
};