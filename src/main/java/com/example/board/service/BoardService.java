package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.channels.MulticastChannel;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        board.setFilename(fileName);
        board.setFilepath("/files/" + fileName);
        board.setFilepath("/files/" + fileName);

        boardRepository.save(board);
    }

    // 게시물 리스트 처리
    public List<Board> boardList(){

        return boardRepository.findAll();
    }

    //특정 게시물 불러오기
    public Board boardView(Integer id){
        return boardRepository.findById(id).get();
    }

    // 특정 게시물 삭제

    public void boardDelete(Integer id){
        boardRepository.deleteById(id);
    }


}
