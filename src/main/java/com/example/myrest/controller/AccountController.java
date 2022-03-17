package com.example.myrest.controller;
import com.example.myrest.model.TB_User;
import com.example.myrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/register2")
    public String register2() {
        return "account/register2";
    }

    @PostMapping("/register")
    //사용자 저장 (패스워드 암호화, 사용자 권한 추가)
    public String register(@Valid TB_User user) {
        System.out.println(user.toString());
        userService.save(user);
        return "account/login";
    }


    @ResponseBody
    @PostMapping("/register2")
    //사용자 저장 (패스워드 암호화, 사용자 권한 추가)
    public HashMap<String, Object> register2(@Valid TB_User user) {
        System.out.println("#############             1            #######################");
        HashMap<String, Object> result = userService.usernameOverlap(user.getUsername());
        System.out.println("###################         2                 #################");
        System.out.println(result);
        return result;
    }


//    @ResponseBody
//    @PostMapping("/register2")
//    //사용자 저장 (패스워드 암호화, 사용자 권한 추가)
//    public String register2(@Valid TB_User user) {
//        String result = userService.usernameOverlap(user.getUsername());
//        System.out.println(user.getUsername());
//        System.out.println("####################################");
//        System.out.println(result);
//        return result;
////        userService.save(user);
////        return "redirect:/";
//    }

//    @ResponseBody
//    @PostMapping("/idcheck")
//    //사용자 저장 (패스워드 암호화, 사용자 권한 추가)
//    public HashMap<String, Object> idcheck(@RequestBody TB_User user) {
//        System.out.println("#############             1            #######################");
//        HashMap<String, Object> result = userService.usernameOverlap(user.getUsername());
//        System.out.println("###################         2                 #################");
//        System.out.println(result);
//        return result;
//    }





}

