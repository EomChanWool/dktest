package apc.sl.monitoring.dashBoard.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("DashBoardMapper")
public interface DashBoardMapper {

	List<?> selectNoticeList(SearchVO searchVO);

	Map<String, Object> selectNoticeInfo(Map<String, Object> map);

	List<?> selectItemList();

}
