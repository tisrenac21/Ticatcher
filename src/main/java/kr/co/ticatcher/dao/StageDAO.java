package kr.co.ticatcher.dao;

import java.util.List;
import java.util.Map;

import kr.co.ticatcher.vo.*;

public interface StageDAO {


    StageVO getStageByIdx(long stage_idx);

    List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx);

    TheaterVO getAllTheaterByTheaterIdx(long theater_idx);

    List<PriceVO> getAllPriceBySchedule(long schedule_idx);

    int saveStage(StageVO svo);

    List<Map<String, Object>> seleteTheaters();
}
