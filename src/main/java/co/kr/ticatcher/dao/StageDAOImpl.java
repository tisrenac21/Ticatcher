package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sdao")
public class StageDAOImpl implements StageDAO {
	
	@Autowired
	private SqlSession sqlSession;


	@Override
	public StageVO getStageByIdx(long stage_idx) {
		return sqlSession.selectOne("stage.getStageByIdx", stage_idx);
	}

	@Override
	public List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx) {
		return sqlSession.selectList("stage.getAllScheduleByStageIdx", stage_idx);
	}

	@Override
	public TheaterVO getAllTheaterByTheaterIdx(long theater_idx) {
		return sqlSession.selectOne("stage.getAllTheaterByTheaterIdx", theater_idx);
	}

	@Override
	public List<PriceVO> getAllPriceBySchedule(long schedule_idx) {
		return sqlSession.selectList("stage.getAllPriceBySchedule",schedule_idx);
	}
}