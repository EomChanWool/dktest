package apc.sl.collectInfo.cutting.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CuttingMapper")
public interface CuttingMapper {

	int selectCuttingListToCnt(SearchVO searchVO);

	List<?> selectCuttingList(SearchVO searchVO);

	List<?> selectAccountList();

	List<?> selectProdList();

	List<?> selectCompanyList();

	List<?> selectComapnyInfo(Map<String, Object> map);

	List<?> selectProdPerPrice(Map<String, Object> map);

	void registCutting(Map<String, Object> map);

	Map<String, Object> selectCuttingInfo(Map<String, Object> map);

	void modifyCutting(Map<String, Object> map);

	void deleteCutting(Map<String, Object> map);

}
