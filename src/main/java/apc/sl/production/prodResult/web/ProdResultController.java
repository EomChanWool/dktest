package apc.sl.production.prodResult.web;

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

import apc.sl.production.prodResult.service.ProdResultService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ProdResultController {
	@Autowired
	private ProdResultService prodResultService;
	
	@RequestMapping("/sl/production/prodResult/prodResultList.do")
	public String prodResultList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = prodResultService.selectProdResultListToCnt(searchVO);
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
		List<?> prResultList = prodResultService.selectProdResultList(searchVO);
		model.put("prResultList", prResultList);
		model.put("paginationInfo", paginationInfo);
		return "sl/production/prodResult/prodResultList";
	}
	
	@RequestMapping("/sl/production/prodResult/registProdResult.do")
	public String registProdResult(ModelMap model) {
		List<?> woList = prodResultService.selectWorkOrderList();
		model.put("woList", woList);
		return "sl/production/prodResult/prodResultRegist";
	}
	
	@RequestMapping(value="/sl/production/prodResult/prReWorkOrderInfoAjax.do", method=RequestMethod.POST)
	public ModelAndView prReWorkOrderInfoAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		List<?> list = prodResultService.selectWorkOrderInfo(map);
		mav.setViewName("jsonView");
		mav.addObject("wo_info", list);
		return mav;
	}
	
	@RequestMapping("/sl/production/prodResult/registProdResultOk.do")
	public String registProdResultOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//작업지시번호 체크
		int exists = prodResultService.selectExistsWorkOrderIdx(map);
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg","존재하지 않는 작업지시번호 입니다.");
			redirectAttributes.addFlashAttribute("prodResultVO", map);
			return "redirect:/sl/production/prodResult/registProdResult.do";
		}
		
		//이미 등록되었는지 체크
		exists = prodResultService.selectExistsProdResult(map);
		if(exists != 0) {
			redirectAttributes.addFlashAttribute("msg", "이미 존재하는 내역입니다.");
			redirectAttributes.addFlashAttribute("prodResultVO", map);
			return "redirect:/sl/production/prodResult/registProdResult.do";
		}
		map.put("userId", session.getAttribute("user_id"));
		prodResultService.registProdResult(map);
		
		//작업상태가 작업종료일때 sm_process갱신
		if(map.get("prReState").equals("1")) {
			//modify = true --> sm_process에서 현재 공정 순서,idx,nm 등 수정하겠음.
			map.put("modify","true");
			Map<String, Object> process = prodResultService.selectProcessSeqInfo(map);
			int processSeq = Integer.parseInt(process.get("prCurSeq")+"");
			map.put("curStTime", "pr_st_time"+processSeq);
			map.put("curEdTime", "pr_ed_time"+processSeq);
			map.put("curCnt", "pr_cnt"+processSeq);
			map.put("curFaulty", "pr_faulty"+processSeq);
			map.put("nextIdx", "pr_list_idx"+(processSeq+1));
			map.put("nextNm", "pr_list_nm"+(processSeq+1));
			
			map.put("userId", session.getAttribute("user_id"));
			prodResultService.updateProcess(map);
			updateProcess(process,map);
		}
		
		redirectAttributes.addFlashAttribute("msg","등록 되었습니다.");
		return "redirect:/sl/production/prodResult/prodResultList.do";
	}
	
	@RequestMapping("/sl/production/prodResult/modifyProdResult.do")
	public String modifyProdResult(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> woList = prodResultService.selectWorkOrderList();
		model.put("woList", woList);
		Map<String, Object> detail = prodResultService.selectProdResultInfo(map);
		model.put("prodResultVO", detail);
		return "sl/production/prodResult/prodResultModify";
	}
	
	@RequestMapping("/sl/production/prodResult/modifyProdResultOk.do")
	public String modifyProdResultOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		map.put("userId", session.getAttribute("user_id"));
		prodResultService.modifyProdResult(map);
		//작업상태가 작업종료일때 sm_process갱신
		if(map.get("prReState").equals("1")) {
			//modify = false --> sm_process에서 수정하고자 하는 순서의 공정시작시간, 공정종료시간, 생산수량, 불량수량만 갱신
			map.put("modify","false");
			Map<String, Object> process = prodResultService.selectProcessSeqInfo(map);
			int processSeq = Integer.parseInt(map.get("curSeq")+"");
			map.put("curStTime", "pr_st_time"+processSeq);
			map.put("curEdTime", "pr_ed_time"+processSeq);
			map.put("curCnt", "pr_cnt"+processSeq);
			map.put("curFaulty", "pr_faulty"+processSeq);
			
			if(map.get("curPrReState").equals("0") && map.get("prReState").equals("1")) {
				//이전 상태가 작업중이였다가 작업완료로 변경되었을 경우
				map.replace("modify", "true");
				map.put("nextIdx", "pr_list_idx"+(processSeq+1));
				map.put("nextNm", "pr_list_nm"+(processSeq+1));
			}
			
			map.put("userId", session.getAttribute("user_id"));
			prodResultService.updateProcess(map);
			if(map.get("curPrReState").equals("0") && map.get("prReState").equals("1")) {
				updateProcess(process,map);
			}
		}
		
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/production/prodResult/prodResultList.do";
	}
	
	@RequestMapping("/sl/production/prodResult/deleteProdResult.do")
	public String deleteProdResultOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		prodResultService.deleteProdResult(map);
		redirectAttributes.addFlashAttribute("msg", "삭제 되었습니다.");
		return "redirect:/sl/production/prodResult/prodResultList.do";
	}
	
	private void updateProcess(Map<String, Object> process, Map<String, Object> map) {
		//완료된 공정이 마지막 공정이면 sm_work_order, sm_orders 상태를 작업 완료로
		String lastProcessNm = prodResultService.selectLastProcessNm(map);
		if(process.get("prListCurNm") != null) {
			if(process.get("prListCurNm").equals(lastProcessNm)) {
				map.put("state", "2");
				prodResultService.updateWorkOrder(map);
				//실제 완제품(밸런싱 통과 수량)을 기반으로 투입 자재 재조정
				if(map.get("prReFaultyCnt") == "") {
					map.replace("prReFaultyCnt", 0);
				}
				int prodCnt = Integer.parseInt(map.get("prReCnt")+"") + Integer.parseInt(map.get("prReFaultyCnt")+"");
				Map<String, Object> in_mt = prodResultService.selectMaterialList(map);
				Map<String, Object> temp = new HashMap<>();
				temp.put("woIdx", map.get("woIdx"));
				for(int i=1;i<=15;i++) {
					Map<String, Object> temp2 = new HashMap<>();
					String itemCd = "itemCd"+i;
					String cnt = "cnt"+i;
					
					if(in_mt.get(itemCd) == null) break;
					temp.put(itemCd, in_mt.get(itemCd));
					temp2.put("itemCd", in_mt.get(itemCd));
					
					if(in_mt.get(itemCd).equals("MT01") || in_mt.get(itemCd).equals("MT02")) {
						temp.put(cnt, prodCnt*2);
						temp2.put("cnt", prodCnt*2);
					}else {
						temp.put(cnt, prodCnt);
						temp2.put("cnt", prodCnt);
					}
					int realCnt = Integer.parseInt(in_mt.get(cnt)+"") - Integer.parseInt(temp2.get("cnt")+"");
					temp2.replace("cnt", realCnt);
					
					prodResultService.updateMaterialStock(temp2);
				}
				prodResultService.updateInMaterial(temp);
				
				//제품 재고에 추가해줌
				prodResultService.addItemStock(map);
			}
		}
	}
}
