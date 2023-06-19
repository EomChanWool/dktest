package apc.sl.process.cut.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface CutService {

	int selectCutListToCnt(SearchVO searchVO);

	List<?> selectCutList(SearchVO searchVO);

	List<?> selectWorkOrderList(String str);

	List<?> selectDocumentList(String str);

	void registAnalysisData(Map<String, Object> map);

	Map<String, Object> selectAzIdx();
	
	Map<String, Object> selectAzIdxData();

	int selectExistsAzIdx(Map<String, Object> map);

	int selectExistsDocumentIdx(Map<String, Object> map);

	void registCut(Map<String, Object> map);

	int selectExistsProdResult(Map<String, Object> map);

	Map<String, Object> selectCutInfo(Map<String, Object> map);
	
	Map<String, Object> selectOrderState(Map<String, Object> map);

	void modifyAnalysisData(Map<String, Object> map);

	void modifyCut(Map<String, Object> map);

	void deleteCut(Map<String, Object> map);

	Map<String, Object> selectAnalysisCnt();
	
	Map<String, Object> detailAnalysis(Map<String, Object> map);

	void updateDocumnetState(Map<String, Object> map);
	
	void updatePrReReSt(Map<String, Object> map);
	
	void updateProcess2(Map<String, Object> map);
	
	void deleteProdResult(Map<String, Object> map);

}
