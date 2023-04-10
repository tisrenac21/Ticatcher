package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.AdminVO;
import co.kr.ticatcher.vo.BoardVO;
import co.kr.ticatcher.vo.QnaVO;
import co.kr.ticatcher.vo.StageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("adao")
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public AdminVO adminLogin(AdminVO avo) {
		return sqlSession.selectOne("admin.adminLogin", avo);
	}

	@Override
	public int readCountPost(String board_config) {
		return sqlSession.selectOne("admin.readCountPost", board_config);
	}

	@Override
	public List<BoardVO> readPost(int snum, String board_config) {
		Map<String, Object> params = new HashMap<>();
		params.put("snum", snum);
		params.put("board_config", board_config);
		return sqlSession.selectList("admin.readPost", params);
	}

	@Override
	public BoardVO readOnePost(String board_idx) {
		return sqlSession.selectOne("admin.readOnePost", board_idx);
	}


	@Override
	public int countConidx(String board_config) {
		Integer result = sqlSession.selectOne("admin.countConidx", board_config);
		if(result == null){
			result = 0;
		}
		return result;
	}

	@Override
	public int registerPost(BoardVO bvo) {
		return sqlSession.insert("admin.registerPost", bvo);
	}

	@Override
	public int deletePost(String board_idx) {
		return sqlSession.delete("admin.deletePost", board_idx);
	}

	@Override
	public int modifyPost(BoardVO bvo) {
		return sqlSession.update("admin.modifyPost", bvo);
	}

	@Override
	public List<QnaVO> readQNA(int snum) {
		return sqlSession.selectList("admin.readQNA",snum);
	}

	@Override
	public int readCountQNA() {
		return sqlSession.selectOne("admin.readCountQNA");
	}

	@Override
	public QnaVO readOneQNA(String qna_idx) {
		return sqlSession.selectOne("admin.readOneQNA", qna_idx);
	}


	@Override
	public int answerQNA(QnaVO qvo) {
		return sqlSession.update("admin.answerQNA", qvo);
	}

	@Override
	public int countIndexFromQna(String qna_idx) {
		return sqlSession.selectOne("admin.countIndexFromQna", qna_idx);
	}

	@Override
	public int readCountStage() {
		return sqlSession.selectOne("admin.readCountStage");
	}

	@Override
	public List<StageVO> readStage(int snum) {
		return sqlSession.selectList("admin.readStage", snum);
	}
}