package co.kr.ticatcher.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ScheduleSaveDTO {
    private Long stage_idx; // 공연 ID

    // HTML의 name="schedules[key]..." 를 받기 위한 Map
    // Key는 타임스탬프(String), Value는 날짜 정보 객체
    private Map<String, DateGroupDTO> schedules;

    @Data
    public static class DateGroupDTO {
        private String date; // 공연 날짜 (yyyy-MM-dd)
        
        // HTML의 name="...rounds[key]..." 를 받기 위한 Map
        private Map<String, RoundDTO> rounds;
    }

    @Data
    public static class RoundDTO {
        private String time; // 공연 시간 (HH:mm)

        // HTML의 name="ticket_name", name="ticket_price" 는 
        // 같은 round 내에서 배열(List)로 넘어옵니다.
        private List<String> ticket_name;
        private List<Integer> ticket_price;
    }
}