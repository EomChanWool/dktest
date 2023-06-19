package apc.sl.basicInfo.qualityInfo.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface QualityInfoService {

	int selectQualityInfoToCnt(SearchVO searchVO);

	List<?> selectQualityInfoList(SearchVO searchVO);

	List<?> selectProgramName();

	void registQualityInfo(Map<String, Object> map);

	Map<String, Object> selectQualityInfoInfo(Map<String, Object> map);

	void modifyQualityInfo(Map<String, Object> map);

	void deleteQualityInfo(Map<String, Object> map);

	int selectAccessLevel(String str);
}
