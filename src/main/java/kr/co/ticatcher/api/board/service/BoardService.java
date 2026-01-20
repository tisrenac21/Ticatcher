package kr.co.ticatcher.api.board.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.ticatcher.vo.BoardVO;
import kr.co.ticatcher.vo.QnaVO;

import java.util.List;
import java.util.Map;

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
	int readCountCommunityBoard(Map<String, Object> param);
	List<BoardVO> readCommunityBoard(Map<String, Object> param);
	List<Map<String, Object>> readComment(String board_idx);
	int registerComment(Map<String, Object> param);
	int deleteComment(Map<String, Object> param);
	int deletePost(Map<String, Object> param);

}
