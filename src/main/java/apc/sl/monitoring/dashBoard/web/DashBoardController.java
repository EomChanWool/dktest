package apc.sl.monitoring.dashBoard.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apc.sl.monitoring.actualOutput.service.ActualOutputService;
import apc.sl.monitoring.dashBoard.service.DashBoardService;
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
	
	@RequestMapping("/sl/monitoring/dashBoard.do")
	public String dashBoardList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		//수주량(제품 종류 상관없이 총 제품 수주량)
		List<?> orderCntList = ordersOutputService.selectOrdersCnt(searchVO);
		model.put("orderCntList", orderCntList);
		//생산량(제품별 생산량)
		List<?> prodCntList = actualOutputService.selectProdCnt();
		model.put("prodCntList", prodCntList);
		//매출액
		List<?> salesList = ordersOutputService.selectSales(searchVO);
		model.put("salesList", salesList);
	
		//재고현황
		List<?> itemList = dashBoardService.selectItemList();
		model.put("itemList", itemList);
		
		//공지사항
		List<?> noticeList = dashBoardService.selectNoticeList(searchVO);
		model.put("noticeList", noticeList);
		return "sl/monitoring/dashBoard/dashBoard";
	}
	
	@RequestMapping("/sl/monitoring/detailNotice.do")
	public String detailNotice(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = dashBoardService.selectNoticeInfo(map);
		model.put("noticeVO", detail);
		return "sl/monitoring/notice/detailNotice";
	}
}
