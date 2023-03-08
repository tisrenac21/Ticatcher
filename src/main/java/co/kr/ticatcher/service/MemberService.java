package co.kr.ticatcher.service;

import co.kr.ticatcher.vo.MemberVO;

public interface MemberService {
//    boolean checkLogin(MemberVO mvo);
    MemberVO checkLogin(MemberVO mvo);
    int checkjoin(MemberVO mvo);
    boolean join(MemberVO mvo);
    String checkid(String uid);
    String sendSMS(String to);
    String findId(MemberVO mvo);
    int findPw(MemberVO mvo);
    int changePw(MemberVO mvo);
    int uptmember(MemberVO mvo);
    boolean deleteMember(long mem_idx);
    boolean saveDeleteId(String mem_id);
    String checkDelId(String uid);
    MemberVO selectInfoByIdx(long mem_idx);
}
