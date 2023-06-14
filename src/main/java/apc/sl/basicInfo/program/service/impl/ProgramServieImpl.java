package apc.sl.basicInfo.program.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.basicInfo.program.service.ProgramService;
import apc.util.SearchVO;
@Service
public class ProgramServieImpl implements ProgramService {
	@Resource
	private ProgramMapper programMapper;

	@Override
	public int selectProgramListToCnt(SearchVO searchVO) {
		return programMapper.selectProgramListToCnt(searchVO);
	}

	@Override
	public List<?> selectProgramList(SearchVO searchVO) {
		return programMapper.selectProgramList(searchVO);
	}

	@Override
	public void registProgram(Map<String, Object> map) {
		programMapper.registProgram(map);
	}

	@Override
	public Map<String, Object> selectProgramInfo(Map<String, Object> map) {
		return programMapper.selectProgramInfo(map);
	}

	@Override
	public void modifyProgram(Map<String, Object> map) {
		programMapper.modifyProgram(map);
	}

	@Override
	public void deleteProgram(Map<String, Object> map) {
		programMapper.deleteProgram(map);
	}

}
