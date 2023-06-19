package apc.sl.process.performance.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface PerformanceService {

	int selectPerformanceListToCnt(SearchVO searchVO);

	List<?> selectPerformanceList(SearchVO searchVO);

	void registDocument(Map<String, Object> map);

	Map<String, Object> selectDocumentInfo(Map<String, Object> map);

	void modifyDocument(Map<String, Object> map);

	void deletePerformance(Map<String, Object> map);
}
