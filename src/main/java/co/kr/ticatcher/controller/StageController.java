package co.kr.ticatcher.controller;

import co.kr.ticatcher.service.StageService;
import co.kr.ticatcher.vo.PriceVO;
import co.kr.ticatcher.vo.ScheduleVO;
import co.kr.ticatcher.vo.StageVO;
import co.kr.ticatcher.vo.TheaterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class StageController {

	@Autowired
	private StageService ssrv;

	@GetMapping("/payment")
	public String payment(Model model, long stage_idx){
		StageVO svo = ssrv.getStageByIdx(stage_idx);

		List<ScheduleVO> stageSchedule = ssrv.getAllScheduleByStageIdx(stage_idx);

		List<TheaterVO> stageTheater = new ArrayList<>();
		List<Long> theaterIdxs = new ArrayList<>();
		List<PriceVO> priceList = new ArrayList<>();

		for(int i = 0 ; i < stageSchedule.size() ; i++){
			long theaterIdx = stageSchedule.get(i).getTheater_idx();
			long scheduleIdx = stageSchedule.get(i).getSchedule_idx();
			List<PriceVO> prices = ssrv.getAllPriceBySchedule(scheduleIdx);
			if(!theaterIdxs.contains(theaterIdx)){
				theaterIdxs.add(theaterIdx);
				stageTheater.add(ssrv.getAllTheaterByTheaterIdx(theaterIdx));
			}
			for(int j = 0 ; j < prices.size() ; j++){
				priceList.add(prices.get(j));
			}
		}

		List<String> priceNames = new ArrayList<>();
		List<PriceVO> realPriceList = new ArrayList<>();
		for(int i = 0 ; i < priceList.size() ; i++){
			String priceName = priceList.get(i).getPrice_name();
			if(!priceNames.contains(priceName)){
				priceNames.add(priceName);
				realPriceList.add(priceList.get(i));
			}
		}
		List<String> dateList = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for(ScheduleVO sc : stageSchedule){
			dateList.add(format.format(sc.getSchedule_date()));
		}



		String[] infoImg = svo.getStage_imgInfoPath().split(",");
		String period = Collections.min(dateList) + " ~ " + Collections.max(dateList);

		model.addAttribute("stage", svo);
		model.addAttribute("schedules", stageSchedule);
		model.addAttribute("theaters", stageTheater);
		model.addAttribute("infoImg", infoImg);
		model.addAttribute("period", period);
		model.addAttribute("prices",realPriceList);

		return "stage/payment";
	}

	@GetMapping("/stage")
	public String goStageList(Model model) {
		List<StageVO> newStage = ssrv.getNewStage();
		model.addAttribute("stages", newStage);
		return "stage/stage";
	}

//	@ResponseBody
//	@PostMapping("/minprice")
//	public PriceVO minPrice(String stageIdx){
//		long stage_idx = Long.parseLong(stageIdx);
//		List<ScheduleVO> AllScheduleByStage = ssrv.getAllScheduleByStageIdx(stage_idx);
//		PriceVO pvo = new PriceVO();
//
//		for(int i = 0 ; i < AllScheduleByStage.size() ; i++){
//			PriceVO price = ssrv.getCheapOfSchedule(AllScheduleByStage.get(i).getSchedule_idx());
//			if(((pvo.getPrice_price()) > (price.getPrice_price())) || (pvo.getPrice_price() == 0)){
//				pvo = price;
//			}
//		}
//		return pvo;
//	}
}