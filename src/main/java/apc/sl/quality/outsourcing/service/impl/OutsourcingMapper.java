package apc.sl.quality.outsourcing.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("OutsourcingMapper")
public interface OutsourcingMapper {

	int selectOutsourcingListToCnt(SearchVO searchVO);

	List<?> selectOutsourcingList(SearchVO searchVO);

	List<?> selectOrdersList();

	List<?> selectOrdersInfo(Map<String, Object> map);

	int selectExistsOrIdx(Map<String, Object> map);

	int selectExistsShipment(Map<String, Object> map);

	void registOutSourcing(Map<String, Object> map);

	void updateOrders(Map<String, Object> map);

	Map<String, Object> selectOutsourcingInfo(Map<String, Object> map);

	void modifyOutsourcing(Map<String, Object> map);

	void deleteOutsourcing(Map<String, Object> map);

}
