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
                    <h1 class="h3 mb-2 text-gray-800">유무검사관리 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/quality/checkProd/registCheckProdOk.do" name="registForm" method="post" encType="multipart/form-data">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>부적합 번호 <span class="req">*</span></th>
												<td>
												<input type="text" class="form-control" name="inIdx" id="inIdx" list="inList" autocomplete="off">
													<datalist id="inList">
														<c:forEach var="list" items="${inList}" varStatus="status">
															<option value="${list.inIdx}">${list.inName}</option>
														</c:forEach>
													</datalist>
													</td>
												<th>부적합명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="inName" id="inName" readonly></td>
											</tr>
											<tr>
												<th>불량 유형 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="chState" id="chState" readonly></td>
												<th>재사용 여부 <span class="req">*</span></th>
												<td><select class="form-control" name="chRecycle" id="chRecycle">
														<option value="">선택</option>
														<option value="Y" <c:if test="${inspVO.chRecycle eq 'Y'}">selected="selected"</c:if>>Y</option>
														<option value="N" <c:if test="${inspVO.chRecycle eq 'N'}">selected="selected"</c:if>>N</option>
													</select></td>
											</tr>
											<tr>
												<th>변경이유</th>
												<td colspan="3"><textArea name="chReason" id="chReason"></textArea></td>
											</tr>
											
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_document()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/quality/checkProd/checkProdList.do'">취소</span>
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
	function fn_regist_document(){
		if($('#inIdx').val() == ''){
			alert("부적합번호를 확인 바랍니다.");
			return;
		}
		
		if($('#chRecycle').val() == ''){
			alert("재사용 여부를 선택해주세요");
			return;
		}
		
		
		
		registForm.submit();
	}
	
	$(function() {
		$('#qualityMenu').addClass("active");
		$('#quality').addClass("show");
		$('#checkProdList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#inIdx').change(function(){
			checkProdInfoAjax();
		});
		
		
	function checkProdInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/quality/checkProd/checkProdInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'inIdx':$('#inIdx').val()
			  },
			  success:function(result){
				  
				  $('#inName').text(result.inInfo.inName)
				  $('#chState').text(result.inInfo.chState)
				  registForm.chState.value = result.inInfo.chState;
				  registForm.inName.value = result.inInfo.inName;
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
		
		
	}	
		
		
		
	});
	</script>
</body>

</html>