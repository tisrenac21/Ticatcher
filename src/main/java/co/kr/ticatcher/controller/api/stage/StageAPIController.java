package co.kr.ticatcher.controller.api.stage;

import co.kr.ticatcher.service.StageService;
import co.kr.ticatcher.vo.ScheduleSaveDTO;
import co.kr.ticatcher.vo.StageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/stage/api")
public class StageAPIController {

    @Autowired
    private StageService stageService;

    @PostMapping("/saveStage")
    public ResponseEntity<Map<String, Object>> saveStage(StageVO svo, MultipartFile poster_file, List<MultipartFile> detail_files){
        Map<String, Object> result = new HashMap<String, Object>();
        svo.setStage_runtime(svo.getStage_runtime() + "분");
        try {
            stageService.saveStage(svo, poster_file, detail_files);
            result.put("success", true);
            result.put("message", "공연이 등록되었습니다.");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error", e);
            result.put("success", false);
            result.put("message", "오류 발생: " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/selectTheaters")
    public List<Map<String, Object>> selectTheaters(){
        return stageService.seleteTheaters();
    }
    
    @PostMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("편성등록양식");
        Row headerRow = sheet.createRow(0);
        String[] headers = {"공연날짜(YYYY-MM-DD)", "시간(HH:mm)", "좌석등급", "가격"};
        
        CellStyle headerStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
            sheet.setColumnWidth(i, 5000);
        }

        Row exampleRow = sheet.createRow(1);
        exampleRow.createCell(0).setCellValue("2026-01-01");
        exampleRow.createCell(1).setCellValue("14:00");
        exampleRow.createCell(2).setCellValue("VIP석");
        exampleRow.createCell(3).setCellValue(150000);
        exampleRow.createCell(4).setCellValue("예시 데이터입니다. 지워주세요.");

        String fileName = URLEncoder.encode("공연편성_일괄등록_양식.xlsx", StandardCharsets.UTF_8.name());
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @PostMapping("/uploadExcel")
    public ResponseEntity<Map<String, Object>> uploadExcel(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        Map<String, List<Map<String, Object>>> parsedData = new LinkedHashMap<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");

            String lastDate = "";
            String lastTime = "";

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell dateCell = row.getCell(0);
                String rawDate = "";

                if (dateCell != null) {
                    if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
                        rawDate = sdf.format(dateCell.getDateCellValue());
                    } else {
                        rawDate = formatter.formatCellValue(dateCell).trim();
                        rawDate = rawDate.replace(".", "-").replace("/", "-");
                        rawDate = rawDate.replace("\u00A0", ""); 
                    }
                }

                String normalizedDate = "";
                if (!rawDate.isEmpty()) {
                    try {
                        String[] parts = rawDate.split("-");
                        if (parts.length == 3) {
                            int y = Integer.parseInt(parts[0]);
                            int m = Integer.parseInt(parts[1]);
                            int d = Integer.parseInt(parts[2]);
                            normalizedDate = String.format("%04d-%02d-%02d", y, m, d);
                        } else {
                            normalizedDate = rawDate; 
                        }
                    } catch (Exception e) {
                        normalizedDate = rawDate;
                    }
                }

                if (!normalizedDate.isEmpty()) {
                    lastDate = normalizedDate;
                }
                
                Cell timeCell = row.getCell(1);
                String currentTime = (timeCell != null) ? formatter.formatCellValue(timeCell).trim() : "";
                if (!currentTime.isEmpty()) {
                    lastTime = currentTime;
                }

                String effectiveDate = normalizedDate.isEmpty() ? lastDate : normalizedDate;
                String effectiveTime = currentTime.isEmpty() ? lastTime : currentTime;

                if (effectiveDate.isEmpty() || effectiveTime.isEmpty()) continue;

                Cell seatCell = row.getCell(2);
                String seatName = (seatCell != null) ? formatter.formatCellValue(seatCell).trim() : "";
                Cell priceCell = row.getCell(3);
                String priceStr = (priceCell != null) ? formatter.formatCellValue(priceCell).replace(",", "").trim() : "0";
                
                if (seatName.isEmpty()) continue;

                parsedData.putIfAbsent(effectiveDate, new ArrayList<>());
                List<Map<String, Object>> rounds = parsedData.get(effectiveDate);

                Map<String, Object> targetRound = null;
                for (Map<String, Object> round : rounds) {
                    if (round.get("time").equals(effectiveTime)) {
                        targetRound = round;
                        break;
                    }
                }

                if (targetRound == null) {
                    targetRound = new HashMap<>();
                    targetRound.put("time", effectiveTime);
                    targetRound.put("tickets", new ArrayList<Map<String, Object>>());
                    rounds.add(targetRound);
                }

                List<Map<String, Object>> tickets = (List<Map<String, Object>>) targetRound.get("tickets");
                Map<String, Object> ticket = new HashMap<>();
                ticket.put("name", seatName);
                ticket.put("price", priceStr);
                tickets.add(ticket);
            }

            result.put("success", true);
            result.put("data", parsedData);
            return new ResponseEntity<>(result, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "오류: " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}
