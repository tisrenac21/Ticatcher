package kr.co.ticatcher.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ticatcher.vo.BoardVO;
import kr.co.ticatcher.vo.QnaVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("bdao")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardVO> selectNoticeBoard(String fkey, String fval, int snum) {

		Map<String, Object> params = new HashMap<>();
		params.put("fkey",fkey);
		params.put("fval",fval);
		params.put("snum",snum);

		return sqlSession.selectList("board.selectNoticeBoard",params);
	}

	@Override
	public int readCountNoticeBoard(String fkey, String fval) {
		Map<String, Object> params = new HashMap<>();
		params.put("fkey",fkey);
		params.put("fval",fval);

		return sqlSession.selectOne("board.selectCountNoticeBoard",params);
	}

	@Override
	public List<BoardVO> selectFAQBoard(String fkey, String fval, int snum) {

		Map<String, Object> params = new HashMap<>();
		params.put("fkey",fkey);
		params.put("fval",fval);
		params.put("snum",snum);

		return sqlSession.selectList("board.selectFAQBoard",params);
	}

	@Override
	public int readCountFAQBoard(String fkey, String fval) {
		Map<String, Object> params = new HashMap<>();
		params.put("fkey",fkey);
		params.put("fval",fval);

		return sqlSession.selectOne("board.selectCountFAQBoard",params);
	}

	@Override
	public BoardVO selectOneBoard(String board_idx) {
		sqlSession.update("board.viewBoard", board_idx);
		return sqlSession.selectOne("board.selectOneBoard",board_idx);
	}
	@Override
	public int registerQnaPost(QnaVO qvo) {
		return sqlSession.insert("board.registerQnaPost", qvo);
	}

	@Override
	public List<QnaVO> readMyQna(int snum, long mem_idx) {
		Map<String, Object> params = new HashMap<>();
		params.put("snum",snum);
		params.put("mem_idx", mem_idx);

		return sqlSession.selectList("board.selectMyQna",params);
	}

	@Override
	public int readCountMyQna(long mem_idx) {
		return sqlSession.selectOne("board.selectCountMyQna", mem_idx);
	}

	@Override
	public QnaVO readOneQna(long qna_idx) {
		return sqlSession.selectOne("board.readOneQna",qna_idx);
	}

	@Override
	public int countMemidx(long mem_idx) {
		Integer result = sqlSession.selectOne("board.countMemidx", mem_idx);
		if(result == null){
			result = 0;
		}
		return result;
	}

	@Override
	public int readCountCommunityBoard(Map<String, Object> param) {
		return sqlSession.selectOne("board.selectCountCommunityBoard", param);
	}

	@Override
	public List<BoardVO> readCommunityBoard(Map<String, Object> param) {
		Map<String, Object> params = new HashMap<String, Object>();
		return sqlSession.selectList("board.selectCommunityBoard", param);
	}

	@Override
	public List<Map<String, Object>> readComment(String board_idx) {
		return sqlSession.selectList("board.selectCommentByBoardIdx", board_idx);
	}

	@Override
	public int registerComment(Map<String, Object> param) {
		return sqlSession.insert("board.insertComment", param);
	}

	@Override
	public int deleteComment(Map<String, Object> param) {
		return sqlSession.delete("board.deleteCommentByCommentIdx", param);
	}

	@Override
	public int deletePost(Map<String, Object> param) {
		int result = 0;
		result += sqlSession.delete("board.deleteCommentByBoardIdx", param);
		result += sqlSession.delete("board.deletePost", param);
		return result;
	}
}
