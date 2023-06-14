package apc.sl.production.process.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProcessMapper")
public interface ProcessMapper {

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
