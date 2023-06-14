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
                        <!-- Nav member - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">납품 상세</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/sales/delivery/modifyDeliveryOk.do" name="modifyForm" method="post">
                            		<input type="hidden" name="deIdx" id="deIdx" value="${deliveryVO.deIdx}">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>납품일</th>
												<td><span class="form-control val-area">${deliveryVO.deDte}</span></td>
												<th>자사 담당자</th>
												<td><span class="form-control val-area">${deliveryVO.deChkManager}</span></td>
											</tr>
											<tr>
												<th>거래처명</th>
												<td><span class="form-control val-area">${deliveryVO.aName}</span></td>
												<th>거래처 대표</th>
												<td><span class="form-control val-area">${deliveryVO.aOwner}</span></td>
											</tr>
											<tr>
												<th>금액</th>
												<td><span class="form-control val-area" id="deMoney">${deliveryVO.deMoney}</span></td>
												<th>납품확인 거래처 담당자</th>
												<td><span class="form-control val-area">${deliveryVO.deChkAcManager}</span></td>
											</tr>
											<tr>
												<th>거래처 연락처</th>
												<td><span class="form-control val-area">${deliveryVO.aTel}</span></td>
												<th>납품확인 일자</th>
												<td><span class="form-control val-area">${deliveryVO.deChkDte}</span></td>
											</tr>
											<tr>
												<th>납품 상태</th>
												<td>
													<span class="form-control val-area">
														<c:if test="${deliveryVO.deState eq '0'}">미확인</c:if>
														<c:if test="${deliveryVO.deState eq '1'}">납품확인</c:if>
														<c:if test="${deliveryVO.deState eq '2'}">납품불량</c:if>
														<c:if test="${deliveryVO.deState eq '3'}">수금확인</c:if>
													</span>
												</td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textArea disabled="disabled">${deliveryVO.deNote}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/sales/delivery/deliveryList.do'">목록</span>
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
	$(function() {
		$('#salesMenu').addClass("active");
		$('#sales').addClass("show");
		$('#deliveryList').addClass("active");
		
		let money = $('#deMoney').text().toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#deMoney').text(money);
	});
	</script>
</body>

</html>