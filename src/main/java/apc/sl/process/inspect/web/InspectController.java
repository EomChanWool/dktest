package apc.sl.process.inspect.web;

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

import apc.sl.process.inspect.service.InspectService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class InspectController {

	@Autowired
	private InspectService inspectService;
	
	@RequestMapping("/sl/process/inspect/inspectList.do")
	public String inspectList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = inspectService.selectInspectListToCnt(searchVO);
		
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
		
		List<?> inspectList = inspectService.selectInspectList(searchVO);
		
		model.put("inspectList", inspectList);
		model.put("paginationInfo", paginationInfo);
		
		
		
		return "sl/process/inspect/inspectList";
	}
	
	@RequestMapping("/sl/process/inspect/registInspect.do")
	public String registInspect(ModelMap model) {
		
		List<?> tiList = inspectService.selectTiList();
		
		List<?> biList = inspectService.selectBiList();
		
		model.put("tiList", tiList);
		model.put("biList", biList);
		
		return "sl/process/inspect/inspectRegist";
	}
	
	@RequestMapping(value="/sl/process/inspect/inspectInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView inspectInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = inspectService.selectInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("tiInfo", list);
		
		
		return mav;
	}
	
	@RequestMapping("/sl/process/inspect/registInspectOk.do")
	public String registInspectOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		//검사번호 체크
		int exists = inspectService.selectTiIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 검사번호 입니다.");
			map.replace("tiIdx", "");
			redirectAttributes.addFlashAttribute("incoVO", map);
			return "redirect:/sl/process/inspect/registInspect.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		
		inspectService.registInspect(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		
		return "redirect:/sl/process/inspect/inspectList.do";
	}
	
	@RequestMapping("/sl/process/inspect/modifyInspect.do")
	public String modifyInspect(@RequestParam Map<String,Object> map, ModelMap model) {
		
		Map<String, Object> list = inspectService.selectInco(map);
		
		List<?> biList = inspectService.selectBiList();
		
		model.put("incoVO", list);
		model.put("biList", biList);
		
		
		return "sl/process/inspect/inspectModify";
	}
	
	@RequestMapping("/sl/process/inspect/modifyInspectOk.do")
	public String modifyInspectOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		map.put("userId", session.getAttribute("user_id"));
		inspectService.modifyInspect(map);
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		
		return "redirect:/sl/process/inspect/inspectList.do";
	}
	
	@RequestMapping("/sl/process/inspect/deleteInspect.do")
	public String deleteInspect(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		
		inspectService.deleteInspect(map);
		
	
		
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/process/inspect/inspectList.do";
	}
	
}
