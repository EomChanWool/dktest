package apc.sl.db.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.db.service.ExcelReaderService;

@Service
public class ExcelReaderServieImpl implements ExcelReaderService {
	
	@Resource ExcelReaderMapper excelReaderMapper;

	@Override
	public void registdb(Map<String, Object> map) {
		excelReaderMapper.registdb(map);
	}

	@Override
	public void deletedb() {
		excelReaderMapper.deletedb();
		
	}

	@Override
	public void registMm(Map<String, Object> map) {
		excelReaderMapper.registMm(map);
	}

	@Override
	public void deleteMm() {
		excelReaderMapper.deleteMm();
	}

	@Override
	public void registOrder(Map<String, Object> map) {
		excelReaderMapper.registOrder(map);
	}

	@Override
	public void registRelease(Map<String, Object> map) {
		excelReaderMapper.registRelease(map);
	}

	@Override
	public void testRegist(Map<String,String> map) {
		excelReaderMapper.testRegist(map);
	}

}
