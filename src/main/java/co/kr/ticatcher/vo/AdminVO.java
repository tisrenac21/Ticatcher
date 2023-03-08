package co.kr.ticatcher.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class AdminVO {
	
	private Long admin_idx;
	private String admin_id;
	private String admin_pw;
	private String admin_tel;
	private String admin_name;
	private String admin_grade;
	private String admin_email;
}
