package co.kr.ticatcher.service;

import co.kr.ticatcher.vo.PriceVO;
import co.kr.ticatcher.vo.ScheduleVO;
import co.kr.ticatcher.vo.StageVO;

import java.util.List;

public interface IndexService {
    List<StageVO> getNewStage();

    List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx);

    PriceVO getCheapOfSchedule(long schedule_idx);
}
