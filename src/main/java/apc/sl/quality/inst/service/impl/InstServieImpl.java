package apc.sl.quality.inst.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.quality.inst.service.InstService;
import apc.util.SearchVO;
@Service
public class InstServieImpl implements InstService {
	@Resource
	private InstMapper instMapper;

	@Override
	public int selectInstListToCnt(SearchVO searchVO) {
		return instMapper.selectInstListToCnt(searchVO);
	}

	@Override
	public List<?> selectInstList(SearchVO searchVO) {
		return instMapper.selectInstList(searchVO);
	}

	@Override
	public void registDocument(Map<String, Object> map) {
		instMapper.registDocument(map);
	}

	@Override
	public Map<String, Object> selectDocumentInfo(Map<String, Object> map) {
		return instMapper.selectDocumentInfo(map);
	}

	@Override
	public void modifyDocument(Map<String, Object> map) {
		instMapper.modifyDocument(map);
	}

	@Override
	public void deleteInst(Map<String, Object> map) {
		instMapper.deleteInst(map);
	}
}
