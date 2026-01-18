package kr.co.ticatcher.dao;

import java.util.List;

import kr.co.ticatcher.vo.PriceVO;
import kr.co.ticatcher.vo.ScheduleVO;
import kr.co.ticatcher.vo.StageVO;

public interface IndexDAO {

    List<ScheduleVO> getAllScheduleOrderByDateDesc();

    StageVO getStageByIdx(long stage_idx);

    PriceVO getCheapOfSchedule(long schedule_idx);
}
