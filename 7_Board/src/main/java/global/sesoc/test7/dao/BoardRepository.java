package global.sesoc.test7.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import global.sesoc.test7.vo.Board;

@Repository
public class BoardRepository {
	
	@Autowired
	SqlSession session;
	
	/**
	 * 전체 글 조회
	 * @return list 조회된 전체 글
	 */
	public List<Board> select(String searchItem, String searchWord, int startRecord, int countPerPage) {
		RowBounds rb = new RowBounds(startRecord, countPerPage);
		
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		Map<String,String> map = new HashMap<>();
		
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		
		List<Board> list = mapper.select(map, rb);
		
		return list;
	}
	
	public int insert(Board board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int result = mapper.insert(board);
		
		return result;
	}
	
	public Board selectOne(int boardnum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		Board result = mapper.selectOne(boardnum);
		
		return result;
	}
	
	public int updateHitcount(int boardnum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int result = mapper.updateHitcount(boardnum);
		
		return result;
	}
	
	public int delete(int boardnum) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int result = mapper.delete(boardnum);
		
		return result;
	}
	
	public int update(Board board) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int result = mapper.update(board);
		
		return result;
	}
	
	public int getTotalBoard(String searchItem, String searchWord) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		Map<String,String> map = new HashMap<>();
		map.put("searchItem", searchItem);
		map.put("searchWord", searchWord);
		int result = mapper.getTotalBoard(map);
		
		return result;
	}
	
}
