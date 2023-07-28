package apc.sl.pop.manufacture.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import apc.sl.pop.manufacture.service.PopManufactureService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class PopManufactureController {
	
	@Autowired
	private PopManufactureService popManufactureService;
	
	@RequestMapping("/sl/pop/popMf/popMfList.do")
	public String popManufactureList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		
		if(searchVO.getSearchKeyword().length() > 17) {
			String searSpilt = searchVO.getSearchKeyword().substring(0,16);
			searchVO.setSearchKeyword(searSpilt);
		}
		
		if(model.get("sear") != null) {
			Map<String, Object> temp = (Map<String, Object>) model.get("sear");
			searchVO.setSearchKeyword(temp.get("searchKeyword")+"");	
		}
		int totCnt = popManufactureService.selectMfListToCnt(searchVO);
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
		List<?> ordList = popManufactureService.selectMfList(searchVO);
		List<?> mfmList = popManufactureService.selectMfManager();
		model.put("mfmList", mfmList);
		model.put("ordList", ordList);
		model.put("paginationInfo", paginationInfo);
		
		
		return "sl/pop/popManufacture/popManufactureList";
	}
	
	@RequestMapping(value="/sl/pop/popMf/goMf.do" , method=RequestMethod.POST)
	public String goManufacture(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		if(!map.get("searchKeyword").equals("")) {
			redirectAttributes.addFlashAttribute("sear",map);
		}
		map.put("userId", session.getAttribute("user_id"));
		
		popManufactureService.registMfLog(map);
		popManufactureService.updateOrState(map);
		
		return "redirect:/sl/pop/popMf/popMfList.do";
	}
	
	@RequestMapping(value="/sl/pop/popMf/reMf.do" , method=RequestMethod.POST)
	public String reManufacture(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(!map.get("searchKeyword").equals("")) {
			redirectAttributes.addFlashAttribute("sear",map);
		}
		map.put("userId", session.getAttribute("user_id"));
		
		popManufactureService.updateMfStopLog2(map);
		
		return "redirect:/sl/pop/popMf/popMfList.do";
	}
	
	@RequestMapping(value="/sl/pop/popMf/stopMf.do" , method=RequestMethod.POST)
	public String stopManufacture(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		if(!map.get("searchKeyword").equals("")) {
			redirectAttributes.addFlashAttribute("sear",map);
		}
		map.put("userId", session.getAttribute("user_id"));
		
		popManufactureService.registMfStopLog(map);
		
		return "redirect:/sl/pop/popMf/popMfList.do";
	}
	
	@RequestMapping(value="/sl/pop/popMf/finishMf.do" , method=RequestMethod.POST)
	public String finishManufacture(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		
		if(!map.get("searchKeyword").equals("")) {
			redirectAttributes.addFlashAttribute("sear",map);
		}
		map.put("userId", session.getAttribute("user_id"));
		int checkProd = popManufactureService.selectCheckStop(map);
		if(checkProd != 0) {
			
			redirectAttributes.addFlashAttribute("msg", "공정을 재개하여 주십시오.");
			return "redirect:/sl/pop/popMf/popMfList.do";
			}
		redirectAttributes.addFlashAttribute("msg", "작업이 완료되었습니다.");
		popManufactureService.updateProcess3(map);
		popManufactureService.updateLogEdtime(map);
		
		return "redirect:/sl/pop/popMf/popMfList.do";
	}
}
