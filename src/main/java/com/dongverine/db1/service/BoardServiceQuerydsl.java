package com.dongverine.db1.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.dongverine.db1.domain.Board;
import com.dongverine.db1.domain.QBoard;
import com.dongverine.db1.repository.BoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@Service("BaordSreviceQuerydsl")
public class BoardServiceQuerydsl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;	
	
	@PersistenceContext
    EntityManager em;
	
	public List<Board> getBoardList(Board board){
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QBoard qboard = QBoard.board;
		List<Board> list = queryFactory.selectFrom(qboard).fetch();
		return list;
	}
	
	public void insertBoard(Board board) {
		//jpql은 insert가 없다
		boardRepo.save(board);
	}
	
	public Board getBoard(Board board) {
		QBoard qboard = QBoard.board;
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		return queryFactory.selectFrom(qboard)
				.where(qboard.seq.eq(board.getSeq()))
				.fetchOne();
	}
	
	@Transactional 
	public void updateBoard(Board board) {
        QBoard qboard = QBoard.board;
        JPAUpdateClause updateClause = new JPAUpdateClause(em, qboard);
        updateClause.where(qboard.seq.eq(board.getSeq()))
        			.set(qboard.title, board.getTitle())
        			.set(qboard.content, board.getContent())
        			.execute();
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
		//boardRepo.deleteById(board.getSeq());
		boardRepo.delete(board.getSeq());
	}
}
