package apc.sl.basicInfo.systemLog.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("SystemLogMapper")
public interface SystemLogMapper {

	int selectSystemLogToCnt(SearchVO searchVO);

	List<?> selectSystemLogList(SearchVO searchVO);

}
