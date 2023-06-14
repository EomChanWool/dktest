package apc.sl.sales.orders.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.sales.orders.service.OrdersService;
import apc.util.SearchVO;
@Service
public class OrdersServieImpl implements OrdersService {
	@Resource
	private OrdersMapper ordersMapper;

	@Override
	public int selectOrdersListToCnt(SearchVO searchVO) {
		return ordersMapper.selectOrdersListToCnt(searchVO);
	}

	@Override
	public List<?> selectOrdersList(SearchVO searchVO) {
		return ordersMapper.selectOrdersList(searchVO);
	}

	@Override
	public List<?> selectAccountList() {
		return ordersMapper.selectAccountList();
	}

	@Override
	public List<?> selectCompanyList() {
		return ordersMapper.selectCompanyList();
	}

	@Override
	public List<?> selectProductList() {
		return ordersMapper.selectProductList();
	}

	@Override
	public int selectProdStockChk(Map<String, Object> temp) {
		return ordersMapper.selectProdStockChk(temp);
	}

	@Override
	public void registEstimate(Map<String, Object> map) {
		ordersMapper.registEstimate(map);
	}

	@Override
	public void registOrders(Map<String, Object> map) {
		ordersMapper.registOrders(map);
	}

	@Override
	public void registOutSourcing(Map<String, Object> map) {
		ordersMapper.registOutSourcing(map);
	}

	@Override
	public void registShipment(Map<String, Object> map) {
		ordersMapper.registShipment(map);
	}

	@Override
	public void registDelivery(Map<String, Object> map) {
		ordersMapper.registDelivery(map);
	}

	@Override
	public void updateItemCnt(Map<String, Object> temp) {
		ordersMapper.updateItemCnt(temp);
	}

	@Override
	public Map<String, Object> selectOrdersInfo(Map<String, Object> map) {
		return ordersMapper.selectOrdersInfo(map);
	}

	@Override
	public void modifyOrders(Map<String, Object> map) {
		ordersMapper.modifyOrders(map);
	}

	@Override
	public void modifyEstimate(Map<String, Object> map) {
		ordersMapper.modifyEstimate(map);
	}

	@Override
	public void deleteDelivery(Map<String, Object> map) {
		ordersMapper.deleteDelivery(map);
	}

	@Override
	public void deleteShipment(Map<String, Object> map) {
		ordersMapper.deleteShipment(map);
	}

	@Override
	public void deleteOutSourcing(Map<String, Object> map) {
		ordersMapper.deleteOutSourcing(map);
	}

	@Override
	public void deleteOrders(Map<String, Object> map) {
		ordersMapper.deleteOrders(map);
	}

	@Override
	public void deleteEstimate(Map<String, Object> map) {
		ordersMapper.deleteEstimate(map);
	}

}
