package apc.sl.process.inspect.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface InspectService {
	
	int selectInspectListToCnt(SearchVO searchVO);
	
	List<?> selectInspectList(SearchVO searchVO);
	
	List<?> selectTiList();
	
	List<?> selectBiList();
	
	Map<String, Object> selectInfo(Map<String, Object> map);
	
	Map<String, Object> detailInspec(Map<String, Object> map);
	
	int selectTiIdx(Map<String,Object> map);
	
	void registInspect(Map<String, Object> map);
	
	Map<String, Object> selectInco(Map<String, Object> map);
	
	void modifyInspect(Map<String, Object> map);
	
	void deleteInspect(Map<String, Object> map);

}
