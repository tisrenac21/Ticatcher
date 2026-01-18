package kr.co.ticatcher.service;

import kr.co.ticatcher.dao.AdminDAO;
import kr.co.ticatcher.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("asrv")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adao;



	@Override
	public AdminVO adminLogin(AdminVO avo) {
		return adao.adminLogin(avo);
	}

	@Override
	public int readCountPost(String board_config) {
		return adao.readCountPost(board_config);
	}

	@Override
	public List<BoardVO> readPost(int snum, String board_config) {
		return adao.readPost(snum, board_config);
	}

	@Override
	public BoardVO readOnePost(String board_idx) {
		return adao.readOnePost(board_idx);
	}


	@Override
	public int countConidx(String board_config) {
		return adao.countConidx(board_config);
	}

	@Override
	public boolean registerPost(BoardVO bvo, MultipartFile file) throws IOException {
		boolean result = false;
		if(file.getOriginalFilename() != ""){
			String projectpath = this.getClass().getResource("").getPath();
			projectpath = projectpath.split("/Ticatcher/")[0];
			projectpath = projectpath + "\\Ticatcher\\src\\main\\webapp\\resources\\static\\adminFiles\\";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File saveFile = new File(projectpath+fileName);
			file.transferTo(saveFile);
			bvo.setBoard_attachName(fileName);
			bvo.setBoard_attachPath("static/adminFiles/" +fileName);
		}

		if(adao.registerPost(bvo) > 0) result = true;

		return result;
	}

	@Override
	public boolean deletePost(String board_idx) {
		boolean isDelete = false;

		if(adao.deletePost(board_idx) > 0) isDelete = true;

		return isDelete;
	}

	@Override
	public boolean modifyPost(BoardVO bvo, MultipartFile file) throws IOException {
		boolean isModify = false;
		if(file.getOriginalFilename() != ""){
			String projectpath = this.getClass().getResource("").getPath();
			projectpath = projectpath.split("/Ticatcher/")[0];
			projectpath = projectpath + "\\Ticatcher\\src\\main\\webapp\\resources\\static\\adminFiles\\";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File saveFile = new File(projectpath+fileName);
			file.transferTo(saveFile);
			bvo.setBoard_attachName(fileName);
			bvo.setBoard_attachPath("static/adminFiles/" +fileName);
		}
		if(adao.modifyPost(bvo) > 0){
			isModify = true;
		}
		return isModify;
	}

	@Override
	public List<QnaVO> readQNA(int snum) {
		return adao.readQNA(snum);
	}

	@Override
	public int readCountQNA() {
		return adao.readCountQNA();
	}

	@Override
	public QnaVO readOneQNA(String qna_idx) {
		return adao.readOneQNA(qna_idx);
	}

	@Override
	public int answerQNA(QnaVO qvo) {
		return adao.answerQNA(qvo);
	}

	@Override
	public int countIndexFromQna(String qna_idx) {
		return adao.countIndexFromQna(qna_idx);
	}

	@Override
	public int readCountStage(Map<String, Object> param) {
		return adao.readCountStage(param);
	}
	@Override
	public List<StageVO> readStage(Map<String, Object> param) {
		return adao.readStage(param);
	}

	@Override
	public int readCountMember() {
		return adao.readCountMember();
	}

	@Override
	public List<MemberVO> readMember(int snum) {
		return adao.readMember(snum);
	}
	
	@Override
	@Transactional
	public int registerSchedule(ScheduleSaveDTO dto) {
	    long stageIdx = dto.getStage_idx();

	    adao.deletePriceByStageIdx(stageIdx);
	    adao.deleteScheduleByStageIdx(stageIdx);

	    int result = 0;
	    
	    if (dto.getSchedules() == null || dto.getSchedules().isEmpty()) {
	        return 1; 
	    }

	    List<PriceVO> allPriceList = new ArrayList<>();

	    for (ScheduleSaveDTO.DailyScheduleDTO dayDto : dto.getSchedules()) {
	        String dateStr = dayDto.getDate();

	        if (dayDto.getRounds() != null) {
	            for (ScheduleSaveDTO.RoundDTO roundDto : dayDto.getRounds()) {
	                
	                ScheduleVO svo = new ScheduleVO();
	                svo.setStage_idx((int) stageIdx);
	                svo.setSchedule_date(dateStr);
	                svo.setSchedule_time(roundDto.getTime());

	                adao.insertSchedule(svo);
	                result++;

	                long currentScheduleIdx = svo.getSchedule_idx();

	                List<String> names = roundDto.getTicket_name();
	                List<Integer> prices = roundDto.getTicket_price();

	                if (names != null && prices != null) {
	                    for (int i = 0; i < names.size(); i++) {
	                        String tName = names.get(i);
	                        if(tName == null || tName.trim().isEmpty()) continue;
	                        Integer tPrice = (i < prices.size()) ? prices.get(i) : 0;

	                        PriceVO pvo = new PriceVO();
	                        pvo.setSchedule_idx((int) currentScheduleIdx);
	                        pvo.setPrice_name(tName);
	                        pvo.setPrice_price(tPrice);
	                        
	                        allPriceList.add(pvo);
	                    }
	                }
	            }
	        }
	    }

	    if (allPriceList.size() > 0) {
	        adao.insertPriceBatch(allPriceList);
	        result += allPriceList.size();
	    }

	    return result;
	}
	
	@Override
	public List<Map<String, Object>> getFullSchedule(long stage_idx) {
	    return adao.selectFullScheduleByStageIdx(stage_idx);
	}

	@Override
	public int readCountDeleteMember() {
		return adao.readCountDeleteMember();
	}

	@Override
	public List<MemberVO> readDeleteMember(int snum) {
		return adao.readDeleteMember(snum);
	}
}
