package apc.sl.monitoring.moStockState.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.monitoring.moStockState.service.MoStockStateService;
import apc.util.SearchVO;
@Service
public class MoStockStateServieImpl implements MoStockStateService {
	@Resource
	private MoStockStateMapper moStockStateMapper;

	@Override
	public int selectMoStockStateListToCnt(SearchVO searchVO) {
		return moStockStateMapper.selectMoStockStateListToCnt(searchVO);
	}

	@Override
	public List<?> selectMoStockStateList(SearchVO searchVO) {
		return moStockStateMapper.selectMoStockStateList(searchVO);
	}

}
