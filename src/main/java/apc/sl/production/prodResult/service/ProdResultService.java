package apc.sl.production.prodResult.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ProdResultService {

	int selectProdResultListToCnt(SearchVO searchVO);

	List<?> selectProdResultList(SearchVO searchVO);

	List<?> selectWorkOrderList();

	List<?> selectWorkOrderInfo(Map<String, Object> map);

	int selectExistsWorkOrderIdx(Map<String, Object> map);

	int selectExistsProdResult(Map<String, Object> map);

	void registProdResult(Map<String, Object> map);

	Map<String, Object> selectProcessSeqInfo(Map<String, Object> map);

	void updateProcess(Map<String, Object> map);

	Map<String, Object> selectProdResultInfo(Map<String, Object> map);

	void modifyProdResult(Map<String, Object> map);

	String selectLastProcessNm(Map<String, Object> map);

	void updateWorkOrder(Map<String, Object> map);

	void deleteProdResult(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	void addItemStock(Map<String, Object> map);

	String selectItemCd(Map<String, Object> map);

	Map<String, Object> selectMaterialList(Map<String, Object> map);

	void updateInMaterial(Map<String, Object> temp);

	void updateMaterialStock(Map<String, Object> temp2);

}
