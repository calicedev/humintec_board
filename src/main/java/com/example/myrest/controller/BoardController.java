package com.example.myrest.controller;

import com.example.myrest.model.TB_Board;
import com.example.myrest.model.TB_User;
import com.example.myrest.repository.BoardRepository;
import com.example.myrest.repository.UserRepository;
import com.example.myrest.service.BoardService;
import com.example.myrest.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size=4) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText){
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<TB_Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText,searchText,pageable);
        System.out.println(pageable);



        int startPage= Math.max(1,boards.getPageable().getPageNumber() -10);
        int endPage= Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() +10);
        model.addAttribute("starPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
//        System.out.println("#############################################################################");
//        System.out.println(startPage);
//        System.out.println(boards.getTotalPages());
//        System.out.println(endPage);
        return "board/list";
    }

//    @GetMapping("/list")
//    public Page<TB_User> getAllUsers() {
//        PageRequest pageRequest = PageRequest.of(0, 5);
//        return userRepository.findAll(pageRequest);
//    }


    @GetMapping("/form")
    public String form(Model model,@RequestParam(required = false) Long id) {
        if(id==null){
            model.addAttribute("board", new TB_Board() );
        }
        else{
            TB_Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board );
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String postForm(@Valid TB_Board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()){
            return "board/form";
        }

        String username=authentication.getName();
        boardService.save(username, board);
//        boardRepository.save(board);
        return "redirect:/board/list"; //리다이렉트 하면서 재업데이트 하는 과정
    }
}
