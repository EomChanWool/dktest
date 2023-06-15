package apc.cmn;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import apc.sl.basicInfo.authority.service.AuthorityService;
import apc.util.ScriptAlert;


public class CommonInterceptor extends HandlerInterceptorAdapter {
	@Inject
	private AuthorityService authorityService;
	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		String str1 = request.getRequestURL().toString();
//		String[] str2 = str1.split("/");
//		int accessLevel = authorityService.selectAccessLevel(str2[5]);
//		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("memberVO");
//		if(session.getAttribute("user_id") == null) {
//			ScriptAlert.alertAndMovePage(response, "접근 권한이 없습니다","/sl/main.do");
//			return false;
//		}else {
//			if(accessLevel > Integer.parseInt(userMap.get("mLev")+"")) {
//				ScriptAlert.alertAndMovePage(response, "접근 권한이 없습니다","/sl/material/income/incomeList.do");
//				return false;
//			}
//		}
//		return true;
//	}
	
	public void goMain(HttpServletRequest request, HttpServletResponse response,String msg,String place) throws Exception {
		FlashMap flashMap = new FlashMap();
		flashMap.put("msg", msg);
		FlashMapManager flashMapManager = RequestContextUtils.getFlashMapManager(request);
		flashMapManager.saveOutputFlashMap(flashMap, request, response);
		response.sendRedirect(request.getContextPath()+"/"+place+"/main.do");
	}
}
