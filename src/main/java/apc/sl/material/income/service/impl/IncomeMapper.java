package apc.sl.material.income.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("IncomeMapper")
public interface IncomeMapper {

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
