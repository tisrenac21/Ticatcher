package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.MemberDAO;
import co.kr.ticatcher.vo.MemberVO;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service("msrv")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO mdao;

	@Override
	public MemberVO checkLogin(MemberVO mvo) {

		MemberVO result = mdao.selectOneMember(mvo);

		//회원이 존재한다면
		if(result != null){ mvo = result; };


		return mvo;
	}

	@Override
	public int checkjoin(MemberVO mvo) {
		return mdao.checkjoin(mvo);
	}

	@Override
	public boolean join(MemberVO mvo) {
		boolean isJoin = false;
		if(mdao.joinMember(mvo) > 0){
			isJoin = true;
		}
		return isJoin;
	}

	@Override
	public String checkid(String uid) {
		return String.valueOf(mdao.checkId(uid));
	}

	@Override
	public String sendSMS(String to) {
		Random rand = new Random();

		String numStr = "";
		for(int i=0; i<6; i++){
			String ran = Integer.toString(rand.nextInt(10));
			numStr+=ran;
		}

//		String api_key = "NCSPY00K2BTHQXVE";
//		String api_secret = "ZVWGH7KUB98Z5K9MUZEADFRPCKT4NPTG";
//		Message coolsms = new Message(api_key, api_secret);
//
//		Random rand = new Random();
//
//		String numStr = "";
//		for(int i=0; i<6; i++){
//			String ran = Integer.toString(rand.nextInt(10));
//			numStr+=ran;
//		}
//
//		HashMap<String, String> params = new HashMap<String, String>();
//		params.put("to", to);
//		params.put("from", "01086631564");
//		params.put("type", "SMS");
//		params.put("text", "[Ticatcher] 인증번호 [ " + numStr + " ] 를 입력하세요.");
//		params.put("app_version", "test app 1.2"); // application name and version
//
//		try {
//			coolsms.send(params);
//		} catch (CoolsmsException e) {
//			throw new RuntimeException(e);
//		}

		return numStr;
	}

	@Override
	public String findId(MemberVO mvo) {
		return mdao.findId(mvo);
	}

	@Override
	public int findPw(MemberVO mvo) {
		return mdao.findPw(mvo);
	}

	@Override
	public int changePw(MemberVO mvo) {
		return mdao.changePw(mvo);
	}

	@Override
	public int uptmember(MemberVO mvo) {
		return mdao.uptmember(mvo);
	}
	@Override
	public boolean deleteMember(long mem_idx) {
		boolean isDelete = false;

		if(mdao.removeMember(mem_idx) > 0) {
			isDelete = true;
		}

		return isDelete;
	}

	@Override
	public boolean saveDeleteId(String mem_id){
		boolean isSave = false;
		if(mdao.saveDeleteId(mem_id) > 0){
			isSave = true;
		}
		return isSave;
	}

	@Override
	public String checkDelId(String uid) {
		return String.valueOf(mdao.checkDelId(uid));
	}

	@Override
	public MemberVO selectInfoByIdx(long mem_idx) {
		return mdao.selectInfoByIdx(mem_idx);
	}


}
