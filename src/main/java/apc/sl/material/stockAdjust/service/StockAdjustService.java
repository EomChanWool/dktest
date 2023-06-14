package apc.sl.material.stockAdjust.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface StockAdjustService {

	int selectStockAdjustListToCnt(SearchVO searchVO);

	List<?> selectStockAdjustList(SearchVO searchVO);

	List<?> selectItemList(String type);

	List<?> selectItemCntInfo(Map<String, Object> map);

	int selectExistsItemCode(Map<String, Object> map);

	void registStockAdjust(Map<String, Object> map);

	void updateItemCnt(Map<String, Object> map);

	Map<String, Object> selectStockAdjustInfo(Map<String, Object> map);

	void modifyStockAdjust(Map<String, Object> map);

	int selectChkRecent(Map<String, Object> map);

	void deleteStockAdjust(Map<String, Object> map);

}
