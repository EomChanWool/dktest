package apc.sl.basicInfo.company.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CompanyMapper")
public interface CompanyMapper {

	int selectCompanyListToCnt(SearchVO searchVO);
	
	List<?> selectCompanyList(SearchVO searchVO);

	void registCompany(Map<String, Object> map);

	Map<String, Object> selectCompanyInfo(Map<String, Object> map);

	void modifyCompany(Map<String, Object> map);

	void deleteCompany(Map<String, Object> map);

}
