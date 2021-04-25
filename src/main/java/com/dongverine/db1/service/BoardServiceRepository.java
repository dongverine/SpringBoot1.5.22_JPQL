package com.dongverine.db1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongverine.db1.domain.Board;
import com.dongverine.db1.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("BaordSreviceRepository")
public class BoardServiceRepository implements BoardService{

	@Autowired
	private BoardRepository boardRepo;
	
	public List<Board> getBoardList(Board board){
		return (List<Board>) boardRepo.findAll();
	}
	
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}
	
	public Board getBoard(Board board) {
		//return boardRepo.findById(board.getSeq()).get();
		return boardRepo.findOne(board.getSeq());
	}
	
	public void updateBoard(Board board) {
		//Board findBoard = boardRepo.findById(board.getSeq()).get();
		Board findBoard = boardRepo.findOne(board.getSeq());
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}
	
	public void updateBoardCnt(Board board) {
		//Board findBoard = boardRepo.findById(board.getSeq()).get();
		Board findBoard = boardRepo.findOne(board.getSeq());
		log.info("1:"+findBoard.toString());
		findBoard.setCnt(findBoard.getCnt()+1);
		log.info("2:"+findBoard.toString());
		boardRepo.save(findBoard);
	}
	
	public void deleteBoard(Board board) {
		//boardRepo.deleteById(board.getSeq());//1.5.22에는 없다.
		boardRepo.delete(board.getSeq());
	}
}
