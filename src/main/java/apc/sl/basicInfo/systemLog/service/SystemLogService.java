package apc.sl.basicInfo.systemLog.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface SystemLogService {

	int selectSystemLogToCnt(SearchVO searchVO);

	List<?> selectSystemLogList(SearchVO searchVO);

}
