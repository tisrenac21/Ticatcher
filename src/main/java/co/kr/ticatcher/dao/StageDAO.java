package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.StageInfoVO;
import co.kr.ticatcher.vo.StageVO;

public interface StageDAO {

    StageInfoVO getStageInfo(String stageinfo_idx);

    StageVO finStageByStageIdx(long stage_idx);
}
