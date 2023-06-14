package apc.sl.basicInfo.authority.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface AuthorityService {

	int selectAuthorityToCnt(SearchVO searchVO);

	List<?> selectAuthorityList(SearchVO searchVO);

	List<?> selectProgramName();

	void registAuthority(Map<String, Object> map);

	Map<String, Object> selectAuthorityInfo(Map<String, Object> map);

	void modifyAuthority(Map<String, Object> map);

	void deleteAuthority(Map<String, Object> map);

	int selectAccessLevel(String str);

}
