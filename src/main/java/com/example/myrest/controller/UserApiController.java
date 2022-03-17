package com.example.myrest.controller;

import java.util.List;

import com.example.myrest.model.TB_Board;
import com.example.myrest.model.TB_User;
import com.example.myrest.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
class UserApiController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<TB_User> all(){
        List<TB_User> users=repository.findAll();
        users.get(0).getBoards().size();
        return users;
    }
    // end::get-aggregate-root[]

    @PostMapping("/users")
    TB_User newUser(@RequestBody TB_User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("/users/{id}")
    TB_User one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);

    }

    @PutMapping("/users/{id}")
    TB_User replaceUser(@RequestBody TB_User newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
//                    user.setBoards(newUser.getBoards());
                    user.getBoards().clear();
                    user.getBoards().addAll(newUser.getBoards());
                    for(TB_Board board : user.getBoards()){
                        board.setUser(user);
                    }
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }
}