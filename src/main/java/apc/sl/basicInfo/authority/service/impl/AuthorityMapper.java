package apc.sl.basicInfo.authority.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("AuthorityMapper")
public interface AuthorityMapper {

	int selectAuthorityToCnt(SearchVO searchVO);

	List<?> selectAuthorityList(SearchVO searchVO);

	List<?> selectProgramName();

	void registAuthority(Map<String, Object> map);

	Map<String, Object> selectAuthorityInfo(Map<String, Object> map);

	void modifyAuthority(Map<String, Object> map);

	void deleteAuthority(Map<String, Object> map);

	int selectAccessLevel(String str);

}
