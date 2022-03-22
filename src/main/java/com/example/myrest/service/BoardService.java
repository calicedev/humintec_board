package com.example.myrest.service;

import com.example.myrest.model.TB_Board;
import com.example.myrest.model.TB_User;
import com.example.myrest.repository.BoardRepository;
import com.example.myrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public TB_Board save(String username, TB_Board board){
        TB_User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
