package apc.sl.basicInfo.authority.web;

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

import apc.sl.basicInfo.authority.service.AuthorityService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class AuthorityController {
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping("/sl/basicInfo/authority/authorityList.do")
	public String authorityList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = authorityService.selectAuthorityToCnt(searchVO);
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
		List<?> authorityList = authorityService.selectAuthorityList(searchVO);
		model.put("authorityList", authorityList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/authority/authorityList";
	}
	
	@RequestMapping("/sl/basicInfo/authority/registAuthority.do")
	public String registAuthority(ModelMap model) {
		List<?> programNameList = authorityService.selectProgramName();
		model.put("programNameList", programNameList);
		return "sl/basicInfo/authority/authorityRegist";
	}
	
	@RequestMapping("/sl/basicInfo/authority/registAuthorityOk.do")
	public String registAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		authorityService.registAuthority(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/authority/authorityList.do";
	}
	
	@RequestMapping("/sl/basicInfo/authority/modifyAuthority.do")
	public String modifyAuthority(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = authorityService.selectAuthorityInfo(map);
		List<?> programNameList = authorityService.selectProgramName();
		model.put("programNameList", programNameList);
		model.put("authorityVO", detail);
		return "sl/basicInfo/authority/authorityModify";
	}
	
	@RequestMapping("/sl/basicInfo/authority/modifyAuthorityOk.do")
	public String modifyAuthorityOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		authorityService.modifyAuthority(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/authority/authorityList.do";
	}
	
	@RequestMapping("/sl/basicInfo/authority/deleteAuthority.do")
	public String deleteAuthority(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		authorityService.deleteAuthority(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/authority/authorityList.do";
	}
}
