package apc.sl.quality.inst.service;

import java.util.List;
import java.util.Map;

import apc.util.SearchVO;

public interface InstService {

	int selectInstListToCnt(SearchVO searchVO);

	List<?> selectInstList(SearchVO searchVO);

	void registDocument(Map<String, Object> map);

	Map<String, Object> selectDocumentInfo(Map<String, Object> map);

	void modifyDocument(Map<String, Object> map);

	void deleteInst(Map<String, Object> map);
}
