package apc.sl.process.cut.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import apc.sl.process.cut.service.CutService;
import apc.util.SearchVO;
@Service
public class CutServieImpl implements CutService {
	@Resource
	private CutMapper cutMapper;

	@Override
	public int selectCutListToCnt(SearchVO searchVO) {
		return cutMapper.selectCutListToCnt(searchVO);
	}

	@Override
	public List<?> selectCutList(SearchVO searchVO) {
		return cutMapper.selectCutList(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList(String str) {
		return cutMapper.selectWorkOrderList(str);
	}

	@Override
	public List<?> selectDocumentList(String str) {
		return cutMapper.selectDocumentList(str);
	}
	
	@Override
	public void registAnalysisData(Map<String, Object> map) {
		cutMapper.registAnalysisData(map);
	}

	@Override
	public Map<String, Object> selectAzIdx() {
		return cutMapper.selectAzIdx();
	}

	@Override
	public int selectExistsAzIdx(Map<String, Object> map) {
		return cutMapper.selectExistsAzIdx(map);
	}
	
	@Override
	public int selectExistsDocumentIdx(Map<String, Object> map) {
		return cutMapper.selectExistsDocumentIdx(map);
	}

	@Override
	public void registCut(Map<String, Object> map) {
		cutMapper.registCut(map);
	}

	@Override
	public int selectExistsProdResult(Map<String, Object> map) {
		return cutMapper.selectExistsProdResult(map);
	}

	@Override
	public Map<String, Object> selectCutInfo(Map<String, Object> map) {
		return cutMapper.selectCutInfo(map);
	}

	@Override
	public void modifyAnalysisData(Map<String, Object> map) {
		cutMapper.modifyAnalysisData(map);
	}

	@Override
	public void modifyCut(Map<String, Object> map) {
		cutMapper.modifyCut(map);
	}

	@Override
	public void deleteCut(Map<String, Object> map) {
		cutMapper.deleteCut(map);
	}

	@Override
	public Map<String, Object> selectAnalysisCnt() {
		return cutMapper.selectAnalysisCnt();
	}

	@Override
	public void updateDocumnetState(Map<String, Object> map) {
		cutMapper.updateDocumnetState(map);
	}

	@Override
	public void updatePrReReSt(Map<String, Object> map) {
		cutMapper.updatePrReReSt(map);
	}

	@Override
	public Map<String, Object> detailAnalysis(Map<String, Object> map) {
		return cutMapper.detailAnalysis(map);
	}

	@Override
	public Map<String, Object> selectAzIdxData() {
		return cutMapper.selectAzIdxData();
	}

	@Override
	public void updateProcess2(Map<String, Object> map) {
		cutMapper.updateProcess2(map);
	}

	@Override
	public void deleteProdResult(Map<String, Object> map) {
		cutMapper.deleteProdResult(map);
	}

	@Override
	public Map<String, Object> selectOrderState(Map<String, Object> map) {
		return cutMapper.selectOrderState(map);
	}
}
