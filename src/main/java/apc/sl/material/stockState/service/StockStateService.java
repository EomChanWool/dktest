package apc.sl.material.stockState.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface StockStateService {

	int selectStockStateListToCnt(SearchVO searchVO);

	List<?> selectStockStateList(SearchVO searchVO);

}
