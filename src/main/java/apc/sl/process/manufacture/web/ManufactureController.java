package apc.sl.process.manufacture.web;

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

import apc.sl.process.manufacture.service.ManufactureService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ManufactureController {

	@Autowired
	private ManufactureService manufactureService;
	
	@RequestMapping("/sl/process/manufacture/manufactureList.do")
	public String ManufactureList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = manufactureService.selectManufactureListToCnt(searchVO);
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
		
		List<?> manufactureList = manufactureService.selectManufactureList(searchVO);
		model.put("manufactureList", manufactureList);
		model.put("paginationInfo", paginationInfo);
	
		
		return "sl/process/manufacture/manufactureList";
	}
	
	@RequestMapping("/sl/process/manufacture/registManufacture.do")
	public String registManufacture(ModelMap model) {
		
		List<?> inList = manufactureService.selelctInList();
		
		model.put("inList", inList);
		
		
		
		return "sl/process/manufacture/manufactureRegist";
	}
	
	@RequestMapping(value="/sl/process/manufacture/manufactureInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView manufactureInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = manufactureService.selectInfo(map);
		
		mav.setViewName("jsonView");
		mav.addObject("inInfo", list);
		
		return mav;
	}
	
	
	@RequestMapping("/sl/process/manufacture/registManufactureOk.do")
	public String registManufactureOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		int exists = manufactureService.selectInIdx(map);
		
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 부적합번호 입니다.");
			map.replace("inIdx", "");
			redirectAttributes.addFlashAttribute("inspVO", map);
			return "redirect:/sl/process/manufacture/registManufacture.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		
		manufactureService.registManufacture(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		
				
		
		return "redirect:/sl/process/manufacture/manufactureList.do";
	}
	
	@RequestMapping("/sl/process/manufacture/modifyManufacture.do")
	public String modifyManufacture(@RequestParam Map<String, Object> map, ModelMap model) { 
		
		Map<String, Object> list = manufactureService.selectCheck(map);
		
		model.put("checkVO", list);
		
		
		return "sl/process/manufacture/manufactureModify";
	}
	
	@RequestMapping("/sl/process/manufacture/modifyManufactureOk.do")
	public String modifyManufactureOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {

		map.put("userId", session.getAttribute("user_id"));
		manufactureService.modifyManufacture(map);
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다");
		
		return "redirect:/sl/process/manufacture/manufactureList.do";
	}
	
	@RequestMapping("/sl/process/manufacture/deleteManufacture.do")
	public String deleteManufacture(@RequestParam Map<String, Object> map , RedirectAttributes redirectAttributes, HttpSession session) {
		
		manufactureService.deleteManufacture(map);
		
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		
		return "redirect:/sl/process/manufacture/manufactureList.do";
	}
	
	@RequestMapping("/sl/process/manufacture/detailManufacture.do")
	public String detailManufacture(@RequestParam Map<String, Object> map, ModelMap model) {
		
	
		
		Map<String, Object> detail = manufactureService.selectDetailManufacture(map);
		
		model.put("checkVO", detail);
		
		
		return "sl/process/manufacture/manufactureDetail";
	}
	
}
