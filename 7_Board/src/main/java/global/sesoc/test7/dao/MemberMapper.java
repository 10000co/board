package global.sesoc.test7.dao;

import global.sesoc.test7.vo.Member;

public interface MemberMapper {
	
	/**
	 * 회원 가입
	 * @param member
	 * @return
	 */	
	public int insert(Member member);
	
	/**
	 * 회원정보 수정
	 * @param member
	 * @return
	 */
	public int update(Member member);
	
	/**
	 * 로그인, id 중복확인
	 * @param member
	 * @return
	 */
	public Member selectOne(Member member);
}
