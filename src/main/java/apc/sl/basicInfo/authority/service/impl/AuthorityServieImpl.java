package apc.sl.basicInfo.authority.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.authority.service.AuthorityService;
import apc.util.SearchVO;
@Service
public class AuthorityServieImpl implements AuthorityService {
	@Resource
	private AuthorityMapper authorityMapper;

	@Override
	public int selectAuthorityToCnt(SearchVO searchVO) {
		return authorityMapper.selectAuthorityToCnt(searchVO);
	}

	@Override
	public List<?> selectAuthorityList(SearchVO searchVO) {
		return authorityMapper.selectAuthorityList(searchVO);
	}

	@Override
	public List<?> selectProgramName() {
		return authorityMapper.selectProgramName();
	}

	@Override
	public void registAuthority(Map<String, Object> map) {
		authorityMapper.registAuthority(map);
	}

	@Override
	public Map<String, Object> selectAuthorityInfo(Map<String, Object> map) {
		return authorityMapper.selectAuthorityInfo(map);
	}

	@Override
	public void modifyAuthority(Map<String, Object> map) {
		authorityMapper.modifyAuthority(map);
	}

	@Override
	public void deleteAuthority(Map<String, Object> map) {
		authorityMapper.deleteAuthority(map);
	}

	@Override
	public int selectAccessLevel(String str) {
		return authorityMapper.selectAccessLevel(str);
	}


}
