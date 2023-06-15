package apc.sl.quality.analyManage.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import apc.sl.quality.analyManage.service.AnalyManageService;
import apc.util.SearchVO;
@Service
public class AnalyManageServieImpl implements AnalyManageService {
	@Resource
	private AnalyManageMapper analyManageMapper;

	@Override
	public int selectAnalyManageListToCnt(SearchVO searchVO) {
		return analyManageMapper.selectAnalyManageListToCnt(searchVO);
	}

	@Override
	public List<?> selectAnalyManageList(SearchVO searchVO) {
		return analyManageMapper.selectAnalyManageList(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList(String str) {
		return analyManageMapper.selectWorkOrderList(str);
	}

	@Override
	public List<?> selectDocumentList(String str) {
		return analyManageMapper.selectDocumentList(str);
	}
	
	@Override
	public void registAnalysisData(Map<String, Object> map) {
		analyManageMapper.registAnalysisData(map);
	}

	@Override
	public Map<String, Object> selectAzIdx() {
		return analyManageMapper.selectAzIdx();
	}

	@Override
	public int selectExistsAzIdx(Map<String, Object> map) {
		return analyManageMapper.selectExistsAzIdx(map);
	}
	
	@Override
	public int selectExistsDocumentIdx(Map<String, Object> map) {
		return analyManageMapper.selectExistsDocumentIdx(map);
	}

	@Override
	public void registAnalyManage(Map<String, Object> map) {
		analyManageMapper.registAnalyManage(map);
	}

	@Override
	public int selectExistsProdResult(Map<String, Object> map) {
		return analyManageMapper.selectExistsProdResult(map);
	}

	@Override
	public Map<String, Object> selectAnalyManageInfo(Map<String, Object> map) {
		return analyManageMapper.selectAnalyManageInfo(map);
	}

	@Override
	public void modifyAnalysisData(Map<String, Object> map) {
		analyManageMapper.modifyAnalysisData(map);
	}

	@Override
	public void modifyAnalyManage(Map<String, Object> map) {
		analyManageMapper.modifyAnalyManage(map);
	}

	@Override
	public void deleteAnalyManage(Map<String, Object> map) {
		analyManageMapper.deleteAnalyManage(map);
	}

	@Override
	public Map<String, Object> selectAnalysisCnt() {
		return analyManageMapper.selectAnalysisCnt();
	}

	@Override
	public void updateDocumnetState(Map<String, Object> map) {
		analyManageMapper.updateDocumnetState(map);
	}

	@Override
	public void updatePrReReSt(Map<String, Object> map) {
		analyManageMapper.updatePrReReSt(map);
	}

	@Override
	public Map<String, Object> detailAnalysis(Map<String, Object> map) {
		return analyManageMapper.detailAnalysis(map);
	}

	@Override
	public Map<String, Object> selectAzIdxData() {
		return analyManageMapper.selectAzIdxData();
	}

	@Override
	public void updateProcess2(Map<String, Object> map) {
		analyManageMapper.updateProcess2(map);
	}

	@Override
	public void deleteProdResult(Map<String, Object> map) {
		analyManageMapper.deleteProdResult(map);
	}

	@Override
	public Map<String, Object> selectOrderState(Map<String, Object> map) {
		return analyManageMapper.selectOrderState(map);
	}
}
