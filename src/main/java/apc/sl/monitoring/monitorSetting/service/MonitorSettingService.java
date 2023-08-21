package apc.sl.monitoring.monitorSetting.service;

import java.util.List;
import java.util.Map;

public interface MonitorSettingService {

	List<?> selectSetting();
	
	void settingGo(Map<String,Object> map);
	
}
