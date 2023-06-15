package apc.sl.quality.incongruent.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.quality.incongruent.service.IncongruentService;
import apc.util.SearchVO;
@Service
public class IncongruentServieImpl implements IncongruentService {
	
	@Resource
	private IncongruentMapper incongruentMapper;
	

	@Override
	public int selectIncongruentListToCnt(SearchVO searchVO) {
		return incongruentMapper.selectIncongruentListToCnt(searchVO);
	}

	@Override
	public List<?> selectIncongruentList(SearchVO searchVO) {
	
		return incongruentMapper.selectIncongruentList(searchVO);
	}

	@Override
	public List<?> selectTiList() {
		return incongruentMapper.selectTiList();
	}

	@Override
	public List<?> selectBiList() {
		return incongruentMapper.selectBiList();
	}

	@Override
	public Map<String, Object> selectInfo(Map<String, Object> map) {
		
		return incongruentMapper.selectInfo(map);
	}

	@Override
	public int selectTiIdx(Map<String, Object> map) {
		
		return incongruentMapper.selectTiIdx(map);
	}

	@Override
	public void registIncongruent(Map<String, Object> map) {
		incongruentMapper.registIncongruent(map);
		
	}

	@Override
	public Map<String, Object> selectInco(Map<String, Object> map) {
		
		return incongruentMapper.selectInco(map);
	}

	@Override
	public void modifyIncongruent(Map<String, Object> map) {
		
		incongruentMapper.modifyIncongruent(map);
	}

	@Override
	public void deleteIncongruent(Map<String, Object> map) {
		incongruentMapper.deleteIncongruent(map);
		
	}

	
}
