package apc.sl.basicInfo.qualityInfo.web;

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
		List<?> standardList = qualityInfoService.selectStandard();
		model.put("standardList", standardList);
		return "sl/basicInfo/qualityInfo/qualityInfoRegist";
	}
	
	@RequestMapping(value="/sl/basicInfo/qualityInfo/standardInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView standardInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = qualityInfoService.selectStandardAjaxInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("standard_ajax", list);
		return mav;
		
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/registQualityInfoOk.do")
	public String registQualityInfoOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId",session.getAttribute("user_id"));
		int exists = qualityInfoService.selectExistsQualInfo(map);
		
		if(exists == 1) {
			redirectAttributes.addFlashAttribute("msg","이미등록된 정보입니다.");
			return "redirect:/sl/basicInfo/qualityInfo/registQualityInfo.do";
		}
		
		qualityInfoService.registQualityInfo(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/qualityInfoList.do";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/modifyQualityInfo.do")
	public String modifyQualityInfo(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = qualityInfoService.selectQualityInfoInfo(map);
		model.put("qualityInfoVO", detail);
		return "sl/basicInfo/qualityInfo/qualityInfoModify";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/modifyQualityInfoOk.do")
	public String modifyQualityInfoOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		qualityInfoService.modifyQualityInfo(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/qualityInfoList.do";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/detailQualityInfo.do")
	public String detailQualityInfo(@RequestParam Map<String, Object> map, ModelMap model) {
		
		Map<String, Object> detailQual = qualityInfoService.selectDetailQualInfo(map);
		model.put("detail", detailQual);
		return "sl/basicInfo/qualityInfo/qualityInfoDetail";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/deleteQualityInfo.do")
	public String deleteQualityInfo(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		qualityInfoService.deleteQualityInfo(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/qualityInfoList.do";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/listStandard.do")
	public String listStandard(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = qualityInfoService.selectStandardInfoToCnt(searchVO);
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
		List<?> standardList = qualityInfoService.selectStandardInfo(searchVO);
		
		model.put("standardList", standardList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/qualityInfo/standardList";
	}
	
	@RequestMapping("/sl/basicInfo/qualityInfo/registStandard.do")
	public String registStandard(ModelMap model) {
		
		List<?> sList = qualityInfoService.selectStandard();
		model.put("sList", sList);
		
		
		return "sl/basicInfo/qualityInfo/standardRegist";
	}
	@RequestMapping("/sl/basicInfo/qualityInfo/registStandardOk.do")
	public String registStandardOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		qualityInfoService.registStandard(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/qualityInfoList.do";
	}
	@RequestMapping("/sl/basicInfo/qualityInfo/deleteStandard.do")
	public String deleteStandard(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		qualityInfoService.deleteStandardInfo(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/qualityInfo/listStandard.do";
	}
}
