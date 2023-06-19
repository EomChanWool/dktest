package apc.sl.basicInfo.goal.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface GoalService {

	int selectGoalToCnt(SearchVO searchVO);

	List<?> selectGoalList(SearchVO searchVO);

}
