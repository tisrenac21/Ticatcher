package co.kr.ticatcher.service;

import co.kr.ticatcher.vo.BoardVO;
import co.kr.ticatcher.vo.QnaVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

	List<BoardVO> readNoticeBoard(String fkey, String fval, int snum);
	int readCountNoticeBoard(String fkey, String fval);
	List<BoardVO> readFAQBoard(String fkey, String fval, int snum);
	int readCountFAQBoard(String fkey, String fval);
	BoardVO readOneBoard(String board_idx);
	boolean registerQna(QnaVO qvo, MultipartFile file) throws Exception;
	int readCountMyQna(long mem_idx);
	List<QnaVO> readMyQna(int snum, long mem_idx);
	QnaVO readOneQna(long qna_idx);
	int countMemidx(long mem_idx);


}
