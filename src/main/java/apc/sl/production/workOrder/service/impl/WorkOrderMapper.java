package apc.sl.production.workOrder.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("WorkOrderMapper")
public interface WorkOrderMapper {

	int selectWorkOrderListToCnt(SearchVO searchVO);

	List<?> selectWorkOrderList(SearchVO searchVO);

	List<?> selectMaterialList();

	List<?> selectProductList();

	int selectOrderIdx(Map<String, Object> map);

	int selectItemCode(Map<String, Object> map);

	void registWorkOrder(Map<String, Object> map);

	List<?> selectProcessList(Map<String, Object> map);

	void registProcess(Map<String, Object> map);

	void updateOrder(Map<String, Object> map);

	Map<String, Object> selectWorkOrderInfo(Map<String, Object> map);

	void modifyWorkOrder(Map<String, Object> map);

	void deleteWorkOrder(Map<String, Object> map);

	Map<String, Object> selectMaterialStd(Map<String, Object> map);

	void registInMaterial(Map<String, Object> map);

	Map<String, Object> selectInMaterialList(Map<String, Object> map);

	void modifyInMaterial(Map<String, Object> map);

	void deleteInMaterial(Map<String, Object> map);

	int selectExistsInMaterial(Map<String, Object> map);

	void deleteProcess(Map<String, Object> map);

	void registInsertInfo(Map<String, Object> map);

	String selectItemStd(String str);

	void deleteInsertInfo(Map<String, Object> map);

	int checkItemStock(Map<String, Object> temp);

	void updateMaterialStock(Map<String, Object> temp);

}
