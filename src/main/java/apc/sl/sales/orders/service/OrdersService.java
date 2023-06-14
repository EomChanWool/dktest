package apc.sl.sales.orders.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface OrdersService {

	int selectOrdersListToCnt(SearchVO searchVO);

	List<?> selectOrdersList(SearchVO searchVO);

	List<?> selectAccountList();

	List<?> selectCompanyList();

	List<?> selectProductList();

	int selectProdStockChk(Map<String, Object> temp);

	void registEstimate(Map<String, Object> map);

	void registOrders(Map<String, Object> map);

	void registOutSourcing(Map<String, Object> map);

	void registShipment(Map<String, Object> map);

	void registDelivery(Map<String, Object> map);

	void updateItemCnt(Map<String, Object> temp);

	Map<String, Object> selectOrdersInfo(Map<String, Object> map);

	void modifyOrders(Map<String, Object> map);

	void modifyEstimate(Map<String, Object> map);

	void deleteDelivery(Map<String, Object> map);

	void deleteShipment(Map<String, Object> map);

	void deleteOutSourcing(Map<String, Object> map);

	void deleteOrders(Map<String, Object> map);

	void deleteEstimate(Map<String, Object> map);

}
