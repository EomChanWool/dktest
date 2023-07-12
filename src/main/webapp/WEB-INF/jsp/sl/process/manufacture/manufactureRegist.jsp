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
                        <!-- Nav analyManage - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="btn_bottom_wrap">
                    	<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">절단공정 등록</h1>
                    	
                    </div>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/process/manufacture/registManufactureOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>설비 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="eqId" id="eqId"  list="eqList" autocomplete="off">
													<datalist id="eqList">
														<c:forEach var="list" items="${eqList}" varStatus="status">
															<option value="${list.eqId}">${list.eqName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>가공공정번호 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="mpMfno" id="mpMfno" ></td>
											</tr>
											<tr>
											<th>로트번호</th>
											<td><input type="text" class="form-control" name="poLotno" id="poLotno"  list="lotList" autocomplete="off">
											<datalist id="lotList">
												<c:forEach var="list" items="${lotnoList}" varStatus="status">
													<option value="${list.poLotno}">${list.poLotno}</option>
												</c:forEach>
											</datalist>
											</td>
											<th>타입</th>
											<td><input type="text" class="form-control" name="piItemType" id="piItemType" readonly></td>
											</tr>
											<tr>
											<th>시작일시</th>
											<td><input type="datetime-local" class="form-control" name="mpStarttime" id="mpStarttime" ></td>
											<th>종료일시</th>
											<td><input type="datetime-local" class="form-control" name="mpEndtime" id="mpEndtime" ></td>
											</tr>
											<tr>
											<th>가공수량</th>
											<td><input type="text" class="form-control" name="mpMfQty" id="mpMfQty"></td>
											<th>불량수량</th>
											<td><input type="text" class="form-control" name="mpBadQty" id="mpBadQty"></td>
											</tr>
											<tr>
												<th>공정상태</th>
													<td>
													<select class="form-control" name="poState" id="poState">
														<option value="">선택</option>
														<option value="1">진행중</option>
														<option value="2">작업완료</option>
													</select>
													</td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_manufacture()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/process/manufacture/manufactureList.do'">취소</span>
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
	function fn_regist_manufacture(){
		if($('#eqId').val() == ''){
			alert("설비를 확인 바랍니다.");
			return;
		}
		
		if($('#mpMfno').val() == ''){
			alert("공정번호를 확인 바랍니다.");
			return;
		}
		
		if($('#poLotno').val() == ''){
			alert("로트번호를 확인 바랍니다.");
			return;
		}
		
		if($('#mpStarttime').val() == ''){
			alert("시작일을 확인 바랍니다.");
			return;
		}
		
		
		if($('#mpEndtime').val() == ''){
			alert("종료일을 확인 바랍니다.");
			return;
		}
		if($('#mpMfQty').val() == ''){
			alert("가공수량을 확인 바랍니다.");
			return;
		}
		
		
		
		registForm.submit();
	}
	
	
	
	$(function() {
		$('#processMenu').addClass("active");
		$('#process').addClass("show");
		$('#manufactureList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		
		
		$('#poLotno').change(function(){
			manufactureAjax();
		});
		
	});
	
	
	function manufactureAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/process/manufacture/manufactureInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'poLotno': $('#poLotno').val(),
				  
			  },
			  success:function(result){
				  registForm.piItemType.value = result.mf_ajax.piItemType;
				  registForm.mpMfQty.value = result.mf_ajax.poOrderQty;
				  if(result.mf_ajax.poState == 0 || result.mf_ajax.poState == 1){
					  $('#poState').val("1");
				  }
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	
	</script>
</body>

</html>