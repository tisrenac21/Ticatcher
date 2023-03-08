package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.StageDAO;
import co.kr.ticatcher.vo.StageInfoVO;
import co.kr.ticatcher.vo.StageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ssrv")
public class StageServiceImpl implements StageService {

	@Autowired
	private StageDAO sdao;


	@Override
	public StageInfoVO getStageInfo(String stageinfo_idx) {
		return sdao.getStageInfo(stageinfo_idx);
	}

	@Override
	public StageVO finStageByStageIdx(long stage_idx) {
		return sdao.finStageByStageIdx(stage_idx);
	}
}
