package apc.sl.production.prodEquip.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.prodEquip.service.ProdEquipService;
import apc.util.SearchVO;
@Service
public class ProdEquipServieImpl implements ProdEquipService {
	@Resource
	private ProdEquipMapper prodEquipMapper;

	@Override
	public int selectProdEquipListToCnt(SearchVO searchVO) {
		return prodEquipMapper.selectProdEquipListToCnt(searchVO);
	}

	@Override
	public List<?> selectProdEquipList(SearchVO searchVO) {
		return prodEquipMapper.selectProdEquipList(searchVO);
	}

	@Override
	public List<?> selectProcessList() {
		return prodEquipMapper.selectProcessList();
	}

	@Override
	public void registProdEquip(Map<String, Object> map) {
		prodEquipMapper.registProdEquip(map);
	}

	@Override
	public Map<String, Object> selectProdEquipInfo(Map<String, Object> map) {
		return prodEquipMapper.selectProdEquipInfo(map);
	}

	@Override
	public void modifyProdEquip(Map<String, Object> map) {
		prodEquipMapper.modifyProdEquip(map);
	}

	@Override
	public void deleteProdEquip(Map<String, Object> map) {
		prodEquipMapper.deleteProdEquip(map);
	}

}
