package kr.co.ticatcher.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ticatcher.service.AdminService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminAPIController {

	@Autowired
	AdminService asrv;
	
	@PostMapping("/insertAdmin")
	public ResponseEntity<?> insertAdmin(@RequestBody Map<String, Object> param) {
		try {
			if(asrv.insertAdmin(param) > 0) {
				return ResponseEntity.ok("success");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("관리자 신규 등록 중 오류가 발생하였습니다.");
			}
  		} catch(Exception e) {
  			log.error("Unexpected error in [{}]. Caused by: [{}].", "insertAdmin", e.getClass().getSimpleName());
  			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("관리자 신규 등록 중 오류가 발생하였습니다.");
  		}
	}
	
	@PostMapping("/uptAdmin")
	public ResponseEntity<?> uptAdmin(@RequestBody Map<String, Object> param) {
		try {
			if(asrv.uptAdmin(param) > 0) {
				return ResponseEntity.ok("success");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 중 오류가 발생했습니다.");
			}
		} catch(Exception e) {
			log.error("Unexpected error in [{}]. Caused by: [{}].", "uptAdmin", e.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 중 오류가 발생했습니다.");
		}
	}
	
	@PostMapping("/deleteAdmin")
	public ResponseEntity<?> deleteAdmin(@RequestBody List<String> param) {
		try {
			if(asrv.deleteAdmin(param) > 0) {
				return ResponseEntity.ok("success");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 중 오류가 발생했습니다.");
			}
		} catch(Exception e) {
			log.error("Unexpected error in [{}]. Caused by: [{}].", "deleteAdmin", e.getClass().getSimpleName());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정 중 오류가 발생했습니다.");
		}
	}
	
	@PostMapping("/resetPwd")
	public ResponseEntity<Map<String, Object>> resetPwd(@RequestBody String admin_id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(asrv.resetPwd(admin_id) > 0) {
				result.put("result", "success");
				result.put("message", admin_id + " 관리자의 비밀번호를 초기화 했습니다.");
				
				return ResponseEntity.ok(result);
			} else {
				result.put("result", "fail");
				result.put("message", "비밀번호 초기화 중 오류가 발생했습니다.");
                
                return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			log.error("Unexpected error in [{}]. Caused by: [{}].", "resetPwd", e.getClass().getSimpleName());
			result.put("result", "fail");
			result.put("message", "비밀번호 초기화 중 오류가 발생했습니다.");
            
            return ResponseEntity.ok(result);
		}
	}
	
}
