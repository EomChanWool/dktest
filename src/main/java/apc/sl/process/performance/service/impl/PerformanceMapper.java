package apc.sl.process.performance.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("PerformanceMapper")
public interface PerformanceMapper {

	int selectPerformanceListToCnt(SearchVO searchVO);

	List<?> selectPerformanceList(SearchVO searchVO);

	void registDocument(Map<String, Object> map);

	Map<String, Object> selectDocumentInfo(Map<String, Object> map);

	void modifyDocument(Map<String, Object> map);

	void deletePerformance(Map<String, Object> map);

}
