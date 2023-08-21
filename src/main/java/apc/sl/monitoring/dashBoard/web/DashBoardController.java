package apc.sl.monitoring.dashBoard.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apc.sl.monitoring.actualOutput.service.ActualOutputService;
import apc.sl.monitoring.dashBoard.service.DashBoardService;
import apc.sl.monitoring.lineRunning.service.LineRunningService;
import apc.sl.monitoring.ordersOutput.service.OrdersOutputService;
import apc.util.SearchVO;

@Controller
public class DashBoardController {
	@Autowired
	private DashBoardService dashBoardService;
	@Autowired
	private OrdersOutputService ordersOutputService;
	@Autowired
	private ActualOutputService actualOutputService;
	
	@Autowired
	private LineRunningService lineRunningService;
	
	@RequestMapping("/sl/monitoring/dashBoard.do")
	public String dashBoardList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		
		
		String str1 = dashBoardService.selectYear1().get("setYear")+"";
		model.put("date", str1);
		//수주량(제품 종류 상관없이 총 제품 수주량)
		List<?> orderCntList = dashBoardService.selectOrdersCnt(str1);
		model.put("orderCntList", orderCntList);
		//생산량(제품별 생산량)
		List<?> prodCntList = dashBoardService.selectProdCnt(str1);
		model.put("prodCntList", prodCntList);
		
		
		//라인
		String str2 = dashBoardService.selectYear2().get("setYear")+"";
		model.put("date2", str2);
		List<?> list = dashBoardService.selectLineYear(str2);
		model.put("eqList", list);
		
		//생산집계
		String str3 = dashBoardService.selectYear3().get("setYear")+"";
		model.put("date3", str3);
		List<?> prodCntListAc = dashBoardService.selectProdCntAc(str3);
		model.put("prodCntListAc",prodCntListAc);
		

		
		return "sl/monitoring/dashBoard/dashBoard";
	}
	
	@RequestMapping("/sl/monitoring/detailNotice.do")
	public String detailNotice(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = dashBoardService.selectNoticeInfo(map);
		model.put("noticeVO", detail);
		return "sl/monitoring/notice/detailNotice";
	}
}
