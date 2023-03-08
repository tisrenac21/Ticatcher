package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ToString
public class HallVO {
	private long hall_idx;
	private String hall_name;
	private int hall_audience;
	private long theater_idx;
	private TheaterVO theaterVO;
}
