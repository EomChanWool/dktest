<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
<style>
	.table th{
		padding-top: 1.3rem;
	}
</style>
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
                        <!-- Nav materialMove - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">프로그램 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/materialMove/modifyMaterialMoveOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="pIdx" value="${materialMoveVO.pIdx}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>메뉴그룹 <span class="req">*</span></th>
												<td>
													<select class="form-control" name="pGroup" id="pGroup">
														<option value="기준정보관리" <c:if test="${materialMoveVO.pGroup eq '기준정보관리'}">selected="selected"</c:if>>기준정보관리</option>
														<option value="영업관리" <c:if test="${materialMoveVO.pGroup eq '영업관리'}">selected="selected"</c:if>>영업관리</option>
														<option value="자재/출하관리" <c:if test="${materialMoveVO.pGroup eq '자재/출하관리'}">selected="selected"</c:if>>자재/출하관리</option>
														<option value="생산관리" <c:if test="${materialMoveVO.pGroup eq '생산관리'}">selected="selected"</c:if>>생산관리</option>
														<option value="모니터링" <c:if test="${materialMoveVO.pGroup eq '모니터링'}">selected="selected"</c:if>>모니터링</option>
													</select>
												</td>
												<th>메뉴명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="pName" id="pName" value="${materialMoveVO.pName}"/></td>
											</tr>
											<tr>
												<th>메뉴 URL <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="pUrl" id="pUrl" value="${materialMoveVO.pUrl}"/></td>
												<th>사용여부</th>
												<td>
													<select class="form-control" name="pUseFlag" id="pUseFlag">
														<option value="Y" <c:if test="${materialMoveVO.pUseFlag eq 'Y'}">selected="selected"</c:if>>사용</option>
														<option value="N" <c:if test="${materialMoveVO.pUseFlag eq 'N'}">selected="selected"</c:if>>미사용</option>
													</select>
												</td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_materialMove()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/materialMove/materialMoveList.do'">취소</span>
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
	function fn_modify_materialMove(){
		if($('#pName').val() == ''){
			alert("메뉴명을 확인 바랍니다.");
			return;
		}
		
		if($('#pUrl').val() == ''){
			alert("메뉴URL을 확인 바랍니다.");
			return;
		}
		
		modifyForm.submit();
	}
	
	$(function() {
		$('#basicInfoMenu').addClass("active");
		$('#basicInfo').addClass("show");
		$('#materialMoveList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
	});
	</script>
</body>

</html>