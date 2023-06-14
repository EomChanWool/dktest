package apc.sl.monitoring.actualOutput.service;

import java.util.List;
import java.util.Map;

public interface ActualOutputService {

	Map<String, Object> selectPressBendingCnt(Map<String, Object> map);

	Map<String, Object> selectPlcCnt();

	List<?> selectProdCnt();

}
