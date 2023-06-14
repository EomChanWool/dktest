package apc.sl.sales.collectMoney.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface CollectMoneyService {

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
