<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Sidebar - Brand -->
<a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/sl/monitoring/dashBoard.do">
    <div class="sidebar-brand-icon rotate-n-15">
<!--         <i class="fas fa-laugh-wink"></i> -->
		<img alt="정인로고" src="/resources/conf/images/jungin.png" style="width: 55px; transform: rotate(15deg);">
    </div>
    <div class="sidebar-brand-text mx-3">(주)정인테크</div>
</a>

<!-- Divider -->
<hr class="sidebar-divider my-0">
<c:if test="${mLevel.P0030 <= memberVO.mLev}">
<li class="nav-item" id="dashboard">
    <a class="nav-link" href="${pageContext.request.contextPath}/sl/monitoring/dashBoard.do">
        <i class="fas fa-fw fa-tachometer-alt"></i>
        <span>Dashboard</span>
    </a>
</li>
</c:if>
<!-- Divider -->
<hr class="sidebar-divider">
<c:if test="${mLevel.P0001 <= memberVO.mLev || mLevel.P0002 <= memeberVO.mLev || mLevel.P0003 <= memberVO.mLev || mLevel.P0004 <= memberVO.mLev || mLevel.P0005 <= memberVO.mLev || mLevel.P0006 <= memberVO.mLev || mLevel.P0007 <= memberVO.mLev}">
<li class="nav-item" id="basicInfoMenu">
	<a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#basicInfo"
	    aria-expanded="true" aria-controls="basicInfo">
	    <i class="fas fa-fw fa-cog"></i>
	    <span>기준정보관리</span>
	</a>
	<div id="basicInfo" class="collapse" aria-labelledby="headingBasicInfo" data-parent="#accordionSidebar">
	   	<div class="bg-white py-2 collapse-inner rounded">
		    <c:if test="${mLevel.P0001 <= memberVO.mLev}"><a class="collapse-item" id="companyList" href="${pageContext.request.contextPath}/sl/basicInfo/company/companyList.do">사업장관리</a></c:if>
		    <c:if test="${mLevel.P0002 <= memberVO.mLev}"><a class="collapse-item" id="accountList" href="${pageContext.request.contextPath}/sl/basicInfo/account/accountList.do">거래처관리</a></c:if>
		    <c:if test="${mLevel.P0003 <= memberVO.mLev}"><a class="collapse-item" id="itemList" href="${pageContext.request.contextPath}/sl/basicInfo/item/itemList.do">품목코드관리</a></c:if>
		    <c:if test="${mLevel.P0004 <= memberVO.mLev}"><a class="collapse-item" id="memberList" href="${pageContext.request.contextPath}/sl/basicInfo/member/memberList.do">사용자관리</a></c:if>
		    <c:if test="${mLevel.P0005 <= memberVO.mLev}"><a class="collapse-item" id="programList" href="${pageContext.request.contextPath}/sl/basicInfo/program/programList.do">프로그램관리</a></c:if>
		    <c:if test="${mLevel.P0006 <= memberVO.mLev}"><a class="collapse-item" id="authorityList" href="${pageContext.request.contextPath}/sl/basicInfo/authority/authorityList.do">권한관리</a></c:if>
		    <c:if test="${mLevel.P0007 <= memberVO.mLev}"><a class="collapse-item" id="systemLogList" href="${pageContext.request.contextPath}/sl/basicInfo/systemLog/systemLogList.do">시스템사용로그</a></c:if>
		</div>
	</div>
