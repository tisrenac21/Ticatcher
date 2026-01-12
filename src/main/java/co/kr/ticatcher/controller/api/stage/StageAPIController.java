package co.kr.ticatcher.controller.api.stage;

import co.kr.ticatcher.service.StageService;
import co.kr.ticatcher.vo.StageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/stage/api")
public class StageAPIController {

    @Autowired
    private StageService stageService;

    @PostMapping("/saveStage")
    public ResponseEntity<Map<String, Object>> saveStage(StageVO svo, MultipartFile poster_file, List<MultipartFile> detail_files){
        Map<String, Object> result = new HashMap<String, Object>();
        svo.setStage_runtime(svo.getStage_runtime() + "분");
        try {
            stageService.saveStage(svo, poster_file, detail_files);
            result.put("success", true);
            result.put("message", "공연이 등록되었습니다.");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error", e);
            result.put("success", false);
            result.put("message", "오류 발생: " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/selectTheaters")
    public List<Map<String, Object>> selectTheaters(){
        return stageService.seleteTheaters();
    }
}
