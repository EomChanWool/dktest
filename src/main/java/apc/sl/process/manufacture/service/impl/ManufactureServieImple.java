package apc.sl.process.manufacture.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.process.manufacture.service.ManufactureService;
import apc.util.SearchVO;

@Service
public class ManufactureServieImple implements ManufactureService {
	
	@Resource
	private ManufactureMapper manufactureMapper;

	@Override
	public int selectManufactureListToCnt(SearchVO searchVO) {
		return manufactureMapper.selectManufactureListToCnt(searchVO);
	}

	@Override
	public List<?> selectManufactureList(SearchVO searchVO) {
		return manufactureMapper.selectManufactureList(searchVO);
	}

	@Override
	public List<?> selelctEQList() {
		return manufactureMapper.selelctEQList();
	}

	@Override
	public Map<String, Object> selectInfo(Map<String, Object> map) {
		return manufactureMapper.selectInfo(map);
	}

	@Override
	public int selctExistsEq(Map<String, Object> map) {
		return manufactureMapper.selctExistsEq(map);
	}

	@Override
	public void registManufacture(Map<String, Object> map) {
		manufactureMapper.registManufacture(map);
		
	}

	@Override
	public Map<String, Object> selectCheck(Map<String, Object> map) {
		return manufactureMapper.selectCheck(map);
	}

	@Override
	public void modifyManufacture(Map<String, Object> map) {
		manufactureMapper.modifyManufacture(map);
		
	}

	@Override
	public void deleteManufacture(Map<String, Object> map) {
		manufactureMapper.deleteManufacture(map);
	}

	@Override
	public Map<String, Object> selectDetailManufacture(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return manufactureMapper.selectDetailManufacture(map);
	}

	@Override
	public List<?> selectLotnoList() {
		return manufactureMapper.selectLotnoList();
	}

	@Override
	public int selectExistsLot(Map<String, Object> map) {
		return manufactureMapper.selectExistsLot(map);
	}

	@Override
	public Map<String, Object> selectMfInfo(Map<String, Object> map) {
		return manufactureMapper.selectMfInfo(map);
	}

}
