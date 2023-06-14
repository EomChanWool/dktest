package apc.sl.monitoring.ordersOutput.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import apc.sl.monitoring.actualOutput.service.ActualOutputService;
import apc.sl.monitoring.ordersOutput.service.OrdersOutputService;
import apc.util.SearchVO;

@Controller
public class OrdersOutputController {
	@Autowired
	private OrdersOutputService ordersOutputService;
	@Autowired
	private ActualOutputService actualOutputService;
	
	@RequestMapping("/sl/monitoring/ordersOutput/ordersOutput.do")
	public String actualOutput(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
//		List<?> data = ordersOutputService.selectOrderOutputData(searchVO);
//		model.put("dataList", data);
		
		//수주량(제품 종류 상관없이 총 제품 수주량)
		List<?> orderCntList = ordersOutputService.selectOrdersCnt(searchVO);
		model.put("orderCntList", orderCntList);
		//생산량(제품별 생산량)
		List<?> prodCntList = actualOutputService.selectProdCnt();
		model.put("prodCntList", prodCntList);
		//매출액
		List<?> salesList = ordersOutputService.selectSales(searchVO);
		model.put("salesList", salesList);
		
		return "sl/monitoring/ordersOutput/ordersOutput";
	}
}
