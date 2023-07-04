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
                    <h1 class="h3 mb-2 text-gray-800">KPI목표관리 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/kpi/kpimanagement/registKpiOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
											<tr>
												<th>기준년도   <span class="req">*</span></th>
												<td>
													<select class="form-control" name="kiYear" id="kiYear">
						    							<option value="2023">2023년</option>
						    							<option value="2024">2024년</option>
						    							<option value="2025">2025년</option>
						    							<option value="2026">2026년</option>
						    						</select>
												</td>
												<th>월  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="kiMonth" id="kiMonth">
							    						<c:forEach begin="1" end="12" varStatus="status">
							    							<option value="${status.count}">${status.count}월</option>
							    						</c:forEach>
						    						</select>
												</td>
											</tr>
											<tr>
												<th>KPI 구분</th>
												<td>
													<select class="form-control" name="kiType" id="kiType">
														<option value="">선택</option>
														<option value="1">절단공정</option>
														<option value="2">가공공정</option>
													</select>
												</td>
												<th>목표생산량  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="kiQty" id="kiQty" value="${kpiVO.kiQty}"></td>
											</tr>
											<tr>
												<th>목표생산률  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="kiGoodQty" id="kiGoodQty" value="${kpiVO.kiGoodQty}"></td>
												<th>목표불량률  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="kiBadQty" id="kiBadQty" value="${kpiVO.kiBadQty}"></td>
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_kpi()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/kpi/kpimanagement/kpiList.do'">취소</span>
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
	function fn_regist_kpi(){
		var num =  /^[0-9.]+$/;
// 		if($('#exYear').val() == ''){
// 			alert("년도를 확인 바랍니다.");
// 			return;
// 		}
		
// 		if($('#exMonth').val() == ''){
// 			alert("월을 확인 바랍니다.");
// 			return;
// 		}
		
// 		if(!num.test($('#exTrgError').val())){
// 			alert("목표 불량율을 확인 바랍니다.");
// 			return;
// 		}
		
// 		if($('#exTrgSales').text() == ''){
// 			alert("목표 생산량을 확인 바랍니다.");
// 			return;
// 		}
		
		registForm.submit();
	}
	
	$(function() {
		$('#kpiMenu').addClass("active");
		$('#kpi').addClass("show");
		$('#kpiList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#ex760n').change(function(){
			prodPerPriceInfoAjax();
		});
		$('#ex760o').change(function(){
			prodPerPriceInfoAjax();
		});
		$('#ex760w').change(function(){
			prodPerPriceInfoAjax();
		});
		$('#ex560').change(function(){
			prodPerPriceInfoAjax();
		});
	});
	
	function prodPerPriceInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/kpi/kpi/prodPerPriceInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
			  },
			  success:function(result){
				  const ex760n = Number($('#ex760n').val());
				  const ex760o = Number($('#ex760o').val());
				  const ex760w = Number($('#ex760w').val());
				  const ex560 = Number($('#ex560').val());
				  
				  const ex760n_cnt = result.pp_info[0].itemPerPrice;
				  const ex760o_cnt = result.pp_info[1].itemPerPrice;
				  const ex760w_cnt = result.pp_info[2].itemPerPrice;
				  const ex560_cnt = result.pp_info[3].itemPerPrice;
				  
				  const temp = ((ex760n * ex760n_cnt)+(ex760o * ex760o_cnt)+(ex760w * ex760w_cnt)+(ex560 * ex560_cnt))/10000;
				  var trgSales = temp.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				  $('#exTrgSales').text(trgSales);
				  registForm.exTrgSales.value = temp;
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	</script>
</body>

</html>