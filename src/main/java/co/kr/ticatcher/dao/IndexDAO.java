package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.ScheduleVO;
import co.kr.ticatcher.vo.StageVO;

import java.util.List;

public interface IndexDAO {

    List<ScheduleVO> getAllScheduleOrderByDateDesc();

    StageVO getStageByIdx(long stage_idx);
}
