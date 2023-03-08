package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ToString
public class TheaterVO {
	private long theater_idx;
	private String theater_name;
	private int theater_hallcount;
	private int theater_audience;
	private String theater_year;
	private String theater_addr;
	private String theater_sido;
	private String theater_gugun;
}
