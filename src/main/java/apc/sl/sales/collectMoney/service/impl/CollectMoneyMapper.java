package apc.sl.sales.collectMoney.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CollectMoneyMapper")
public interface CollectMoneyMapper {

	int selectCollectMoneyListToCnt(SearchVO searchVO);

	List<?> selectCollectMoneyList(SearchVO searchVO);

	List<?> selectDeliveryList();

	List<?> selectDeliveryInfo(Map<String, Object> map);

	int selectExistsDeliveryIdx(Map<String, Object> map);

	int selectAlreadyRegistDeIdx(Map<String, Object> map);

	void registCollectMoney(Map<String, Object> map);

	Map<String, Object> selectCollectInfo(Map<String, Object> map);

	void modifyCollectMoney(Map<String, Object> map);

	void updateDelivery(Map<String, Object> map);

	void deleteCollect(Map<String, Object> map);

}
