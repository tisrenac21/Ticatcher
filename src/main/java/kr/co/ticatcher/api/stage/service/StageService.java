package kr.co.ticatcher.api.stage.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.ticatcher.vo.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StageService {
    StageVO getStageByIdx(long stage_idx);
    List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx);
    TheaterVO getAllTheaterByTheaterIdx(long theater_idx);
    List<PriceVO> getAllPriceBySchedule(long schedule_idx);
    List<StageVO> getNewStage();
    PriceVO getCheapOfSchedule(long scheduleIdx);
    int saveStage(StageVO svo, MultipartFile posterFile, List<MultipartFile> detailFiles) throws IOException;
    List<Map<String, Object>> seleteTheaters();
}
