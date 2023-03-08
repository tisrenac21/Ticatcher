package co.kr.ticatcher.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@NoArgsConstructor
@ToString
public class MemberVO {
	
	private Long mem_idx;
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_email;
	private String mem_birth;
	private String mem_tel;
	private String mem_gen;
	private String mem_aka;
	private Date mem_regDate;
}
