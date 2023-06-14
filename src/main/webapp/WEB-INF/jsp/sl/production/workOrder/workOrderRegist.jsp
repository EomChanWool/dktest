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
                        <!-- Nav WorkOrder - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <div class="btn_bottom_wrap">
                   		<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">생산계획/지시 등록</h1>
					</div>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/production/workOrder/registWorkOrderOk.do" name="registForm" method="post">
	                                <table class="table table-bordered">
	                                    <tbody>
											<tr>
												<th>작업지시명  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="woName" id="woName" value="${workOrderVO.woName}"></td>
												<th>제품코드  <span class="req">*</span></th>
												<td>
													<select class="form-control" name="itemCd" id="itemCd">
														<option value="">선택</option>
														<c:forEach var="list" items="${prodList}" varStatus="status">
															<option value="${list.itemCd}">${list.itemCd} (${list.itemName})</option>
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<th>계획수량  <span class="req">*</span></th>
												<td><input type="text" class="form-control" name="woPdtCnt" id="woPdtCnt" value="${workOrderVO.woPdtCnt}"></td>
												<th>작업지시일  <span class="req">*</span></th>
												<td><input type="date" class="form-control" name="woItnDte" id="woItnDte" value="${workOrderVO.woItnDte}"></td>
											</tr>
											<tr>
												<th>작업완료요구일</th>
												<td><input type="date" class="form-control" name="woCmtDte" id="woCmtDte" value="${workOrderVO.woCmtDte}"></td>
												<th>납품예정일</th>
												<td><input type="date" class="form-control" name="woDlvDte" id="woDlvDte" value="${workOrderVO.woDlvDte}"></td>
											</tr>
											<tr>
												<th>비고</th>
												<td colspan="3"><textarea name="woNote">${workORderVO.woNote}</textarea></td>
											</tr>
										</tbody>
	                                </table>
	                                <div class="btn_bottom_wrap">
				                  		<h1 class="h5 mb-2 text-gray-800" style="display: inline-block;">투입 원자재</h1>
	 	                                <div style="display: inline-block; float: right; margin-top: -10px;">
				                   			<button type="button" class="btn btn-success btn-icon-split" onclick="addRow()" style="border:none;"><span class="text">추가</span></button>
											<button type="button" class="btn btn-danger btn-icon-split" onclick="delRow()" style="border:none;"><span class="text">삭제</span></button>
			                   			</div>
									</div>

	                                <table class="table table-bordered" id="dataTable">
	                                	<thead>
	                                		<tr>
                                   		 	 	<th style="padding-top: 1rem; width: 35%;">품목코드</th>
	                                            <th style="padding-top: 1rem; width: 35%;">규격</th>
												<th style="padding-top: 1rem;">수량</th>
	                                        </tr>
	                                	</thead>
                                   		<tbody>
                                   			<tr>
                                   				<td>
                                   					<select class="form-control" name="itemCd1" id="itemCd1" style="text-align: center;">
                                   						<option value="">선택</option>
                                   						<c:forEach var="list" items="${materialList}">
                                   							<option value="${list.itemCd}">${list.itemName}</option>
                                   						</c:forEach>
                                   					</select>
                                   					<input type="hidden" name="itemName1" id="itemName1">
                                   				</td>
                                   				<td><input type="text" class="form-control" name="itemStd1" id="itemStd1" style="text-align: center;"></td>
                                   				<td><input type="text" class="form-control" name="cnt1" id="cnt1" style="text-align: right;"></td>
                                   			</tr>
                                   		</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_workOrder()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/production/workOrder/workOrderList.do'">취소</span>
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
	function fn_regist_workOrder(){
		var num = /^\d+$/;
		
		if($('#woName').val() == ''){
			alert("작업지시명을 확인 바랍니다.");
			return;
		}
		
		if($('#itemCd').val() == ''){
			alert("제품코드를 확인 바랍니다.");
			return;
		}
		
		if(!num.test($('#woPdtCnt').val())){
			alert("계획수량을 확인 바랍니다.");
			return;
		}
		
		if($('#woItnDte').val() == ''){
			alert("작업지시일을 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	function addRow(){
		var trCnt = $('#dataTable tr').length;
		if(trCnt <= 15){
			var innerHtml = "";
			innerHtml += '<tr>';
			innerHtml += '	<td>';
			innerHtml += '		<select class="form-control" name="itemCd'+(trCnt)+'" id="itemCd'+(trCnt)+'"style="text-align: center;">';
			innerHtml += '			<option value="">선택</option>';
			innerHtml += '			<c:forEach var="list" items="${materialList}">';
			innerHtml += '				<option value="${list.itemCd}">${list.itemName}</option>';
			innerHtml += '			</c:forEach>';
			innerHtml += '		</select>';
			innerHtml += '		<input type="hidden" name="itemName'+(trCnt)+'" id="itemName'+(trCnt)+'">';
			innerHtml += '	</td>';
			innerHtml += '	<td><input type="text" class="form-control" name="itemStd'+(trCnt)+'" id="itemStd'+(trCnt)+'" style="text-align: center;"></td>';
			innerHtml += '	<td><input type="text" class="form-control" name="cnt'+(trCnt)+'" id="cnt'+(trCnt)+'" style="text-align: right;"></td>';
			innerHtml += '</tr>';
			
			$('#dataTable > tbody:last').append(innerHtml);
		}else{
			alert("최대 15개까지만 가능합니다.");
			return;
		}
		
		changeChk();
	}
	
	function delRow(){
		var trCnt = $('#dataTable tr').length;
		if(trCnt > 2){
			$('#dataTable > tbody:last > tr:last').remove();
		}else{
			return;
		}
		
		changeChk();
	}
	
	function changeChk(){
		$('#itemCd1').change(function(){ materialInfoAjax($('#itemCd1').val(),1);	});
		$('#itemCd2').change(function(){ materialInfoAjax($('#itemCd2').val(),2);	});
		$('#itemCd3').change(function(){ materialInfoAjax($('#itemCd3').val(),3);	});
		$('#itemCd4').change(function(){ materialInfoAjax($('#itemCd4').val(),4);	});
		$('#itemCd5').change(function(){ materialInfoAjax($('#itemCd5').val(),5);	});
		$('#itemCd6').change(function(){ materialInfoAjax($('#itemCd6').val(),6);	});
		$('#itemCd7').change(function(){ materialInfoAjax($('#itemCd7').val(),7);	});
		$('#itemCd8').change(function(){ materialInfoAjax($('#itemCd8').val(),8);	});
		$('#itemCd9').change(function(){ materialInfoAjax($('#itemCd9').val(),9);	});
		$('#itemCd10').change(function(){ materialInfoAjax($('#itemCd10').val(),10);	});
		$('#itemCd11').change(function(){ materialInfoAjax($('#itemCd11').val(),11);	});
		$('#itemCd12').change(function(){ materialInfoAjax($('#itemCd12').val(),12);	});
		$('#itemCd13').change(function(){ materialInfoAjax($('#itemCd13').val(),13);	});
		$('#itemCd14').change(function(){ materialInfoAjax($('#itemCd14').val(),14);	});
		$('#itemCd15').change(function(){ materialInfoAjax($('#itemCd15').val(),15);	});
	}
	
	function materialInfoAjax(value, index){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/production/workOrder/woMaterialInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'itemCd':value
			  },
			  success:function(result){
				  console.log(result);
				  var std = '#itemStd'+index;
				  var name = '#itemName'+index;
				  $(std).val(result.mt_info.itemStd);
				  $(name).val(result.mt_info.itemName)
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	$(function() {
		$('#productionMenu').addClass("active");
		$('#production').addClass("show");
		$('#workOrderList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#woItnDte').val(new Date().toISOString().slice(0,10));
		
		changeChk();
	});
	</script>
</body>

</html>