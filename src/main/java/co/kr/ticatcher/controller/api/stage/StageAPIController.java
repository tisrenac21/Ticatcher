package co.kr.ticatcher.controller.api.stage;

import co.kr.ticatcher.service.StageService;
import co.kr.ticatcher.vo.StageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/stage/api")
public class StageAPIController {

    @Autowired
    private StageService stageService;

    @PostMapping("/saveStage")
    public String saveStage(StageVO svo, MultipartFile poster_file, List<MultipartFile> detail_files){
        String returnPage = "redirect:/managestage";
        svo.setStage_runtime(svo.getStage_runtime() + "분");
        try {
            stageService.saveStage(svo, poster_file, detail_files);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return returnPage;
    }
}
