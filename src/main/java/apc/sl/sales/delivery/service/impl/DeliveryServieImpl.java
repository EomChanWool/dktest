package apc.sl.sales.delivery.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.sales.delivery.service.DeliveryService;
import apc.util.SearchVO;
@Service
public class DeliveryServieImpl implements DeliveryService {
	@Resource
	private DeliveryMapper deliveryMapper;

	@Override
	public int selectDeliveryListToCnt(SearchVO searchVO) {
		return deliveryMapper.selectDeliveryListToCnt(searchVO);
	}

	@Override
	public List<?> selectDeliveryList(SearchVO searchVO) {
		return deliveryMapper.selectDeliveryList(searchVO);
	}

	@Override
	public List<?> selectShipmentList(Map<String, Object> map) {
		return deliveryMapper.selectShipmentList(map);
	}

	@Override
	public List<?> shipmentAjax(Map<String, Object> map) {
		return deliveryMapper.shipmentAjax(map);
	}

	@Override
	public int selectShipmentIdx(Map<String, Object> map) {
		return deliveryMapper.selectShipmentIdx(map);
	}

	@Override
	public List<?> selectEstimateProdList(Map<String, Object> map) {
		return deliveryMapper.selectEstimateProdList(map);
	}

	@Override
	public int selectExistsShipment(Map<String, Object> map) {
		return deliveryMapper.selectExistsShipment(map);
	}

	@Override
	public Map<String, Object> selectStockOk(Map<String, Object> map) {
		return deliveryMapper.selectStockOk(map);
	}

	@Override
	public void updateItem(Map<String, Object> tempMap) {
		deliveryMapper.updateItem(tempMap);
	}

	@Override
	public void registDelivery(Map<String, Object> map) {
		deliveryMapper.registDelivery(map);
	}

	@Override
	public void updateShipment(Map<String, Object> map) {
		deliveryMapper.updateShipment(map);
	}

	@Override
	public void updateOrders(Map<String, Object> map) {
		deliveryMapper.updateOrders(map);
	}

	@Override
	public Map<String, Object> selectDeliveryInfo(Map<String, Object> map) {
		return deliveryMapper.selectDeliveryInfo(map);
	}

	@Override
	public void modifyDelivery(Map<String, Object> map) {
		deliveryMapper.modifyDelivery(map);
	}

	@Override
	public void deleteDelivery(Map<String, Object> map) {
		deliveryMapper.deleteDelivery(map);
	}

}
