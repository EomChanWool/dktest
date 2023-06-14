package apc.sl.basicInfo.company.web;

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

import apc.sl.basicInfo.company.service.CompanyService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/sl/basicInfo/company/companyList.do")
	public String companyList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = companyService.selectCompanyListToCnt(searchVO);
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
		
		List<?> companyList = companyService.selectCompanyList(searchVO);
		model.put("companyList", companyList);
		model.put("paginationInfo", paginationInfo);
		
		return "sl/basicInfo/company/companyList";
	}
	
	@RequestMapping("/sl/basicInfo/company/registCompany.do")
	public String registCompany() {
		return "sl/basicInfo/company/companyRegist";
	}
	
	@RequestMapping("/sl/basicInfo/company/registCompanyOk.do")
	public String registCompanyOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		companyService.registCompany(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/company/companyList.do";
	}
	
	@RequestMapping("/sl/basicInfo/company/modifyCompany.do")
	public String modifyCompany(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = companyService.selectCompanyInfo(map);
		model.put("companyVO", detail);
		return "sl/basicInfo/company/companyModify";
	}
	
	@RequestMapping("/sl/basicInfo/company/modifyCompanyOk.do")
	public String modifyCompanyOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		companyService.modifyCompany(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/company/companyList.do";
	}
	
	@RequestMapping("/sl/basicInfo/company/deleteCompany.do")
	public String deleteCompany(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		companyService.deleteCompany(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/company/companyList.do";
	}
}
