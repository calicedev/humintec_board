package com.example.myrest.controller;

import java.util.List;

import com.example.myrest.model.TB_Board;
import com.example.myrest.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@RestController
@RequestMapping("/api")
class BoardApiController {
    @Autowired
    private BoardRepository repository;

    @GetMapping("/boards") // 전체 조회
    List<TB_Board> all(@RequestParam(required = false, defaultValue = "") String title,
                       @RequestParam(required=false) String content) {
//        타이틀이랑 컨텐트 빈값이면 전체 조회 (게시글 검색)
        if(StringUtils.isEmpty(title)&&StringUtils.isEmpty(content)){
            return repository.findAll();
        }else{
            return repository.findByTitle(title);

        }
    }

    @PostMapping("/boards")
    TB_Board newBoard(@RequestBody TB_Board newBoard) {
        return repository.save(newBoard);
    }


    @GetMapping("/boards/{id}") //아이디에 해당하는 한명만 가져오기 (조회)
    TB_Board one(@PathVariable Long id) {

        return repository.findById(id).orElse(null);

    }

    @PutMapping("/boards/{id}") //사용자가 있는지 확인하고 있으면 업데이트 없으면 insert (업데이트)
    TB_Board replaceBoard(@RequestBody TB_Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}