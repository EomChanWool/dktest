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
                    <h1 class="h3 mb-2 text-gray-800">사업장 수정</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/company/modifyCompanyOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="cIdx" value="${companyVO.cIdx}">
	                                <table class="table table-bordered" id="dataTable"  >
	                                    <tbody>
											<tr>
												<th>사업장명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="cName" value="${companyVO.cName}"/></td>
												<th>주소 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="cAddr" value="${companyVO.cAddr}"/></td>
											</tr>
											<tr>
												<th>사업자등록번호</th>
												<td><input type="text" class="form-control" name="cRegitNo" value="${companyVO.cRegitNo}"/></td>
												<th>대표자</th>
												<td><input type="text" class="form-control" name="cOwner" value="${companyVO.cOwner}"/></td>
											</tr>
											<tr>
												<th>전화</th>
												<td><input type="text" class="form-control" name="cTel" value="${companyVO.cTel}"/></td>
												<th>팩스</th>
												<td><input type="text" class="form-control" name="cFax" value="${companyVO.cFax}"/></td>
											</tr>
											<tr>
												<th>업종</th>
												<td><input type="text" class="form-control" name="cSector" value="${companyVO.cSector}"/></td>
												<th>업태</th>
												<td><input type="text" class="form-control" name="cBusiness" value="${companyVO.cBusiness}"/></td>
											</tr>
											<tr>
												<th>홈페이지</th>
												<td><input type="text" class="form-control" name="cHomepage" value="${companyVO.cHomepage}"/></td>
												<th>이메일</th>
												<td><input type="text" class="form-control" name="cEmail" value="${companyVO.cEmail}"/></td>
											</tr>
											<tr>
												<th>법인번호</th>
												<td><input type="text" class="form-control" name="cCorNo" value="${companyVO.cCorNo}"/></td>
												<th>결제은행</th>
												<td><input type="text" class="form-control" name="cPayBank" value="${companyVO.cPayBank}"/></td>
											</tr>
											<tr>
												<th>결제계좌</th>
												<td><input type="text" class="form-control" name="cPayAccount" value="${companyVO.cPayAccount}"/></td>
												<th>예금주</th>
												<td><input type="text" class="form-control" name="cAccHolder" value="${companyVO.cAccHolder}"/></td>
											</tr>
											<tr>
												<th>현장정보</th>
												<td colspan="3"><input type="text" class="form-control" name="cSiteInfo" value="${companyVO.cSiteInfo}"/></td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea name="cNote">${companyVO.cNote}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_modify_company()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/company/companyList.do'">취소</span>
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
		function fn_modify_company(){
			//연락처
			var localPhone = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;
			var cellPhone = /^\d{3}-\d{3,4}-\d{4}$/;
			//사업자 등록번호
			const regitNo = /^[0-9]{3}-[0-9]{2}-[0-9]{5}$/;
			//이메일
			const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
			
			if(modifyForm.cName.value == ''){
				alert("사업장명을 확인 바랍니다.");
				return;
			}
			
			if(modifyForm.cAddr.value == ''){
				alert("주소를 확인 바랍니다.");
				return;
			}
			
			if(modifyForm.cTel.value != '' && !localPhone.test(modifyForm.cTel.value)){
				alert("전화번호를 확인 바랍니다.");
				return;
			}
			
			if(modifyForm.cFax.value != '' && !localPhone.test(modifyForm.cFax.value)){
				alert("팩스번호를 확인 바랍니다.");
				return;
			}
			
			if(modifyForm.cRegitNo.value != '' && !regitNo.test(modifyForm.cRegitNo.value)){
				alert("사업자등록 번호를 확인 바랍니다.");
				return;
			}
			
			if(modifyForm.cEmail.value != '' && !email.test(modifyForm.cEmail.value)){
				alert("이메일을 확인 바랍니다.");
				return;
			}
			
			modifyForm.submit();
		}
		
		$(function() {
			$('#basicInfoMenu').addClass("active");
			$('#basicInfo').addClass("show");
			$('#companyList').addClass("active");
			
			let msg = '${msg}';
			if(msg) {
				alert(msg);
			}
		});
	</script>
</body>

</html>