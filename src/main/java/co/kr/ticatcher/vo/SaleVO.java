package co.kr.ticatcher.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ToString
public class SaleVO {
	private long sale_idx;
	private String sale_name;
	private int sale_rate;
}
