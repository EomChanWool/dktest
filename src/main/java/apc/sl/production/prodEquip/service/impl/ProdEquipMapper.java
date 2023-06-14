package apc.sl.production.prodEquip.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProdEquipMapper")
public interface ProdEquipMapper {

	int selectProdEquipListToCnt(SearchVO searchVO);

	List<?> selectProdEquipList(SearchVO searchVO);

	List<?> selectProcessList();

	void registProdEquip(Map<String, Object> map);

	Map<String, Object> selectProdEquipInfo(Map<String, Object> map);

	void modifyProdEquip(Map<String, Object> map);

	void deleteProdEquip(Map<String, Object> map);
}
