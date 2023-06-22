package apc.util;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import apc.sl.db.service.ExcelReaderService;
import egovframework.rte.fdl.filehandling.EgovFileUtil;

@Component
public class Scheduler {
	@Autowired
	private ExcelReaderService excelReaderService;
	
	@Scheduled(cron = "10 * * * * *")
	public void delete1() throws Exception{
		excelReaderService.deletedb();
		excelReaderService.deleteMm();
	}
	
	@Scheduled(cron = "20 * * * * *")
	public void autoUpdate1() throws Exception {
		String fileName = "C:\\test\\datatest.xls";
		FileInputStream fis = new FileInputStream(fileName);

		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		int rowindex = 0;
		int columnindex = 0;		
		int sheetCn = workbook.getNumberOfSheets();	// 시트 수
		
		String[] excelCol = new String[] {
				"piItemType","재질","규격","두께","길이","piItemMiddle","이월","mmIn","mmOut","piCnt","이월중량","mmInKg","mmOutKg","piItemRemain","piPrice","piHeat","piItemState"
				,"piItemType2","piItemCode1","piItemCode2","piItemCode3","piItemCode4","piId"
		};
		for(int sheetnum=0; sheetnum<sheetCn; sheetnum++) {	// 시트 수만큼 반복
			
			int sheetnum2=sheetnum+1;
			System.out.println("sheet = " + sheetnum2);
			
			HSSFSheet sheet = workbook.getSheetAt(sheetnum);	// 읽어올 시트 선택
			int rows = sheet.getPhysicalNumberOfRows();    // 행의 수
			HSSFRow row = null;
			
			for (rowindex = 1; rowindex < rows; rowindex++) {	// 행의 수만큼 반복

				row = sheet.getRow(rowindex);	// rowindex 에 해당하는 행을 읽는다
				
				List<Integer> selectedIndexes = Arrays.asList(0, 5, 9, 13, 14, 15, 16, 18, 19, 20, 21,22);
				List<Integer> selectedIndexes2 = Arrays.asList(7,8,9,11,12,13,22);

				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();	// 셀의 수
					cells = row.getPhysicalNumberOfCells();    // 열의 수
					Map<String, Object> rowMap = new HashMap<String, Object>();
					Map<String, Object> map = new HashMap<String, Object>();
					int boolcnt = 0;
					for (columnindex = 0; columnindex < cells; columnindex++) {	// 열의 수만큼 반복
						HSSFCell cell_filter = row.getCell(columnindex);	// 셀값을 읽는다
						String value = "";
								// 셀이 빈값일경우를 위한 널체크
						if (cell_filter == null || cell_filter.getCellType() == cell_filter.CELL_TYPE_BLANK) {
							value="";
							
						} else {
									// 타입별로 내용 읽기
							switch (cell_filter.getCellType()) {
							case HSSFCell.CELL_TYPE_FORMULA:
								value = cell_filter.getCellFormula();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								value = cell_filter.getNumericCellValue() + "";
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value = cell_filter.getStringCellValue() + "";
								break;
							case HSSFCell.CELL_TYPE_ERROR:
								value = cell_filter.getErrorCellValue() + "";
								break;
							}
						}
						if(columnindex == 22 && value=="") {
							rowMap.clear();
							map.clear();
							break;
						}
						if (selectedIndexes.contains(columnindex)) {
				            rowMap.put(excelCol[columnindex],value);
				        }
						if(selectedIndexes2.contains(columnindex)) {
				        	if((columnindex == 7 || columnindex == 8) && value == "") {
				        		boolcnt++;
				        	}
				        	map.put(excelCol[columnindex], value);
				        	map.put("mmRegId","admin");
				        }
					} 
					if(rowMap.size() != 0) {
						excelReaderService.registdb(rowMap);
					}
					if(map.size() !=0 && boolcnt != 2) {
						excelReaderService.registMm(map);
					}
				}
			}
		}
		fis.close();	//파일 읽기 종료
		//db업데이트후 파일 지우기
		//File file = new File(fileName);
		//EgovFileUtil.delete(file);
	}
	
}
