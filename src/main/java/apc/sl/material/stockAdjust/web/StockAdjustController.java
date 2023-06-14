package apc.sl.material.stockAdjust.web;

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

import apc.sl.material.stockAdjust.service.StockAdjustService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class StockAdjustController {
	@Autowired
	private StockAdjustService stockAdjustService;
	
	@RequestMapping("/sl/material/stockAdjust/stockAdjustList.do")
	public String stockAdjustList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = stockAdjustService.selectStockAdjustListToCnt(searchVO);
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
		List<?> stockAdjustList = stockAdjustService.selectStockAdjustList(searchVO);
		model.put("stockAdjustList", stockAdjustList);
		model.put("paginationInfo", paginationInfo);
		return "sl/material/stockAdjust/stockAdjustList";
	}
	
	@RequestMapping("/sl/material/stockAdjust/registStockAdjust.do")
	public String registStockAdjust(ModelMap model) {
		String type = "자재";
		List<?> materialList = stockAdjustService.selectItemList(type);
		model.put("materialList", materialList);
		type="제품";
		List<?> productList = stockAdjustService.selectItemList(type);
		model.put("productList", productList);
		return "sl/material/stockAdjust/stockAdjustRegist";
	}
	
	@RequestMapping(value="/sl/material/stockAdjust/itemCntInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView itemCntInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = stockAdjustService.selectItemCntInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("item_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/material/stockAdjust/registStockAdjustOk.do")
	public String registStockAdjustOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(map.get("itemType").equals("자재")) {
			map.put("itemCd", map.get("mtItemCd"));
		}else {
			map.put("itemCd", map.get("pdItemCd"));
		}
		
		int exists = stockAdjustService.selectExistsItemCode(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 물품입니다.");
			return "redirect:/sl/material/stockAdjust/registStockAdjust.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		stockAdjustService.registStockAdjust(map);
		
		//sm_item 재고 Stock 갱신
		stockAdjustService.updateItemCnt(map);
		
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/material/stockAdjust/stockAdjustList.do";
	}
	
	@RequestMapping("/sl/material/stockAdjust/modifyStockAdjust.do")
	public String modifyStockAdjust(@RequestParam Map<String, Object> map, ModelMap model) {
		String type = "자재";
		List<?> materialList = stockAdjustService.selectItemList(type);
		model.put("materialList", materialList);
		type="제품";
		List<?> productList = stockAdjustService.selectItemList(type);
		model.put("productList", productList);
		
		if(!map.isEmpty()) {
			Map<String, Object> detail = stockAdjustService.selectStockAdjustInfo(map);
			model.put("adjustVO", detail);
		}
		return "sl/material/stockAdjust/stockAdjustModify";
	}
	
	@RequestMapping("/sl/material/stockAdjust/modifyStockAdjustOk.do")
	public String modifyStockAdjustOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(map.get("itemType").equals("자재")) {
			map.put("itemCd", map.get("mtItemCd"));
		}else {
			map.put("itemCd", map.get("pdItemCd"));
		}
		
		int exists = stockAdjustService.selectExistsItemCode(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 물품입니다.");
			redirectAttributes.addFlashAttribute("adjustVO", map);
			return "redirect:/sl/material/stockAdjust/modifyStockAdjust.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		stockAdjustService.modifyStockAdjust(map);
		
		//수정하려는 항목이 해당 물품 중에서 가장 최근에 등록된 항목이면 sm_item항목도 수정
		int recentIdx = stockAdjustService.selectChkRecent(map);
		if(Integer.parseInt(map.get("adIdx")+"") == recentIdx) {
			stockAdjustService.updateItemCnt(map);
		}
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/material/stockAdjust/stockAdjustList.do";
	}
	
	@RequestMapping("/sl/material/stockAdjust/deleteStockAdjust.do")
	public String deleteStockAdjust(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		stockAdjustService.deleteStockAdjust(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/material/stockAdjust/stockAdjustList.do";
	}
}
