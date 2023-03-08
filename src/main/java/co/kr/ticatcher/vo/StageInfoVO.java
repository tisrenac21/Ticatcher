package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ToString
public class StageInfoVO {
	private long stageinfo_idx;
	private long stage_idx;
	private StageVO stageVo;
	private long time_idx;
	private TimeVO timeVO;
	private long theater_idx;
	private TheaterVO theaterVO;
	private long sale_idx;
	private SaleVO saleVO;
	private int stageinfo_saleprice;
}
