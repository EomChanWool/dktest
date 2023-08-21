package apc.sl.monitoring.monitorSetting.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("MonitorSettingMapper")
public interface MonitorSettingMapper {
	List<?> selectSetting();
	
	void settingGo(Map<String,Object> map);
}
