package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.IndexDAO;
import co.kr.ticatcher.dao.StageDAO;
import co.kr.ticatcher.vo.PriceVO;
import co.kr.ticatcher.vo.ScheduleVO;
import co.kr.ticatcher.vo.StageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("isrv")
public class IndexServiceImpl implements IndexService{
	
	@Autowired
	private IndexDAO idao;

	@Autowired
	private StageDAO sdao;

	@Override
	public List<StageVO> getNewStage() {
		int sum = 0;
		int i = 0;
		List<Long> stageIdxs = new ArrayList<>();
		List<StageVO> stages = new ArrayList<>();
		List<ScheduleVO> schedules = idao.getAllScheduleOrderByDateDesc();

		while(sum<4) {
			if(!stageIdxs.contains(schedules.get(i).getStage_idx())){
				stageIdxs.add(schedules.get(i).getStage_idx());
				sum++;
			}
			i++;
		}

		for(long stage_idx : stageIdxs){
			stages.add(idao.getStageByIdx(stage_idx));
		}

		return stages;
	}

	@Override
	public List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx) {
		return sdao.getAllScheduleByStageIdx(stage_idx);
	}

	@Override
	public PriceVO getCheapOfSchedule(long schedule_idx) {
		return idao.getCheapOfSchedule(schedule_idx);
	}
}
