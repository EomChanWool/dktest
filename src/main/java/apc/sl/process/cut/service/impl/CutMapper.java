package apc.sl.process.cut.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CutMapper")
public interface CutMapper {

	int selectCutListToCnt(SearchVO searchVO);

	List<?> selectCutList(SearchVO searchVO);
	
	List<?> selectEQList();
	
	List<?> selectLotnoList();

	void registAnalysisData(Map<String, Object> map);

	Map<String, Object> selectCutAjax(Map<String, Object> map);

	int selctExistsEq(Map<String, Object> map);

	int selectExistsLot(Map<String, Object> map);

	void registCut(Map<String, Object> map);

	int selectExistsProdResult(Map<String, Object> map);

	Map<String, Object> selectCutInfo(Map<String, Object> map);
	
	Map<String, Object> selectOrderState(Map<String, Object> map);

	void modifyAnalysisData(Map<String, Object> map);

	void modifyCut(Map<String, Object> map);

	void deleteCut(Map<String, Object> map);

	Map<String, Object> selectAnalysisCnt();
	
	Map<String, Object> detailAnalysis(Map<String, Object> map);

	void updatePoState(Map<String, Object> map);
	
	void updatePrReReSt(Map<String, Object> map);
	
	void updateProcess2(Map<String, Object> map);
	
	void deleteProdResult(Map<String, Object> map);

}
