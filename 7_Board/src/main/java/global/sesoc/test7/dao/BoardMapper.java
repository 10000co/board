package global.sesoc.test7.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import global.sesoc.test7.vo.Board;

public interface BoardMapper {
	
	/**
	 * 전체 글 조회
	 * @return list 조회된 전체 글
	 */
	public List<Board> select(Map<String,String> map, RowBounds rb);
	
	/**
	 * 읽기
	 * @param board
	 * @return
	 */
	public Board selectOne(int boardnum);
	
	/**
	 * 수정
	 * @param board
	 * @return
	 */
	public int update(Board board);
	
	/**
	 * 삭제
	 * @param boardnum
	 * @return
	 */
	public int delete(int boardnum);
	
	/**
	 * 글쓰기
	 * @param board
	 * @return
	 */
	public int insert(Board board);
	
	/**
	 * 조회수
	 * @param boardnum
	 * @return
	 */
	public int updateHitcount(int boardnum);
	
	/**
	 * 등록된 전체 글 개수
	 * @return
	 */
	public int getTotalBoard(Map<String,String> map);
	
}
