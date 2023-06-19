package apc.sl.collectInfo.cutting.web;

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

import apc.sl.collectInfo.cutting.service.CuttingService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class CuttingController {
	@Autowired
	private CuttingService cuttingService;
	
	@RequestMapping("/sl/collectInfo/cutting/cuttingList.do")
	public String cuttingList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = cuttingService.selectCuttingListToCnt(searchVO);
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
		List<?> cuttingList = cuttingService.selectCuttingList(searchVO);
		model.put("cuttingList", cuttingList);
		model.put("paginationInfo", paginationInfo);
		return "sl/collectInfo/cutting/cuttingList";
	}
	
	@RequestMapping("/sl/collectInfo/cutting/registCutting.do")
	public String registCutting(ModelMap model) {
		//사업장목록
		List<?> compnayList = cuttingService.selectCompanyList();
		model.put("compnayList", compnayList);
		//거래처목록
		List<?> accountList = cuttingService.selectAccountList();
		model.put("accountList",accountList);
		//제품목록
		List<?> prodList = cuttingService.selectProdList();
		model.put("prodList", prodList);
		
		return "sl/collectInfo/cutting/cuttingRegist";
	}
	
	@RequestMapping(value="/sl/collectInfo/cutting/companyInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView companyInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = cuttingService.selectComapnyInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("com_info", list);
		return mav;
	}
	
	@RequestMapping(value="/sl/collectInfo/cutting/prodPerPriceInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView prodPerPriceInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = cuttingService.selectProdPerPrice(map);
		mav.setViewName("jsonView");
		mav.addObject("item_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/collectInfo/cutting/registCuttingOk.do")
	public String registCuttingOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("user", session.getAttribute("user_id"));
		cuttingService.registCutting(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/collectInfo/cutting/cuttingList.do";
	}
	
	@RequestMapping("/sl/collectInfo/cutting/modifyCutting.do")
	public String modifyCutting(@RequestParam Map<String, Object> map, ModelMap model) {
		//사업장목록
		List<?> compnayList = cuttingService.selectCompanyList();
		model.put("compnayList", compnayList);
		//거래처목록
		List<?> accountList = cuttingService.selectAccountList();
		model.put("accountList",accountList);
		//제품목록
		List<?> prodList = cuttingService.selectProdList();
		model.put("prodList", prodList);
		
		Map<String, Object> detail = cuttingService.selectCuttingInfo(map);
		model.put("cuttingVO", detail);
		
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
		return "sl/collectInfo/cutting/cuttingModify";
	}
	
	@RequestMapping("/sl/collectInfo/cutting/modifyCuttingOk.do")
	public String modifyCuttingOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		cuttingService.modifyCutting(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/collectInfo/cutting/cuttingList.do";
	}
	
	@RequestMapping("/sl/collectInfo/cutting/deleteCutting.do")
	public String deleteCutting(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		cuttingService.deleteCutting(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/collectInfo/cutting/cuttingList.do";
	}
}
