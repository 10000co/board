package global.sesoc.test7.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.test7.vo.Reply;

@Repository
public class ReplyRepository {

	@Autowired
	SqlSession session;
	
	// 댓글 전체 조회
	public List<Reply> replySelect(int boardnum) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		List<Reply> result = mapper.select(boardnum);
		
		return result;
	}
	
	// 댓글 등록
	public int replyInsert(Reply reply) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		int result = mapper.insert(reply);
		
		return result;
	}
	
	// 댓글 수정
	public int replyUpdate(Reply reply) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		int result = mapper.update(reply);
		
		return result;
	}

	// 댓글 삭제
	public int replyDelete(int replynum) {
		ReplyMapper mapper = session.getMapper(ReplyMapper.class);
		int result = mapper.delete(replynum);
		
		return result;
	}
}
