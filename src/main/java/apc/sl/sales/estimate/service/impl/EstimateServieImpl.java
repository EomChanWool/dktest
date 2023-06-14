package apc.sl.sales.estimate.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.sales.estimate.service.EstimateService;
import apc.util.SearchVO;
@Service
public class EstimateServieImpl implements EstimateService {
	@Resource
	private EstimateMapper estimateMapper;

	@Override
	public int selectEstimateListToCnt(SearchVO searchVO) {
		return estimateMapper.selectEstimateListToCnt(searchVO);
	}

	@Override
	public List<?> selectEstimateList(SearchVO searchVO) {
		return estimateMapper.selectEstimateList(searchVO);
	}

	@Override
	public List<?> selectAccountList() {
		return estimateMapper.selectAccountList();
	}

	@Override
	public List<?> selectProdList() {
		return estimateMapper.selectProdList();
	}

	@Override
	public List<?> selectCompanyList() {
		return estimateMapper.selectCompanyList();
	}

	@Override
	public List<?> selectComapnyInfo(Map<String, Object> map) {
		return estimateMapper.selectComapnyInfo(map);
	}

	@Override
	public List<?> selectProdPerPrice(Map<String, Object> map) {
		return estimateMapper.selectProdPerPrice(map);
	}

	@Override
	public void registEstimate(Map<String, Object> map) {
		estimateMapper.registEstimate(map);
	}

	@Override
	public Map<String, Object> selectEstimateInfo(Map<String, Object> map) {
		return estimateMapper.selectEstimateInfo(map);
	}

	@Override
	public void modifyEstimate(Map<String, Object> map) {
		estimateMapper.modifyEstimate(map);
	}

	@Override
	public void deleteEstimate(Map<String, Object> map) {
		estimateMapper.deleteEstimate(map);
	}

}
