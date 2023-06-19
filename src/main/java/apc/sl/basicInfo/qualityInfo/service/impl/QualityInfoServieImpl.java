package apc.sl.basicInfo.qualityInfo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.qualityInfo.service.QualityInfoService;
import apc.util.SearchVO;
@Service
public class QualityInfoServieImpl implements QualityInfoService {
	@Resource
	private QualityInfoMapper qualityInfoMapper;

	@Override
	public int selectQualityInfoToCnt(SearchVO searchVO) {
		return qualityInfoMapper.selectQualityInfoToCnt(searchVO);
	}

	@Override
	public List<?> selectQualityInfoList(SearchVO searchVO) {
		return qualityInfoMapper.selectQualityInfoList(searchVO);
	}

	@Override
	public List<?> selectProgramName() {
		return qualityInfoMapper.selectProgramName();
	}

	@Override
	public void registQualityInfo(Map<String, Object> map) {
		qualityInfoMapper.registQualityInfo(map);
	}

	@Override
	public Map<String, Object> selectQualityInfoInfo(Map<String, Object> map) {
		return qualityInfoMapper.selectQualityInfoInfo(map);
	}

	@Override
	public void modifyQualityInfo(Map<String, Object> map) {
		qualityInfoMapper.modifyQualityInfo(map);
	}

	@Override
	public void deleteQualityInfo(Map<String, Object> map) {
		qualityInfoMapper.deleteQualityInfo(map);
	}

	@Override
	public int selectAccessLevel(String str) {
		return qualityInfoMapper.selectAccessLevel(str);
	}


}
