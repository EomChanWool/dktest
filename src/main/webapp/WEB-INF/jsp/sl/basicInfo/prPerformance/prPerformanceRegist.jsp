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
                        <!-- Nav member - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">생산실적 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/prPerformance/registPrPerformanceOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>로트번호<span class="req">*</span></th>
												<td><input type="text" class="form-control" name="poLotno" id="poLotno" /></td>
												<th>지시일<span class="req">*</span></th>
												<td><input type="date" class="form-control" name="poLotDate" id="poLotDate" /></td>
											</tr>
											<tr>
												<th>발주처<span class="req">*</span></th>
												<td><input type="text" class="form-control" name="poClient" id="poClient"/></td>
												<th>발주번호<span class="req">*</span></th>
												<td><input type="text" class="form-control" name="poOrderNo" id="poOrderNo"/></td>
											</tr>
											<tr>
												<th>제품ID</th>
												<td><input type="text" class="form-control" name="piId" id="piId"/></td>
												<th>지시수량</th>
												<td><input type="text" class="form-control" name="poOrderQty" id="poOrderQty"/></td>
											</tr>
										
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_prPerfomance()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/prPerformance/prPerformanceList.do'">취소</span>
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
	function fn_regist_prPerfomance(){
		if($('#poLotno').val() == ""){
			alert("로트번호를 확인 바랍니다.");
			return;
		}
		
		if($('#poLotDate').val() == ""){
			alert("지시일을 확인 바랍니다.");
			return;
		}
		
		if($('#poClient').val() == "" ){
			alert("발주처를 입력해주세요.");
			return;
		}
		if($('#poOrderNo').val() == "" ){
			alert("발주번호를 입력해주세요.");
			return;
		}
		if($('#piId').val() == "" ){
			alert("제품ID를 입력해주세요.");
			return;
		}
		if($('#poOrderQty').val() == "" ){
			alert("지시수량을 입력해주세요.");
			return;
		}
		
		
		
		registForm.submit();
	}
	
	
	
	$(function() {
		$('#basicInfoMenu').addClass("active");
		$('#basicInfo').addClass("show");
		$('#prPerformanceList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
	});
	</script>
</body>

</html>