package apc.sl.quality.incongruent.web;

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

import apc.sl.quality.incongruent.service.IncongruentService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
public class IncongruentController {

	@Autowired
	private IncongruentService incongruentService;
	
	@RequestMapping("/sl/quality/incongruent/incongruentList.do")
	public String incongruentList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		int totCnt = incongruentService.selectIncongruentListToCnt(searchVO);
		
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
		
		List<?> incongruentList = incongruentService.selectIncongruentList(searchVO);
		
		model.put("incongruentList", incongruentList);
		model.put("paginationInfo", paginationInfo);
		
		
		
		return "sl/quality/incongruent/incongruentList";
	}
	
	@RequestMapping("/sl/quality/incongruent/registIncongruent.do")
	public String registIncongruent(ModelMap model) {
		
		List<?> tiList = incongruentService.selectTiList();
		
		List<?> biList = incongruentService.selectBiList();
		
		model.put("tiList", tiList);
		model.put("biList", biList);
		
		return "sl/quality/incongruent/incongruentRegist";
	}
	
	@RequestMapping(value="/sl/quality/incongruent/incongruentInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView incongruentInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = incongruentService.selectInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("tiInfo", list);
		
		
		return mav;
	}
	
	@RequestMapping("/sl/quality/incongruent/registIncongruentOk.do")
	public String registIncongruentOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		//검사번호 체크
		int exists = incongruentService.selectTiIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 검사번호 입니다.");
			map.replace("tiIdx", "");
			redirectAttributes.addFlashAttribute("incoVO", map);
			return "redirect:/sl/quality/incongruent/registIncongruent.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		
		incongruentService.registIncongruent(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		
		return "redirect:/sl/quality/incongruent/incongruentList.do";
	}
	
	@RequestMapping("/sl/quality/incongruent/modifyIncongruent.do")
	public String modifyIncongruent(@RequestParam Map<String,Object> map, ModelMap model) {
		
		Map<String, Object> list = incongruentService.selectInco(map);
		
		List<?> biList = incongruentService.selectBiList();
		
		model.put("incoVO", list);
		model.put("biList", biList);
		
		
		return "sl/quality/incongruent/incongruentModify";
	}
	
	@RequestMapping("/sl/quality/incongruent/modifyIncongruentOk.do")
	public String modifyIncongruentOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		map.put("userId", session.getAttribute("user_id"));
		incongruentService.modifyIncongruent(map);
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		
		return "redirect:/sl/quality/incongruent/incongruentList.do";
	}
	
	@RequestMapping("/sl/quality/incongruent/deleteIncongruent.do")
	public String deleteIncongruent(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		
		incongruentService.deleteIncongruent(map);
		
	
		
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/quality/incongruent/incongruentList.do";
	}
	
}
