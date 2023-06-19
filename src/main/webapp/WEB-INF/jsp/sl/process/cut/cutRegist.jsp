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
                    	<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">검사결과 등록</h1>
                    	<div style="display: inline-block; float: right; margin-top: -5px;">
                   			<button type="button" class="btn btn-success btn-icon-split" style="border:none;" data-toggle="modal" data-target="#analysis">
                   				<span class="text">분석데이터 입력</span>
                   			</button>
                   		</div>
                    </div>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/analyManage/registAnalyManageOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>작업지시서 <span class="req">*</span></th>
												<td>
													<input type="text" class="form-control" name="woIdx" id="woIdx" value="${analyManageVO.woIdx}" list="woList" autocomplete="off">
													<datalist id="woList">
														<c:forEach var="list" items="${workOrderList}" varStatus="status">
															<option value="${list.woIdx}">${list.woName}</option>
														</c:forEach>
													</datalist>
												</td>
												<th>검사명 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="tiName" id="tiName" value="${analyManageVO.tiName}"></td>
											</tr>
											<tr>
												<th>샘플 분석 결과 코드 <span class="req">*</span></th>
												<td>
													<span class="form-control val-area" id="azIdx">${analyManageVO.azIdx}</span>
													<input type="hidden" class="form-control" name="azIdx" value="${analyManageVO.azIdx}">
												</td>
												<th>검사자 <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="tiAnalyst" id="tiAnalyst" value="${analyManageVO.tiAnalyst}"></td>
											</tr>
											<tr>
												<th>적합여부 <span class="req">*</span></th>
												<td>
													<select class="form-control" name="tiState" id="tiState">
														
														<option value="적합" <c:if test="${analyManageVO.tiState eq '적합'}">selected="selected"</c:if>>적합</option>
														<option value="부적합" <c:if test="${analyManageVO.tiState eq '부적합'}">selected="selected"</c:if>>부적합</option>
													</select>
												</td>
												<th>검사일 <span class="req">*</span></th>
												<td><input type="date" class="form-control" name="tiDte" id="tiDte" value="${analyManageVO.tiDte}"></td>
											</tr>
											<tr>
												<th>성적서</th>
												<td>
													<input type="text" class="form-control" name="doIdx" id="doIdx" list="doList" autocomplete="off">
													<datalist id="doList">
														<c:forEach var="list" items="${documentList}" varStatus="status">
															<option value="${list.doIdx}">${list.doName}</option>
														</c:forEach>
													</datalist>
												</td>
											</tr>
											<tr>
												<th>샘플 정보<span class="req">*</span></th>
												<td colspan="3"><input type="text" class="form-control" name="tiSample" id="tiSample" value="${analyManageVO.tiSample}"></td>
											</tr>
											<tr>
												<th>특이사항</th>
												<td colspan="3"><textArea name="tiNote">${analyManageVO.woIdx}</textArea></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_analyManage()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/quality/analyManage/analyManageList.do'">취소</span>
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
    <div class="modal fade" id="analysis" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-lg" role="document">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h5 class="modal-title" id="exampleModalLabel">데이터 입력</h5>
	               
	                <a class="btn btn-primary" style="position: relative; left: 30px;" href="#" onclick="registAnalysisDataAjax2()">데이터불러오기</a>
	              
	                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
	                
	                    <span aria-hidden="true">×</span>
	                </button>
	                
	            </div>
	            <div class="modal-body">
	            	<form action="#" name="registForm2" method="post">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>Brix 기준값</th>
								<td><input type="text" class="form-control" name="azBrixStd" id="azBrixStd" value="5"></td>
								<th>Brix</th>
								<td><input type="text" class="form-control" name="azBrix" id="azBrix"></td>
							</tr>
							<tr>
								<th>수분 기준값</th>
								<td><input type="text" class="form-control" name="azWaterStd" id="azWaterStd" value="30"></td>
								<th>수분</th>
								<td><input type="text" class="form-control" name="azWater" id="azWater"></td>
							</tr>
							<tr>
								<th>PH 기준값</th>
								<td><input type="text" class="form-control" name="azPhStd" id="azPhStd" value="7"></td>
								<th>PH</th>
								<td><input type="text" class="form-control" name="azPh" id="azPh"></td>
							</tr>
							<tr>
								<th>온도 기준값</th>
								<td><input type="text" class="form-control" name="azTempStd" id="azTempStd" value="25"></td>
								<th>온도</th>
								<td><input type="text" class="form-control" name="azTemp" id="azTemp"></td>
							</tr>
							<tr>
								<th>점도 기준값</th>
								<td><input type="text" class="form-control" name="azViscosityStd" id="azViscosityStd" value="100"></td>
								<th>점도</th>
								<td><input type="text" class="form-control" name="azViscosity" id="azViscosity"></td>
							</tr>
							<tr>
								<th>비중 기준값</th>
								<td><input type="text" class="form-control" name="azSgStd" id="azSgStd" value="1.5"></td>
								<th>비중</th>
								<td><input type="text" class="form-control" name="azSg" id="azSg"></td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
	            <div class="modal-footer">
	                <button class="btn btn-secondary" type="button" data-dismiss="modal" id="closeBtn">취소</button>
	                <a class="btn btn-primary" href="#" onclick="registData()">등록</a>
	            </div>
	        </div>
	    </div>
	</div>
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
	function fn_regist_analyManage(){
		if($('#woIdx').val() == ''){
			alert("작업지시서를 확인 바랍니다.");
			return;
		}
		
		if($('#tiName').val() == ''){
			alert("검사명을 확인 바랍니다.");
			return;
		}
		
		if($('#tiAnalyst').val() == ''){
			alert("검사자를 확인 바랍니다.");
			return;
		}
		
		if($('#tiState').val == ''){
			alert("적합여부를 선택해주세요.");
			return;
		}
		
		if(registForm.azIdx.value == ''){
			alert("분석데이터를 입력해주세요.");
			return;
		}
		
		if($('#tiDte').val() == ''){
			alert("검사일을 확인 바랍니다.");
			return;
		}
		if($('#tiSample').val() == ''){
			alert("샘플정보를 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	function registData(){
		registAnalysisDataAjax();
		$('#closeBtn').click();
		$('#azBrix').val('');
		$('#azWater').val('');
		$('#azPh').val('');
		$('#azTemp').val('');
		$('#azViscosity').val('');
		$('#azSg').val('');
	}
	
	$(function() {
		$('#qualityMenu').addClass("active");
		$('#quality').addClass("show");
		$('#analyManageList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		if($('#tiDte').val() == ''){
			$('#tiDte').val(new Date().toISOString().slice(0,10));	
		}
		
		
	});
	
	function registAnalysisDataAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/quality/analyManage/registAnalysisDataAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'azBrixStd': $('#azBrixStd').val(),
				  'azWaterStd': $('#azWaterStd').val(),
				  'azPhStd': $('#azPhStd').val(),
				  'azTempStd': $('#azTempStd').val(),
				  'azViscosityStd': $('#azViscosityStd').val(),
				  'azSgStd': $('#azSgStd').val(),
				  'azBrix': $('#azBrix').val(),
				  'azWater': $('#azWater').val(),
				  'azPh': $('#azPh').val(),
				  'azTemp': $('#azTemp').val(),
				  'azViscosity': $('#azViscosity').val(),
				  'azSg': $('#azSg').val()
			  },
			  success:function(result){
				  console.log(result);
				  if(result.analysis_ajax != null){
					  $('#azIdx').text(result.analysis_ajax.azIdx);
					  registForm.azIdx.value = result.analysis_ajax.azIdx;
					  alert("등록 되었습니다.");  
				  }
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	function registAnalysisDataAjax2(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/quality/analyManage/registAnalysisDataAjax2.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'azBrixStd': $('#azBrixStd').val(),
				  'azWaterStd': $('#azWaterStd').val(),
				  'azPhStd': $('#azPhStd').val(),
				  'azTempStd': $('#azTempStd').val(),
				  'azViscosityStd': $('#azViscosityStd').val(),
				  'azSgStd': $('#azSgStd').val(),
				  'azBrix': $('#azBrix').val(),
				  'azWater': $('#azWater').val(),
				  'azPh': $('#azPh').val(),
				  'azTemp': $('#azTemp').val(),
				  'azViscosity': $('#azViscosity').val(),
				  'azSg': $('#azSg').val()
			  },
			  success:function(result){
				  console.log(result.analysis_ajax2);
				  registForm2.azBrix.value = result.analysis_ajax2.azBrix;
				  registForm2.azWater.value = result.analysis_ajax2.azWater;
				  registForm2.azPh.value = result.analysis_ajax2.azPh;
				  registForm2.azTemp.value = result.analysis_ajax2.azTemp;
				  registForm2.azViscosity.value = result.analysis_ajax2.azViscosity;
				  registForm2.azSg.value = result.analysis_ajax2.azSg;
				 
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>