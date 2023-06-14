package apc.sl.sales.delivery.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.sales.delivery.service.DeliveryService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DeliveryController {
	@Autowired
	private DeliveryService deliveryService;
	
	@RequestMapping("/sl/sales/delivery/deliveryList.do")
	public String deliveryList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = deliveryService.selectDeliveryListToCnt(searchVO);
		/** pageing setting */
		searchVO.setPageSize(10);
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex()); // 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); // 한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(searchVO.getPageSize()); // 페이징 리스트의 사이즈
		paginationInfo.setTotalRecordCount(totCnt);
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		List<?> deliveryList = deliveryService.selectDeliveryList(searchVO);
		model.put("deliveryList", deliveryList);
		model.put("paginationInfo", paginationInfo);
		return "sl/sales/delivery/deliveryList";
	}
	
	@RequestMapping("/sl/sales/delivery/registDelivery.do")
	public String registDelivery(ModelMap model) {
		Map<String,Object> map = new HashMap<>();
		map.put("state", "0");
		List<?> shipmentList = deliveryService.selectShipmentList(map);
		model.put("shipmentList", shipmentList);
		return "sl/sales/delivery/deliveryRegist";
	}
	
	@RequestMapping(value="/sl/sales/delivery/shipmentInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView shipmentAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = deliveryService.shipmentAjax(map);
		mav.setViewName("jsonView");
		mav.addObject("sh_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/sales/delivery/registDeliveryOk.do")
	public String registDeliveryOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//납품불량인 경우 전량 반환하여 재고로 다시 넣어줌
		if(map.get("deState").equals("2")) {
			calcItemCnt(map, redirectAttributes, "faulty");
		}else if(map.get("deState").equals("1") && map.get("deState").equals("0")){
			//견적서 제품코드와 수량 리스트
			calcItemCnt(map, redirectAttributes, "regist");
		}
		
		map.put("userId", session.getAttribute("user_id"));
		deliveryService.registDelivery(map);
		//출하계획 상태 출하완료(1)로 변경
		map.put("state", "1");
		deliveryService.updateShipment(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/sales/delivery/deliveryList.do";
	}
	
	@RequestMapping("/sl/sales/delivery/modifyDelivery.do")
	public String modifyDelivery(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = deliveryService.selectDeliveryInfo(map);
		model.put("deliveryVO", detail);
		return "sl/sales/delivery/deliveryModify";
	}
	
	@RequestMapping("/sl/sales/delivery/modifyDeliveryOk.do")
	public String modifyDelivery(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		deliveryService.modifyDelivery(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/sales/delivery/deliveryList.do";
	}
	
	@RequestMapping("/sl/sales/delivery/detailDelivery.do")
	public String detailDelivery(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = deliveryService.selectDeliveryInfo(map);
		model.put("deliveryVO", detail);
		return "sl/sales/delivery/deliveryDetail";
	}
	
	@RequestMapping("/sl/sales/delivery/deleteDelivery.do")
	public String deleteDelivery(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		calcItemCnt(map, redirectAttributes, "del");
		deliveryService.deleteDelivery(map);
		//sm_shipment 상태를 출하대기로
		map.put("state", "0");
		deliveryService.updateShipment(map);
		
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/sales/delivery/deliveryList.do";
	}
	
	private String calcItemCnt(Map<String, Object> map, RedirectAttributes redirectAttributes, String type) {
		//견적서 제품코드와 수량 리스트
		List<?> estimateProdList = deliveryService.selectEstimateProdList(map);
		String temp = estimateProdList.toString().substring(2,estimateProdList.toString().length()-2);
		Map<String, Object> tempMap = new HashMap<>();
		String[] temp1 = temp.split(", ");
		for(int i=0;i<temp1.length;i+=2) {
			tempMap.clear();
			tempMap.put("itemCd", temp1[i].split("=")[1]);
			if(type.equals("regist")) {
				tempMap.put("itemCnt", "-"+temp1[i+1].split("=")[1]);
			}else if(type.equals("del") || type.equals("faulty")){
				tempMap.put("itemCnt", temp1[i+1].split("=")[1]);
			}
			
			//재고체크(충분하면 1, 재고가 부족하면 0)
			Map<String, Object> stockOk = deliveryService.selectStockOk(tempMap);
			if(Integer.parseInt(stockOk.get("count")+"") == 0 && type.equals("regist")) {
				redirectAttributes.addFlashAttribute("msg", stockOk.get("itemName")+"의 재고가 부족합니다.");
				redirectAttributes.addFlashAttribute("deliveryVO", map);
				return "redirect:/sl/sales/delivery/deliveryList.do";
			}
		}
		
		for(int i=0;i<temp1.length;i+=2) {
			tempMap.clear();
			tempMap.put("itemCd", temp1[i].split("=")[1]);
			if(type.equals("regist")) {
				tempMap.put("itemCnt", "-"+temp1[i+1].split("=")[1]);
			}else if(type.equals("del") || type.equals("faulty")){
				tempMap.put("itemCnt", temp1[i+1].split("=")[1]);
			}
			
			//재고가 충분하면 제품 재고에서 빼줌
			deliveryService.updateItem(tempMap);
		}
		
		return "";
	}
	
}
