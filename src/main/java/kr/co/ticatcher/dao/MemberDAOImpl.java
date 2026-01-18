package kr.co.ticatcher.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.ticatcher.vo.MemberVO;

@Repository("mdao")
public class  MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO selectOneMember(MemberVO mvo) {
		return sqlSession.selectOne("member.selectCountMember", mvo);
	}

	@Override
	public int checkjoin(MemberVO mvo) {
		return sqlSession.selectOne("member.checkjoin", mvo);
	}

	@Override
	public int joinMember(MemberVO mvo) {
		return sqlSession.insert("member.joinMember",mvo);
	}

	@Override
	public int checkId(String uid) {
		return sqlSession.selectOne("member.checkid",uid);
	}

	@Override
	public String findId(MemberVO mvo) {
		return sqlSession.selectOne("member.findId", mvo);
	}

	@Override
	public int findPw(MemberVO mvo) {
		return sqlSession.selectOne("member.selectCountMemberForFindPw", mvo);
	}

	@Override
	public int changePw(MemberVO mvo) {
		return sqlSession.update("member.changePw",mvo);
	}

	@Override
	public int uptmember(MemberVO mvo) {
		return sqlSession.update("member.uptmember",mvo );
	}
	@Override
	public int removeMember(long mem_idx) {
		return sqlSession.delete("member.deleteMember", mem_idx);
	}

	@Override
	public int saveDeleteId(String mem_id) {
		return sqlSession.insert("member.saveDeleteId", mem_id);
	}

	@Override
	public int checkDelId(String uid) {
		return sqlSession.selectOne("member.checkDeleteId",uid);
	}

	@Override
	public MemberVO selectInfoByIdx(long mem_idx) {
		return sqlSession.selectOne("member.selectInfoByIdx", mem_idx);
	}
	
	@Override
	public int deleteMembers(List<String> checkedIdList) {
		System.out.println("여기는오냐고");
		return sqlSession.delete("member.deleteMembers", checkedIdList);
	}

	@Override
	public int resetPwd(String mem_id) {
		return sqlSession.update("member.resetPwd", mem_id);
	}

	@Override
	public int restore(List<String> checkedIdList) {
		return sqlSession.update("member.restore", checkedIdList);
	}

}