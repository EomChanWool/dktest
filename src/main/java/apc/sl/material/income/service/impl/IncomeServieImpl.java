package apc.sl.material.income.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.material.income.service.IncomeService;
import apc.util.SearchVO;
@Service
public class IncomeServieImpl implements IncomeService {
	@Resource
	private IncomeMapper incomeMapper;

	@Override
	public int selectIncomeListToCnt(SearchVO searchVO) {
		return incomeMapper.selectIncomeListToCnt(searchVO);
	}

	@Override
	public List<?> selectIncomeList(SearchVO searchVO) {
		return incomeMapper.selectIncomeList(searchVO);
	}

	@Override
	public List<?> selectAccountList() {
		return incomeMapper.selectAccountList();
	}

	@Override
	public List<?> selectMaterialList() {
		return incomeMapper.selectMaterialList();
	}

	@Override
	public List<?> selectItemInfo(Map<String, Object> map) {
		return incomeMapper.selectItemInfo(map);
	}

	@Override
	public void registIncome(Map<String, Object> map) {
		incomeMapper.registIncome(map);
	}

	@Override
	public void updateMaterialCnt(Map<String, Object> map) {
		incomeMapper.updateMaterialCnt(map);
	}
	
	@Override
	public Map<String, Object> selectIncomeInfo(Map<String, Object> map) {
		return incomeMapper.selectIncomeInfo(map);
	}

	@Override
	public void modifyIncome(Map<String, Object> map) {
		incomeMapper.modifyIncome(map);
	}

	@Override
	public void deleteIncome(Map<String, Object> map) {
		incomeMapper.deleteIncome(map);
	}

}
