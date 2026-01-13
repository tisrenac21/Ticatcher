package co.kr.ticatcher.vo;

import lombok.Data;
import java.util.List;

@Data
public class ScheduleSaveDTO {
    private long stage_idx;
    
    private List<DailyScheduleDTO> schedules;

    @Data
    public static class DailyScheduleDTO {
        private String date;
        private List<RoundDTO> rounds;
    }

    @Data
    public static class RoundDTO {
        private String time;
        private List<String> ticket_name;
        private List<Integer> ticket_price;
    }
}