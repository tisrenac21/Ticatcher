package co.kr.ticatcher.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class PriceVO {
	
	private long price_idx;
	private long schedule_idx;
	private ScheduleVO scheduleVo;
	private String price_name;
	private int price_price;
}
