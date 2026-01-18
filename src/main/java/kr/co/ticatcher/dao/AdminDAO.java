package kr.co.ticatcher.dao;

import java.util.List;
import java.util.Map;

import kr.co.ticatcher.vo.*;

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
    int readCountStage(Map<String, Object> param);
    List<StageVO> readStage(Map<String, Object> param);
    int readCountMember();
    List<MemberVO> readMember(int snum);
    int insertSchedule(ScheduleVO scheduleVO);
    int insertPrice(PriceVO priceVO);
    int deletePriceByStageIdx(long stage_idx);
    int deleteScheduleByStageIdx(long stage_idx);
    List<Map<String, Object>> selectFullScheduleByStageIdx(long stage_idx);
    int insertPriceBatch(List<PriceVO> priceList);
	int readCountDeleteMember();
	List<MemberVO> readDeleteMember(int snum);
}
