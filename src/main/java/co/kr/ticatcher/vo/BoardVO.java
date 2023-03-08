package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@ToString
public class BoardVO {
	
	private long board_idx;
	private String board_config;
	private long board_conidx;
	private String board_name;
	private String board_contents;
	private String board_attachName;
	private String board_attachPath;
	private Date board_regdate;
	private int board_readcount;
	private long mem_idx;

}
