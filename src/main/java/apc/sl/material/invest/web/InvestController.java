package apc.sl.material.invest.web;

import java.util.ArrayList;
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

import apc.sl.material.invest.service.InvestService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class InvestController {
	@Autowired
	private InvestService investService;
	
	@RequestMapping("/sl/material/invest/investList.do")
	public String investList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = investService.selectInvestListToCnt(searchVO);
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
		List<?> investList = investService.selectInvestList(searchVO);
		model.put("investList", investList);
		model.put("paginationInfo", paginationInfo);
		return "sl/material/invest/investList";
	}
	
	@RequestMapping("/sl/material/invest/registInvest.do")
	public String registInvest(ModelMap model) {
		List<?> workOrderList = investService.selectWorkOrderList();
		model.put("workOrderList", workOrderList);
		List<?> materialList = investService.selectMaterialList();
		model.put("materialList", materialList);
		return "sl/material/invest/investRegist";
	}
	
	@RequestMapping(value="/sl/material/invest/investInMaterialInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView investInMaterialInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = investService.selectInMaterialsInfo(map);
		List<Map<String, Object>> materialsList = new ArrayList<>();
		for(int i=1;i<=15;i++) {
			Map<String, Object> temp = new HashMap<>();
			String itemCd = "itemCd"+i;
			String itemName = "itemName"+i;
			String cnt = "cnt"+i;
			if(list.get(itemCd) == null) {
				break;
			}
			temp.put("itemCd", list.get(itemCd));
			temp.put("itemName", list.get(itemName));
			temp.put("cnt", list.get(cnt));
			materialsList.add(temp);
		}
		mav.setViewName("jsonView");
		mav.addObject("mts_info", materialsList);
		return mav;
	}
	
	@RequestMapping(value="/sl/material/invest/investMaterialInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView investMaterialInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = investService.selectItemInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("mt_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/material/invest/registInvestOk.do")
	public String registInvestOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		for(int i=1;i<=15;i++) {
			Map<String, Object> temp = new HashMap<>();
			String itemCd = "itemCd"+i;
			String itemName = "itemName"+i;
			String cnt = "cnt"+i;
			if(map.get(itemCd) == null || map.get(cnt) == null) {
				break;
			}
			temp.put("itemCd", map.get(itemCd));
			temp.put("cnt", map.get(cnt));
			//재고확인
			if(!map.get(itemCd).equals("")) {
				int stockOk = investService.selectMaterialStock(temp);
				if(stockOk == 0) {
					redirectAttributes.addFlashAttribute("msg", map.get(itemName)+"의 재고가 부족합니다.");
					return "redirect:/sl/material/invest/registInvest.do";
				}
			}
		}
		
		//작업지시번호 체크
		int exists = investService.selectExistsInsertInfo(map);
		if(exists != 0) {
			redirectAttributes.addFlashAttribute("msg", "이미 등록된 내역입니다.");
			redirectAttributes.addFlashAttribute("investVO",map);
			return "redirect:/sl/material/invest/registInvest.do";
		}
		
		//sm_material 재고 갱신
//		for(int i=1;i<=15;i++) {
//			Map<String, Object> temp = new HashMap<>();
//			String itemCd = "itemCd"+i;
//			String cnt = "cnt"+i;
//			if(map.get(itemCd) == null || map.get(cnt) == null) {
//				break;
//			}
//			temp.put("itemCd", map.get(itemCd));
//			temp.put("cnt", "-"+map.get(cnt));
//			investService.updateMaterialStock(temp);
//		}
		
		map.put("userId", session.getAttribute("user_id"));
		investService.registInvest(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/material/invest/investList.do";
	}
	
	@RequestMapping("/sl/material/invest/modifyInvest.do")
	public String modifyInvest(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> materialList = investService.selectMaterialList();
		model.put("materialList", materialList);

		if(!map.isEmpty()) {
			Map<String, Object> detail = investService.selectInvestInfo(map);
			model.put("investVO", detail);
		}
		
		return "sl/material/invest/investModify";
	}
	
	@RequestMapping("/sl/material/invest/modifyInvestOk.do")
	public String modifyInvestOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		for(int i=1;i<=15;i++) {
			Map<String, Object> temp = new HashMap<>();
			String itCd = "itemCd"+i;
			String itNm = "itemName"+i;
			String cnt = "cnt"+i;
			
			if(map.get(itCd) == null || map.get(cnt) == null || map.get(itCd).equals("") || map.get(cnt).equals("")) {
				break;
			}
			
			temp.put("itemCd", map.get(itCd));
			temp.put("cnt", map.get(cnt));
			//재고확인
			int stockOk = investService.selectMaterialStock(temp);
			if(stockOk == 0) {
				redirectAttributes.addFlashAttribute("msg", map.get(itNm)+"의 재고가 부족합니다.");
				return "redirect:/sl/material/invest/investList.do";
			}
		}
		
//		for(int i=1;i<=15;i++) {
//			Map<String, Object> temp = new HashMap<>();
//			temp.put("itemCd", "");
//			temp.put("cnt", "");
//			
//			String itCd = "itemCd"+i;
//			String curItCd = "curItemCd"+i;
//			String cnt = "cnt"+i;
//			String curCnt = "curCnt"+i;
//			
//			if(map.get(itCd) == null || map.get(cnt) == null) {
//				break;
//			}
//			
//			if(map.get(curItCd) != null) {
//				temp.replace("itemCd", map.get(curItCd));
//				temp.replace("cnt", map.get(curCnt));
//				investService.updateMaterialStock(temp);
//			}
//			temp.replace("itemCd", map.get(itCd));
//			temp.replace("cnt", "-"+map.get(cnt));
//			investService.updateMaterialStock(temp);
//		}
		
		investService.updateInMaterial(map);
		
		map.put("userId", session.getAttribute("user_id"));
		investService.modifyInvest(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/material/invest/investList.do";
	}
	
	@RequestMapping("/sl/material/invest/detailInvest.do")
	public String detailInvest(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = investService.selectInvestInfo(map);
		model.put("investVO", detail);
		return "sl/material/invest/investDetail";
	}
	
	@RequestMapping("/sl/material/invest/deleteInvest.do")
	public String deleteInvest(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		investService.deleteInvest(map);
		
		//sm_marterial 재고 개수 갱신
		map.put("cnt", map.get("inCnt"));
		investService.updateMaterialStock(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/material/invest/investList.do";
	}
}
