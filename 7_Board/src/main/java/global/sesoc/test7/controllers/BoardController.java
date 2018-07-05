package global.sesoc.test7.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import global.sesoc.test7.dao.BoardRepository;
import global.sesoc.test7.util.FileService;
import global.sesoc.test7.util.PageNavigator;
import global.sesoc.test7.vo.Board;

@Controller
public class BoardController {
	
	@Autowired
	BoardRepository repository;
	
	// 파일 업로드 경로
	final String uploadPath = "/boardfile";
		
	@RequestMapping(value="/listboard", method=RequestMethod.GET)
	public String listboard
	(
		@RequestParam(value="currentPage", defaultValue="1") int currentPage, 
		@RequestParam(value="searchItem", defaultValue="userid") String searchItem, 
		@RequestParam(value="searchWord", defaultValue="") String searchWord, 
		Model model
	) {	
		
		int totalRecordCount = repository.getTotalBoard(searchItem, searchWord);		
		PageNavigator navi = new PageNavigator(currentPage, totalRecordCount);		
		List<Board> list = repository.select(searchItem, searchWord, navi.getStartRecord(), navi.getCountPerPage());		
		
		model.addAttribute("list", list);
		model.addAttribute("searchItem", searchItem);
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("navi", navi);
		model.addAttribute("currentPage", currentPage);
		
		return "board/boardList";
	}
	
	@RequestMapping(value="/writeboard", method=RequestMethod.GET)
	public String boardWrite() {
		
		return "board/boardWrite";
	}
	
	@RequestMapping(value="/writeboard", method=RequestMethod.POST)
	public String boardWrite(
		Board board, 
		MultipartFile upload, 
		HttpSession session
	) {
		String userid = session.getAttribute("loginId").toString();
		String originalfile = upload.getOriginalFilename();		
		String savedfile = FileService.saveFile(upload, uploadPath);
		
		board.setUserid(userid);
		board.setOriginalfile(originalfile);
		board.setSavedfile(savedfile);
		
		System.out.println("업로드 파일 : " + upload);
		
		repository.insert(board);
		
		return "redirect:listboard";
	}
	
	@RequestMapping(value="/detailboard", method=RequestMethod.GET)
	public String boardDetail(int boardnum, Model model) {
		// 조회수 증가
		repository.updateHitcount(boardnum);
		
		Board board = repository.selectOne(boardnum);
		
		model.addAttribute("board", board);
		
		return "board/boardDetail";
	}
	
	@RequestMapping(value="/deleteboard", method=RequestMethod.GET)
	public String boardDelete(int boardnum) {
		repository.delete(boardnum);
		
		return "redirect:listboard";
	}
	
	@RequestMapping(value="/updateboard", method=RequestMethod.GET)
	public String boardUpdate(int boardnum, Model model) {
		Board board = repository.selectOne(boardnum);
		
		model.addAttribute("board", board);
		
		return "board/boardUpdate";
	}
	
	@RequestMapping(value="/updateboard", method=RequestMethod.POST)
	public String boardUpdate(Board board) {
		repository.update(board);
		
		return "redirect:listboard";
	}
}