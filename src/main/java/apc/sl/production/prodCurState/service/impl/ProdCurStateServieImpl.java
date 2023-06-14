package apc.sl.production.prodCurState.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.prodCurState.service.ProdCurStateService;
import apc.util.SearchVO;
@Service
public class ProdCurStateServieImpl implements ProdCurStateService {
	@Resource
	private ProdCurStateMapper prodStateMapper;

	@Override
	public int selectProdCurStateListToCnt(SearchVO searchVO) {
		return prodStateMapper.selectProdCurStateListToCnt(searchVO);
	}

	@Override
	public List<?> selectProdCurStateList(SearchVO searchVO) {
		return prodStateMapper.selectProdCurStateList(searchVO);
	}

}
