package apc.sl.material.invest.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.invest.service.InvestService;
import apc.util.SearchVO;
@Service
public class InvestServieImpl implements InvestService {
	@Resource
	private InvestMapper investMapper;

	@Override
	public int selectInvestListToCnt(SearchVO searchVO) {
		return investMapper.selectInvestListToCnt(searchVO);
	}

	@Override
	public List<?> selectInvestList(SearchVO searchVO) {
		return investMapper.selectInvestList(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList() {
		return investMapper.selectWorkOrderList();
	}

	@Override
	public List<?> selectMaterialList() {
		return investMapper.selectMaterialList();
	}

	@Override
	public int selectMaterialStock(Map<String, Object> map) {
		return investMapper.selectMaterialStock(map);
	}

	@Override
	public int selectExistsInsertInfo(Map<String, Object> map) {
		return investMapper.selectExistsInsertInfo(map);
	}

	@Override
	public void registInvest(Map<String, Object> map) {
		investMapper.registInvest(map);
	}

	@Override
	public void updateMaterialStock(Map<String, Object> map) {
		investMapper.updateMaterialStock(map);
	}

	@Override
	public void updateWorkOrder(Map<String, Object> map) {
		investMapper.updateWorkOrder(map);
	}

	@Override
	public void updateOrders(Map<String, Object> map) {
		investMapper.updateOrders(map);
	}
	
	@Override
	public int selectInvestWorkOrder(Map<String, Object> map) {
		return investMapper.selectInvestWorkOrder(map);
	}

	@Override
	public Map<String, Object> selectInvestInfo(Map<String, Object> map) {
		return investMapper.selectInvestInfo(map);
	}

	@Override
	public void modifyInvest(Map<String, Object> map) {
		investMapper.modifyInvest(map);
	}

	@Override
	public void deleteInvest(Map<String, Object> map) {
		investMapper.deleteInvest(map);
	}

	@Override
	public List<?> selectItemInfo(Map<String, Object> map) {
		return investMapper.selectItemInfo(map);
	}

	@Override
	public Map<String, Object> selectInMaterialsInfo(Map<String, Object> map) {
		return investMapper.selectInMaterialsInfo(map);
	}

	@Override
	public void updateInMaterial(Map<String, Object> map) {
		investMapper.updateInMaterial(map);
	}

}
