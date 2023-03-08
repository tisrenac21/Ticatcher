package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@ToString
public class TimeVO {
	private long time_idx;
	private Date time_date;
	private Time time_starttime;
	private Time time_endtime;
	private int time_count;
}