</li>
</c:if>
<c:if test="${mLevel.P0008 <= memberVO.mLev || mLevel.P0009 <= memberVO.mLev || mLevel.P0010 <= memberVO.mLev || mLevel.P0011 <= memberVO.mLev || mLevel.P0012 <= memberVO.mLev || mLevel.P0013 <= memberVO.mLev}">
<li class="nav-item" id="salesMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#sales"
        aria-expanded="true" aria-controls="sales">
        <i class="fas fa-fw fa-folder"></i>
        <span>영업관리</span>
    </a>
    <div id="sales" class="collapse" aria-labelledby="headingSales" data-parent="#accordionSidebar">
        <div class="bg-white py-2 collapse-inner rounded">
		    <c:if test="${mLevel.P0008 <= memberVO.mLev}"><a class="collapse-item" id="estimateList" href="${pageContext.request.contextPath}/sl/sales/estimate/estimateList.do">견적서관리</a></c:if>
		    <c:if test="${mLevel.P0009 <= memberVO.mLev}"><a class="collapse-item" id="ordersList" href="${pageContext.request.contextPath}/sl/sales/ordersManage/ordersList.do">수주관리</a></c:if>
		    <c:if test="${mLevel.P0010 <= memberVO.mLev}"><a class="collapse-item" id="deliveryList" href="${pageContext.request.contextPath}/sl/sales/delivery/deliveryList.do">납품관리</a></c:if>
		    <c:if test="${mLevel.P0011 <= memberVO.mLev}"><a class="collapse-item" id="collectMoneyList" href="${pageContext.request.contextPath}/sl/sales/collectMoney/collectMoneyList.do">수금관리</a></c:if>
		    <c:if test="${mLevel.P0012 <= memberVO.mLev}"><a class="collapse-item" id="stateAggregate" href="${pageContext.request.contextPath}/sl/sales/stateAggregate/stateAggregate.do">현황 및 집계</a></c:if>
		    <c:if test="${mLevel.P0013 <= memberVO.mLev}"><a class="collapse-item" id="documentList" href="${pageContext.request.contextPath}/sl/sales/document/documentList.do">문서관리</a></c:if>
		</div>
    </div>
</li>
</c:if>
<c:if test="${mLevel.P0014 <= memberVO.mLev || mLevel.P0015 <= memberVO.mLev || mLevel.P0016 <= memberVO.mLev || mLevel.P0017 <= memberVO.mLev || mLevel.P0018 <= memberVO.mLev || mLevel.P0019 <= memberVO.mLev}">
<li class="nav-item" id="materialMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#material"
        aria-expanded="true" aria-controls="material">
        <i class="fas fa-fw fa-folder"></i>
        <span>자재/출하관리</span>
    </a>
    <div id="material" class="collapse" aria-labelledby="headingMaterial" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <c:if test="${mLevel.P0014 <= memberVO.mLev}"><a class="collapse-item" id="incomeList" href="${pageContext.request.contextPath}/sl/material/income/incomeList.do">입고관리</a></c:if>
		    <c:if test="${mLevel.P0015 <= memberVO.mLev}"><a class="collapse-item" id="investList" href="${pageContext.request.contextPath}/sl/material/invest/investList.do">투입관리</a></c:if>
		    <c:if test="${mLevel.P0016 <= memberVO.mLev}"><a class="collapse-item" id="stockStateList" href="${pageContext.request.contextPath}/sl/material/mtStockState/stockStateList.do">재고현황조회</a></c:if>
		    <c:if test="${mLevel.P0017 <= memberVO.mLev}"><a class="collapse-item" id="stockAdjustList" href="${pageContext.request.contextPath}/sl/material/stockAdjust/stockAdjustList.do">재고조정관리</a></c:if>
		    <c:if test="${mLevel.P0018 <= memberVO.mLev}"><a class="collapse-item" id="outsourcingList" href="${pageContext.request.contextPath}/sl/material/outsourcing/outsourcingList.do">외주 출고/입고 관리</a></c:if>
		    <c:if test="${mLevel.P0019 <= memberVO.mLev}"><a class="collapse-item" id="shipmentList" href="${pageContext.request.contextPath}/sl/material/shipment/shipmentList.do">출하계획/지시관리</a></c:if>
		</div>
    </div>
