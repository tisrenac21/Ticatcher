package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@ToString
public class ScheduleVO {
	private long schedule_idx;
	private String schedule_date;
	private String schedule_time;
	private long stage_idx;
}
