package apc.sl.sales.collectMoney.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import apc.sl.sales.collectMoney.service.CollectMoneyService;
import apc.util.SearchVO;
@Service
public class CollectMoneyServieImpl implements CollectMoneyService {
	@Resource
	private CollectMoneyMapper collectMoneyMapper;

	@Override
	public int selectCollectMoneyListToCnt(SearchVO searchVO) {
		return collectMoneyMapper.selectCollectMoneyListToCnt(searchVO);
	}

	@Override
	public List<?> selectCollectMoneyList(SearchVO searchVO) {
		return collectMoneyMapper.selectCollectMoneyList(searchVO);
	}

	@Override
	public List<?> selectDeliveryList() {
		return collectMoneyMapper.selectDeliveryList();
	}

	@Override
	public List<?> selectDeliveryInfo(Map<String, Object> map) {
		return collectMoneyMapper.selectDeliveryInfo(map);
	}

	@Override
	public int selectExistsDeliveryIdx(Map<String, Object> map) {
		return collectMoneyMapper.selectExistsDeliveryIdx(map);
	}

	@Override
	public int selectAlreadyRegistDeIdx(Map<String, Object> map) {
		return collectMoneyMapper.selectAlreadyRegistDeIdx(map);
	}

	@Override
	public void registCollectMoney(Map<String, Object> map) {
		collectMoneyMapper.registCollectMoney(map);
	}

	@Override
	public Map<String, Object> selectCollectInfo(Map<String, Object> map) {
		return collectMoneyMapper.selectCollectInfo(map);
	}

	@Override
	public void modifyCollectMoney(Map<String, Object> map) {
		collectMoneyMapper.modifyCollectMoney(map);
	}

	@Override
	public void updateDelivery(Map<String, Object> map) {
		collectMoneyMapper.updateDelivery(map);
	}

	@Override
	public void deleteCollect(Map<String, Object> map) {
		collectMoneyMapper.deleteCollect(map);
	}

}
