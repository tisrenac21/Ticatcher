package kr.co.ticatcher.dao;

import java.util.List;
import java.util.Map;

import kr.co.ticatcher.vo.BoardVO;
import kr.co.ticatcher.vo.QnaVO;

public interface BoardDAO {
	List<BoardVO> selectNoticeBoard(String fkey, String fval, int snum);
	int readCountNoticeBoard(String fkey, String fval);
	List<BoardVO> selectFAQBoard(String fkey, String fval, int snum);
	int readCountFAQBoard(String fkey, String fval);
	BoardVO selectOneBoard(String board_idx);
	int registerQnaPost(QnaVO qvo);
	List<QnaVO> readMyQna(int snum, long mem_idx);
	int readCountMyQna(long mem_idx);
    QnaVO readOneQna(long qna_idx);
    int countMemidx(long mem_idx);
	int readCountCommunityBoard(Map<String, Object> param);
	List<BoardVO> readCommunityBoard(Map<String, Object> param);
	List<Map<String, Object>> readComment(String board_idx);
	int registerComment(Map<String, Object> param);
	int deleteComment(Map<String, Object> param);
	int deletePost(Map<String, Object> param);
}
