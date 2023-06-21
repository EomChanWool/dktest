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
                        <!-- Nav Item - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">사원 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/user/registUserOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable"  >
	                                    <tbody>
											<tr>
												<th>사원번호 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="miUserid" value="${userVO.miUserid}"/></td>
											</tr>
											<tr>
												<th>아이디 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="miId" value="${userVO.miId}"/></td>
												<th>비밀번호 <span class="req">*</span></th>
												<td><input type="password" class="form-control" name="miPass" value="${userVO.miPass}"/></td>
											</tr>
											<tr>
												<th>이름 </th>
												<td><input type="text" class="form-control" name="miName" value="${userVO.miName}"/></td>
												<th>부서명 </th>
												<td><input type="text" class="form-control" name="miDepartment" value="${userVO.miDepartment}"/></td>
											</tr>
											<tr>
												<th>직급명 </th>
												<td><input type="text" class="form-control" name="miPosition" value="${userVO.miPosition}"/></td>
												<th>이메일 </th>
												<td><input type="text" class="form-control" name="miEmail" value="${userVO.miEmail}"/></td>
											</tr>
											<tr>
												<th>전화번호 </th>
												<td><input type="text" class="form-control" name="miPhone" value="${userVO.miPhone}"/></td>
												<th>사용권한</th>
												<td><input type="text" class="form-control" name="miLevel" value="${userVO.miLevel}"/></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_user()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/user/userList.do'">취소</span>
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
		function fn_regist_user(){
			//연락처
			var localPhone = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;
			var cellPhone = /^\d{3}-\d{3,4}-\d{4}$/;
			//사업자 등록번호
			const regitNo = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/;
			//이메일
			const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			
// 			if(registForm.cName.value == ''){
// 				alert("사업장명을 확인 바랍니다.");
// 				return;
// 			}
			
// 			if(registForm.cAddr.value == ''){
// 				alert("주소를 확인 바랍니다.");
// 				return;
// 			}
			
// 			if(registForm.cTel.value != '' && !localPhone.test(registForm.cTel.value)){
// 				alert("전화번호를 확인 바랍니다.");
// 				return;
// 			}
			
// 			if(registForm.cFax.value != '' && !localPhone.test(registForm.cFax.value)){
// 				alert("팩스번호를 확인 바랍니다.");
// 				return;
// 			}
			
// 			if(registForm.cRegitNo.value != '' && !regitNo.test(registForm.cRegitNo.value)){
// 				alert("사업자등록 번호를 확인 바랍니다.");
// 				return;
// 			}
			
// 			if(registForm.cEmail.value != '' && !email.test(registForm.cEmail.value)){
// 				alert("이메일을 확인 바랍니다.");
// 				return;
// 			}
			
			registForm.submit();
		}
		
		$(function() {
			$('#basicInfoMenu').addClass("active");
			$('#basicInfo').addClass("show");
			$('#userList').addClass("active");
			
			let msg = '${msg}';
			if(msg) {
				alert(msg);
			}
		});
	</script>
</body>

</html>