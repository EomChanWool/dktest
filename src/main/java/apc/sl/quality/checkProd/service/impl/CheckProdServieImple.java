package apc.sl.quality.checkProd.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.quality.checkProd.service.CheckProdService;
import apc.util.SearchVO;

@Service
public class CheckProdServieImple implements CheckProdService {
	
	@Resource
	private CheckProdMapper checkProdMapper;

	@Override
	public int selectCheckProdListToCnt(SearchVO searchVO) {
		return checkProdMapper.selectCheckProdListToCnt(searchVO);
	}

	@Override
	public List<?> selectCheckProdList(SearchVO searchVO) {
		return checkProdMapper.selectCheckProdList(searchVO);
	}

	@Override
	public List<?> selelctInList() {
		return checkProdMapper.selelctInList();
	}

	@Override
	public Map<String, Object> selectInfo(Map<String, Object> map) {
		return checkProdMapper.selectInfo(map);
	}

	@Override
	public int selectInIdx(Map<String, Object> map) {
		return checkProdMapper.selectInIdx(map);
	}

	@Override
	public void registCheckProd(Map<String, Object> map) {
		checkProdMapper.registCheckProd(map);
		
	}

	@Override
	public Map<String, Object> selectCheck(Map<String, Object> map) {
		return checkProdMapper.selectCheck(map);
	}

	@Override
	public void modifyCheckProd(Map<String, Object> map) {
		checkProdMapper.modifyCheckProd(map);
		
	}

	@Override
	public void deleteCheckProd(Map<String, Object> map) {
		checkProdMapper.deleteCheckProd(map);
	}

	@Override
	public Map<String, Object> selectDetailCheckProd(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return checkProdMapper.selectDetailCheckProd(map);
	}

}
