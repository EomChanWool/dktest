<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
<style>
	.table th{
		padding-top: 1.3rem;
	}
	
	.val-area{
		text-align: left;
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
                        <!-- Nav document - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">검사공정 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/process/inspect/modifyInspectOk.do" name="modifyForm" method="post">
	                                <input type="hidden" name="isiId" value="${incoVO.isiId}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>로트번호  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="isiLotno" id="isiLotno" value="${incoVO.isiLotno}" readonly/>
												</td>
												<th>수주번호  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="orId" id="orId" value="${incoVO.orId}" readonly>
												</td>
											</tr>
											<tr>
												<th>품목코드  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="isiItemType" id="isiItemType" value="${incoVO.isiItemType}" readonly/></td>
												<th>품목명</th>
												<td><input type="text" class="form-control" name="isiItemName" id="isiItemName" value="${incoVO.isiItemName}" readonly/></td>
											</tr>
											<tr>
												<th>검사방식</th>
												<td><input type="text" class="form-control" name="isiWay" id="isiWay" value="${incoVO.isiWay}"/></td>
												<th>검사자</th>
												<td><input type="text" class="form-control" name="isiManager" id="isiManager" value="${incoVO.isiManager}"/></td>
											</tr>
											
											
										</tbody>
	                                </table>
	                                
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>SPC항목  <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="siType" id="siType" value="${incoVO.siName}" readonly>
													
												</td>
												<th>SPC스펙  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="isiSpcSpec" id="isiSpcSpec" value="${incoVO.isiSpcSpec}" readonly/>
												</td>
											</tr>
											<tr>
												<th>검사일</th>
												<td><input type="date" class="form-control" name="isiDate" id="isiDate" value="${incoVO.isiDate}"/></td>
												<th>검사파일1 <span class="req">*</span></th>
												<td><input type="text" class="form-control" value="${incoVO.isiFile1}" readonly/></td>
											</tr>
											<tr>
												<th>검사파일2 <span class="req">*</span></th>
												<td><input type="text" class="form-control" value="${incoVO.isiFile2}" readonly/></td>
												<th>검사파일3  <span class="req">*</span></th>
												<td><input type="text" class="form-control" value="${incoVO.isiFile3}" readonly/></td>
											</tr>
											<tr>
												<th>검사파일4  <span class="req">*</span></th>
												<td><input type="text" class="form-control" value="${incoVO.isiFile4}" readonly/></td>
												<th>검사파일5  <span class="req">*</span></th>
												<td><input type="text" class="form-control" value="${incoVO.isiFile5}" readonly/></td>	
												
											</tr>
											
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_ins()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/process/inspect/inspectList.do'">취소</span>
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
	function fn_modify_ins(){
		/* if($('#inName').val() == ''){
			alert("부적합명을 확인 바랍니다.");
			return;
		}
		
		if($('#tiIdx').val() == ''){
			alert("검사명을 확인 바랍니다.");
			return;
		}
		
		
		if($('#biIdx').val() == ''){
			alert("불량항목 번호를 확인 바랍니다.");
			return;
		} */
		
		modifyForm.submit();
	}
	
	$(function() {
		$('#processMenu').addClass("active");
		$('#process').addClass("show");
		$('#inspectList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		
		
	});

	s
	

	
	
	</script>
</body>

</html>