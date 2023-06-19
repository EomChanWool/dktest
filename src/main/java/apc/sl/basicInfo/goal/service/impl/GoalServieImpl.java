package apc.sl.basicInfo.goal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.goal.service.GoalService;
import apc.util.SearchVO;
@Service
public class GoalServieImpl implements GoalService {
	@Resource
	private GoalMapper goalMapper;

	@Override
	public int selectGoalToCnt(SearchVO searchVO) {
		return goalMapper.selectGoalToCnt(searchVO);
	}

	@Override
	public List<?> selectGoalList(SearchVO searchVO) {
		return goalMapper.selectGoalList(searchVO);
	}

}
