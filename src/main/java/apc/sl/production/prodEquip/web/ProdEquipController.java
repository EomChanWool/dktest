package apc.sl.production.prodEquip.web;

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

import apc.sl.production.prodEquip.service.ProdEquipService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ProdEquipController {
	@Autowired
	private ProdEquipService prodEquipService;
	
	@RequestMapping("/sl/production/prodEquip/prodEquipList.do")
	public String prodEquipList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = prodEquipService.selectProdEquipListToCnt(searchVO);
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
		List<?> prodEquipList = prodEquipService.selectProdEquipList(searchVO);
		model.put("prodEquipList", prodEquipList);
		model.put("paginationInfo", paginationInfo);
		return "sl/production/prodEquip/prodEquipList";
	}
	
	@RequestMapping("/sl/production/prodEquip/registProdEquip.do")
	public String registProdEquip(ModelMap model) {
		List<?> processList = prodEquipService.selectProcessList();
		model.put("processList", processList);
		return "sl/production/prodEquip/prodEquipRegist";
	}
	
	@RequestMapping("/sl/production/prodEquip/registProdEquipOk.do")
	public String registProdEquipOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		prodEquipService.registProdEquip(map);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/production/prodEquip/prodEquipList.do";
	}
	
	@RequestMapping("/sl/production/prodEquip/modifyProdEquip.do")
	public String modifyProdEquip(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> processList = prodEquipService.selectProcessList();
		model.put("processList", processList);
		
		Map<String, Object> detail = prodEquipService.selectProdEquipInfo(map);
		model.put("prodEquipVO", detail);
		return "sl/production/prodEquip/prodEquipModify";
	}
	
	@RequestMapping("/sl/production/prodEquip/modifyProdEquipOk.do")
	public String modifyProdEquipOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		prodEquipService.modifyProdEquip(map);
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/production/prodEquip/prodEquipList.do";
	}
	
	@RequestMapping("/sl/production/prodEquip/deleteProdEquip.do")
	public String deleteProdEquip(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		prodEquipService.deleteProdEquip(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/production/prodEquip/prodEquipList.do";
	}
}
