package co.kr.ticatcher.service;

import co.kr.ticatcher.vo.StageInfoVO;
import co.kr.ticatcher.vo.StageVO;

public interface StageService {

    StageInfoVO getStageInfo(String stageinfo_idx);
    StageVO finStageByStageIdx(long stage_idx);
}
