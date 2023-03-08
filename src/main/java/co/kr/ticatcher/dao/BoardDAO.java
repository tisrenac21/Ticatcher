package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.BoardVO;
import co.kr.ticatcher.vo.QnaVO;

import java.util.List;

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
}
