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
                        <!-- Nav qualityInfo - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">품질정보 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/basicInfo/qualityInfo/registQualityInfoOk.do" name="registForm" method="post">
                            	<input type="hidden" name=qiTrustType id="qiTrustType"/>
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>관리항목코드<span class="req">*</span></th>
												<td>
													<select name="giGroupcode" id="giGroupcode" class="form-control">
														<option value="">선택</option>
														<c:forEach var="list" items="${standardList}" varStatus="status">
															<option value="${list.siId}">${list.siName}</option>
														</c:forEach>
													</select>
												</td>
												<th>관리항목명</th>
												<td><input type="text" class="form-control" name=qiName id="qiName" readonly/></td>
											</tr>
											<tr>
											<th>신뢰성구분</th>
											<td><input type="text" class="form-control" id="qiT" disabled></td>
											<th>정성/정량 구분</th>
											<td>
											<select name="qiType" class="form-control">
											<option value="">선택</option>
											<option value="1">정성</option>
											<option value="2">정량</option>
											</select>
											</td>
											</tr>
											<tr>
											
											<th>사용여부</th>
											<td>
											<select name="qiIsuse" class="form-control">
											<option value="">선택</option>
											<option value="1">사용</option>
											<option value="0">미사용</option>
											</select>
											</td>
											</tr>
											
											
										</tbody>
	                                </table>
	                                <table class="table table-bordered" id="dataTable">
	                                	<thead>
											<tr>
												<th colspan="3"><span onclick="qiBtn()" id="qiBtn" style="cursor: pointer; width:100%; height:30px; margin-top: 10px;">펼치기</span></th>
											</tr>
											</thead>
											<tbody class="qiList" style="display: none;">
											
											<tr>
											<th colspan="3">부적합내용(없으면 빈칸)</th>
											
											
											</tr>
											<tr><td colspan="3"><textArea name="qiComment" id="qiComment"></textArea></td></tr>
											<tr>
											<th>비고</th>
											
											</tr>
											<tr><td colspan="3"><textArea name="qiRemark" id="qiRemark"></textArea></td></tr>
											
											</tbody>
											
											</table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_qualityInfo()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/basicInfo/qualityInfo/qualityInfoList.do'">취소</span>
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
	function fn_regist_qualityInfo(){
		/* if(registForm.pIdx.value == ''){
			alert("메뉴명을 확인 바랍니다.");
			return;
		} */
		registForm.submit();
	}
	
	function qiBtn(){
		if($('#qiBtn').text() == '펼치기'){
			$('#qiBtn').text("접기");
			$('.qiList').show();
		}else{
			$('#qiBtn').text("펼치기");
			$('.qiList').hide();
		}
	}
	
	
	
	$(function() {
		$('#basicInfoMenu').addClass("active");
		$('#basicInfo').addClass("show");
		$('#qualityInfoList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#giGroupcode').change(function(){
			standardInfoAjax();
		});
		
		
	});
	
	
	function standardInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/basicInfo/qualityInfo/standardInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'giGroupcode':$('#giGroupcode').val()
			  },
			  success:function(result){
				  $('#qiTrustType').val(result.standard_ajax[0].siType);
				  $('#qiName').val(result.standard_ajax[0].siName);
				  
				  if(result.standard_ajax[0].siType == 1){
					  $('#qiT').val('KS');
				  }
				  if(result.standard_ajax[0].siType == 2){
					  $('#qiT').val('JIS');
				  }
				  if(result.standard_ajax[0].siType == 3){
					  $('#qiT').val('ASME');
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