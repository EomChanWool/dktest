package apc.sl.production.prodCurState.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProdCurStateMapper")
public interface ProdCurStateMapper {

	int selectProdCurStateListToCnt(SearchVO searchVO);

	List<?> selectProdCurStateList(SearchVO searchVO);

}
