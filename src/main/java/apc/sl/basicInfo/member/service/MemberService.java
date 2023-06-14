package apc.sl.basicInfo.member.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface MemberService {

	Map<String, Object> selectMember(Map<String, Object> map);

	int selectMemberListToCnt(SearchVO searchVO);

	List<?> selectMemberList(SearchVO searchVO);

	String selectId(Map<String, Object> map);

	void registMemberOk(Map<String, Object> map);

	Map<String, Object> selectMemberInfo(Map<String, Object> map);

	void modifyMember(Map<String, Object> map);

	void deleteMember(Map<String, Object> map);

	void insertSystemLog(Map<String, Object> member);

	List<?> selectMenuLevel();

}
