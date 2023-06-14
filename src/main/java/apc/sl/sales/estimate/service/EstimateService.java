package apc.sl.sales.estimate.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface EstimateService {

	int selectEstimateListToCnt(SearchVO searchVO);

	List<?> selectEstimateList(SearchVO searchVO);

	List<?> selectAccountList();

	List<?> selectProdList();

	List<?> selectCompanyList();

	List<?> selectComapnyInfo(Map<String, Object> map);

	List<?> selectProdPerPrice(Map<String, Object> map);

	void registEstimate(Map<String, Object> map);

	Map<String, Object> selectEstimateInfo(Map<String, Object> map);

	void modifyEstimate(Map<String, Object> map);

	void deleteEstimate(Map<String, Object> map);

}
