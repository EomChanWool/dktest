package apc.sl.process.inspect.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	
	private String filePath = "C:\\test\\";
	
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
		
		List<?> mfList = inspectService.selectMfList();
		
		List<?> siList = inspectService.selectSiList();
		
		model.put("siList", siList);
		
		model.put("mfList", mfList);
		
		return "sl/process/inspect/inspectRegist";
	}
	
	@RequestMapping(value="/sl/process/inspect/inspectInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView inspectInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> list = inspectService.selectInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("inInfo", list);
		
		return mav;
	}
	
	@RequestMapping(value="/sl/process/inspect/inspectInfoAjax2.do", method=RequestMethod.POST)
	public ModelAndView inspectInfoAjax2(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = inspectService.selectInfo2(map);
		mav.setViewName("jsonView");
		mav.addObject("inInfo2", list);
		
		return mav;
	}
	
	@RequestMapping("/sl/process/inspect/registInspectOk.do")
	public String registInspectOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		//검사번호 체크
//		int exists = inspectService.selectCheckIns(map);
//		if(exists != 0) {
//			redirectAttributes.addFlashAttribute("msg", "이미검사한 제품 입니다.");
//			return "redirect:/sl/process/inspect/registInspect.do";
//		}
		
		
		inspectService.registInspect(map);
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		
		return "redirect:/sl/process/inspect/inspectList.do";
	}
	
	@RequestMapping("/sl/process/inspect/modifyInspect.do")
	public String modifyInspect(@RequestParam Map<String,Object> map, ModelMap model) {
		
		Map<String, Object> list = inspectService.selectInco(map);
		
		//List<?> biList = inspectService.selectBiList();
		
		model.put("incoVO", list);
		//model.put("biList", biList);
		System.out.println("리스트 : " + list);
		
		return "sl/process/inspect/inspectModify";
	}
	
	@RequestMapping("/sl/process/inspect/modifyInspectOk.do")
	public String modifyInspectOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		inspectService.modifyInspect(map);
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		
		return "redirect:/sl/process/inspect/inspectList.do";
	}
	
	@RequestMapping("/sl/process/inspect/detailInspect.do")
	public String detailInspect(@RequestParam Map<String, Object> map,ModelMap model) {
		String type = map.get("isiItemType")+"";
		if(type.equals("90E(L)") || type.equals("90E(S)") || type.equals("45E(L)")) {
			Map<String, Object> detail = inspectService.detailInspec(map);
			model.put("detail", detail);
			String spcSpect = map.get("isiSpcSpec")+"";
			Map<String, Object> spcInfo = inspectService.spcInfo(spcSpect);
			model.put("spcInfo",spcInfo);
			String Edata = map.get("isiFile")+"";
			Map<String, Object> eDataInfo = inspectService.eDataInfo(Edata);
			model.put("eDataInfo", eDataInfo);
			model.put("cIsiFile", Edata);
			model.put("cFile", map.get("cFile"));
			return "sl/process/inspect/ElbowDetail";
		}
		
		Map<String, Object> detail = inspectService.detailInspec(map);
		
		
		model.put("detail", detail);
		return "sl/process/inspect/inspectDetail";
	}
	
	@RequestMapping("/sl/process/inspect/updateStat.do")
	public String updateStat(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes) {
		
		
		inspectService.updateStat(map);
		
		redirectAttributes.addFlashAttribute("msg", "판정이 완료되었습니다.");
		
		return "redirect:/sl/process/inspect/inspectList.do";
	}
	
	
	
	@RequestMapping("/sl/process/inspect/deleteInspect.do")
	public String deleteInspect(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		
		inspectService.deleteInspect(map);
		
	
		
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/process/inspect/inspectList.do";
	}
	
}
