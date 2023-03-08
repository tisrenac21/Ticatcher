package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.StageInfoVO;
import co.kr.ticatcher.vo.StageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("sdao")
public class StageDAOImpl implements StageDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public StageInfoVO getStageInfo(String stageinfo_idx) {
		return sqlSession.selectOne("stage.getStageInfo", stageinfo_idx);
	}

	@Override
	public StageVO finStageByStageIdx(long stage_idx) {
		return sqlSession.selectOne("stage.finStageByStageIdx", stage_idx);
	}
}