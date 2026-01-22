package kr.co.ticatcher.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.ticatcher.vo.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    int readCountStage(Map<String, Object> param);
    List<StageVO> readStage(Map<String, Object> param);
    int readCountMember();
    List<MemberVO> readMember(int snum);
    int registerSchedule(ScheduleSaveDTO dto);
    List<Map<String, Object>> getFullSchedule(long stage_idx);
	int readCountDeleteMember();
	List<MemberVO> readDeleteMember(int snum);
	int readCountAdminAccount(Map<String, Object> param);
	List<Map<String, Object>> readAdminAccount(Map<String, Object> param);
	int insertAdmin(Map<String, Object> param);
	int uptAdmin(Map<String, Object> param);
	int deleteAdmin(List<String> param);
	int resetPwd(String admin_id);
	int readCountTheater();
	List<Map<String, Object>> readTheater(int snum);
}
