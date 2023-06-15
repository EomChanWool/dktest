package apc.sl.quality.analyManage.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface AnalyManageService {

	int selectAnalyManageListToCnt(SearchVO searchVO);

	List<?> selectAnalyManageList(SearchVO searchVO);

	List<?> selectWorkOrderList(String str);

	List<?> selectDocumentList(String str);

	void registAnalysisData(Map<String, Object> map);

	Map<String, Object> selectAzIdx();
	
	Map<String, Object> selectAzIdxData();

	int selectExistsAzIdx(Map<String, Object> map);

	int selectExistsDocumentIdx(Map<String, Object> map);

	void registAnalyManage(Map<String, Object> map);

	int selectExistsProdResult(Map<String, Object> map);

	Map<String, Object> selectAnalyManageInfo(Map<String, Object> map);
	
	Map<String, Object> selectOrderState(Map<String, Object> map);

	void modifyAnalysisData(Map<String, Object> map);

	void modifyAnalyManage(Map<String, Object> map);

	void deleteAnalyManage(Map<String, Object> map);

	Map<String, Object> selectAnalysisCnt();
	
	Map<String, Object> detailAnalysis(Map<String, Object> map);

	void updateDocumnetState(Map<String, Object> map);
	
	void updatePrReReSt(Map<String, Object> map);
	
	void updateProcess2(Map<String, Object> map);
	
	void deleteProdResult(Map<String, Object> map);

}
