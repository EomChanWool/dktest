package apc.sl.monitoring.actualOutput.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import apc.sl.monitoring.actualOutput.service.ActualOutputService;
import apc.util.SearchVO;

@Controller
public class ActualOutputController {
	@Autowired
	private ActualOutputService actualOutputService;
	
	@RequestMapping("/sl/monitoring/actualOutput/actualOutput.do")
	public String actualOutput(ModelMap model, HttpSession session) {
		
		List<?> prodCntList = actualOutputService.selectProdCnt();
		model.put("prodCntList",prodCntList);
		//일일 프레스/벤딩 생산 실적
//		model.put("pressBendingData", pressBending());
		//일일 PLC데이터
//		model.put("plcData", plcData());
		
		
		
		return "sl/monitoring/actualOutput/actualOutput";
	}
//	
//	public Map<String, Object> pressBending() {
//		Map<String, Object> map = new HashMap<>();
//		map.put("processNm", "프레스성형/벤딩");
//		Map<String, Object> pressBendingData = actualOutputService.selectPressBendingCnt(map);
//		if(pressBendingData == null) {
//			pressBendingData = new HashMap<>();
//			pressBendingData.put("prReCnt", 0);
//		}
//		return pressBendingData;
//	}
//	
//	public Map<String, Object> plcData(){
//		Map<String, Object> plc = actualOutputService.selectPlcCnt();
//		if(plc == null) {
//			plc = new HashMap<>();
//			plc.put("plCnt", 0);
//		}
//		return plc;
//	}
}
