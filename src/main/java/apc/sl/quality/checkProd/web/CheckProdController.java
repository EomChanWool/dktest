package apc.sl.quality.checkProd.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import apc.sl.quality.checkProd.service.CheckProdService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class CheckProdController {

	@Autowired
	private CheckProdService checkProdService;
	
	@RequestMapping("/sl/quality/checkProd/checkProdList.do")
	public String CheckProdList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = checkProdService.selectCheckProdListToCnt(searchVO);
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
		
		List<?> checkProdList = checkProdService.selectCheckProdList(searchVO);
		model.put("checkProdList", checkProdList);
		model.put("paginationInfo", paginationInfo);
	
		
		return "sl/quality/checkProd/checkProdList";
	}
	
	@RequestMapping("/sl/quality/checkProd/registCheckProd.do")
	public String registCheckProd(ModelMap model) {
		
		List<?> inList = checkProdService.selelctInList();
		
		model.put("inList", inList);
		
		
		
		return "sl/quality/checkProd/checkProdRegist";
	}
	
	@RequestMapping(value="/sl/quality/checkProd/checkProdInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView checkProdInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = checkProdService.selectInfo(map);
		
		mav.setViewName("jsonView");
		mav.addObject("inInfo", list);
		
		return mav;
	}
	
	
	@RequestMapping("/sl/quality/checkProd/registCheckProdOk.do")
	public String registCheckProdOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		int exists = checkProdService.selectInIdx(map);
		
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 부적합번호 입니다.");
			map.replace("inIdx", "");
			redirectAttributes.addFlashAttribute("inspVO", map);
			return "redirect:/sl/quality/checkProd/registCheckProd.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		
		checkProdService.registCheckProd(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		
				
		
		return "redirect:/sl/quality/checkProd/checkProdList.do";
	}
	
	@RequestMapping("/sl/quality/checkProd/modifyCheckProd.do")
	public String modifyCheckProd(@RequestParam Map<String, Object> map, ModelMap model) { 
		
		Map<String, Object> list = checkProdService.selectCheck(map);
		
		model.put("checkVO", list);
		
		
		return "sl/quality/checkProd/checkProdModify";
	}
	
	@RequestMapping("/sl/quality/checkProd/modifyCheckProdOk.do")
	public String modifyCheckProdOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {

		map.put("userId", session.getAttribute("user_id"));
		checkProdService.modifyCheckProd(map);
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다");
		
		return "redirect:/sl/quality/checkProd/checkProdList.do";
	}
	
	@RequestMapping("/sl/quality/checkProd/deleteCheckProd.do")
	public String deleteCheckProd(@RequestParam Map<String, Object> map , RedirectAttributes redirectAttributes, HttpSession session) {
		
		checkProdService.deleteCheckProd(map);
		
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		
		return "redirect:/sl/quality/checkProd/checkProdList.do";
	}
	
	@RequestMapping("/sl/quality/checkProd/detailCheckProd.do")
	public String detailCheckProd(@RequestParam Map<String, Object> map, ModelMap model) {
		
	
		
		Map<String, Object> detail = checkProdService.selectDetailCheckProd(map);
		
		model.put("checkVO", detail);
		
		
		return "sl/quality/checkProd/checkProdDetail";
	}
	
}
