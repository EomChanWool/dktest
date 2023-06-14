package apc.sl.monitoring.moStockState.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MoStockStateMapper")
public interface MoStockStateMapper {

	int selectMoStockStateListToCnt(SearchVO searchVO);

	List<?> selectMoStockStateList(SearchVO searchVO);

}
