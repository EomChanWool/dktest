package apc.sl.process.performance.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.process.performance.service.PerformanceService;
import apc.util.SearchVO;
@Service
public class PerformanceServieImpl implements PerformanceService {
	@Resource
	private PerformanceMapper performanceMapper;

	@Override
	public int selectPerformanceListToCnt(SearchVO searchVO) {
		return performanceMapper.selectPerformanceListToCnt(searchVO);
	}

	@Override
	public List<?> selectPerformanceList(SearchVO searchVO) {
		return performanceMapper.selectPerformanceList(searchVO);
	}

	@Override
	public void registDocument(Map<String, Object> map) {
		performanceMapper.registDocument(map);
	}

	@Override
	public Map<String, Object> selectDocumentInfo(Map<String, Object> map) {
		return performanceMapper.selectDocumentInfo(map);
	}

	@Override
	public void modifyDocument(Map<String, Object> map) {
		performanceMapper.modifyDocument(map);
	}

	@Override
	public void deletePerformance(Map<String, Object> map) {
		performanceMapper.deletePerformance(map);
	}
}
