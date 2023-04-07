package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.*;

import java.util.List;

public interface StageDAO {


    StageVO getStageByIdx(long stage_idx);

    List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx);

    TheaterVO getAllTheaterByTheaterIdx(long theater_idx);

    List<PriceVO> getAllPriceBySchedule(long schedule_idx);
}
