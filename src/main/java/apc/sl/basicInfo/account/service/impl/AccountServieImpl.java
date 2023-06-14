package apc.sl.basicInfo.account.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.account.service.AccountService;
import apc.util.SearchVO;
@Service
public class AccountServieImpl implements AccountService {
	@Resource
	private AccountMapper accountMapper;

	@Override
	public int selectAccountListToCnt(SearchVO searchVO) {
		return accountMapper.selectAccountListToCnt(searchVO);
	}

	@Override
	public List<?> selectAccountList(SearchVO searchVO) {
		return accountMapper.selectAccountList(searchVO);
	}

	@Override
	public void registAccount(Map<String, Object> map) {
		accountMapper.registAccount(map);
	}

	@Override
	public Map<String, Object> selectAccountInfo(Map<String, Object> map) {
		return accountMapper.selectAccountInfo(map);
	}

	@Override
	public void modifyAccount(Map<String, Object> map) {
		accountMapper.modifyAccount(map);
	}

	@Override
	public void deleteAccount(Map<String, Object> map) {
		accountMapper.deleteAccount(map);
	}
}
