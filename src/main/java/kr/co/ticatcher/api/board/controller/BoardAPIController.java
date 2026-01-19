package kr.co.ticatcher.api.board.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ticatcher.api.board.service.BoardService;
import kr.co.ticatcher.service.AdminService;
import kr.co.ticatcher.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/board")
public class BoardAPIController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	AdminService adminService;

	@PostMapping("/registerPost")
	public ResponseEntity<String> registerPost(BoardVO bvo, MultipartFile file) {
		bvo.setBoard_conidx(adminService.countConidx(bvo.getBoard_config()) + 1);
		try {
			if(adminService.registerPost(bvo, file)) {
				return ResponseEntity.ok("success");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 중 오류가 발생했습니다.");
			}
		} catch(IOException e) {
			log.error("Unexpected error in [{}]. Caused by: [{}].", "communityWrite", e.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 저장 중 오류가 발생했습니다.");
		}
	}
	
	@PostMapping("/registerComment")
	public ResponseEntity<String> registerComment(@RequestBody Map<String, Object> param) {
		try {
			if(boardService.registerComment(param) > 0) {
				return ResponseEntity.ok("success");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 등록 중 오류가 발생했습니다.");
			}
		} catch(Exception e) {
			log.error("Unexpected error in [{}]. Caused by: [{}].", "registerComment", e.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 등록 중 오류가 발생했습니다.");
		}
	}
}
