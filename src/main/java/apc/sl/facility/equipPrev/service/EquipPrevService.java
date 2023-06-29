package apc.sl.facility.equipPrev.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface EquipPrevService {

	int selectEquipPrevListToCnt(SearchVO searchVO);

	List<?> selectEquipPrevList(SearchVO searchVO);

	List<?> selectFailList();

	void failReportIscomp(Map<String, Object> map);
	
	void registEquipPrev(Map<String, Object> map);

	Map<String, Object> selectEquipPrevInfo(Map<String, Object> map);

	void modifyEquipPrev(Map<String, Object> map);

	void deleteEquipPrev(Map<String, Object> map);

}
