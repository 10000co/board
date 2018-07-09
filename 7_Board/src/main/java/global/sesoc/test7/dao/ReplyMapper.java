package global.sesoc.test7.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.test7.vo.Board;
import global.sesoc.test7.vo.Reply;

public interface ReplyMapper {
	
	public List<Reply> select(int boardnum);
	public int insert(Reply reply);
	public int update(Reply reply);
	public int delete(int replynum);
	
}
