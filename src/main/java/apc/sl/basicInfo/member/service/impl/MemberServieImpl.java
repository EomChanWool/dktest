package apc.sl.basicInfo.member.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.member.service.MemberService;
import apc.util.SearchVO;
@Service
public class MemberServieImpl implements MemberService {
	@Resource
	private MemberMapper memberMapper;

	@Override
	public Map<String, Object> selectMember(Map<String, Object> map) {
		return memberMapper.selectMember(map);
	}

	@Override
	public int selectMemberListToCnt(SearchVO searchVO) {
		return memberMapper.selectMemberListToCnt(searchVO);
	}

	@Override
	public List<?> selectMemberList(SearchVO searchVO) {
		return memberMapper.selectMemberList(searchVO);
	}

	@Override
	public String selectId(Map<String, Object> map) {
		return memberMapper.selectId(map);
	}

	@Override
	public void registMemberOk(Map<String, Object> map) {
		memberMapper.registMemberOk(map);
	}

	@Override
	public Map<String, Object> selectMemberInfo(Map<String, Object> map) {
		return memberMapper.selectMemberInfo(map);
	}

	@Override
	public void modifyMember(Map<String, Object> map) {
		memberMapper.modifyMember(map);
	}

	@Override
	public void deleteMember(Map<String, Object> map) {
		memberMapper.deleteMember(map);
	}

	@Override
	public void insertSystemLog(Map<String, Object> member) {
		memberMapper.insertSystemLog(member);
	}

	@Override
	public List<?> selectMenuLevel() {
		return memberMapper.selectMenuLevel();
	}

}
