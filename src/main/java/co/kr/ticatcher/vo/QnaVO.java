package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@ToString
public class QnaVO {
	
	private long qna_idx;
	private long qna_memidx;
	private String qna_name;
	private String qna_contents;
	private String qna_answer;
	private String qna_attachName;
	private String qna_attachPath;
	private Date qna_regdate;
	private long mem_idx;
	private MemberVO memberVO;

}
