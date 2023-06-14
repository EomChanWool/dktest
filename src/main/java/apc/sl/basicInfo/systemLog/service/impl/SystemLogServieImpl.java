package apc.sl.basicInfo.systemLog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.systemLog.service.SystemLogService;
import apc.util.SearchVO;
@Service
public class SystemLogServieImpl implements SystemLogService {
	@Resource
	private SystemLogMapper systemLogMapper;

	@Override
	public int selectSystemLogToCnt(SearchVO searchVO) {
		return systemLogMapper.selectSystemLogToCnt(searchVO);
	}

	@Override
	public List<?> selectSystemLogList(SearchVO searchVO) {
		return systemLogMapper.selectSystemLogList(searchVO);
	}

}
