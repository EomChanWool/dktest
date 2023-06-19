package apc.sl.facility.prodCurState.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ProdCurStateService {

	int selectProdCurStateListToCnt(SearchVO searchVO);

	List<?> selectProdCurStateList(SearchVO searchVO);

}
