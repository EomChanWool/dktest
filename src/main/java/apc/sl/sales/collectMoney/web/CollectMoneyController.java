package apc.sl.sales.collectMoney.web;

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

import apc.sl.sales.collectMoney.service.CollectMoneyService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class CollectMoneyController {
	@Autowired
	private CollectMoneyService collectMoneyService;
	
	@RequestMapping("/sl/sales/collectMoney/collectMoneyList.do")
	public String collectMoneyList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = collectMoneyService.selectCollectMoneyListToCnt(searchVO);
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
		List<?> collectList = collectMoneyService.selectCollectMoneyList(searchVO);
		model.put("collectList", collectList);
		model.put("paginationInfo", paginationInfo);
		return "sl/sales/collect/collectMoneyList";
	}
	
	@RequestMapping("/sl/sales/collectMoney/registCollect.do")
	public String registCollect(ModelMap model) {
		List<?> deliveryList = collectMoneyService.selectDeliveryList();
		model.put("deliveryList", deliveryList);
		return "sl/sales/collect/collectMoneyRegist";
	}
	
	@RequestMapping(value="/sl/sales/collectMoney/deliveryInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView deliveryInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = collectMoneyService.selectDeliveryInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("de_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/sales/collectMoney/registCollectOk.do")
	public String registCollectMoneyOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//이미 존재여부
		int exists = collectMoneyService.selectAlreadyRegistDeIdx(map);
		if(exists != 0) {
			redirectAttributes.addFlashAttribute("msg", "이미 등록된 내역입니다.");
			redirectAttributes.addFlashAttribute("collectVO", map);
			return "redirect:/sl/sales/collectMoney/registCollect.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		collectMoneyService.registCollectMoney(map);
		//납품 상태 변경
		if(map.get("coState").equals("1")) {
			map.put("state","3");
			collectMoneyService.updateDelivery(map);
		}
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/sales/collectMoney/collectMoneyList.do";
	}
	
	@RequestMapping("/sl/sales/collectMoney/modifyCollect.do")
	public String modifyCollectMoney(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = collectMoneyService.selectCollectInfo(map);
		model.put("collectVO", detail);
		List<?> deliveryList = collectMoneyService.selectDeliveryList();
		model.put("deliveryList", deliveryList);
		
		return "sl/sales/collect/collectMoneyModify";
	}
	
	@RequestMapping("/sl/sales/collectMoney/modifyCollectOk.do")
	public String modifyCollectMoneyOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		collectMoneyService.modifyCollectMoney(map);
		
		//납품번호가 다르면 납품 상태 변경
		if(map.get("coState").equals("1")) {
			map.put("state", "3");
			collectMoneyService.updateDelivery(map);
		}else if(map.get("coState").equals("0")){
			map.put("state", "1");
			collectMoneyService.updateDelivery(map);
		}
		
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/sales/collectMoney/collectMoneyList.do";
	}
	
	@RequestMapping("/sl/sales/collectMoney/detailCollect.do")
	public String detailCollectMoney(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = collectMoneyService.selectCollectInfo(map);
		model.put("collectVO", detail);
		return "sl/sales/collect/collectMoneyDetail";
	}
	
	@RequestMapping("/sl/sales/collectMoney/deleteCollect.do")
	public String deleteCollectMoney(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		collectMoneyService.deleteCollect(map);
		//납품 상태 변경
		map.put("state","1");
		collectMoneyService.updateDelivery(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/sales/collectMoney/collectMoneyList.do";
	}
}
