package kr.co.ticatcher.api.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ticatcher.api.member.service.MemberService;
import kr.co.ticatcher.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/member")
public class MemberAPIController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("/deleteIdList")
	public ResponseEntity<Map<String, Object>> deleteIdList(@RequestBody List<String> checkedIdList) {
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            if(memberService.deleteMembers(checkedIdList) > 0) {
            	response.put("result", "success");
                response.put("message", "정상적으로 삭제되었습니다.");
                
                return ResponseEntity.ok(response);
            } else {
            	response.put("result", "fail");
                response.put("message", "삭제 처리 중 오류가 발생했습니다.");
                
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
        	log.error("Unexpected error in [{}]. Caused by: [{}].", "deleteIdList", e.getClass().getSimpleName());
            response.put("result", "fail");
            response.put("message", "삭제 처리 중 오류가 발생했습니다.");
            
            return ResponseEntity.ok(response);
        }
    }
	
	@PostMapping("/uptmem")
	public ResponseEntity<Map<String, Object>> uptmem(@RequestBody MemberVO mvo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(memberService.uptmember(mvo) > 0) {
				result.put("result", "success");
				result.put("message", "정상적으로 수정되었습니다.");
				
				return ResponseEntity.ok(result);
			} else {
				result.put("result", "fail");
				result.put("message", "수정 중 오류가 발생했습니다.");
                
                return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			log.error("Unexpected error in [{}]. Caused by: [{}].", "uptmem", e.getClass().getSimpleName());
			result.put("result", "fail");
			result.put("message", "수정 중 오류가 발생했습니다.");
            
            return ResponseEntity.ok(result);
		}
	}
	
	@PostMapping("/resetPwd")
	public ResponseEntity<Map<String, Object>> resetPwd(@RequestBody String mem_id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(memberService.resetPwd(mem_id) > 0) {
				result.put("result", "success");
				result.put("message", mem_id + " 회원의 비밀번호를 초기화 했습니다.");
				
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
	
	@PostMapping("/restore")
	public ResponseEntity<Map<String, Object>> restore(@RequestBody List<String> checkedIdList) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if(memberService.restore(checkedIdList) > 0) {
				result.put("result", "success");
				result.put("message", checkedIdList + " 회원을 복원했습니다.");
				
				return ResponseEntity.ok(result);
			} else {
				result.put("result", "fail");
				result.put("message", "회원 복원 중 오류가 발생했습니다.");
                
                return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			log.error("Unexpected error in [{}]. Caused by: [{}].", "resetPwd", e.getClass().getSimpleName());
			result.put("result", "fail");
			result.put("message", "회원 복원 중 오류가 발생했습니다.");
            
            return ResponseEntity.ok(result);
		}
	}
	
}
