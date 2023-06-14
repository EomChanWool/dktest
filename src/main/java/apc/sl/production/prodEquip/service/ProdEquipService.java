package apc.sl.production.prodEquip.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ProdEquipService {

	int selectProdEquipListToCnt(SearchVO searchVO);

	List<?> selectProdEquipList(SearchVO searchVO);

	List<?> selectProcessList();

	void registProdEquip(Map<String, Object> map);

	Map<String, Object> selectProdEquipInfo(Map<String, Object> map);

	void modifyProdEquip(Map<String, Object> map);

	void deleteProdEquip(Map<String, Object> map);

}
