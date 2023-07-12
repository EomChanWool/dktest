package apc.sl.process.manufacture.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ManufactureService {
	
	int selectManufactureListToCnt(SearchVO searchVO);
	
	List<?> selectManufactureList(SearchVO searchVO);
	
	List<?> selelctEQList();
	
	List<?> selectLotnoList();
	
	Map<String, Object> selectInfo(Map<String,Object> map);
	
	int selctExistsEq(Map<String, Object> map);
	
	int selectExistsLot(Map<String, Object> map);
	
	void registManufacture(Map<String, Object> map);
	
	Map<String, Object> selectCheck(Map<String, Object> map);
	
	Map<String, Object> selectMfInfo(Map<String, Object> map);
	
	void modifyManufacture(Map<String, Object> map);
	
	void deleteManufacture(Map<String, Object> map);
	
	Map<String, Object> selectDetailManufacture(Map<String, Object> map);
	

}
