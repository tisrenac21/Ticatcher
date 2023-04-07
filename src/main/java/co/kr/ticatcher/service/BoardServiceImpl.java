package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.BoardDAO;
import co.kr.ticatcher.vo.BoardVO;
import co.kr.ticatcher.vo.QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service("bsrv")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO bdao;

	@Override
	public List<BoardVO> readNoticeBoard(String fkey, String fval, int snum) {

		return bdao.selectNoticeBoard(fkey, fval+"%", snum);
	}

	@Override
	public int readCountNoticeBoard(String fkey, String fval) {
		return bdao.readCountNoticeBoard(fkey, fval+"%");
	}

	@Override
	public List<BoardVO> readFAQBoard(String fkey, String fval, int snum) {

		return bdao.selectFAQBoard(fkey, fval+"%", snum);
	}

	@Override
	public int readCountFAQBoard(String fkey, String fval) {
		return bdao.readCountFAQBoard(fkey, fval+"%");
	}

	@Override
	public BoardVO readOneBoard(String board_idx) {
		return bdao.selectOneBoard(board_idx);
	}

	@Override
	public boolean registerQna(QnaVO qvo, MultipartFile file) throws Exception {
		boolean result = false;
		if(file.getOriginalFilename() != ""){
			String projectpath = this.getClass().getResource("").getPath();
			projectpath = projectpath.split("/Ticatcher/")[0];
			projectpath = projectpath + "\\Ticatcher\\src\\main\\webapp\\resources\\static\\qnaFiles\\";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File saveFile = new File(projectpath+fileName);
			file.transferTo(saveFile);
			qvo.setQna_attachName(fileName);
			qvo.setQna_attachPath("static/qnaFiles/" +fileName);
		}

		if(bdao.registerQnaPost(qvo) > 0) result = true;

		return result;
	}

	@Override
	public int readCountMyQna(long mem_idx) {
		return bdao.readCountMyQna(mem_idx);
	}

	@Override
	public List<QnaVO> readMyQna(int snum, long mem_idx) {
		return bdao.readMyQna(snum, mem_idx);
	}

	@Override
	public QnaVO readOneQna(long qna_idx) {
		return bdao.readOneQna(qna_idx);
	}

	@Override
	public int countMemidx(long mem_idx) {
		return bdao.countMemidx(mem_idx);
	}

}
