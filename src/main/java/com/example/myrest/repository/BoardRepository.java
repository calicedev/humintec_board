package com.example.myrest.repository;

import com.example.myrest.model.TB_Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Long 이 의미하는 것은 pk
public interface BoardRepository extends JpaRepository <TB_Board, Long>{
    List<TB_Board> findByTitle(String title);
    List<TB_Board>findByTitleOrContent(String title, String content);
    Page<TB_Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
