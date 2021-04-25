package com.dongverine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dongverine.db1.domain.Board;
import com.dongverine.db1.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	//@Qualifier("BaordSreviceRepository")//기존방식
	@Qualifier("BaordSreviceQuerydsl")//Querydsl
	private BoardService boardService;
	
	@RequestMapping(value={"/getBoardList" , "/"})
	public String getBoardList(Model model, Board board) {
		List<Board> boardList = boardService.getBoardList(board);
		model.addAttribute("boardList",boardList);
		return "getBoardList";
	}
	
	@RequestMapping("/getAjaxBoardList")
	@ResponseBody
	public List<Board> getAuaxBoardList(Board board) {
		List<Board> boardList = boardService.getBoardList(board);
		return boardList;
	}	
	
	@RequestMapping("/insertBoard")
	public String insertBoardView() {
		return "insertBoard";
	}
	
	
	@PostMapping("/insertBoard")
	public String insertBoardView(Board board) {
		boardService.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Board board, Model model) {
		Board boardGet = boardService.getBoard(board);
		boardService.updateBoardCnt(boardGet);
		model.addAttribute("board",boardGet);
		return "getBoard";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
