package co.kr.ticatcher.controller;

import co.kr.ticatcher.service.AdminService;
import co.kr.ticatcher.service.MemberService;
import co.kr.ticatcher.service.StageService;
import co.kr.ticatcher.vo.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AdminController {

	@Autowired
	private AdminService asrv;

	@Autowired
	private MemberService msrv;

	@Autowired
	private StageService ssrv;



	@GetMapping("/admin")
	public String goToAdminLogin(HttpSession session) {
		String returnPage = "admin/adminlogin";
		if(session.getAttribute("admin") != null){
			returnPage = "redirect:/adminindex";
		}

		return returnPage;
	}

	@ResponseBody
	@PostMapping("/admin")
	public int adminLogin(AdminVO avo, HttpSession session){
		AdminVO result = asrv.adminLogin(avo);
		int isLogin = 0;

		if(result != null){
			session.setAttribute("admin", result);
			isLogin = 1;
		}

		return isLogin;
	}

	@GetMapping("/quitadmin")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/adminindex")
	public String goAdminIndex(HttpSession session){
		String returnPage;
		if(session.getAttribute("admin") != null){
			returnPage = "admin/adminIndex";
		}
		else {
			returnPage = "redirect:/admin";
		}
		return returnPage;
	}

	@GetMapping("/manageNotice")
	public String goManageNotice(HttpSession session, Model m, String cpg){
		String returnPage;
		if(session.getAttribute("admin") != null){
			int perPage = 10;
			if (cpg == null || cpg.equals("")) cpg = "1";
			int cpage = Integer.parseInt(cpg);
			int snum = (cpage-1) * perPage;
			int stpgn = ((cpage - 1) / 10) * 10 + 1;
			String board_config = "notice";

			m.addAttribute("pages",asrv.readCountPost(board_config));
			m.addAttribute("boardList", asrv.readPost(snum, board_config));
			m.addAttribute("stpgn", stpgn);

			returnPage = "admin/manageNotice";
		}
		else {
			returnPage = "redirect:/admin";
		}
		return returnPage;
	}

	@GetMapping("/manageFAQ")
	public String goManageFAQ(HttpSession session, Model m, String cpg){
		String returnPage;
		if(session.getAttribute("admin") != null){
			int perPage = 10;
			if (cpg == null || cpg.equals("")) cpg = "1";
			int cpage = Integer.parseInt(cpg);
			int snum = (cpage-1) * perPage;
			int stpgn = ((cpage - 1) / 10) * 10 + 1;
			String board_config = "FAQ";

			m.addAttribute("pages",asrv.readCountPost(board_config));
			m.addAttribute("boardList", asrv.readPost(snum, board_config));
			m.addAttribute("stpgn", stpgn);

			returnPage = "admin/manageFAQ";
		}
		else {
			returnPage = "redirect:/admin";
		}
		return returnPage;
	}

	@GetMapping("/manageNoticeView")
	public String adminNoticeView(Model model, String board_idx, HttpSession session) {
		String retrunPage;
		if(session.getAttribute("admin") != null) {
			model.addAttribute("detail", asrv.readOnePost(board_idx));
			retrunPage = "admin/manageNoticeView";
		}else {
			retrunPage = "redirect:/admin";
		}

		return retrunPage;
	}

	@GetMapping("/manageFAQView")
	public String adminFAQView(Model model, String board_idx, HttpSession session) {
		String retrunPage;
		if(session.getAttribute("admin") != null) {
			model.addAttribute("detail", asrv.readOnePost(board_idx));
			retrunPage = "admin/manageFAQView";
		}else {
			retrunPage = "redirect:/admin";
		}

		return retrunPage;
	}

	@GetMapping("/manageQNAView")
	public String manageQNAView(Model model, String qna_idx, HttpSession session) {
		String retrunPage;
		if(session.getAttribute("admin") != null) {
			QnaVO qna = asrv.readOneQNA(qna_idx);
			qna.setMemberVO(msrv.selectInfoByIdx(qna.getMem_idx()));
			model.addAttribute("detail", qna);
			model.addAttribute("index", asrv.countIndexFromQna(qna_idx));
			retrunPage = "admin/manageQNAView";
		}else {
			retrunPage = "redirect:/admin";
		}

		return retrunPage;
	}


	@GetMapping("/writeNotice")
	public String goWriteNotice(HttpSession session){
		String resultPage;
		if(session.getAttribute("admin") != null) {
			resultPage = "admin/writeNotice";
		}else {
			resultPage = "redirect:/admin";
		}
		return resultPage;
	}

	@PostMapping("/writeNotice")
	public String writeNotice(BoardVO bvo, MultipartFile file) throws IOException {
		String returnPage = "redirect:/manageNotice";
		String board_config = "notice";
		bvo.setBoard_conidx((asrv.countConidx(board_config))+1);
		bvo.setBoard_config(board_config);
		asrv.registerPost(bvo, file);
		return returnPage;
	}

	@GetMapping("/writeFAQ")
	public String goWriteFAQ(HttpSession session){
		String resultPage;
		if(session.getAttribute("admin") != null) {
			resultPage = "admin/writeFAQ";
		}else {
			resultPage = "redirect:/admin";
		}
		return resultPage;
	}

	@PostMapping("/writeFAQ")
	public String writeFAQ(BoardVO bvo, MultipartFile file) throws IOException {
		String returnPage = "redirect:/manageFAQ";
		String board_config = "FAQ";
		bvo.setBoard_conidx((asrv.countConidx(board_config))+1);
		bvo.setBoard_config(board_config);
		asrv.registerPost(bvo, file);
		return returnPage;
	}

	@GetMapping("/deleteNotice")
	public String deleteNotice(HttpSession session, String board_idx){
		String returnPage = "redirect:/manageNotice";

		if(session.getAttribute("admin") == null){
			returnPage = "redirect:/admin";
		}
		else {
			asrv.deletePost(board_idx);
		}
		return returnPage;
	}

	@GetMapping("/deleteFAQ")
	public String deleteFAQ(HttpSession session, String board_idx){
		String returnPage = "redirect:/manageFAQ";

		if(session.getAttribute("admin") == null){
			returnPage = "redirect:/admin";
		}
		else {
			asrv.deletePost(board_idx);
		}
		return returnPage;
	}

	@GetMapping("/modifyNotice")
	public String goModifyNotice(HttpSession session, String board_idx, Model model){
		String returnPage;
		if(session.getAttribute("admin") == null){
			returnPage = "redirect:/admin";
		}else {
			model.addAttribute("detail", asrv.readOnePost(board_idx));
			returnPage = "admin/updateNotice";
		}
		return returnPage;
	}

	@GetMapping("/modifyFAQ")
	public String goModifyFAQ(HttpSession session, String board_idx, Model model){
		String returnPage;
		if(session.getAttribute("admin") == null){
			returnPage = "redirect:/admin";
		}else {
			model.addAttribute("detail", asrv.readOnePost(board_idx));
			returnPage = "admin/updateFAQ";
		}
		return returnPage;
	}

	@PostMapping("/modifyNotice")
	public String modifyNotice(HttpSession session, BoardVO bvo, MultipartFile file) throws IOException {
		String returnPage = "redirect:/manageNoticeView?board_idx="+bvo.getBoard_idx();

		if(session.getAttribute("admin") == null){
			returnPage = "redirect:/admin";
		}else{
			asrv.modifyPost(bvo, file);
		}
		return returnPage;
	}

	@PostMapping("/modifyFAQ")
	public String modifyFAQ(HttpSession session, BoardVO bvo, MultipartFile file) throws IOException {
		String returnPage = "redirect:/manageFAQView?board_idx="+bvo.getBoard_idx();

		if(session.getAttribute("admin") == null){
			returnPage = "redirect:/admin";
		}else{
			asrv.modifyPost(bvo, file);
		}
		return returnPage;
	}

	@GetMapping("/manageQNA")
	public String goMyQna(HttpSession session, Model m, String cpg){
		String returnPage = "redirect:/admin";
		if(session.getAttribute("admin") != null){
			int perPage = 10;
			if (cpg == null || cpg.equals("")) cpg = "1";
			int cpage = Integer.parseInt(cpg);
			int snum = (cpage-1) * perPage;
			int stpgn = ((cpage - 1) / 10) * 10 + 1;

			List<QnaVO> list = asrv.readQNA(snum);
			for(QnaVO qna : list){
				qna.setMemberVO(msrv.selectInfoByIdx(qna.getMem_idx()));
			}

			m.addAttribute("pages",asrv.readCountQNA());
			m.addAttribute("qna", list);
			m.addAttribute("stpgn", stpgn);

			returnPage = "admin/manageQNA";
		}
		return returnPage;
	}

	@PostMapping("/manageQNAView")
	public String answerQNA(QnaVO qvo){
		String returnPage = "redirect:/manageQNAView?qna_idx=" + qvo.getQna_idx();
		asrv.answerQNA(qvo);

		return returnPage;
	}

	@GetMapping("/managestage")
	public String manageStage(HttpSession session, Model model, String cpg){
		String returnPage = "redirect:/admin";
		if(session.getAttribute("admin") != null){
			int perPage = 10;
			if (cpg == null || cpg.equals("")) cpg = "1";
			int cpage = Integer.parseInt(cpg);
			int snum = (cpage-1) * perPage;
			int stpgn = ((cpage - 1) / 10) * 10 + 1;
			String board_config = "notice";

			model.addAttribute("pages",asrv.readCountStage());
			model.addAttribute("stageList", asrv.readStage(snum));
			model.addAttribute("stpgn", stpgn);

			returnPage = "admin/manageStage";
		}
		return returnPage;
	}

	@GetMapping("/manageStageView")
	public String manageStageView(HttpSession session, Model model, long stage_idx){
		String returnPage = "redirect:/admin";
		if(session.getAttribute("admin") != null){

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

			returnPage = "admin/manageStageView";

		}

		return returnPage;

	}

	@GetMapping("/manageMember")
	public String goManageMember(HttpSession session, Model model, String cpg){
		String returnPage = "redirect:/admin";
		if(session.getAttribute("admin") != null){
			int perPage = 10;
			if (cpg == null || cpg.equals("")) cpg = "1";
			int cpage = Integer.parseInt(cpg);
			int snum = (cpage-1) * perPage;
			int stpgn = ((cpage - 1) / 10) * 10 + 1;

			model.addAttribute("pages",asrv.readCountMember());
			model.addAttribute("memberList", asrv.readMember(snum));
			model.addAttribute("stpgn", stpgn);

			returnPage = "admin/manageMember";
		}

		return returnPage;
	}

	@GetMapping("/testtest")
	public String testtest() {
		return "test";
	}
}
