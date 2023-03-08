package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.BoardVO;
import co.kr.ticatcher.vo.QnaVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
		//조회수 증가
		sqlSession.update("board.viewBoard", board_idx);

		//본문글 가져오기
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
}
