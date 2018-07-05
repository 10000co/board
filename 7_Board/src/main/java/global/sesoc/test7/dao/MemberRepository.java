package global.sesoc.test7.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.test7.vo.Member;

@Repository
public class MemberRepository {
	
	@Autowired
	SqlSession session;
	
	/**
	 * 회원 가입
	 * @param member
	 * @return int 회원가입처리
	 */
	public int join(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);		
		int result = mapper.insert(member);
		
		return result;
	}
	
	public int update(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		int result = mapper.update(member);
		
		return result;
	}
	
	/**
	 * 아이디 중복확인, 로그인
	 * @param member
	 * @return 조회된 member
	 */
	public Member selectMember(Member member) {
		MemberMapper mapper = session.getMapper(MemberMapper.class);
		Member m = mapper.selectOne(member);
		
		return m;
	}
	
}
