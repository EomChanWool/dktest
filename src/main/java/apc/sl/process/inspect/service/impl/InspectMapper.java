package apc.sl.process.inspect.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("InspectMapper")
public interface InspectMapper {
	
	
	int selectInspectListToCnt(SearchVO searchVO);
	
	List<?>selectInspectList(SearchVO searchVO);
	
	List<?> selectSiList();
	
	List<?> selectMfList();
	
	List<?> selectInfo2(Map<String,Object> map);
	
	Map<String, Object> selectInfo(Map<String, Object> map);
	
	Map<String, Object> detailInspec(Map<String, Object> map);
	
	Map<String, Object> spcInfo(String str);
	
	Map<String,Object> eDataInfo(String str);
	
	int selectCheckIns(Map<String,Object> map);
	
	void registInspect(Map<String, Object> map);
	
	Map<String, Object> selectInco(Map<String, Object> map);
	
	void modifyInspect(Map<String, Object> map);
	
	void deleteInspect(Map<String, Object> map);
	
	void updateStat(Map<String, Object> map);
	
}
