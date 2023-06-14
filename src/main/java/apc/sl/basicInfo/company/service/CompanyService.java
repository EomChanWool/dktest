package apc.sl.basicInfo.company.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface CompanyService {

	int selectCompanyListToCnt(SearchVO searchVO);
	
	List<?> selectCompanyList(SearchVO searchVO);

	void registCompany(Map<String, Object> map);

	Map<String, Object> selectCompanyInfo(Map<String, Object> map);

	void modifyCompany(Map<String, Object> map);

	void deleteCompany(Map<String, Object> map);

}
