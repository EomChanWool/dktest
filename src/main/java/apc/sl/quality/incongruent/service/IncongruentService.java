package apc.sl.quality.incongruent.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface IncongruentService {
	
	int selectIncongruentListToCnt(SearchVO searchVO);
	
	List<?> selectIncongruentList(SearchVO searchVO);
	
	List<?> selectTiList();
	
	List<?> selectBiList();
	
	Map<String, Object> selectInfo(Map<String, Object> map);
	
	int selectTiIdx(Map<String,Object> map);
	
	void registIncongruent(Map<String, Object> map);
	
	Map<String, Object> selectInco(Map<String, Object> map);
	
	void modifyIncongruent(Map<String, Object> map);
	
	void deleteIncongruent(Map<String, Object> map);

}
