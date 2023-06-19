package apc.sl.process.inspect.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.process.inspect.service.InspectService;
import apc.util.SearchVO;
@Service
public class InspectServieImpl implements InspectService {
	
	@Resource
	private InspectMapper inspectMapper;
	

	@Override
	public int selectInspectListToCnt(SearchVO searchVO) {
		return inspectMapper.selectInspectListToCnt(searchVO);
	}

	@Override
	public List<?> selectInspectList(SearchVO searchVO) {
	
		return inspectMapper.selectInspectList(searchVO);
	}

	@Override
	public List<?> selectTiList() {
		return inspectMapper.selectTiList();
	}

	@Override
	public List<?> selectBiList() {
		return inspectMapper.selectBiList();
	}

	@Override
	public Map<String, Object> selectInfo(Map<String, Object> map) {
		
		return inspectMapper.selectInfo(map);
	}

	@Override
	public int selectTiIdx(Map<String, Object> map) {
		
		return inspectMapper.selectTiIdx(map);
	}

	@Override
	public void registInspect(Map<String, Object> map) {
		inspectMapper.registInspect(map);
		
	}

	@Override
	public Map<String, Object> selectInco(Map<String, Object> map) {
		
		return inspectMapper.selectInco(map);
	}

	@Override
	public void modifyInspect(Map<String, Object> map) {
		
		inspectMapper.modifyInspect(map);
	}

	@Override
	public void deleteInspect(Map<String, Object> map) {
		inspectMapper.deleteInspect(map);
		
	}

	
}
