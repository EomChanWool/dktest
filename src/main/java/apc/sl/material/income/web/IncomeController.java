package apc.sl.material.income.web;

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

import apc.sl.material.income.service.IncomeService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class IncomeController {
	@Autowired
	private IncomeService incomeService;
	
	@RequestMapping("/sl/material/income/incomeList.do")
	public String incomeList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = incomeService.selectIncomeListToCnt(searchVO);
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
		List<?> incomeList = incomeService.selectIncomeList(searchVO);
		model.put("incomeList", incomeList);
		model.put("paginationInfo", paginationInfo);
		return "sl/material/income/incomeList";
	}
	
	@RequestMapping("/sl/material/income/registIncome.do")
	public String registIncome(ModelMap model) {
		List<?> accountList = incomeService.selectAccountList();
		model.put("accountList", accountList);
		List<?> materialList = incomeService.selectMaterialList();
		model.put("materialList", materialList);
		return "sl/material/income/incomeRegist";
	}
	
	@RequestMapping(value="/sl/material/income/itemInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView itemInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = incomeService.selectItemInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("item_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/material/income/registIncomeOk.do")
	public String registIncomeOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		incomeService.registIncome(map);
		
		//sm_material에 재고 갱신
		map.put("cnt", map.get("stCnt"));
		incomeService.updateMaterialCnt(map);
		
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/material/income/incomeList.do";
	}
	
	@RequestMapping("/sl/material/income/modifyIncome.do")
	public String modifyIncome(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = incomeService.selectIncomeInfo(map);
		model.put("incomeVO", detail);
		List<?> accountList = incomeService.selectAccountList();
		model.put("accountList", accountList);
		List<?> materialList = incomeService.selectMaterialList();
		model.put("materialList", materialList);
		return "sl/material/income/incomeModify";
	}
	
	@RequestMapping("/sl/material/income/modifyIncomeOk.do")
	public String modifyIncomeOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		incomeService.modifyIncome(map);
		
		String itemCd = map.get("itemCd")+"";
		//sm_item에 재고 갱신
		//자재가 바뀌었을경우
		map.put("cnt", "");
		if(!map.get("curItemCd").equals(map.get("itemCd"))) {
			//이전 자재 재고 복구
			map.replace("cnt", "-"+map.get("curStCnt"));
			map.replace("itemCd", map.get("curItemCd"));
			incomeService.updateMaterialCnt(map);
			//변경된 자재 재고 더해줌
			map.replace("cnt", map.get("stCnt"));
			map.replace("itemCd", itemCd);
			incomeService.updateMaterialCnt(map);
		}else {
			//자재가 안바뀌었을 경우
			int cnt = Integer.parseInt(map.get("stCnt")+"") - Integer.parseInt(map.get("curStCnt")+"");
			map.replace("cnt", cnt);
			incomeService.updateMaterialCnt(map);
		}
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/material/income/incomeList.do";
	}
	
	@RequestMapping("/sl/material/income/detailIncome.do")
	public String detailIncome(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = incomeService.selectIncomeInfo(map);
		model.put("incomeVO", detail);
		return "sl/material/income/incomeDetail";
	}
	
	@RequestMapping("/sl/material/income/deleteIncome.do")
	public String deleteIncome(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		incomeService.deleteIncome(map);
		
		//sm_item에서 재고 제외
		int cnt = Integer.parseInt("-"+map.get("stCnt"));
		map.put("cnt", cnt);
		incomeService.updateMaterialCnt(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/material/income/incomeList.do";
	}
}
