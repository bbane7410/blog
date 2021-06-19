package com.jun.blog.service;

import com.jun.blog.model.Board;
import com.jun.blog.model.User;
import com.jun.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;



    @Transactional
    public void 글쓰기(Board board, User user) {//title,content
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    public List<Board> 글목록(){
        return boardRepository.findAll();
    }
}
