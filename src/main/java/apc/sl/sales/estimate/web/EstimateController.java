package apc.sl.sales.estimate.web;

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

import apc.sl.sales.estimate.service.EstimateService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class EstimateController {
	@Autowired
	private EstimateService estimateService;
	
	@RequestMapping("/sl/sales/estimate/estimateList.do")
	public String estimateList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = estimateService.selectEstimateListToCnt(searchVO);
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
		List<?> estimateList = estimateService.selectEstimateList(searchVO);
		model.put("estimateList", estimateList);
		model.put("paginationInfo", paginationInfo);
		return "sl/sales/estimate/estimateList";
	}
	
	@RequestMapping("/sl/sales/estimate/registEstimate.do")
	public String registEstimate(ModelMap model) {
		//사업장목록
		List<?> compnayList = estimateService.selectCompanyList();
		model.put("compnayList", compnayList);
		//거래처목록
		List<?> accountList = estimateService.selectAccountList();
		model.put("accountList",accountList);
		//제품목록
		List<?> prodList = estimateService.selectProdList();
		model.put("prodList", prodList);
		
		return "sl/sales/estimate/estimateRegist";
	}
	
	@RequestMapping(value="/sl/sales/estimate/companyInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView companyInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = estimateService.selectComapnyInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("com_info", list);
		return mav;
	}
	
	@RequestMapping(value="/sl/sales/estimate/prodPerPriceInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView prodPerPriceInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = estimateService.selectProdPerPrice(map);
		mav.setViewName("jsonView");
		mav.addObject("item_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/sales/estimate/registEstimateOk.do")
	public String registEstimateOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("user", session.getAttribute("user_id"));
		estimateService.registEstimate(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/sales/estimate/estimateList.do";
	}
	
	@RequestMapping("/sl/sales/estimate/modifyEstimate.do")
	public String modifyEstimate(@RequestParam Map<String, Object> map, ModelMap model) {
		//사업장목록
		List<?> compnayList = estimateService.selectCompanyList();
		model.put("compnayList", compnayList);
		//거래처목록
		List<?> accountList = estimateService.selectAccountList();
		model.put("accountList",accountList);
		//제품목록
		List<?> prodList = estimateService.selectProdList();
		model.put("prodList", prodList);
		
		Map<String, Object> detail = estimateService.selectEstimateInfo(map);
		model.put("estimateVO", detail);
		
		Map<String, Object> mapTemp = new HashMap<>();
		List<Map<String, Object>> listTemp = new ArrayList<>();
		for(int i=1;i<=4;i++) {
			mapTemp = new HashMap<>();
			String prod = "esProd"+i;
			String cnt = "esCnt"+i;
			String perPrice = "esPerPrice"+i;
			
			if(detail.get(prod)+"" != "") {
				mapTemp.put("prod", detail.get(prod)+"");
				mapTemp.put("cnt", detail.get(cnt)+"");
				mapTemp.put("perPrice", detail.get(perPrice)+"");
			}else {
				mapTemp.put("prod", null);
				mapTemp.put("cnt", null);
				mapTemp.put("perPrice", null);
			}
			listTemp.add(mapTemp);
		}
		model.put("prList", listTemp);
		return "sl/sales/estimate/estimateModify";
	}
	
	@RequestMapping("/sl/sales/estimate/modifyEstimateOk.do")
	public String modifyEstimateOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		estimateService.modifyEstimate(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/sales/estimate/estimateList.do";
	}
	
	@RequestMapping("/sl/sales/estimate/deleteEstimate.do")
	public String deleteEstimate(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		estimateService.deleteEstimate(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/sales/estimate/estimateList.do";
	}
}
