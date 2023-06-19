package apc.sl.basicInfo.user.web;

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

import apc.sl.basicInfo.user.service.UserService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/sl/basicInfo/user/userList.do")
	public String userList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = userService.selectUserListToCnt(searchVO);
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
		
		List<?> userList = userService.selectUserList(searchVO);
		model.put("userList", userList);
		model.put("paginationInfo", paginationInfo);
		
		return "sl/basicInfo/user/userList";
	}
	
	@RequestMapping("/sl/basicInfo/user/registUser.do")
	public String registUser() {
		return "sl/basicInfo/user/userRegist";
	}
	
	@RequestMapping("/sl/basicInfo/user/registUserOk.do")
	public String registUserOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		userService.registUser(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/basicInfo/user/userList.do";
	}
	
	@RequestMapping("/sl/basicInfo/user/modifyUser.do")
	public String modifyUser(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = userService.selectUserInfo(map);
		model.put("userVO", detail);
		return "sl/basicInfo/user/userModify";
	}
	
	@RequestMapping("/sl/basicInfo/user/modifyUserOk.do")
	public String modifyUserOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		userService.modifyUser(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/basicInfo/user/userList.do";
	}
	
	@RequestMapping("/sl/basicInfo/user/deleteUser.do")
	public String deleteUser(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		userService.deleteUser(map);
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/basicInfo/user/userList.do";
	}
}