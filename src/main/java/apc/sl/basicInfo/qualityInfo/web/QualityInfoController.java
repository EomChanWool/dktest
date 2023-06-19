package apc.sl.basicInfo.qualityInfo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.basicInfo.qualityInfo.service.QualityInfoService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class QualityInfoController {
	@Autowired
	private QualityInfoService qualityInfoService;
	
	@RequestMapping("/sl/basicInfo/qualityInfo/qualityInfoList.do")
	public String qualityInfoList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = qualityInfoService.selectQualityInfoToCnt(searchVO);
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
		List<?> qualityInfoList = qualityInfoService.selectQualityInfoList(searchVO);
		model.put("qualityInfoList", qualityInfoList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/qualityInfo/qualityInfoList";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/registQualityInfo.do")
	public String registQualityInfo(ModelMap model) {
		List<?> programNameList = qualityInfoService.selectProgramName();
		model.put("programNameList", programNameList);
		return "sl/basicInfo/qualityInfo/qualityInfoRegist";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/registQualityInfoOk.do")
	public String registQualityInfoOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		qualityInfoService.registQualityInfo(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/qualityInfoList.do";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/modifyQualityInfo.do")
	public String modifyQualityInfo(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = qualityInfoService.selectQualityInfoInfo(map);
		List<?> programNameList = qualityInfoService.selectProgramName();
		model.put("programNameList", programNameList);
		model.put("qualityInfoVO", detail);
		return "sl/basicInfo/qualityInfo/qualityInfoModify";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/modifyQualityInfoOk.do")
	public String modifyQualityInfoOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		qualityInfoService.modifyQualityInfo(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/qualityInfoList.do";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/deleteQualityInfo.do")
	public String deleteQualityInfo(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		qualityInfoService.deleteQualityInfo(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/qualityInfoList.do";
	}
}
