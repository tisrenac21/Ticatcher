package co.kr.ticatcher.controller;

import co.kr.ticatcher.service.StageService;
import co.kr.ticatcher.vo.StageInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StageController {

	@Autowired
	private StageService ssrv;

	@GetMapping("/payment")
	public String payment(Model model, String stageinfo_idx){
		stageinfo_idx = "1";
		StageInfoVO sivo = ssrv.getStageInfo(stageinfo_idx);
		sivo.setStageVo(ssrv.finStageByStageIdx(sivo.getStage_idx()));

		model.addAttribute("stage", sivo);

		return "stage/payment";
	}
}
