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
	private Date schedule_date;
	private Time schedule_time;
	private long stage_idx;
	private StageVO stageVo;
	private long theater_idx;
	private TheaterVO theaterVo;

}
