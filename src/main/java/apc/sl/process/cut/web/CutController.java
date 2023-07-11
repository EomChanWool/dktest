package apc.sl.process.cut.web;

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

import apc.sl.facility.failReport.service.FailReportService;
import apc.sl.process.cut.service.CutService;
import apc.util.SearchVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class CutController {
	@Autowired
	private CutService cutService;
	@Autowired
	private FailReportService failReportService;
	
	@RequestMapping("/sl/process/cutProcess/cutList.do")
	public String cutList(@ModelAttribute("searchVO") SearchVO searchVO, ModelMap model, HttpSession session) {
		int totCnt = cutService.selectCutListToCnt(searchVO);
		
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
		List<?> cutList = cutService.selectCutList(searchVO);
		model.put("cutList", cutList);
		model.put("paginationInfo", paginationInfo);
		return "sl/process/cut/cutList";
	}
	
	@RequestMapping("/sl/production/cut/registCut.do")
	public String registCut(ModelMap model) {
		List<?> workOrderList = cutService.selectWorkOrderList("품질검사");
		model.put("workOrderList", workOrderList);
		List<?> documentList = cutService.selectDocumentList("검사기준서");
		model.put("documentList", documentList);
		return "sl/process/cut/cutRegist";
	}
	
	@RequestMapping(value="/sl/process/cut/registAnalysisDataAjax.do", method=RequestMethod.POST)
	public ModelAndView registNonOperFacilityListAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		//cutService.registAnalysisData(map);
		Map<String, Object> list = cutService.selectAzIdx();
		mav.setViewName("jsonView");
		mav.addObject("analysis_ajax", list);
		return mav;
	}
	
	@RequestMapping(value="/sl/process/cut/registAnalysisDataAjax2.do", method=RequestMethod.POST)
	public ModelAndView registAnalysisDataAjax2(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> list = cutService.selectAzIdxData();
		mav.setViewName("jsonView");
		mav.addObject("analysis_ajax2", list);
		
		return mav;
	}
	
	@RequestMapping("/sl/production/cut/registCutOk.do")
	public String registCutOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//작업지시서 체크
		int exists = cutService.selectExistsAzIdx(map);
		
		if(exists == 0) {
			redirectAttributes.addFlashAttribute("msg", "존재하지 않는 작업지서번호 입니다.");
			redirectAttributes.addFlashAttribute("cutVO", map);
			return "redirect:/sl/production/cut/registCut.do";
		}
		
		
		
		
		//성적서 체크
		if(!map.get("doIdx").equals("")) {
			exists = cutService.selectExistsDocumentIdx(map);
			if(exists == 0) {
				redirectAttributes.addFlashAttribute("msg", "존재하지 않는 성적서번호 입니다.");
				redirectAttributes.addFlashAttribute("cutVO", map);
				return "redirect:/sl/production/cut/registCut.do";
			}
			map.put("state", "1");
			cutService.updateDocumnetState(map);
		}
		
		map.put("userId", session.getAttribute("user_id"));
		cutService.registCut(map);
		
		
		
		
		
	
		 
		 
		
		//sm_prod_result에 wo_idx가 없으면 생산실적에도 등록
		map.put("prNm","품질검사");
		exists = cutService.selectExistsProdResult(map);
		
		if(exists == 0) {
			map.put("cut", "true");
			map.put("prReEdD0te", map.get("tiDte"));
			map.put("prReManager", map.get("tiAnalyst"));
			map.put("prReState", "1");
			
			updateProcess(map);
		}
		
		redirectAttributes.addFlashAttribute("msg", "등록 되었습니다.");
		return "redirect:/sl/process/cut/cutList.do";
	}
	
	@RequestMapping("/sl/production/cut/modifyCut.do")
	public String modifyCut(@RequestParam Map<String, Object> map, ModelMap model) {
		List<?> workOrderList = cutService.selectWorkOrderList("품질검사");
		model.put("workOrderList", workOrderList);
		List<?> documentList = cutService.selectDocumentList("검사기준서");
		model.put("documentList", documentList);
		
		if(!map.isEmpty()) {
			Map<String, Object> detail = cutService.selectCutInfo(map);
			model.put("cutVO", detail);
		}
		return "sl/process/cut/cutModify";
	}
	
	@RequestMapping(value="/sl/process/cut/modifyAnalysisDataAjax.do", method=RequestMethod.POST)
	public ModelAndView modifyNonOperFacilityListAjax(@RequestParam Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		cutService.modifyAnalysisData(map);
		mav.setViewName("jsonView");
		mav.addObject("analysis_ajax", "");
		return mav;
	}
	
	@RequestMapping("/sl/production/cut/modifyCutOk.do")
	public String modfiyCutOk(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		//성적서 체크
		if(!map.get("doIdx").equals("")) {
			int exists = cutService.selectExistsDocumentIdx(map);
			if(exists == 0) {
				redirectAttributes.addFlashAttribute("msg", "존재하지 않는 성적서번호 입니다.");
				redirectAttributes.addFlashAttribute("cutVO", map);
				return "redirect:/sl/production/cut/registCut.do";
			}
		}
		
		map.put("userId", session.getAttribute("user_id"));
		cutService.modifyCut(map);
		
		if(!map.get("doIdx").equals("")) {
			map.put("state", "1");
			cutService.updateDocumnetState(map);
		}
		
		//성적서가 변경되었거나 없어졌으면 이전 성적서 상태 변경
		if(!map.get("curDoIdx").equals(map.get("doIdx"))) {
			map.put("state", "0");
			map.replace("doIdx", map.get("curDoIdx"));
			cutService.updateDocumnetState(map);
		}
		if(map.get("tiState").equals("부적합")) { 
			
			cutService.updatePrReReSt(map);
		}
				
		redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
		return "redirect:/sl/process/cut/cutList.do";
	}
	
	@RequestMapping("/sl/production/cut/detailCut.do")
	public String detailCut(@RequestParam Map<String, Object> map, ModelMap model) {
		
		Map<String, Object> detail = cutService.detailAnalysis(map);
		
		model.put("analyVO", detail);
		
		
		
		return "sl/process/cut/cutDetail";
	}
	
	
	
	@RequestMapping("/sl/production/cut/deleteCut.do")
	public String deleteCut(@RequestParam Map<String, Object> map, RedirectAttributes redirectAttributes, HttpSession session) {
		cutService.deleteCut(map);
		map.put("state", "0");
		cutService.updateDocumnetState(map);
		System.out.println("맵확인 : " + map);
		
		//삭제시 공정변경
		Map<String, Object> process = failReportService.selectProcessSeqInfo(map);
		int processSeq = Integer.parseInt(process.get("prCurSeq")+"");
		map.put("nextIdx", "pr_list_idx"+(processSeq-1));
		map.put("nextNm", "pr_list_nm"+(processSeq-1));
		map.put("curSeq", processSeq-1);
		
		Map<String, Object> orderStat = cutService.selectOrderState(map);
		int orderState = Integer.parseInt(orderStat.get("orState")+"");
		
		if(orderState==1) {
			cutService.updateProcess2(map); 
			cutService.deleteProdResult(map);
		}
		
		
		
		
		redirectAttributes.addFlashAttribute("msg","삭제 되었습니다.");
		return "redirect:/sl/process/cut/cutList.do";
	}
	
	@RequestMapping("/sl/production/cut/graphCut.do")
	public String graphAnalyMange(ModelMap model) {
		Map<String, Object> list = cutService.selectAnalysisCnt();
		model.put("list", list);
		return "sl/process/cut/cutGraph";
	}
	
	private void updateProcess(Map<String, Object> map) {
		
		
		
		
		if(map.get("tiState").equals("부적합")) {
			
			map.put("prReReSt", map.get("tiState"));
//			prodResultService.updatePrReReSt(map);
			
		}
		if(!map.get("tiState").equals("부적합")) {
//			failReportService.registProdResult(map);
		}
		
		
		
	
		
		map.put("modify","true");
		Map<String, Object> process = failReportService.selectProcessSeqInfo(map);
		int processSeq = Integer.parseInt(process.get("prCurSeq")+"");
		System.out.println("확인 : " + processSeq);
		map.put("curSeq", processSeq);
		map.put("curStDte", "pr_st_time"+processSeq);
		map.put("curEdDte", "pr_ed_time"+processSeq);
		map.put("curCnt", "pr_cnt"+processSeq);
		map.put("curFaulty", "pr_faulty"+processSeq);
		map.put("nextIdx", "pr_list_idx"+(processSeq+1));
		map.put("nextNm", "pr_list_nm"+(processSeq+1));
		failReportService.updateProcess(map);
	
		
	}
}
