package global.sesoc.test7.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.SessionScope;

import global.sesoc.test7.dao.MemberRepository;
import global.sesoc.test7.vo.Member;

@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberRepository repository;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		
		return "member/joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Member member) {
		logger.info("member : {}", member.toString());
		
		repository.join(member);
		
		return "redirect:/";
	}
	
	// idCheck : 폼요청(GET)
	@RequestMapping(value = "/idCheck", method = RequestMethod.GET)
	public String idCheck() {
		
		return "member/idCheck";
	}
	
	// idCheck : 처리요청(POST)
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String idCheck(Member member, Model model) {
		logger.info(member.toString());	
		
		Member m = repository.selectMember(member);
		
		model.addAttribute("userid", member.getUserid());
		model.addAttribute("m", m);	
		
		return "member/idCheck";
	}
	
	// login : 폼요청(GET)
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		
		return "member/loginForm";
	}
	
	// login : 처리요청(POST)
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(
			Member member, 
			boolean saveid, 
			HttpServletResponse response, 
			HttpSession session, 
			Model model
	) {
		logger.info(member.toString());
		
		// 쿠키처리
		if(saveid) {
			Cookie cookie = new Cookie("saveid", member.getUserid());
			cookie.setMaxAge(60 * 60 * 24);
			
			response.addCookie(cookie);
		}
		else {
			Cookie cookie = new Cookie("saveid", null);
			cookie.setMaxAge(0);
			
			response.addCookie(cookie);
		}
		
		// DB처리
		Member m = repository.selectMember(member);
		
		// 세션처리
		if(m != null) {
			session.setAttribute("loginId", m.getUserid());
			session.setAttribute("loginName", m.getUsername());
			
			return "redirect:/";
		}
		else {
			model.addAttribute("message", "해당 아이디나 비밀번호가 없습니다.");
			return "member/loginForm";
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		//session.removeAttribute("loginId");
		//session.removeAttribute("loginName");
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, HttpSession session) {
		Member m = new Member();
		m.setUserid(session.getAttribute("loginId").toString());
		
		Member rs = repository.selectMember(m);
		
		model.addAttribute("rs", rs);
		
		return "member/updateForm";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Member member) {
		logger.info(member.toString());
		
		repository.update(member);
		
		return "redirect:/";
	}
}
