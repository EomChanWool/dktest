package apc.sl.material.stockAdjust.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.stockAdjust.service.StockAdjustService;
import apc.util.SearchVO;
@Service
public class StockAdjustServieImpl implements StockAdjustService {
	@Resource
	private StockAdjustMapper stockAdjustMapper;

	@Override
	public int selectStockAdjustListToCnt(SearchVO searchVO) {
		return stockAdjustMapper.selectStockAdjustListToCnt(searchVO);
	}

	@Override
	public List<?> selectStockAdjustList(SearchVO searchVO) {
		return stockAdjustMapper.selectStockAdjustList(searchVO);
	}

	@Override
	public List<?> selectItemList(String type) {
		return stockAdjustMapper.selectItemList(type);
	}

	@Override
	public List<?> selectItemCntInfo(Map<String, Object> map) {
		return stockAdjustMapper.selectItemCntInfo(map);
	}

	@Override
	public int selectExistsItemCode(Map<String, Object> map) {
		return stockAdjustMapper.selectExistsItemCode(map);
	}

	@Override
	public void registStockAdjust(Map<String, Object> map) {
		stockAdjustMapper.registStockAdjust(map);
	}

	@Override
	public void updateItemCnt(Map<String, Object> map) {
		stockAdjustMapper.updateItemCnt(map);
	}

	@Override
	public Map<String, Object> selectStockAdjustInfo(Map<String, Object> map) {
		return stockAdjustMapper.selectStockAdjustInfo(map);
	}

	@Override
	public void modifyStockAdjust(Map<String, Object> map) {
		stockAdjustMapper.modifyStockAdjust(map);
	}

	@Override
	public int selectChkRecent(Map<String, Object> map) {
		return stockAdjustMapper.selectChkRecent(map);
	}

	@Override
	public void deleteStockAdjust(Map<String, Object> map) {
		stockAdjustMapper.deleteStockAdjust(map);
	}

}
