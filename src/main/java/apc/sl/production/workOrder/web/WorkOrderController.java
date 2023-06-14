package apc.sl.production.workOrder.web;

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

import apc.sl.production.workOrder.service.WorkOrderService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class WorkOrderController {
	@Autowired
	private WorkOrderService workOrderService;
	
	@RequestMapping("/sl/production/workOrder/workOrderList.do")
	public String workOrderList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = workOrderService.selectWorkOrderListToCnt(searchVO);
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
		List<?> workOrderList = workOrderService.selectWorkOrderList(searchVO);
		model.put("workOrderList", workOrderList);
		model.put("paginationInfo", paginationInfo);
		return "sl/production/workOrder/workOrderList";
	}
	
	@RequestMapping("/sl/production/workOrder/registWorkOrder.do")
	public String registWorkOrder(ModelMap model) {
		List<?> materialList = workOrderService.selectMaterialList();
		model.put("materialList", materialList);
		List<?> prodList = workOrderService.selectProductList();
		model.put("prodList", prodList);
		return "sl/production/workOrder/workOrderRegist";
	}
	
	@RequestMapping(value="/sl/production/workOrder/woMaterialInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView woMaterialInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> mtStd = workOrderService.selectMaterialStd(map);
		mav.setViewName("jsonView");
		mav.addObject("mt_info", mtStd);
		return mav;
	}
	
	@RequestMapping("/sl/production/workOrder/registWorkOrderOk.do")
	public String registWorkOrderOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//재고확인
		for(int i=1;i<=15;i++) {
			if(map.get("itemCd"+i) == null) break;
			
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", map.get("cnt"+i));
			int pass = workOrderService.checkItemStock(temp);
			
			if(pass == 0) {
				redirectAttributes.addFlashAttribute("msg", map.get("itemName"+i)+"의 재고가 부족합니다.");
				redirectAttributes.addFlashAttribute("workOrderVO", map);
				return "redirect:/sl/production/workOrder/registWorkOrder.do";
			}
		}
		
		//재고 갱신
		for(int i=1;i<=10;i++) {
			if(map.get("itemCd"+i) == null) break;
			
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", "-"+map.get("cnt"+i));
			workOrderService.updateMaterialStock(temp);
		}
		
		map.put("userId", session.getAttribute("user_id"));
		workOrderService.registWorkOrder(map);
		workOrderService.registInMaterial(map);
		workOrderService.registInsertInfo(map); 
		//해당 작지에 대한 공정목록 생성
		createProcess(map, session);
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/production/workOrder/workOrderList.do";
	}
	
	@RequestMapping("/sl/production/workOrder/modifyWorkOrder.do")
	public String modifyWorkOrder(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> itemList = workOrderService.selectMaterialList();
		model.put("itemList", itemList);
		List<?> prodList = workOrderService.selectProductList();
		model.put("prodList", prodList);
		
		if(!map.isEmpty()) {
			Map<String, Object> detail = workOrderService.selectWorkOrderInfo(map);
			model.put("workOrderVO", detail);
		}
		
		Map<String, Object> materialList = workOrderService.selectInMaterialList(map);
		List<Map<String, Object>> mtList = new ArrayList<>();
		for(int i=1;i<=15;i++) {
			Map<String, Object> temp = new HashMap<>();
			String itemCd = "itemCd"+i;
			String itemName = "itemName"+i;
			String cnt = "cnt"+i;
			if(materialList.get(itemCd) == null) {
				break;
			}
			String std = workOrderService.selectItemStd(materialList.get(itemCd)+"");
			
			temp.put("itemCd", materialList.get(itemCd));
			temp.put("itemName", materialList.get(itemName));
			temp.put("cnt", materialList.get(cnt));
			temp.put("std", std);
			mtList.add(temp);
		}
		model.put("mtList", mtList);
		return "sl/production/workOrder/workOrderModify";
	}
	
	@RequestMapping("/sl/production/workOrder/modifyWorkOrderOk.do")
	public String modifyWorkOrderOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//재고확인
		for(int i=1;i<=10;i++) {
			if(map.get("itemCd"+i) == null) break;
			
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", map.get("cnt"+i));
			int pass = workOrderService.checkItemStock(temp);
			
			if(pass == 0) {
				redirectAttributes.addFlashAttribute("msg", map.get("itemName"+i)+"의 재고가 부족합니다.");
				redirectAttributes.addFlashAttribute("workOrderVO", map);
				return "redirect:/sl/production/workOrder/registWorkOrder.do";
			}
		}
		
		//이전 등록 자재들 수량 복구
		for(int i=1;i<=15;i++) {
			if(map.get("curItemCd"+i) == null) break;
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("curItemCd"+i));
			temp.put("cnt", map.get("curCnt"+i));
			workOrderService.updateMaterialStock(temp);
		}
		
		//새로 수정된 자재들 수량으로 갱신
		for(int i=1;i<=15;i++) {
			if(map.get("itemCd"+i) == null) break;
			Map<String, Object> temp = new HashMap<>();
			temp.put("itemCd", map.get("itemCd"+i));
			temp.put("cnt", "-"+map.get("cnt"+i));
			workOrderService.updateMaterialStock(temp);
		}
		
		workOrderService.modifyWorkOrder(map);
		workOrderService.modifyInMaterial(map);
		
		redirectAttributes.addFlashAttribute("msg","수정 되었습니다.");
		return "redirect:/sl/production/workOrder/workOrderList.do";
	}
	
	@RequestMapping("/sl/production/workOrder/detailWorkOrder.do")
	public String deatail(@RequestParam Map<String, Object> map, ModelMap model) {
		Map<String, Object> detail = workOrderService.selectWorkOrderInfo(map);
		model.put("workOrderVO", detail);
		return "sl/production/workOrder/workOrderDetail";
	}
	
	@RequestMapping("/sl/production/workOrder/deleteWorkOrder.do")
	public String deleteWorkOrder(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		workOrderService.deleteWorkOrder(map);
		workOrderService.deleteInsertInfo(map);
		workOrderService.deleteInMaterial(map);
		workOrderService.deleteProcess(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/production/workOrder/workOrderList.do";
	}
	
	private void createProcess(Map<String, Object> map, HttpSession session) {
		List<?> processList = workOrderService.selectProcessList(map);
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
		workOrderService.registProcess(process);
	}
}
