package kr.co.ticatcher.api.index.service;

import java.util.List;

import kr.co.ticatcher.vo.BoardVO;
import kr.co.ticatcher.vo.PriceVO;
import kr.co.ticatcher.vo.ScheduleVO;
import kr.co.ticatcher.vo.StageVO;

public interface IndexService {
    List<StageVO> getNewStage();

    List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx);

    PriceVO getCheapOfSchedule(long schedule_idx);

	List<BoardVO> selectFourPost(String boardConfig);
}
