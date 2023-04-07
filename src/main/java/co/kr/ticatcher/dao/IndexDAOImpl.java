package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.BoardVO;
import co.kr.ticatcher.vo.QnaVO;
import co.kr.ticatcher.vo.ScheduleVO;
import co.kr.ticatcher.vo.StageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("idao")
public class IndexDAOImpl implements IndexDAO {

	@Autowired
	private SqlSession sqlSession;


	@Override
	public List<ScheduleVO> getAllScheduleOrderByDateDesc() {
		return sqlSession.selectList("index.getAllScheduleOrderByDateDesc");
	}

	@Override
	public StageVO getStageByIdx(long stage_idx) {
		return sqlSession.selectOne("stage.getStageByIdx", stage_idx);
	}
}