</li>
</c:if>
<c:if test="${mLevel.P0020 <= memberVO.mLev || mLevel.P0021 <= memberVO.mLev || mLevel.P0022 <= memberVO.mLev || mLevel.P0023 <= memberVO.mLev || mLevel.P0024 <= memberVO.mLev || mLevel.P0025 <= memberVO.mLev}">
<li class="nav-item" id="productionMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#production"
        aria-expanded="true" aria-controls="production">
        <i class="fas fa-fw fa-folder"></i>
        <span>생산관리</span>
    </a>
    <div id="production" class="collapse" aria-labelledby="headingProduction" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <c:if test="${mLevel.P0020 <= memberVO.mLev}"><a class="collapse-item" id="workOrderList" href="${pageContext.request.contextPath}/sl/production/workOrder/workOrderList.do">생산계획/지시관리</a></c:if>
		    <c:if test="${mLevel.P0021 <= memberVO.mLev}"><a class="collapse-item" id="prodResultList" href="${pageContext.request.contextPath}/sl/production/prodResult/prodResultList.do">생산실적관리</a></c:if>
		    <c:if test="${mLevel.P0022 <= memberVO.mLev}"><a class="collapse-item" id="prodEquipList" href="${pageContext.request.contextPath}/sl/production/prodEquip/prodEquipList.do">생산설비정보관리</a></c:if>
		    <c:if test="${mLevel.P0023 <= memberVO.mLev}"><a class="collapse-item" id="prodCurStateList" href="${pageContext.request.contextPath}/sl/production/prodCurState/prodCurStateList.do">생산현황관리</a></c:if>
		    <c:if test="${mLevel.P0024 <= memberVO.mLev}"><a class="collapse-item" id="processChkList" href="${pageContext.request.contextPath}/sl/production/processChk/processChkList.do">공정설비관리</a></c:if>
		    <c:if test="${mLevel.P0025 <= memberVO.mLev}"><a class="collapse-item" id="kpiList" href="${pageContext.request.contextPath}/sl/production/kpi/kpiList.do">KPI관리</a></c:if>
		</div>
    </div>
</li>
</c:if>
<c:if test="${mLevel.P0026 <= memberVO.mLev || mLevel.P0027 <= memberVO.mLev || mLevel.P0028 <= memberVO.mLev || mLevel.P0029 <= memberVO.mLev}">
<li class="nav-item" id="monitoringMenu">
    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#monitoring"
        aria-expanded="true" aria-controls="monitoring">
        <i class="fas fa-fw fa-folder"></i>
        <span>모니터링</span>
    </a>
    <div id="monitoring" class="collapse" aria-labelledby="headingMonitoring" data-parent="#accordionSidebar">
    	<div class="bg-white py-2 collapse-inner rounded">
		    <c:if test="${mLevel.P0026 <= memberVO.mLev}"><a class="collapse-item" id="ordersOutput" href="${pageContext.request.contextPath}/sl/monitoring/ordersOutput/ordersOutput.do">수주대실적현황</a></c:if>
		    <c:if test="${mLevel.P0027 <= memberVO.mLev}"><a class="collapse-item" id="stockState" href="${pageContext.request.contextPath}/sl/monitoring/moStockState/moStockState.do">재고현황</a></c:if>
		    <c:if test="${mLevel.P0028 <= memberVO.mLev}"><a class="collapse-item" id="actualOutput" href="${pageContext.request.contextPath}/sl/monitoring/actualOutput/actualOutput.do">생산실적현황</a></c:if>
		    <c:if test="${mLevel.P0029 <= memberVO.mLev}"><a class="collapse-item" id="noticeList" href="${pageContext.request.contextPath}/sl/monitoring/notice/noticeList.do">공지사항</a></c:if>
		</div>
    </div>
</li>
</c:if>
<!-- Divider -->
<hr class="sidebar-divider d-none d-md-block">
<!-- Sidebar Toggler (Sidebar) -->
<div class="text-center d-none d-md-inline">
    <button class="rounded-circle border-0" id="sidebarToggle"></button>
</div>
