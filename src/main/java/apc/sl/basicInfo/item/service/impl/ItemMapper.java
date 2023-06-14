package apc.sl.basicInfo.item.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ItemMapper")
public interface ItemMapper {

	int selectItemListToCnt(SearchVO searchVO);

	List<?> selectItemList(SearchVO searchVO);

	void registItem(Map<String, Object> map);

	Map<String, Object> selectItemInfo(Map<String, Object> map);

	void modifyItem(Map<String, Object> map);

	void deleteItem(Map<String, Object> map);

}
