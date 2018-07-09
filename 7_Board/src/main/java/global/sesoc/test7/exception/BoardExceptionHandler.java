package global.sesoc.test7.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*

1) web.xml 에 등록
2) Spring 에서 에러를 처리하도록 등록
	- 클래스 생성
	- @ControllerAdvice
	- 메서드 생성
	 -> @ExceptionHandler(Exception.class)
	 	 
 */

@ControllerAdvice
public class BoardExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String errorHandler(Exception e, Model model) {
		model.addAttribute("errorMsg", e.getMessage());
		
		return "/error";
	}
	
}


