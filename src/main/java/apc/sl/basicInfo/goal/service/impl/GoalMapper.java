package apc.sl.basicInfo.goal.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("GoalMapper")
public interface GoalMapper {

	int selectGoalToCnt(SearchVO searchVO);

	List<?> selectGoalList(SearchVO searchVO);

}
