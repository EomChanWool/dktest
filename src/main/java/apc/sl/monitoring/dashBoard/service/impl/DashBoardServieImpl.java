package apc.sl.monitoring.dashBoard.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.monitoring.dashBoard.service.DashBoardService;
import apc.util.SearchVO;
@Service
public class DashBoardServieImpl implements DashBoardService {
	@Resource
	private DashBoardMapper dashBoardMapper;

	@Override
	public List<?> selectNoticeList(SearchVO searchVO) {
		return dashBoardMapper.selectNoticeList(searchVO);
	}

	@Override
	public Map<String, Object> selectNoticeInfo(Map<String, Object> map) {
		return dashBoardMapper.selectNoticeInfo(map);
	}

	@Override
	public List<?> selectItemList() {
		return dashBoardMapper.selectItemList();
	}

}
