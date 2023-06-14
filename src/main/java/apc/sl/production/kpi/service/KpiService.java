package apc.sl.production.kpi.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface KpiService {

	int selectKpiListToCnt(SearchVO searchVO);

	List<?> selectKpiList(SearchVO searchVO);

	List<?> selectKpiGraphList(SearchVO searchVO);

	List<?> selectErrorOutput(SearchVO searchVO);

	List<?> selectSales(SearchVO searchVO);

	int selectExistsKpi(Map<String, Object> map);

	List<?> selectProdPerPriceInfo(Map<String, Object> map);

	void registKpi(Map<String, Object> map);

	Map<String, Object> selectKpiInfo(Map<String, Object> map);

	void modifyKpi(Map<String, Object> map);

	void deleteKpi(Map<String, Object> map);

}
