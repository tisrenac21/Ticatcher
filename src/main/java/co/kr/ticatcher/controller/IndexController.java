package co.kr.ticatcher.controller;

import co.kr.ticatcher.service.IndexService;
import co.kr.ticatcher.vo.ScheduleVO;
import co.kr.ticatcher.vo.StageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
