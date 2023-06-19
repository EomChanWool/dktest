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
                        <!-- Nav cutting - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">견적서 등록</h1>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<form action="${pageContext.request.contextPath}/sl/collectInfo/cutting/registCuttingOk.do" name="registForm" method="post">
	                                <table class="table table-bordered" id="dataTable">
	                                    <tbody>
										<tr>
											<th>견적서 제목</th>
											<td><input type="text" class="form-control" name="esName"/></td>
											<th>자사 담당자</th>
											<td><input type="text" class="form-control" name="esComManager"/></td>
										</tr>
										<tr>
											<th>사업장  <span class="req">*</span></th>
											<td>
												<select class="form-control" name="cIdx" id="cIdx">
													<option value="">선택</option>
													<c:forEach var="list" items="${compnayList}" varStatus="status">
														<option value="${list.cIdx}">${list.cName}</option>
													</c:forEach>
												</select>
											</td>
											<th>거래처  <span class="req">*</span></th>
											<td>
												<select class="form-control" name="aIdx" id="aIdx">
													<option value="">선택</option>
													<c:forEach var="list" items="${accountList}" varStatus="status">
														<option value="${list.aIdx}">${list.aName}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<th>대표자</th>
											<td><input type="text" class="form-control" id="cOwner" disabled="disabled"></td>
											<th>사업자 등록번호</th>
											<td><input type="text" class="form-control" id="cRegitNo" disabled="disabled"></td>
										</tr>
										<tr>
											<th>주소</th>
											<td><input type="text" class="form-control" id="cAddr" disabled="disabled"></td>
											<th>이메일</th>
											<td><input type="text" class="form-control" id="cEmail" disabled="disabled"></td>
										</tr>
										<tr>
											<th>전화</th>
											<td><input type="text" class="form-control" id="cTel" disabled="disabled"></td>
											<th>팩스</th>
											<td><input type="text" class="form-control" id="cFax" disabled="disabled"></td>
										</tr>
										<tr>
											<th>비고</th>
											<td colspan="3"><textArea name="esNote"></textArea></td>
										</tr>
									</tbody>
	                                </table>
	                                <table class="table table-bordered" id="dataTable">
	                                	<thead>
											<tr>
												<th colspan="3"><span onclick="prodBtn()" id="prodBtn" style="cursor: pointer; width:100%; height:30px; margin-top: 10px;">펼치기</span></th>
											</tr>
										</thead>
										<tbody class="prodList" style="display: none;">
											<tr>
												<th style="text-align: center;">제품코드</th>
												<th style="text-align: center;">수량(EA)</th>
												<th style="text-align: center;">단가(원)</th>
											</tr>
											<c:forEach begin="1" end="4" varStatus="status">
												<tr>
													<td>
														<input type="text" class="form-control prod" name="prod${status.count}" id="prod${status.count}" list="prList" autocomplete="off" style="text-align: center;">
														<datalist id="prList">
															<c:forEach var="list" items="${prodList}" varStatus="stat">
																<option value="${list.itemCd}">${list.itemName}</option>
															</c:forEach>
														</datalist>
													</td>
													<td><input type="text" class="form-control cnt" name="cnt${status.count}" id="cnt${status.count}" style="text-align: center;"></td>
													<td><input type="text" class="form-control perPrice" name="perPrice${status.count}" id="perPrice${status.count}" style="text-align: right;"></td>
												</tr>
											</c:forEach>
											<tr>
												<th>합계(원)</th>
												<td colspan="2" style="text-align: right; padding-top: 1.1rem;">
													<span id="total"></span>
													<input type="hidden" name="totalPrice">
												</td>	
											</tr>
										</tbody>
	                                </table>
                                </form>
                                <div class="btn_bottom_wrap">
									<button type="submit" class="btn_ok" onclick="fn_regist_cutting()" style="border:none;">확인</button>
									<span class="btn_cancel" onclick="location.href='${pageContext.request.contextPath}/sl/collectInfo/cutting/cuttingList.do'">취소</span>
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
	function fn_regist_cutting(){
		const num = /^\d+$/;
		if(registForm.cIdx.value == ''){
			alert("사업장을 확인 바랍니다.");
			return;
		}
		
		if(registForm.prod1.value == '' || !num.test(registForm.cnt1.value) || !num.test(registForm.perPrice1.value)){
			alert("견적 제품을 확인 바랍니다.");
			return;
		}
		
		registForm.submit();
	}
	
	function prodBtn(){
		if($('#prodBtn').text() == '펼치기'){
			$('#prodBtn').text("접기");
			$('.prodList').show();
		}else{
			$('#prodBtn').text("펼치기");
			$('.prodList').hide();
		}
		
	}
	
	$(function() {
		$('#collectInfoMenu').addClass("active");
		$('#collectInfo').addClass("show");
		$('#cuttingList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		$('#cIdx').change(function(){
			companyInfoAjax();
		});
		
		$('.perPrice').focusout(function(){
			calcTotalPrice();
		});
		
		$('.cnt').focusout(function(){
			calcTotalPrice();
		});
		
		$('#prod1').change(function(){ prodPerPriceInfoAjax($('#prod1').val(),1);	});
		$('#prod2').change(function(){ prodPerPriceInfoAjax($('#prod2').val(),2);	});
		$('#prod3').change(function(){ prodPerPriceInfoAjax($('#prod3').val(),3);	});
		$('#prod4').change(function(){ prodPerPriceInfoAjax($('#prod4').val(),4);	});
		$('#prod5').change(function(){ prodPerPriceInfoAjax($('#prod5').val(),5);	});
		$('#prod6').change(function(){ prodPerPriceInfoAjax($('#prod6').val(),6);	});
		$('#prod7').change(function(){ prodPerPriceInfoAjax($('#prod7').val(),7);	});
		$('#prod8').change(function(){ prodPerPriceInfoAjax($('#prod8').val(),8);	});
		$('#prod9').change(function(){ prodPerPriceInfoAjax($('#prod9').val(),9);	});
		$('#prod10').change(function(){ prodPerPriceInfoAjax($('#prod10').val(),10); });
	});
	
	function calcTotalPrice(){
		var perPrice = document.getElementsByClassName('perPrice');
		var cnt = document.getElementsByClassName('cnt');
		var total = 0;
		for(var i=0;i<perPrice.length;i++){
			if(perPrice[i].value != ''){
				total += (perPrice[i].value*cnt[i].value);
			}
		}

		registForm.totalPrice.value = total;
		let result = total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
		$('#total').text(result);
	}
	
	function companyInfoAjax(){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/collectInfo/cutting/companyInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'cIdx':$('#cIdx').val()
			  },
			  success:function(result){
				  $('#cOwner').val(result.com_info[0].cOwner);
				  $('#cAddr').val(result.com_info[0].cAddr);
				  $('#cRegitNo').val(result.com_info[0].cRegitNo);
				  $('#cEmail').val(result.com_info[0].cEmail);
				  $('#cTel').val(result.com_info[0].cTel);
				  $('#cFax').val(result.com_info[0].cFax);
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	
	function prodPerPriceInfoAjax(value, index){
		$.ajax({
			  type:"POST",
			  url:"<c:url value='${pageContext.request.contextPath}/sl/collectInfo/cutting/prodPerPriceInfoAjax.do'/>",	  		  			  
			  dataType:"JSON",
			  data:{
				  'itemCd':value
			  },
			  success:function(result){
				  var str = '#perPrice'+index;
				  $(str).val(result.item_info[0].itemPerPrice);
			  },
			  error:function(request,status,error){ 
				  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);		  
			  }
		  });
	}
	</script>
</body>

</html>