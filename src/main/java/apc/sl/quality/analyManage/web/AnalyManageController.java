package apc.sl.quality.analyManage.web;

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
import apc.sl.quality.analyManage.service.AnalyManageService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class AnalyManageController {
	@Autowired
	private AnalyManageService analyManageService;
	@Autowired
	private ProdResultService prodResultService;
	
	@RequestMapping("/sl/quality/analyManage/analyManageList.do")
	public String analyManageList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = analyManageService.selectAnalyManageListToCnt(searchVO);
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
		List<?> analyManageList = analyManageService.selectAnalyManageList(searchVO);
		model.put("analyManageList", analyManageList);
		model.put("paginationInfo", paginationInfo);
		return "sl/quality/analyManage/analyManageList";
	}
	
	@RequestMapping("/sl/production/analyManage/registAnalyManage.do")
	public String registAnalyManage(ModelMap model) {
		List<?> workOrderList = analyManageService.selectWorkOrderList("품질검사");
		model.put("workOrderList", workOrderList);
		List<?> documentList = analyManageService.selectDocumentList("검사기준서");
		model.put("documentList", documentList);
		return "sl/quality/analyManage/analyManageRegist";
	}
	
	@RequestMapping(value="/sl/quality/analyManage/registAnalysisDataAjax.do", method=RequestMethod.POST)
	public ModelAndView registNonOperFacilityListAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		//analyManageService.registAnalysisData(map);
		Map<String, Object> list = analyManageService.selectAzIdx();
		mav.setViewName("jsonView");
		mav.addObject("analysis_ajax", list);
		return mav;
	}
	
	@RequestMapping(value="/sl/quality/analyManage/registAnalysisDataAjax2.do", method=RequestMethod.POST)
	public ModelAndView registAnalysisDataAjax2(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> list = analyManageService.selectAzIdxData();
		mav.setViewName("jsonView");
		mav.addObject("analysis_ajax2", list);
		
		return mav;
	}
	
	@RequestMapping("/sl/production/analyManage/registAnalyManageOk.do")
	public String registAnalyManageOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//작업지시서 체크
		int exists = analyManageService.selectExistsAzIdx(map);
		
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 작업지서번호 입니다.");
			redirectAttributes.addFlashAttribute("analyManageVO", map);
			return "redirect:/sl/production/analyManage/registAnalyManage.do";
		}
		
		
		
		
		//성적서 체크
		if(!map.get("doIdx").equals("")) {
			exists = analyManageService.selectExistsDocumentIdx(map);
			if(exists == 0) {
				redirectAttributes.addFlashAttribute("msg", "존재하지 않는 성적서번호 입니다.");
				redirectAttributes.addFlashAttribute("analyManageVO", map);
				return "redirect:/sl/production/analyManage/registAnalyManage.do";
			}
			map.put("state", "1");
			analyManageService.updateDocumnetState(map);
		}
		
		map.put("userId", session.getAttribute("user_id"));
		analyManageService.registAnalyManage(map);
		
		
		
		
		
	
		 
		 
		
		//sm_prod_result에 wo_idx가 없으면 생산실적에도 등록
		map.put("prNm","품질검사");
		exists = analyManageService.selectExistsProdResult(map);
		
		if(exists == 0) {
			map.put("analyManage", "true");
			map.put("prReEdD0te", map.get("tiDte"));
			map.put("prReManager", map.get("tiAnalyst"));
			map.put("prReState", "1");
			
			updateProcess(map);
		}
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/quality/analyManage/analyManageList.do";
	}
	
	@RequestMapping("/sl/production/analyManage/modifyAnalyManage.do")
	public String modifyAnalyManage(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> workOrderList = analyManageService.selectWorkOrderList("품질검사");
		model.put("workOrderList", workOrderList);
		List<?> documentList = analyManageService.selectDocumentList("검사기준서");
		model.put("documentList", documentList);
		
		if(!map.isEmpty()) {
			Map<String, Object> detail = analyManageService.selectAnalyManageInfo(map);
			model.put("analyManageVO", detail);
		}
		return "sl/quality/analyManage/analyManageModify";
	}
	
	@RequestMapping(value="/sl/quality/analyManage/modifyAnalysisDataAjax.do", method=RequestMethod.POST)
	public ModelAndView modifyNonOperFacilityListAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		analyManageService.modifyAnalysisData(map);
		mav.setViewName("jsonView");
		mav.addObject("analysis_ajax", "");
		return mav;
	}
	
	@RequestMapping("/sl/production/analyManage/modifyAnalyManageOk.do")
	public String modfiyAnalyManageOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//성적서 체크
		if(!map.get("doIdx").equals("")) {
			int exists = analyManageService.selectExistsDocumentIdx(map);
			if(exists == 0) {
				redirectAttributes.addFlashAttribute("msg", "존재하지 않는 성적서번호 입니다.");
				redirectAttributes.addFlashAttribute("analyManageVO", map);
				return "redirect:/sl/production/analyManage/registAnalyManage.do";
			}
		}
		
		map.put("userId", session.getAttribute("user_id"));
		analyManageService.modifyAnalyManage(map);
		
		if(!map.get("doIdx").equals("")) {
			map.put("state", "1");
			analyManageService.updateDocumnetState(map);
		}
		
		//성적서가 변경되었거나 없어졌으면 이전 성적서 상태 변경
		if(!map.get("curDoIdx").equals(map.get("doIdx"))) {
			map.put("state", "0");
			map.replace("doIdx", map.get("curDoIdx"));
			analyManageService.updateDocumnetState(map);
		}
		if(map.get("tiState").equals("부적합")) { 
			
			analyManageService.updatePrReReSt(map);
		}
				
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/quality/analyManage/analyManageList.do";
	}
	
	@RequestMapping("/sl/production/analyManage/detailAnalyManage.do")
	public String detailAnalyManage(@RequestParam Map<String, Object> map, ModelMap model) {
		
		Map<String, Object> detail = analyManageService.detailAnalysis(map);
		
		model.put("analyVO", detail);
		
		
		
		return "sl/quality/analyManage/analyManageDetail";
	}
	
	
	
	@RequestMapping("/sl/production/analyManage/deleteAnalyManage.do")
	public String deleteAnalyManage(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		analyManageService.deleteAnalyManage(map);
		map.put("state", "0");
		analyManageService.updateDocumnetState(map);
		System.out.println("맵확인 : " + map);
		
		//삭제시 공정변경
		Map<String, Object> process = prodResultService.selectProcessSeqInfo(map);
		int processSeq = Integer.parseInt(process.get("prCurSeq")+"");
		map.put("nextIdx", "pr_list_idx"+(processSeq-1));
		map.put("nextNm", "pr_list_nm"+(processSeq-1));
		map.put("curSeq", processSeq-1);
		
		Map<String, Object> orderStat = analyManageService.selectOrderState(map);
		int orderState = Integer.parseInt(orderStat.get("orState")+"");
		
		if(orderState==1) {
			analyManageService.updateProcess2(map); 
			analyManageService.deleteProdResult(map);
		}
		
		
		
		
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/quality/analyManage/analyManageList.do";
	}
	
	@RequestMapping("/sl/production/analyManage/graphAnalyManage.do")
	public String graphAnalyMange(ModelMap model) {
		Map<String, Object> list = analyManageService.selectAnalysisCnt();
		model.put("list", list);
		return "sl/quality/analyManage/analyManageGraph";
	}
	
	private void updateProcess(Map<String, Object> map) {
		
		
		
		
		if(map.get("tiState").equals("부적합")) {
			
			map.put("prReReSt", map.get("tiState"));
//			prodResultService.updatePrReReSt(map);
			
		}
		if(!map.get("tiState").equals("부적합")) {
			prodResultService.registProdResult(map);
		}
		
		
		
	
		
		map.put("modify","true");
		Map<String, Object> process = prodResultService.selectProcessSeqInfo(map);
		int processSeq = Integer.parseInt(process.get("prCurSeq")+"");
		System.out.println("확인 : " + processSeq);
		map.put("curSeq", processSeq);
		map.put("curStDte", "pr_st_time"+processSeq);
		map.put("curEdDte", "pr_ed_time"+processSeq);
		map.put("curCnt", "pr_cnt"+processSeq);
		map.put("curFaulty", "pr_faulty"+processSeq);
		map.put("nextIdx", "pr_list_idx"+(processSeq+1));
		map.put("nextNm", "pr_list_nm"+(processSeq+1));
		prodResultService.updateProcess(map);
	
		
	}
}
