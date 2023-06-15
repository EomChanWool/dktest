<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<style>
	#graph {
		width: 100%;
	}
</style>
<%@ include file="../../header.jsp" %>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- Side Menu Section -->
			<%@ include file="../../menu/sideMenu.jsp" %>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav analyManage - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="btn_bottom_wrap">
                    	<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">검사관리</h1>
                    	<div style="display: inline-block; float: right; margin-top: -5px;">
                   			<button type="button" class="btn btn-success btn-icon-split" style="border:none;" onclick="analysisState()">
                   				<span class="text">절단공정관리</span>
                   			</button>
                   		</div>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/quality/analyManage/analyManageList.do" method="post">
									<input type="hidden" name="tiIdx">
									<input type="hidden" name="doIdx">
									<input type="hidden" name="woIdx">
									
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
									<input type="text" class="form-control bg-light border-0 small" name="searchKeyword"
						    									value="${searchVO.searchKeyword}" placeholder="검사명을 입력해 주세요"
						    									style="background-color:#eaecf4; width: 25%; float: left; margin: 0 0.3rem 0 0;">
						    									
									<input class="btn btn-secondary searchDate" id="searchStDate" name="searchStDate" value="${searchVO.searchStDate}" type="date">
									<span class="dash" style="display: inline-block; float: left; margin: 0.5rem 0.3rem 0 0">~</span>
									<input class="btn btn-secondary searchDate" id="searchEdDate" name="searchEdDate" value="${searchVO.searchEdDate}" type="date">
									
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_analyManage()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_analyManage()">
	                                <span class="text">전체목록</span>
	                            </a>
	                            <a href="#" class="btn btn-primary btn-icon-split" onclick="fn_regist_analyManage()" style="float: right;">
	                                <span class="text">등록</span>
	                            </a>
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable"  >
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>지시일자</th>
                                            <th>LOT번호</th>
                                            <th>제품명</th>
                                            <th>지시수량</th>
                                            <th>공정시작일시</th>
                                            <th>공정종료일시</th>
                                            <th>생산수량</th>
                                            <th>불량수량</th>
											<th>수정/삭제</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="result" items="${analyManageList}" varStatus="status">
	                                   		<tr onclick="fn_detail_analyManage('${result.tiIdx}')" style="cursor: pointer;">
	                                   			<td></td>
	                                   			<td></td>
	                                   			<td></td>
	                                   			<td></td>
	                                   			<td></td>
	                                   			<td></td>
	                                   			<td></td>
	                                   			<td></td>
	                                   			<td></td>
	                                            <td class="list_btn" onclick="event.cancelBubble=true" style="padding: 5px 0px; cursor: default;">
	                                            	<a href="#" class="btn btn-warning btn-icon-split" onclick="fn_modify_analyManage_go('${result.tiIdx}')">
				                                        <span class="text">수정</span>
				                                    </a>
				                                    <a href="#" class="btn btn-danger btn-icon-split" onclick="fn_delete_analyManage('${result.tiIdx}', '${result.doIdx}', '${result.woIdx}')">
				                                        <span class="text">삭제</span>
				                                    </a>
	                                            </td>
	                                        </tr>
                                    	</c:forEach>
                                    	<c:if test="${empty analyManageList}"><tr><td colspan='10'>결과가 없습니다.</td><del></del></c:if>
                                    </tbody>
                                </table>
                                <div class="btn_page">
									<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_pageview"/>
							    </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <%@ include file="../../menu/footer/footer.jsp" %>
            <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <%@ include file="../../menu/logout/logout_alert.jsp" %>
    
    <!-- Bootstrap core JavaScript-->
    <script src="/resources/conf/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/conf/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    
    <!-- Core plugin JavaScript-->
    <script src="/resources/conf/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>

	<script>
	function fn_pageview(pageNo) {
		listForm.pageIndex.value = pageNo;
	   	listForm.submit();
	}
	
	function fn_search_analyManage(){
		listForm.submit();
	}
	
	function fn_searchAll_analyManage(){
		listForm.searchKeyword.value = "";
		listForm.searchStDate.value = "";
		listForm.searchEdDate.value = "";
		listForm.pageIndex.value = 1;
		listForm.submit();
	}
	
	function fn_regist_analyManage(){
		listForm.action = "${pageContext.request.contextPath}/sl/production/analyManage/registAnalyManage.do";
		listForm.submit();
	}
	
	function fn_modify_analyManage_go(tiIdx){
		listForm.tiIdx.value = tiIdx;
		listForm.action = "${pageContext.request.contextPath}/sl/production/analyManage/modifyAnalyManage.do";
		listForm.submit();
	}
	
	
	function fn_detail_analyManage(tiIdx){
		listForm.tiIdx.value = tiIdx;
		listForm.action = "${pageContext.request.contextPath}/sl/production/analyManage/detailAnalyManage.do";
		listForm.submit();
	}
	
	function fn_delete_analyManage(tiIdx, doIdx, woIdx){
		if(confirm('해당 내역을 삭제하시겠습니까?')) {
			listForm.tiIdx.value = tiIdx;
			listForm.doIdx.value = doIdx;
			listForm.woIdx.value = woIdx;
			listForm.action = "${pageContext.request.contextPath}/sl/production/analyManage/deleteAnalyManage.do";
			listForm.submit();
		}
	}
	
	function analysisState(){
		listForm.action = "${pageContext.request.contextPath}/sl/production/analyManage/graphAnalyManage.do";
		listForm.submit();
	}
	
	$(function() {
		$('#qualityMenu').addClass("active");
		$('#quality').addClass("show");
		$('#analyManageList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#searchStDate').change(function(){
			listForm.submit();
		});
		
		$('#searchEdDate').change(function(){
			listForm.submit();
		});
	});
	</script>
</body>

</html>