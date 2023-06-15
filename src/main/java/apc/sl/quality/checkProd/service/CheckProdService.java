package apc.sl.quality.checkProd.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface CheckProdService {
	
	int selectCheckProdListToCnt(SearchVO searchVO);
	
	List<?> selectCheckProdList(SearchVO searchVO);
	
	List<?> selelctInList();
	
	Map<String, Object> selectInfo(Map<String,Object> map);
	
	int selectInIdx(Map<String, Object> map);
	
	void registCheckProd(Map<String, Object> map);
	
	Map<String, Object> selectCheck(Map<String, Object> map);
	
	void modifyCheckProd(Map<String, Object> map);
	
	void deleteCheckProd(Map<String, Object> map);
	
	Map<String, Object> selectDetailCheckProd(Map<String, Object> map);
	

}
