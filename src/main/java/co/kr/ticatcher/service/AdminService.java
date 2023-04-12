package co.kr.ticatcher.service;

import co.kr.ticatcher.vo.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {

    AdminVO adminLogin(AdminVO avo);
    int readCountPost(String board_config);
    List<BoardVO> readPost(int snum, String board_config);
    BoardVO readOnePost(String board_idx);
    int countConidx(String board_config);
    boolean registerPost(BoardVO bvo, MultipartFile file) throws IOException;
    boolean deletePost(String board_idx);
    boolean modifyPost(BoardVO bvo, MultipartFile file) throws IOException;
    List<QnaVO> readQNA(int snum);
    int readCountQNA();
    QnaVO readOneQNA(String qna_idx);
    int answerQNA(QnaVO qvo);
    int countIndexFromQna(String qna_idx);
    int readCountStage();
    List<StageVO> readStage(int snum);
    int readCountMember();
    List<MemberVO> readMember(int snum);
}
