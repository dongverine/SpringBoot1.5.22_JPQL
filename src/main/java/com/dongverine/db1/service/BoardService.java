package com.dongverine.db1.service;

import java.util.List;

import com.dongverine.db1.domain.Board;

public interface BoardService {
	
	public List<Board> getBoardList(Board board);
	
	public void insertBoard(Board board);
	
	public Board getBoard(Board board);
	
	public void updateBoard(Board board);
	
	public void deleteBoard(Board board);
	
	public void updateBoardCnt(Board board);

}
