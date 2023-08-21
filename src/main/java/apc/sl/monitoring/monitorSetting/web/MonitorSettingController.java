package apc.sl.monitoring.monitorSetting.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.monitoring.monitorSetting.service.MonitorSettingService;

@Controller
public class MonitorSettingController {

	@Autowired MonitorSettingService monitorSettingService;
	
	@RequestMapping("/sl/monitoring/monitorSetting/monitorSettingList.do")
	public String monitorSettingList(ModelMap model, HttpSession session) {
		
		List<?> list = monitorSettingService.selectSetting();
		
		model.put("msList", list);
		
		return "sl/monitoring/monitorSetting/monitorSetting";
	}
	
	@RequestMapping("/sl/monitoring/monitorSetting/monitorSettingGo.do")
	public String monitorSettingGo(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		System.out.println("ㅁㅐㅂ : " + map);
		
		monitorSettingService.settingGo(map);
		System.out.println("확인");
		redirectAttributes.addFlashAttribute("msg","적용 되었습니다.");
		
		return "redirect:/sl/monitoring/monitorSetting/monitorSettingList.do";
	}
	
}
