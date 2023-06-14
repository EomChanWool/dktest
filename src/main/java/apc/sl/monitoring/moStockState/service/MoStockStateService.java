package apc.sl.monitoring.moStockState.service;

import java.util.List;

import apc.util.SearchVO;

public interface MoStockStateService {

	int selectMoStockStateListToCnt(SearchVO searchVO);

	List<?> selectMoStockStateList(SearchVO searchVO);

}
