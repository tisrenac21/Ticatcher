package co.kr.ticatcher.dao;

import co.kr.ticatcher.vo.AdminVO;
import co.kr.ticatcher.vo.BoardVO;
import co.kr.ticatcher.vo.QnaVO;
import co.kr.ticatcher.vo.StageVO;

import java.util.List;

public interface AdminDAO {


    AdminVO adminLogin(AdminVO avo);
    int readCountPost(String board_config);
    List<BoardVO> readPost(int snum, String board_config);
    BoardVO readOnePost(String board_idx);
    int countConidx(String board_config);
    int registerPost(BoardVO bvo);
    int deletePost(String board_idx);
    int modifyPost(BoardVO bvo);
    List<QnaVO> readQNA(int snum);
    int readCountQNA();
    QnaVO readOneQNA(String qna_idx);
    int answerQNA(QnaVO qvo);
    int countIndexFromQna(String qna_idx);
    int readCountStage();
    List<StageVO> readStage(int snum);
}
