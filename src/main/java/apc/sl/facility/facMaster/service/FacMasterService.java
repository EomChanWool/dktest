package apc.sl.facility.facMaster.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface FacMasterService {

	int selectFacMasterListToCnt(SearchVO searchVO);

	List<?> selectFacMasterList(SearchVO searchVO);

	List<?> selectMaterialList();

	List<?> selectProductList();

	int selectOrderIdx(Map<String, Object> map);

	int selectItemCode(Map<String, Object> map);

	void registFacMaster(Map<String, Object> map);

	List<?> selectProcessList(Map<String, Object> map);

	void registProcess(Map<String, Object> map);

	void updateOrder(Map<String, Object> map);

	Map<String, Object> selectFacMasterInfo(Map<String, Object> map);

	void modifyFacMaster(Map<String, Object> map);

	void deleteFacMaster(Map<String, Object> map);

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
