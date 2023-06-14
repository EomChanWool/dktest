package apc.sl.basicInfo.account.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AccountMapper")
public interface AccountMapper {

	int selectAccountListToCnt(SearchVO searchVO);

	List<?> selectAccountList(SearchVO searchVO);

	void registAccount(Map<String, Object> map);

	Map<String, Object> selectAccountInfo(Map<String, Object> map);

	void modifyAccount(Map<String, Object> map);

	void deleteAccount(Map<String, Object> map);

}
