package com.example.myrest.controller;

import com.example.myrest.model.Reply;
import com.example.myrest.model.TB_User;
import com.example.myrest.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class ReplyController {

    @Autowired
    private final ReplyService replyService;


    /* CREATE */
    @ResponseBody
    @PostMapping("/formid=65")
//    public void save() {
    public String save(Long boardId,Reply reply) {
        System.out.println("success in here ");
        // System.out.println(content);
        System.out.println(reply.getContent());
        System.out.println(boardId);
        System.out.println("success in here ");
        System.out.println("success in here ");
        replyService.replySave(boardId, reply);
        return "성공";
    }


};