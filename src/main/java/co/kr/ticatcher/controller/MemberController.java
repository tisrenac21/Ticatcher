package co.kr.ticatcher.controller;

import co.kr.ticatcher.service.MemberService;
import co.kr.ticatcher.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MemberController {

	@Autowired
	private MemberService msrv;
	
	protected Logger LOGGER =
			LoggerFactory.getLogger(getClass());

	@GetMapping("/login")
	public String login(HttpSession session) {
		String returnPage = "member/login";

		if(session.getAttribute("m") != null) {
			returnPage = "redirect:/";
		}
		return returnPage;
	}

	@ResponseBody
	@PostMapping("/login")
	public int login(String mem_id, String mem_pw, HttpSession session){
		MemberVO mvo = new MemberVO();
		mvo.setMem_id(mem_id);
		mvo.setMem_pw(mem_pw);
		MemberVO result = msrv.checkLogin(mvo);
		int returnPage = 0;

		if((result.getMem_idx()) != null){
			session.setAttribute("m", result);
			returnPage = 1;
		}

		return returnPage;
	}


	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

	@GetMapping("/joinready")
	public String joinready(HttpSession session) {
		String returnPage = "member/join_before";

		if(session.getAttribute("m") != null) {
			returnPage = "redirect:/";
		}
		return returnPage;
	}

	@PostMapping("/joinready")
	public String checkjoin(MemberVO mvo, RedirectAttributes redirect){
		String returnPage = "redirect:/join";

		if(msrv.checkjoin(mvo) == 1){
			returnPage = "member/join_fail";
		}
		else{
			redirect.addFlashAttribute("mbr", mvo);
		}
		return returnPage;
	}

	@GetMapping("/join")
	public String goJoin(Model model, HttpServletRequest request){
		Map<String, ?> paramMap = RequestContextUtils.getInputFlashMap(request);

		if(paramMap != null){ MemberVO mvo = (MemberVO) paramMap.get("mbr");
			model.addAttribute("mbr", mvo);}
		return "member/join";
	}

	@PostMapping("/join")
	public String join(MemberVO mvo){
		msrv.join(mvo);
		return "redirect:/login";
	}

	@ResponseBody
	@GetMapping("/checkid")
	public String checkId(String uid){
		String result = "잘못된 방법으로 호출하였습니다.";

		if(uid != null || !uid.equals("")){
			result = msrv.checkid(uid);
			String delresult = msrv.checkDelId(uid);
			if(delresult.equals("1")){result = "3";}
		}
		return result;
	}


	@ResponseBody
	@GetMapping("/sendsms")
	public String sendsms(String to){
		return msrv.sendSMS(to);
	}

	@GetMapping("/findinfo")
	public String gotofindinfo(HttpSession session){
		String returnPage = "";
		if(session.getAttribute("m") == null){
			returnPage = "member/find_myinfo";
		}else if (session.getAttribute("m") != null){
			returnPage = "redirect:/";
		}
		return returnPage;
	}

	@ResponseBody
	@PostMapping("/findid")
	public String findId(MemberVO mvo){
		return msrv.findId(mvo);
	}

	@ResponseBody
	@PostMapping("/findpw")
	public int findPw(MemberVO mvo){
		return msrv.findPw(mvo);
	}

	@GetMapping("/changePwForFind")
	public String goToChangePwForFind(MemberVO mvo, Model model){
		model.addAttribute("mbr", mvo);
		return "member/change_pw";
	}

	@GetMapping("/changePwForChange")
	public String goToChangePwForChange(Model model, HttpSession session){
		MemberVO mvo = (MemberVO) session.getAttribute("m");
		model.addAttribute("mbr", mvo);
		return "member/change_pw";
	}

	@ResponseBody
	@PostMapping("/changePw")
	public int changePw(MemberVO mvo){
		return msrv.changePw(mvo);
	}

	@GetMapping("/mypage")
	public String gotoMyPage(HttpSession session){
		String returnPage = "member/myPage";

		if (session.getAttribute("m") == null){
			returnPage = "redirect:/login";
		}

		return returnPage;
	}

	@GetMapping("/refund")
	public String gotoRefund(HttpSession session){
		String returnPage = "member/myPage_cancel";

		if (session.getAttribute("m") == null){
			returnPage = "redirect:/login";
		}

		return returnPage;
	}

	@GetMapping("/chkuptmem")
	public String chkuptmem(HttpSession session){
		String returnPage = "member/fix_myPage";

		if (session.getAttribute("m") == null){
			returnPage = "redirect:/login";
		}

		return returnPage;
	}

	@ResponseBody
	@PostMapping("/chkuptmem")
	public int chkuptmem(String mem_pw, HttpSession session){
		int isChk = 0;
		MemberVO mvo = (MemberVO)session.getAttribute("m");
		if(mvo.getMem_pw().equals(mem_pw)){
			isChk = 1;
		}
		return isChk;
	}

	@GetMapping("/uptmember")
	public String uptmember(HttpSession session, Model model){
		String resultPage = "redirect:/login";
		if(session.getAttribute("m") != null){
			model.addAttribute("mbr", (MemberVO)session.getAttribute("m"));
			resultPage = "member/in_myPage";
		}
		return resultPage;
	}

	@ResponseBody
	@PostMapping("/uptmember")
	public int uptmember(MemberVO mvo, HttpSession session){
		MemberVO originmvo = (MemberVO)session.getAttribute("m");
		originmvo.setMem_aka(mvo.getMem_aka());
		originmvo.setMem_email(mvo.getMem_email());
		session.setAttribute("m", originmvo);
		return msrv.uptmember(originmvo);
	}

	@GetMapping("/readydelmem")
	public String goToDeleteMember(HttpSession session){
		String returnPage = "member/quit_member";

		if (session.getAttribute("m") == null){
			returnPage = "redirect:/login";
		}

		return returnPage;
	}

	@GetMapping("/chkpwdfordelmem")
	public String checkPasswordForDeleteMemeber(HttpSession session){
		String resultPage = "redirect:/login";
		if(session.getAttribute("m") != null){
			resultPage = "member/pop_quit_member";
		}
		return resultPage;
	}

	@ResponseBody
	@PostMapping("/chkpwdfordelmem")
	public int chkpwdfordelmem(HttpSession session, String mem_pw){
		int isChk = 0;
		MemberVO mvo = (MemberVO)session.getAttribute("m");
		if(mvo.getMem_pw().equals(mem_pw)){
			isChk = 1;
		}
		return isChk;
	}

	@GetMapping("/withdrawal")
	public String withdrawal(HttpSession session){
		String returnPage = "";
		if(session.getAttribute("m") != null){
			MemberVO mvo = (MemberVO) session.getAttribute("m");
			session.invalidate();
			msrv.saveDeleteId(mvo.getMem_id());
			msrv.deleteMember(mvo.getMem_idx());
			returnPage = "redirect:/";
		}else {
			returnPage = "redirect:/login";
		}
		return returnPage;
	}


}
