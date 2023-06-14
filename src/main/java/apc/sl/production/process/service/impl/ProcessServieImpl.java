package apc.sl.production.process.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.process.service.ProcessService;
import apc.util.SearchVO;
@Service
public class ProcessServieImpl implements ProcessService {
	@Resource
	private ProcessMapper processMapper;

	@Override
	public int selectProcessChkListToCnt(SearchVO searchVO) {
		return processMapper.selectProcessChkListToCnt(searchVO);
	}

	@Override
	public List<?> selectProcessChkList(SearchVO searchVO) {
		return processMapper.selectProcessChkList(searchVO);
	}

	@Override
	public List<?> selectProcessList() {
		return processMapper.selectProcessList();
	}

	@Override
	public List<?> selectFacilityList() {
		return processMapper.selectFacilityList();
	}

	@Override
	public List<?> selectProcessEquipInfo(Map<String, Object> map) {
		return processMapper.selectProcessEquipInfo(map);
	}

	@Override
	public int selectExistsPrIdx(Map<String, Object> map) {
		return processMapper.selectExistsPrIdx(map);
	}

	@Override
	public int selectExistsFaIdx(Map<String, Object> map) {
		return processMapper.selectExistsFaIdx(map);
	}

	@Override
	public void registProcess(Map<String, Object> map) {
		processMapper.registProcess(map);
	}

	@Override
	public Map<String, Object> selectProcessInfo(Map<String, Object> map) {
		return processMapper.selectProcessInfo(map);
	}

	@Override
	public void modifyProcess(Map<String, Object> map) {
		processMapper.modifyProcess(map);
	}

	@Override
	public void deleteProcess(Map<String, Object> map) {
		processMapper.deleteProcess(map);
	}

}
