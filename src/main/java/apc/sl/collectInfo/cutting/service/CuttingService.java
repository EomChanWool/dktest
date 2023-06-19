package apc.sl.collectInfo.cutting.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface CuttingService {

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
