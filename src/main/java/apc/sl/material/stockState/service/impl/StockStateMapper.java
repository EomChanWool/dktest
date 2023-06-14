package apc.sl.material.stockState.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("StockStateMapper")
public interface StockStateMapper {

	int selectStockStateListToCnt(SearchVO searchVO);

	List<?> selectStockStateList(SearchVO searchVO);

}
