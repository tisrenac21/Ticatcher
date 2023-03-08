package co.kr.ticatcher.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class BookingVO {
	
	private long book_idx;
	private long mem_idx;
	private MemberVO memberVO;
	private long stageinfo_idx;
	private StageInfoVO stageInfoVO;
	private int book_totalprice;
	private String book_success;
}
