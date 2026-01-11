package co.kr.ticatcher.service;

import co.kr.ticatcher.dao.IndexDAO;
import co.kr.ticatcher.dao.StageDAO;
import co.kr.ticatcher.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service("ssrv")
public class StageServiceImpl implements StageService {

	@Autowired
	private StageDAO sdao;

	@Autowired
	private IndexDAO idao;

	@Override
	public StageVO getStageByIdx(long stage_idx) {
		return sdao.getStageByIdx(stage_idx);
	}

	@Override
	public List<ScheduleVO> getAllScheduleByStageIdx(long stage_idx) {
		return sdao.getAllScheduleByStageIdx(stage_idx);
	}

	@Override
	public TheaterVO getAllTheaterByTheaterIdx(long theater_idx) {
		return sdao.getAllTheaterByTheaterIdx(theater_idx);
	}

	@Override
	public List<PriceVO> getAllPriceBySchedule(long schedule_idx) {
		return sdao.getAllPriceBySchedule(schedule_idx);
	}

	@Override
	public List<StageVO> getNewStage() {
		int i = 0;
		List<Long> stageIdxs = new ArrayList<>();
		List<StageVO> stages = new ArrayList<>();
		List<ScheduleVO> schedules = idao.getAllScheduleOrderByDateDesc();

		while(i<schedules.size()) {
			if(!stageIdxs.contains(schedules.get(i).getStage_idx())){
				stageIdxs.add(schedules.get(i).getStage_idx());
			}
			i++;
		}

		for(long stage_idx : stageIdxs){
			stages.add(idao.getStageByIdx(stage_idx));
		}

		return stages;
	}

	@Override
	public PriceVO getCheapOfSchedule(long scheduleIdx) {
		return null;
	}

	@Override
	public int saveStage(StageVO svo, MultipartFile posterFile, List<MultipartFile> detailFiles) throws IOException {
		if (posterFile != null && !posterFile.isEmpty()) {
			String fileName = saveFile(posterFile);
			svo.setStage_posterName(fileName);
			svo.setStage_posterPath("static/stageImage/" + fileName);
		}

		if (detailFiles != null && !detailFiles.isEmpty()) {
			List<String> fileNames = new ArrayList<>();
			List<String> filePaths = new ArrayList<>();

			for (MultipartFile detailFile : detailFiles) {
				if (!detailFile.isEmpty()) {
					String fileName = saveFile(detailFile);
					fileNames.add(fileName);
					filePaths.add("static/stageImage/" + fileName);
				}
			}
			svo.setStage_imgInfoName(String.join(",", fileNames));
			svo.setStage_imgInfoPath(String.join(",", filePaths));
		}

		return sdao.saveStage(svo);
	}

	private String saveFile(MultipartFile file) throws IOException {
			String projectpath = this.getClass().getResource("").getPath();
			projectpath = projectpath.split("/ticatcher/")[0];
			projectpath = projectpath + "\\ticatcher\\src\\main\\webapp\\resources\\static\\stageImage\\";
			UUID uuid = UUID.randomUUID();
			String fileName = uuid + "_" + file.getOriginalFilename();
			File saveFile = new File(projectpath+fileName);
			file.transferTo(saveFile);
		return fileName;
	}
}
