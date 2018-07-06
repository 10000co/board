package global.sesoc.test7.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		String fullPath = null;
		String type = null;
		
		if(board.getSavedfile() != null) {
			fullPath = uploadPath + "/" + board.getSavedfile();
		}
		
		try {
			
			if(fullPath != null) {
				// mime 타입(파일타입) 가져오기
				type = Files.probeContentType(Paths.get(fullPath));
			}
			
			System.out.println(type);
			
			if(type != null && type.contains("image")) {
				model.addAttribute("type", type);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("board", board);
		
		return "board/boardDetail";
	}
	
	@RequestMapping(value="/deleteboard", method=RequestMethod.GET)
	public String boardDelete(int boardnum) {
		Board board = repository.selectOne(boardnum);
		String savedfile = board.getSavedfile();
		
		String fullPath = uploadPath + "/" + savedfile;
		
		// 파일 삭제 처리
		boolean result = FileService.deleteFile(fullPath);
		System.out.println(result == true ? "처리완료" : "처리실패");
		
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
	public String boardUpdate(Board board, MultipartFile upload) {
		int boardnum = board.getBoardnum();
		
		Board old = repository.selectOne(boardnum);
		
		// 첨부한 경우
		if(upload.getSize() != 0 && upload != null) {
			// 기존 파일 삭제
			String fullPath = uploadPath + "/" + board.getSavedfile();
			boolean result = FileService.deleteFile(fullPath);
			System.out.println(result == true ? "기존파일삭제성공" : "기존파일삭제실패");
			
			// 파일 업로드
			String originalfile = upload.getOriginalFilename();
			String savedfile = FileService.saveFile(upload, uploadPath);
			
			board.setOriginalfile(originalfile);
			board.setSavedfile(savedfile);
			
			repository.update(board);
		}
		// 첨부하지 않은 경우
		else {
			// 타이틀과 내용만 새로운 내용으로 적용
			old.setTitle(board.getTitle());
			old.setContent(board.getContent());
			repository.update(old);
		}
		
		return "redirect:listboard";
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public String download(int boardnum, HttpServletResponse response) {
		Board board = repository.selectOne(boardnum);
		
		String savedfile = board.getSavedfile();
		String originalfile = board.getOriginalfile();
		
		String fullPath = uploadPath + "/" + savedfile;
		
		FileInputStream fin = null;
		ServletOutputStream fout = null;
		
		try {
			fin = new FileInputStream(fullPath);
			fout = response.getOutputStream();
			
			// 브라우저가 자동으로 파일을 열지 않도록 지정( Content-Disposition )
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(originalfile, "UTF-8"));
			FileCopyUtils.copy(fin, fout);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(fin != null) fin.close();
				if(fout != null) fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	@RequestMapping(value="/deletefile", method=RequestMethod.GET)
	public String filedelete(int boardnum, RedirectAttributes rttr) {		
		// 파일 정보 가져오기
		Board board = repository.selectOne(boardnum);
		
		// 삭제할 실제 파일명 가져오기
		String savedfile = board.getSavedfile();
		
		// 파일경로
		String fullPath = uploadPath + "/" + savedfile;		
		
		// 삭제후 수정된 부분을 반영하는 부분
		board.setOriginalfile(null);
		board.setSavedfile(null);
		repository.update(board);
		
		// 파일삭제
		boolean result = FileService.deleteFile(fullPath);
		System.out.println(result == true ? "추가된 파일 삭제 성공" : "추가된 파일 삭제 실패");
		
		// 수정된 내용을 반영하여 업데이트 보드로 리다이렉트
		rttr.addAttribute("boardnum", boardnum);
		
		return "redirect:updateboard";
	}
}

