package apc.sl.basicInfo.account.web;

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

import apc.sl.basicInfo.account.service.AccountService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/sl/basicInfo/account/accountList.do")
	public String accountList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = accountService.selectAccountListToCnt(searchVO);
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
		List<?> accountList = accountService.selectAccountList(searchVO);
		model.put("acList", accountList);
		model.put("paginationInfo", paginationInfo);
		return "sl/basicInfo/account/accountList";
	}
	
	@RequestMapping("/sl/basicInfo/account/registAccount.do")
	public String registAccount() {
		return "sl/basicInfo/account/accountRegist";
	}
	
	@RequestMapping("/sl/basicInfo/account/registAccountOk.do")
	public String registAccountOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		accountService.registAccount(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/account/accountList.do";
	}
	
	@RequestMapping("/sl/basicInfo/account/modifyAccount.do")
	public String modifyAccount(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = accountService.selectAccountInfo(map);
		model.put("accountVO", detail);
		return "sl/basicInfo/account/accountModify";
	}
	
	@RequestMapping("/sl/basicInfo/account/modifyAccountOk.do")
	public String modifyAccountOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		accountService.modifyAccount(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/account/accountList.do";
	}
	
	@RequestMapping("/sl/basicInfo/account/deleteAccount.do")
	public String deleteAccount(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		accountService.deleteAccount(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/account/accountList.do";
	}
}
