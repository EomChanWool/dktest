package apc.sl.production.process.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ProcessService {

	int selectProcessChkListToCnt(SearchVO searchVO);

	List<?> selectProcessChkList(SearchVO searchVO);

	List<?> selectProcessList();

	List<?> selectFacilityList();

	List<?> selectProcessEquipInfo(Map<String, Object> map);

	int selectExistsPrIdx(Map<String, Object> map);

	int selectExistsFaIdx(Map<String, Object> map);

	void registProcess(Map<String, Object> map);

	Map<String, Object> selectProcessInfo(Map<String, Object> map);

	void modifyProcess(Map<String, Object> map);

	void deleteProcess(Map<String, Object> map);

}
