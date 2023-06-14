package apc.sl.basicInfo.company.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.company.service.CompanyService;
import apc.util.SearchVO;
@Service
public class CompanyServieImpl implements CompanyService {
	@Resource
	private CompanyMapper companyMapper;

	@Override
	public int selectCompanyListToCnt(SearchVO searchVO) {
		return companyMapper.selectCompanyListToCnt(searchVO);
	}
	
	@Override
	public List<?> selectCompanyList(SearchVO searchVO) {
		return companyMapper.selectCompanyList(searchVO);
	}

	@Override
	public void registCompany(Map<String, Object> map) {
		companyMapper.registCompany(map);
	}

	@Override
	public Map<String, Object> selectCompanyInfo(Map<String, Object> map) {
		return companyMapper.selectCompanyInfo(map);
	}

	@Override
	public void modifyCompany(Map<String, Object> map) {
		companyMapper.modifyCompany(map);
	}

	@Override
	public void deleteCompany(Map<String, Object> map) {
		companyMapper.deleteCompany(map);
	}
}
