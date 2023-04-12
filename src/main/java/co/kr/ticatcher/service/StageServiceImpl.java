package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.IndexDAO;
import co.kr.ticatcher.dao.StageDAO;
import co.kr.ticatcher.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("ssrv")
public class StageServiceImpl implements StageService {

	@Autowired
	private StageDAO sdao;

	@Autowired
	private IndexDAO idao;

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

	@Override
	public List<StageVO> getNewStage() {
		int i = 0;
		List<Long> stageIdxs = new ArrayList<>();
		List<StageVO> stages = new ArrayList<>();
		List<ScheduleVO> schedules = idao.getAllScheduleOrderByDateDesc();

		while(i<schedules.size()) {
			if(!stageIdxs.contains(schedules.get(i).getStage_idx())){
				stageIdxs.add(schedules.get(i).getStage_idx());
			}
			i++;
		}

		for(long stage_idx : stageIdxs){
			stages.add(idao.getStageByIdx(stage_idx));
		}

		return stages;
	}

	@Override
	public PriceVO getCheapOfSchedule(long scheduleIdx) {
		return null;
	}
}
