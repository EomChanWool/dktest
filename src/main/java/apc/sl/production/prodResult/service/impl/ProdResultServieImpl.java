package apc.sl.production.prodResult.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.production.prodResult.service.ProdResultService;
import apc.util.SearchVO;
@Service
public class ProdResultServieImpl implements ProdResultService {
	@Resource
	private ProdResultMapper prodResultMapper;

	@Override
	public int selectProdResultListToCnt(SearchVO searchVO) {
		return prodResultMapper.selectProdResultListToCnt(searchVO);
	}

	@Override
	public List<?> selectProdResultList(SearchVO searchVO) {
		return prodResultMapper.selectProdResultList(searchVO);
	}

	@Override
	public List<?> selectWorkOrderList() {
		return prodResultMapper.selectWorkOrderList();
	}

	@Override
	public List<?> selectWorkOrderInfo(Map<String, Object> map) {
		return prodResultMapper.selectWorkOrderInfo(map);
	}

	@Override
	public int selectExistsWorkOrderIdx(Map<String, Object> map) {
		return prodResultMapper.selectExistsWorkOrderIdx(map);
	}

	@Override
	public int selectExistsProdResult(Map<String, Object> map) {
		return prodResultMapper.selectExistsProdResult(map);
	}

	@Override
	public void registProdResult(Map<String, Object> map) {
		prodResultMapper.registProdResult(map);
	}

	@Override
	public Map<String, Object> selectProcessSeqInfo(Map<String, Object> map) {
		return prodResultMapper.selectProcessSeqInfo(map);
	}

	@Override
	public void updateProcess(Map<String, Object> map) {
		prodResultMapper.updateProcess(map);
	}

	@Override
	public Map<String, Object> selectProdResultInfo(Map<String, Object> map) {
		return prodResultMapper.selectProdResultInfo(map);
	}

	@Override
	public void modifyProdResult(Map<String, Object> map) {
		prodResultMapper.modifyProdResult(map);
	}

	@Override
	public String selectLastProcessNm(Map<String, Object> map) {
		return prodResultMapper.selectLastProcessNm(map);
	}

	@Override
	public void updateWorkOrder(Map<String, Object> map) {
		prodResultMapper.updateWorkOrder(map);
	}

	@Override
	public void deleteProdResult(Map<String, Object> map) {
		prodResultMapper.deleteProdResult(map);
	}

	@Override
	public void updateOrders(Map<String, Object> map) {
		prodResultMapper.updateOrders(map);
	}

	@Override
	public void addItemStock(Map<String, Object> map) {
		prodResultMapper.addItemStock(map);
	}

	@Override
	public String selectItemCd(Map<String, Object> map) {
		return prodResultMapper.selectItemCd(map);
	}

	@Override
	public Map<String, Object> selectMaterialList(Map<String, Object> map) {
		return prodResultMapper.selectMaterialList(map);
	}

	@Override
	public void updateInMaterial(Map<String, Object> temp) {
		prodResultMapper.updateInMaterial(temp);
	}

	@Override
	public void updateMaterialStock(Map<String, Object> temp2) {
		prodResultMapper.updateMaterialStock(temp2);
	}

}
