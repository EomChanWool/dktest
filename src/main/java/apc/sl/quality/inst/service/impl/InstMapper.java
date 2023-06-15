package apc.sl.quality.inst.service.impl;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("InstMapper")
public interface InstMapper {

	int selectInstListToCnt(SearchVO searchVO);

	List<?> selectInstList(SearchVO searchVO);

	void registDocument(Map<String, Object> map);

	Map<String, Object> selectDocumentInfo(Map<String, Object> map);

	void modifyDocument(Map<String, Object> map);

	void deleteInst(Map<String, Object> map);

}
