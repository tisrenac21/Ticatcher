package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.MemberVO;

public interface MemberDAO {

    MemberVO selectOneMember(MemberVO mvo);
    int checkjoin(MemberVO mvo);
    int joinMember(MemberVO mvo);
    int checkId(String uid);
    String findId(MemberVO mvo);
    int findPw(MemberVO mvo);
    int changePw(MemberVO mvo);
    int uptmember(MemberVO mvo);
    int removeMember(long mem_idx);
    int saveDeleteId(String mem_id);
    int checkDelId(String uid);
    MemberVO selectInfoByIdx(long mem_idx);
}
