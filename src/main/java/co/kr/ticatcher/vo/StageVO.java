package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ToString
public class StageVO {
	private long stage_idx;
	private String stage_name;
	private String stage_grade;
	private String stage_runtime;
	private String stage_host;
	private String stage_posterName;
	private String stage_posterPath;
	private String stage_imgInfoName;
	private String stage_imgInfoPath;
	private int stage_price;
	private String stage_info;
}
