package co.kr.ticatcher.controller;

import co.kr.ticatcher.service.BoardService;
import co.kr.ticatcher.service.MemberService;
import co.kr.ticatcher.vo.MemberVO;
import co.kr.ticatcher.vo.QnaVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

	
	protected Logger LOGGER = LoggerFactory.getLogger(getClass());
//
	@Autowired
	private MemberService msrv;

	@Autowired
	private BoardService bsrv;

	@GetMapping("/notice")
	public String goNotice(Model m, String cpg, String fkey, String fval){
		int perPage = 10;
		if (cpg == null || cpg.equals("")) cpg = "1";
		if (fkey == null) fkey = "";
		int cpage = Integer.parseInt(cpg);
		int snum = (cpage-1) * perPage;
		int stpgn = ((cpage - 1) / 10) * 10 + 1;

		m.addAttribute("pages",bsrv.readCountNoticeBoard(fkey, fval));
		m.addAttribute("notice", bsrv.readNoticeBoard(fkey, fval, snum));
		m.addAttribute("stpgn", stpgn);
		m.addAttribute("fqry", "&fkey="+ fkey +"&fval="+ fval);

		return "board/notice";
	}

	@GetMapping("/noticeView")
	public ModelAndView view(ModelAndView mv, String board_idx, String fkey, String fval) {
		if (fkey == null) fkey = "";
		mv.setViewName("board/noticeView");
		mv.addObject("bd", bsrv.readOneBoard(board_idx));

		return mv;
	}

	@GetMapping("/faqView")
	public ModelAndView FAQview(ModelAndView mv, String board_idx, String fkey, String fval) {
		if (fkey == null) fkey = "";
		mv.setViewName("board/faqView");
		mv.addObject("bd", bsrv.readOneBoard(board_idx));

		return mv;
	}

	@GetMapping("/faq")
	public String goFaq(Model m, String cpg, String fkey, String fval){
		int perPage = 10;
		if (cpg == null || cpg.equals("")) cpg = "1";
		if (fkey == null) fkey = "";
		int cpage = Integer.parseInt(cpg);
		int snum = (cpage-1) * perPage;
		int stpgn = ((cpage - 1) / 10) * 10 + 1;

		m.addAttribute("pages",bsrv.readCountFAQBoard(fkey, fval));
		m.addAttribute("faq", bsrv.readFAQBoard(fkey, fval, snum));
		m.addAttribute("stpgn", stpgn);
		m.addAttribute("fqry", "&fkey="+ fkey +"&fval="+ fval);

		return "board/help_faq";
	}
	@GetMapping("/writeqna")
	public String goWriteQna(HttpSession session, Model model){
		String returnPage = "redirect:/login";
		if(session.getAttribute("m") != null){
			returnPage = "board/help_new_qna";
		}
		return returnPage;
	}

	@PostMapping("/writeqna")
	public String WriteQna(QnaVO qvo, MultipartFile file) throws Exception {

		String returnPage = "redirect:/myqna";
		int memidx = bsrv.countMemidx(qvo.getMem_idx());
		qvo.setQna_memidx(memidx+1);
		bsrv.registerQna(qvo, file);
		return returnPage;
	}

	@GetMapping("/myqna")
	public String goMyQna(HttpSession session, Model m, String cpg){
		String returnPage = "redirect:/login";
		if(session.getAttribute("m") != null){
			int perPage = 10;
			if (cpg == null || cpg.equals("")) cpg = "1";
			int cpage = Integer.parseInt(cpg);
			int snum = (cpage-1) * perPage;
			int stpgn = ((cpage - 1) / 10) * 10 + 1;
			long mem_idx = ((MemberVO)session.getAttribute("m")).getMem_idx();

			m.addAttribute("pages",bsrv.readCountMyQna(mem_idx));
			m.addAttribute("qna", bsrv.readMyQna(snum, mem_idx));
			m.addAttribute("member", msrv.selectInfoByIdx(mem_idx));
			m.addAttribute("stpgn", stpgn);

			returnPage = "board/help_qna";
		}
		return returnPage;
	}
	@GetMapping("/qnaView")
	public ModelAndView FAQview(HttpSession session, ModelAndView mv, long qna_idx) {
		long mem_idx = ((MemberVO)session.getAttribute("m")).getMem_idx();
		mv.setViewName("board/qnaView");
		mv.addObject("bd", bsrv.readOneQna(qna_idx));

		return mv;
	}


	@GetMapping("/board")
	public String board(){
		return "board/community";
	}

}
