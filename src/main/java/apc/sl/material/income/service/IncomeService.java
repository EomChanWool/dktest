package apc.sl.material.income.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface IncomeService {

	int selectIncomeListToCnt(SearchVO searchVO);

	List<?> selectIncomeList(SearchVO searchVO);

	List<?> selectAccountList();

	List<?> selectMaterialList();

	List<?> selectItemInfo(Map<String, Object> map);

	void registIncome(Map<String, Object> map);

	void updateMaterialCnt(Map<String, Object> map);

	Map<String, Object> selectIncomeInfo(Map<String, Object> map);

	void modifyIncome(Map<String, Object> map);

	void deleteIncome(Map<String, Object> map);

}
