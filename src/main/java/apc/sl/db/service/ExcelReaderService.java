package apc.sl.db.service;

import java.util.Map;

public interface ExcelReaderService {

	void registdb(Map<String, Object> map);
	
	void registOrder(Map<String, Object> map);
	
	void registMm(Map<String,Object> map);
	
	void registRelease(Map<String,Object> map);
	
	void deletedb();
	
	void deleteMm();
	
	
}
