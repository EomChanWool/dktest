package apc.sl.production.process.web;

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

import apc.sl.production.process.service.ProcessService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ProcessController {
	@Autowired
	private ProcessService processService;
	
	@RequestMapping("/sl/production/processChk/processChkList.do")
	public String prodEquipList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = processService.selectProcessChkListToCnt(searchVO);
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
		List<?> processCheckList = processService.selectProcessChkList(searchVO);
		model.put("processCheckList", processCheckList);
		model.put("paginationInfo", paginationInfo);
		return "sl/production/process/processList";
	}
	
	@RequestMapping("/sl/production/processChk/registProcess.do")
	public String registProcess(ModelMap model) {
		List<?> prList = processService.selectProcessList();
		model.put("prList", prList);
		return "sl/production/process/processRegist";
	}
	
	@RequestMapping(value="/sl/production/processChk/processEquipInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView processEquipInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = processService.selectProcessEquipInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("fa_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/production/processChk/registProcessOk.do")
	public String registProcessOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(checkColumn(map, redirectAttributes)) {
			return "redirect:/sl/production/processChk/registProcess.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		processService.registProcess(map);
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/production/processChk/processChkList.do";
	}
	
	@RequestMapping("/sl/production/processChk/modifyProcess.do")
	public String modifyProcess(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> prList = processService.selectProcessList();
		model.put("prList", prList);
		Map<String, Object> detail = processService.selectProcessInfo(map);
		model.put("processVO", detail);
		return "sl/production/process/processModify";
	}
	
	@RequestMapping("/sl/production/processChk/modifyProcessOk.do")
	public String modifyProcessOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(checkColumn(map, redirectAttributes)) {
			return "redirect:/sl/production/processChk/modifyProcess.do";
		}
		
		map.put("userId", session.getAttribute("user_id"));
		processService.modifyProcess(map);
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/production/processChk/processChkList.do";
	}
	
	@RequestMapping("/sl/production/processChk/detailProcess.do")
	public String detailProcess(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = processService.selectProcessInfo(map);
		model.put("processVO", detail);
		return "sl/production/process/processDetail";
	}
	
	@RequestMapping("/sl/production/processChk/deleteProcess.do")
	public String deleteProcess(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		processService.deleteProcess(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/production/processChk/processChkList.do";
	}
	
	private boolean checkColumn(Map<String, Object> map, RedirectAttributes redirectAttributes) {
		//공정번호 체크
		int exists = processService.selectExistsPrIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "공정번호가 존재하지 않거나 해당사항이 없는 공정번호 입니다.");
			redirectAttributes.addFlashAttribute("processVO", map);
			return true;
		}
		//설비코드 체크
		exists = processService.selectExistsFaIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 설비코드 입니다.");
			redirectAttributes.addFlashAttribute("processVO", map);
			return true;
		}
		return false;
	}
}
