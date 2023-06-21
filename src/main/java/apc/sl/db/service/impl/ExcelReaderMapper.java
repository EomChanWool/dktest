package apc.sl.db.service.impl;

import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ExcelReaderMapper")
public interface ExcelReaderMapper {

	void registdb(Map<String, Object> map);
	
	void registMm(Map<String,Object> map);
	
	void deleteMm();
	
	void deletedb();
}
