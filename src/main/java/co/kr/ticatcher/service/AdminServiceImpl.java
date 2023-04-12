package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.AdminDAO;
import co.kr.ticatcher.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
	public int readCountStage() {
		return adao.readCountStage();
	}
	@Override
	public List<StageVO> readStage(int snum) {
		return adao.readStage(snum);
	}

	@Override
	public int readCountMember() {
		return adao.readCountMember();
	}

	@Override
	public List<MemberVO> readMember(int snum) {
		return adao.readMember(snum);
	}
}
