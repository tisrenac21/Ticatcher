package co.kr.ticatcher.controller;

import co.kr.ticatcher.service.IndexService;
import co.kr.ticatcher.vo.PriceVO;
import co.kr.ticatcher.vo.ScheduleVO;
import co.kr.ticatcher.vo.StageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private IndexService isrv;

	@GetMapping("/")
	public String index(Model model) {
		List<StageVO> newStage = isrv.getNewStage();
		model.addAttribute("stages", newStage);
		return "index";
	}

	@ResponseBody
	@PostMapping("/minprice")
	public PriceVO minPrice(String stageIdx){
		long stage_idx = Long.parseLong(stageIdx);
		List<ScheduleVO> AllScheduleByStage = isrv.getAllScheduleByStageIdx(stage_idx);
		PriceVO pvo = new PriceVO();

		for(int i = 0 ; i < AllScheduleByStage.size() ; i++){
			PriceVO price = isrv.getCheapOfSchedule(AllScheduleByStage.get(i).getSchedule_idx());
			if(((pvo.getPrice_price()) > (price.getPrice_price())) || (pvo.getPrice_price() == 0)){
				pvo = price;
			}
		}
		return pvo;
		}

}
