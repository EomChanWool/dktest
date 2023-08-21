package apc.sl.monitoring.dashBoard.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("DashBoardMapper")
public interface DashBoardMapper {
	
	Map<String, Object> selectYear1();

	Map<String, Object> selectYear2();
	
	Map<String, Object> selectYear3();
	
	List<?> selectNoticeList(SearchVO searchVO);

	Map<String, Object> selectNoticeInfo(Map<String, Object> map);
	
	List<?> selectOrdersCnt(String str1);
	
	List<?> selectProdCnt(String str1);
	
	List<?> selectLineYear(String str2);

	List<?> selectItemList();
	
	List<?> selectProdCntAc(String str3);

}
