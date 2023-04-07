package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.StageDAO;
import co.kr.ticatcher.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ssrv")
public class StageServiceImpl implements StageService {

	@Autowired
	private StageDAO sdao;

	@Override
	public StageVO getStageByIdx(long stage_idx) {
		return sdao.getStageByIdx(stage_idx);
	}

	@Override
	public List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx) {
		return sdao.getAllScheduleByStageIdx(stage_idx);
	}

	@Override
	public TheaterVO getAllTheaterByTheaterIdx(long theater_idx) {
		return sdao.getAllTheaterByTheaterIdx(theater_idx);
	}

	@Override
	public List<PriceVO> getAllPriceBySchedule(long schedule_idx) {
		return sdao.getAllPriceBySchedule(schedule_idx);
	}
}
