package apc.sl.facility.facMaster.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.facility.facMaster.service.FacMasterService;
import apc.util.SearchVO;
@Service
public class FacMasterServieImpl implements FacMasterService {
	@Resource
	private FacMasterMapper facMasterMapper;

	@Override
	public int selectFacMasterListToCnt(SearchVO searchVO) {
		return facMasterMapper.selectFacMasterListToCnt(searchVO);
	}

	@Override
	public List<?> selectFacMasterList(SearchVO searchVO) {
		return facMasterMapper.selectFacMasterList(searchVO);
	}

	@Override
	public List<?> selectMaterialList() {
		return facMasterMapper.selectMaterialList();
	}

	@Override
	public List<?> selectProductList() {
		return facMasterMapper.selectProductList();
	}

	@Override
	public int selectOrderIdx(Map<String, Object> map) {
		return facMasterMapper.selectOrderIdx(map);
	}

	@Override
	public int selectItemCode(Map<String, Object> map) {
		return facMasterMapper.selectItemCode(map);
	}

	@Override
	public void registFacMaster(Map<String, Object> map) {
		facMasterMapper.registFacMaster(map);
	}

	@Override
	public List<?> selectProcessList(Map<String, Object> map) {
		return facMasterMapper.selectProcessList(map);
	}

	@Override
	public void registProcess(Map<String, Object> map) {
		facMasterMapper.registProcess(map);
	}

	@Override
	public void updateOrder(Map<String, Object> map) {
		facMasterMapper.updateOrder(map);
	}

	@Override
	public Map<String, Object> selectFacMasterInfo(Map<String, Object> map) {
		return facMasterMapper.selectFacMasterInfo(map);
	}

	@Override
	public void modifyFacMaster(Map<String, Object> map) {
		facMasterMapper.modifyFacMaster(map);
	}

	@Override
	public void deleteFacMaster(Map<String, Object> map) {
		facMasterMapper.deleteFacMaster(map);
	}

	@Override
	public Map<String, Object> selectMaterialStd(Map<String, Object> map) {
		return facMasterMapper.selectMaterialStd(map);
	}

	@Override
	public void registInMaterial(Map<String, Object> map) {
		facMasterMapper.registInMaterial(map);
	}

	@Override
	public Map<String, Object> selectInMaterialList(Map<String, Object> map) {
		return facMasterMapper.selectInMaterialList(map);
	}

	@Override
	public void modifyInMaterial(Map<String, Object> map) {
		facMasterMapper.modifyInMaterial(map);
	}

	@Override
	public void deleteInMaterial(Map<String, Object> map) {
		facMasterMapper.deleteInMaterial(map);
	}

	@Override
	public int selectExistsInMaterial(Map<String, Object> map) {
		return facMasterMapper.selectExistsInMaterial(map);
	}

	@Override
	public void deleteProcess(Map<String, Object> map) {
		facMasterMapper.deleteProcess(map);
	}

	@Override
	public void registInsertInfo(Map<String, Object> map) {
		facMasterMapper.registInsertInfo(map);
	}

	@Override
	public String selectItemStd(String str) {
		return facMasterMapper.selectItemStd(str);
	}

	@Override
	public void deleteInsertInfo(Map<String, Object> map) {
		facMasterMapper.deleteInsertInfo(map);
	}

	@Override
	public int checkItemStock(Map<String, Object> temp) {
		return facMasterMapper.checkItemStock(temp);
	}

	@Override
	public void updateMaterialStock(Map<String, Object> temp) {
		facMasterMapper.updateMaterialStock(temp);
	}

}
