package kr.co.ticatcher.index.controller;

import kr.co.ticatcher.api.board.service.BoardService;
import kr.co.ticatcher.api.index.service.IndexService;
import kr.co.ticatcher.vo.BoardVO;
import kr.co.ticatcher.vo.PriceVO;
import kr.co.ticatcher.vo.ScheduleVO;
import kr.co.ticatcher.vo.StageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

	@Autowired
	private IndexService isrv;

	@GetMapping("/")
	public String index(Model model) {
		List<StageVO> newStage = isrv.getNewStage();
		model.addAttribute("stages", newStage);
		model.addAttribute("notice", isrv.selectFourPost("A"));
		model.addAttribute("community", isrv.selectFourPost("C"));
		model.addAttribute("audiboard", isrv.selectFourPost("D"));
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
