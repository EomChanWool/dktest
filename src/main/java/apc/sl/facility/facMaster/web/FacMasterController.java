package apc.sl.facility.facMaster.web;

import java.util.ArrayList;
import java.util.HashMap;
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

import apc.sl.facility.facMaster.service.FacMasterService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class FacMasterController {
	@Autowired
	private FacMasterService facMasterService;
	
	@RequestMapping("/sl/facility/facMaster/facMasterList.do")
	public String facMasterList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = facMasterService.selectFacMasterListToCnt(searchVO);
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
		List<?> facMasterList = facMasterService.selectFacMasterList(searchVO);
		model.put("facMasterList", facMasterList);
		model.put("paginationInfo", paginationInfo);
		return "sl/facility/facMaster/facMasterList";
	}
	
	@RequestMapping("/sl/facility/facMaster/registFacMaster.do")
	public String registFacMaster(ModelMap model) {
		List<?> materialList = facMasterService.selectMaterialList();
		model.put("materialList", materialList);
		List<?> prodList = facMasterService.selectProductList();
		model.put("prodList", prodList);
		return "sl/facility/facMaster/facMasterRegist";
	}
	
	@RequestMapping(value="/sl/facility/facMaster/woMaterialInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView woMaterialInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> mtStd = facMasterService.selectMaterialStd(map);
		mav.setViewName("jsonView");
		mav.addObject("mt_info", mtStd);
		return mav;
	}
	
	@RequestMapping("/sl/facility/facMaster/registFacMasterOk.do")
	public String registFacMasterOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//재고확인
		for(int i=1;i<=15;i++) {
			if(map.get("itemCd"+i) == null) break;
			
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", map.get("cnt"+i));
			int pass = facMasterService.checkItemStock(temp);
			
			if(pass == 0) {
				redirectAttributes.addFlashAttribute("msg", map.get("itemName"+i)+"의 재고가 부족합니다.");
				redirectAttributes.addFlashAttribute("facMasterVO", map);
				return "redirect:/sl/facility/facMaster/registFacMaster.do";
			}
		}
		
		//재고 갱신
		for(int i=1;i<=10;i++) {
			if(map.get("itemCd"+i) == null) break;
			
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", "-"+map.get("cnt"+i));
			facMasterService.updateMaterialStock(temp);
		}
		
		map.put("userId", session.getAttribute("user_id"));
		facMasterService.registFacMaster(map);
		facMasterService.registInMaterial(map);
		facMasterService.registInsertInfo(map); 
		//해당 작지에 대한 공정목록 생성
		createProcess(map, session);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/facility/facMaster/facMasterList.do";
	}
	
	@RequestMapping("/sl/facility/facMaster/modifyFacMaster.do")
	public String modifyFacMaster(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> itemList = facMasterService.selectMaterialList();
		model.put("itemList", itemList);
		List<?> prodList = facMasterService.selectProductList();
		model.put("prodList", prodList);
		
		if(!map.isEmpty()) {
			Map<String, Object> detail = facMasterService.selectFacMasterInfo(map);
			model.put("facMasterVO", detail);
		}
		
		Map<String, Object> materialList = facMasterService.selectInMaterialList(map);
		List<Map<String, Object>> mtList = new ArrayList<>();
		for(int i=1;i<=15;i++) {
			Map<String, Object> temp = new HashMap<>();
			String itemCd = "itemCd"+i;
			String itemName = "itemName"+i;
			String cnt = "cnt"+i;
			if(materialList.get(itemCd) == null) {
				break;
			}
			String std = facMasterService.selectItemStd(materialList.get(itemCd)+"");
			
			temp.put("itemCd", materialList.get(itemCd));
			temp.put("itemName", materialList.get(itemName));
			temp.put("cnt", materialList.get(cnt));
			temp.put("std", std);
			mtList.add(temp);
		}
		model.put("mtList", mtList);
		return "sl/facility/facMaster/facMasterModify";
	}
	
	@RequestMapping("/sl/facility/facMaster/modifyFacMasterOk.do")
	public String modifyFacMasterOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//재고확인
		for(int i=1;i<=10;i++) {
			if(map.get("itemCd"+i) == null) break;
			
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", map.get("cnt"+i));
			int pass = facMasterService.checkItemStock(temp);
			
			if(pass == 0) {
				redirectAttributes.addFlashAttribute("msg", map.get("itemName"+i)+"의 재고가 부족합니다.");
				redirectAttributes.addFlashAttribute("facMasterVO", map);
				return "redirect:/sl/facility/facMaster/registFacMaster.do";
			}
		}
		
		//이전 등록 자재들 수량 복구
		for(int i=1;i<=15;i++) {
			if(map.get("curItemCd"+i) == null) break;
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("curItemCd"+i));
			temp.put("cnt", map.get("curCnt"+i));
			facMasterService.updateMaterialStock(temp);
		}
		
		//새로 수정된 자재들 수량으로 갱신
		for(int i=1;i<=15;i++) {
			if(map.get("itemCd"+i) == null) break;
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", "-"+map.get("cnt"+i));
			facMasterService.updateMaterialStock(temp);
		}
		
		facMasterService.modifyFacMaster(map);
		facMasterService.modifyInMaterial(map);
		
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/facility/facMaster/facMasterList.do";
	}
	
	@RequestMapping("/sl/facility/facMaster/detailFacMaster.do")
	public String deatail(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = facMasterService.selectFacMasterInfo(map);
		model.put("facMasterVO", detail);
		return "sl/facility/facMaster/facMasterDetail";
	}
	
	@RequestMapping("/sl/facility/facMaster/deleteFacMaster.do")
	public String deleteFacMaster(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		facMasterService.deleteFacMaster(map);
		facMasterService.deleteInsertInfo(map);
		facMasterService.deleteInMaterial(map);
		facMasterService.deleteProcess(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/facility/facMaster/facMasterList.do";
	}
	
	private void createProcess(Map<String, Object> map, HttpSession session) {
		List<?> processList = facMasterService.selectProcessList(map);
		Map<String, Object> process = new HashMap<>();
		for(int i=0;i<processList.size();i++) {
			String[] str1 = processList.get(i).toString().split(", ");
			String[] idx = str1[0].split("=");
			String[] nm = str1[2].split("=");
			process.put("idx"+(i+1), idx[1]);
			process.put("nm"+(i+1), nm[1].substring(0, nm[1].length()-1));
		}
		process.put("woItnDte", map.get("woItnDte"));
		process.put("totCnt", processList.size());
		process.put("userId", session.getAttribute("user_id"));
		facMasterService.registProcess(process);
	}
}
