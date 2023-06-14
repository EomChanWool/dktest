package apc.sl.monitoring.dashBoard.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface DashBoardService {

	List<?> selectNoticeList(SearchVO searchVO);

	Map<String, Object> selectNoticeInfo(Map<String, Object> map);

	List<?> selectItemList();

}
