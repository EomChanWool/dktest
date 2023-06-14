package apc.sl.basicInfo.program.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("ProgramMapper")
public interface ProgramMapper {

	int selectProgramListToCnt(SearchVO searchVO);

	List<?> selectProgramList(SearchVO searchVO);

	void registProgram(Map<String, Object> map);

	Map<String, Object> selectProgramInfo(Map<String, Object> map);

	void modifyProgram(Map<String, Object> map);

	void deleteProgram(Map<String, Object> map);

}
