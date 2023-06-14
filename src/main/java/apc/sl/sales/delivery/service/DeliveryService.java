package apc.sl.sales.delivery.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface DeliveryService {

	int selectDeliveryListToCnt(SearchVO searchVO);

	List<?> selectDeliveryList(SearchVO searchVO);

	List<?> selectShipmentList(Map<String, Object> map);

	List<?> shipmentAjax(Map<String, Object> map);

	int selectShipmentIdx(Map<String, Object> map);

	List<?> selectEstimateProdList(Map<String, Object> map);

	int selectExistsShipment(Map<String, Object> map);

	Map<String, Object> selectStockOk(Map<String, Object> map);

	void updateItem(Map<String, Object> tempMap);

	void registDelivery(Map<String, Object> map);

	void updateShipment(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	Map<String, Object> selectDeliveryInfo(Map<String, Object> map);

	void modifyDelivery(Map<String, Object> map);

	void deleteDelivery(Map<String, Object> map);

}
