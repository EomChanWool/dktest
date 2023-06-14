package apc.sl.production.workOrder.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.workOrder.service.WorkOrderService;
import apc.util.SearchVO;
@Service
public class WorkOrderServieImpl implements WorkOrderService {
	@Resource
	private WorkOrderMapper workOrderMapper;

	@Override
	public int selectWorkOrderListToCnt(SearchVO searchVO) {
		return workOrderMapper.selectWorkOrderListToCnt(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList(SearchVO searchVO) {
		return workOrderMapper.selectWorkOrderList(searchVO);
	}

	@Override
	public List<?> selectMaterialList() {
		return workOrderMapper.selectMaterialList();
	}

	@Override
	public List<?> selectProductList() {
		return workOrderMapper.selectProductList();
	}

	@Override
	public int selectOrderIdx(Map<String, Object> map) {
		return workOrderMapper.selectOrderIdx(map);
	}

	@Override
	public int selectItemCode(Map<String, Object> map) {
		return workOrderMapper.selectItemCode(map);
	}

	@Override
	public void registWorkOrder(Map<String, Object> map) {
		workOrderMapper.registWorkOrder(map);
	}

	@Override
	public List<?> selectProcessList(Map<String, Object> map) {
		return workOrderMapper.selectProcessList(map);
	}

	@Override
	public void registProcess(Map<String, Object> map) {
		workOrderMapper.registProcess(map);
	}

	@Override
	public void updateOrder(Map<String, Object> map) {
		workOrderMapper.updateOrder(map);
	}

	@Override
	public Map<String, Object> selectWorkOrderInfo(Map<String, Object> map) {
		return workOrderMapper.selectWorkOrderInfo(map);
	}

	@Override
	public void modifyWorkOrder(Map<String, Object> map) {
		workOrderMapper.modifyWorkOrder(map);
	}

	@Override
	public void deleteWorkOrder(Map<String, Object> map) {
		workOrderMapper.deleteWorkOrder(map);
	}

	@Override
	public Map<String, Object> selectMaterialStd(Map<String, Object> map) {
		return workOrderMapper.selectMaterialStd(map);
	}

	@Override
	public void registInMaterial(Map<String, Object> map) {
		workOrderMapper.registInMaterial(map);
	}

	@Override
	public Map<String, Object> selectInMaterialList(Map<String, Object> map) {
		return workOrderMapper.selectInMaterialList(map);
	}

	@Override
	public void modifyInMaterial(Map<String, Object> map) {
		workOrderMapper.modifyInMaterial(map);
	}

	@Override
	public void deleteInMaterial(Map<String, Object> map) {
		workOrderMapper.deleteInMaterial(map);
	}

	@Override
	public int selectExistsInMaterial(Map<String, Object> map) {
		return workOrderMapper.selectExistsInMaterial(map);
	}

	@Override
	public void deleteProcess(Map<String, Object> map) {
		workOrderMapper.deleteProcess(map);
	}

	@Override
	public void registInsertInfo(Map<String, Object> map) {
		workOrderMapper.registInsertInfo(map);
	}

	@Override
	public String selectItemStd(String str) {
		return workOrderMapper.selectItemStd(str);
	}

	@Override
	public void deleteInsertInfo(Map<String, Object> map) {
		workOrderMapper.deleteInsertInfo(map);
	}

	@Override
	public int checkItemStock(Map<String, Object> temp) {
		return workOrderMapper.checkItemStock(temp);
	}

	@Override
	public void updateMaterialStock(Map<String, Object> temp) {
		workOrderMapper.updateMaterialStock(temp);
	}

}
