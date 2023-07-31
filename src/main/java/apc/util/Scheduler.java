package apc.util;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import apc.sl.db.service.ExcelReaderService;
import egovframework.rte.fdl.filehandling.EgovFileUtil;

@Component
public class Scheduler {
	//static String user_id = "";
	FTPClient ftp = null;
	
	@Autowired
	private ExcelReaderService excelReaderService;
	
	
	
	@Scheduled(cron = "10 55 8 * * *")
	public void delete1() throws Exception{
		//excelReaderService.deletedb();
		//excelReaderService.deleteMm();
	}
	
	//ini 파일 읽어들이기 예제
	@Scheduled(cron = "10 55 8 * * *")
	public void sampleIni() throws Exception{
		String fileName = "C:\\test\\sample.ini";
		File fileToParse = new File(fileName);
		
		INIConfiguration iniConfiguration = new INIConfiguration();
		try (FileReader fileReader = new FileReader(fileToParse)) {
		    iniConfiguration.read(fileReader);
		}
		
		Map<String, Map<String,String>> map =  new HashMap<>();
		for (String section : iniConfiguration.getSections()) {
			Map<String,String> subMap = new HashMap<>();
			SubnodeConfiguration confSection = iniConfiguration.getSection(section);
			Iterator<String> keyIlerator = confSection.getKeys();
			while(keyIlerator.hasNext()) {
				String key = keyIlerator.next();
				String value = confSection.getProperty(key)+"";
				subMap.put(key,value);
			}
			map.put(section, subMap);
		}
		Map<String, String> rowMap = map.get("main");
		Map<String, String> rowMap2 = map.get("branch1");
		
		System.out.println("ini맵 : " + rowMap);
		System.out.println("ini맵2 : " + rowMap2);
	}
	

	
	@Scheduled(cron = "20 55 8 * * *")
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
					Map<String, Object> rowMap = new HashMap<String, Object>();//제품정보관리 데이터
					Map<String, Object> map = new HashMap<String, Object>();//자재이동관리 데이터
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
				            rowMap.put("miRegId", "admin");
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
						//excelReaderService.registdb(rowMap);
					}
					if(map.size() !=0 && boolcnt != 2) {
						//excelReaderService.registMm(map);
					}
				}
			}
		}
		fis.close();	//파일 읽기 종료
		//db업데이트후 파일 지우기
		//File file = new File(fileName);
		//EgovFileUtil.delete(file);
	}
	@Scheduled(cron = "20 56 8 * * *")
	public void orderUpdate() throws Exception  {
		String fileName = "C:\\test\\orders.xls";
		
		FileInputStream fis = new FileInputStream(fileName);

		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		int rowindex = 0;
		int columnindex = 0;		
		int sheetCn = workbook.getNumberOfSheets();	// 시트 수
		
		String[] excelCol = new String[] {
				"orDate","orCompany","orOrType","orDueDate","orQty","orUnit","orMoney","orFinDate","orReport","orManager","orId","orProd","orTexture",
				"orThickness","orStandard","orState"
		};
		for(int sheetnum=0; sheetnum<sheetCn; sheetnum++) {	// 시트 수만큼 반복
			
			int sheetnum2=sheetnum+1;
			System.out.println("sheet = " + sheetnum2);
			
			HSSFSheet sheet = workbook.getSheetAt(sheetnum);	// 읽어올 시트 선택
			int rows = sheet.getPhysicalNumberOfRows();    // 행의 수
			HSSFRow row = null;
			
			for (rowindex = 1; rowindex < rows-1; rowindex++) {	// 행의 수만큼 반복

				row = sheet.getRow(rowindex);	// rowindex 에 해당하는 행을 읽는다
				

				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();	// 셀의 수
					cells = row.getPhysicalNumberOfCells();    // 열의 수
					Map<String, Object> rowMap = new HashMap<String, Object>();//수주 데이터
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
							default: value="";	
							}
						}
						
						if(columnindex == 10 && value.equals("")) {
							rowMap.clear();
							break;
						}
						if(columnindex == 10) {
							
							value = value.replaceAll("[-]", "");
						}
				            rowMap.put(excelCol[columnindex],value);
				            rowMap.put("orProcess", "0");
				            
				        
					
					} 
					if(rowMap.size() != 0) {
						//excelReaderService.registOrder(rowMap);
					}
				}
			}
		}
		fis.close();	//파일 읽기 종료
		//db업데이트후 파일 지우기
		//File file = new File(fileName);
		//EgovFileUtil.delete(file);
	}
	@Scheduled(cron = "20 57 8 * * *")
	public void releaseUpdate() throws Exception{
		
		String fileName = "C:\\test\\release.xls";
		
		try {
		FileInputStream fis = new FileInputStream(fileName);
		
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(fis);
		int rowindex = 0;
		int columnindex = 0;		
		int sheetCn = workbook.getNumberOfSheets();	// 시트 수
		
		String[] excelCol = new String[] {
				"relDate","relCompony","relQty","relNego","relPercent","relUnit","relPrice","relTax","relTotalPrice","relNote","relDel","relEsno","relPrno","poLotno",
				"relHeatno","relSub","relOrType","orId","relReport","relBill","relProd","relTexture","relThickness","relStandard","relState"
		};
		for(int sheetnum=0; sheetnum<sheetCn; sheetnum++) {	// 시트 수만큼 반복
			
			int sheetnum2=sheetnum+1;
			System.out.println("sheet = " + sheetnum2);
			
			HSSFSheet sheet = workbook.getSheetAt(sheetnum);	// 읽어올 시트 선택
			int rows = sheet.getPhysicalNumberOfRows();    // 행의 수
			HSSFRow row = null;
			
			for (rowindex = 1; rowindex < rows-1; rowindex++) {	// 행의 수만큼 반복

				row = sheet.getRow(rowindex);	// rowindex 에 해당하는 행을 읽는다
				

				if (row != null) {
					int cells = row.getPhysicalNumberOfCells();	// 셀의 수
					cells = row.getPhysicalNumberOfCells();    // 열의 수
					Map<String, Object> rowMap = new HashMap<String, Object>();//수주 데이터
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
							default: value="";	
							}
						}
						
						if(columnindex == 0 && value.equals("")) {
							rowMap.clear();
							break;
						}
							
				            rowMap.put(excelCol[columnindex],value);
				            rowMap.put("relRegId","admin");
					
					} 
					if(rowMap.size() != 0) {
						//excelReaderService.registRelease(rowMap);
					}
					
				}
			}
		}
		fis.close();	//파일 읽기 종료
		//db업데이트후 파일 지우기
		//File file = new File(fileName);
		//EgovFileUtil.delete(file);
		
		}catch (FileNotFoundException e) {
		System.out.println("파일없음");	}
		}
	
	//@Scheduled(cron = "20 * * * * *")
	public void open() {
		
	    ftp = new FTPClient();
	    //default controlEncoding 값이 "ISO-8859-1" 때문에 한글 파일의 경우 파일명이 깨짐
	    //ftp server 에 저장될 파일명을 uuid 등의 방식으로 한글을 사용하지 않고 저장할 경우 UTF-8 설정이 따로 필요하지 않다.
	    ftp.setControlEncoding("UTF-8");
	    //PrintCommandListener 를 추가하여 표준 출력에 대한 명령줄 도구를 사용하여 FTP 서버에 연결할 때 일반적으로 표시되는 응답을 출력
	    ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

	    try {
	        //ftp 서버 연결
	        ftp.connect("dkbend.iptime.org", 30431);

	        //ftp 서버에 정상적으로 연결되었는지 확인
	        int reply = ftp.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
	            ftp.disconnect();
	            System.out.println("에러");
	        }

	        //socketTimeout 값 설정
	        ftp.setSoTimeout(1000);
	        //ftp 서버 로그인
	        ftp.login("signlab", "dk304316@");
	        //file type 설정 (default FTP.ASCII_FILE_TYPE)
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	            
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("에러");
	    }
	    File get_file = new File("C:\\test\\testDown.txt");
	 
	    try {
	    	FileOutputStream outputstream = new FileOutputStream(get_file);
	    	boolean result = ftp.retrieveFile("/textTest/testDown2.txt", outputstream);
	    	System.out.println("스트링 :" +ftp.getReplyString());

	    	System.out.println(ftp.getReplyCode());
	    	if(result) {
	    		System.out.println("파일다운성공");
	    	}else {
	    		System.out.println("실패");
	    	}
	    	
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.out.println("에러");
	    } finally {
	    	try {
		        ftp.logout();
		        ftp.disconnect();
		    } catch (IOException e) {
		        e.printStackTrace();
		        System.out.println("에러");
		    }
	    }
	    
	    
	}
	

	
//	public static void setUserId(String userId) {
//		user_id = userId;
//	}
	
}
