package apc.sl.basicInfo.program.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface ProgramService {

	int selectProgramListToCnt(SearchVO searchVO);

	List<?> selectProgramList(SearchVO searchVO);

	void registProgram(Map<String, Object> map);

	Map<String, Object> selectProgramInfo(Map<String, Object> map);

	void modifyProgram(Map<String, Object> map);

	void deleteProgram(Map<String, Object> map);

}
