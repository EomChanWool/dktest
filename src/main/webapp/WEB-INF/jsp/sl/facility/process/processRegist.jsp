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
                        <!-- Nav collect - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">공정설비관리 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/processChk/registProcessOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>공정  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="prListIdx" id="prListIdx">
														<option value="">선택</option>
														<c:forEach var="list" items="${prList}" varStatus="status">
															<option value="${list.prListIdx}">${list.prListNm}</option>
														</c:forEach>
													</select>
												</td>
												<th>설비  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="faCd" id="faCd">
														<option value="">선택</option>
													</select>
												</td>
											</tr>
											<tr>
												<th>점검항목  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="prChkList" id="prChkList">
														<option value="">선택</option>
														<option value="수평상태" <c:if test="${processVO.prchkList eq '수평상태'}">selected="selected"</c:if>>수평상태</option>
														<option value="지지대파손여부" <c:if test="${processVO.prchkList eq '지지대파손여부'}">selected="selected"</c:if>>지지대파손여부</option>
														<option value="정격전압" <c:if test="${processVO.prchkList eq '정격전압'}">selected="selected"</c:if>>정격전압</option>
														<option value="접지 고정상태" <c:if test="${processVO.prchkList eq '접지 고정상태'}">selected="selected"</c:if>>접지 고정상태</option>
														<option value="단자부 고정상태" <c:if test="${processVO.prchkList eq '단자부 고정상태'}">selected="selected"</c:if>>단자부 고정상태</option>
													</select>
												</td>
												<th>점검일  <span class="req">*</span></th>
												<td><input type="date" class="form-control" name="prChkDte" id="prChkDte" value="${processVO.prChkDte}"></td>
											</tr>
											<tr>
												<th>점검담당자  <span class="req">*</span></th>
												<td colspan="3"><input type="text" class="form-control" name="prChkManager" id="prChkManager" value="${processVO.prChkManager}"></td>
											</tr>
											<tr>
												<th>점검내역  <span class="req">*</span></th>
												<td colspan="3"><textarea name="prChkInsp" id="prChkInsp">${processVO.prChkInsp}</textarea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_process()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/production/processChk/processChkList.do'">취소</span>
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
	function fn_regist_process(){
		if($('#prListIdx').val() == ''){
			alert("공정번호를 확인 바랍니다.");
			return;
		}
		
		if($('#faCd').val() == ''){
			alert("설비코드를 확인 바랍니다.");
			return;
		}
		
		if($('#prChkList').val() == ''){
			alert("점검항목을 확인 바랍니다.");
			return;
		}
		
		if($('#prChkDte').val() == ''){
			alert("점검일을 확인 바랍니다.");
			return;
		}
		
		if($('#prChkManager').val() == ''){
			alert("담당자를 확인 바랍니다.");
			return;
		}
		
		if($('#prChkInsp').val() == ''){
			alert("점검내역을 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	$(function() {
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#processChkList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		processEquipInfoAjax();
		
		$('#prListIdx').change(function(){
			$('#prChkList').val("");
			$('#faCd').empty();
			processEquipInfoAjax();
		});
	});
	
	function processEquipInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/processChk/processEquipInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'prListIdx':$('#prListIdx').val()
			  },
			  success:function(result){
				  var de_option = $('<option value="">선택</option>');
				  $('#faCd').append(de_option);
				  for(var i=0;i<result.fa_info.length;i++){
					  var option = $('<option value="'+result.fa_info[i].faCd+'">'+result.fa_info[i].faName+'</option>');
					  $('#faCd').append(option);
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