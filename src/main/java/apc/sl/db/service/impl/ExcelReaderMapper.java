package apc.sl.db.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ExcelReaderMapper")
public interface ExcelReaderMapper {

	void registdb(Map<String, Object> map);
	
	void registMm(Map<String,Object> map);
	
	void registOrder(Map<String, Object> map);
	
	void registRelease(Map<String,Object> map);
	
	void deleteMm();
	
	void deletedb();
	
	void testRegist(Map<String,String> map);
	
	Map<String, Object> inspCount(String edDate);
	
	List<Map<String, Object>> noUpList(String edDate);
	
	Map<String, Object> mfProc(String str);
	
	void registinspData(Map<String,Object> map);
}
