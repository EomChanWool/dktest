<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>

<body id="page-top">
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"></script>
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

                        <!-- Nav kpi - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">KPI관리</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
							<div class="search">
								<form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/kpi/kpiState/kpiStateList.do" method="post">
									<input type="hidden" name="kiId">
									<input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
									
						    		<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition" id="searchCondition">
						    			<option value="1" <c:if test="${searchVO.searchCondition eq '1'}">selected="selected"</c:if>>절단공정</option>
						    			<option value="2" <c:if test="${searchVO.searchCondition eq '2'}">selected="selected"</c:if>>가공공정</option>
						    		</select>
									<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition2" id="searchCondition2">
										<option value="">선택</option>
							    		<c:forEach begin="${date.begin}" end="${date.end}" varStatus="status">
							    			<option value="${status.begin+status.count}" <c:if test="${searchVO.searchCondition2 eq status.begin+status.count}">selected="selected"</c:if>>${status.begin+status.count}</option>
							    		</c:forEach>
						    		</select>
						    		<select class="btn btn-secondary dropdown-toggle searchCondition" name="searchCondition3" id="searchCondition3">
							    		<option value="">선택</option>
							    		<c:forEach begin="1" end="12" varStatus="status">
							    			<option value="${status.count}" <c:if test="${searchVO.searchCondition3 eq status.count}">selected="selected"</c:if>>${status.count}월</option>
							    		</c:forEach>
						    		</select>
						    	</form>
						    	<a href="#" class="btn btn-info btn-icon-split" onclick="fn_search_kpi()" style="margin-left: 0.3rem;">
	                                <span class="text">검색</span>
	                            </a>
						    	<a href="#" class="btn btn-success btn-icon-split" onclick="fn_searchAll_kpi()">
	                                <span class="text">전체목록</span>
	                            </a>
							</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                            	<div id="graph" style="width: 100%; height:500px;"></div>
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
		function fn_pageview(pageNo) {
			listForm.pageIndex.value = pageNo;
		   	listForm.submit();
		}
		
		function fn_search_kpi(){
			listForm.submit();
		}
		
		function fn_searchAll_kpi(){
			listForm.searchCondition.value = "";
			listForm.searchCondition2.value = "";
			listForm.searchCondition3.value = "";
			listForm.pageIndex.value = 1;
			listForm.submit();
		}
		
		$(function() {
			$('#kpiMeun').addClass("active");
			$('#kpi').addClass("show");
			$('#kpiStateList').addClass("active");
			
			let msg = '${msg}';
			if(msg) {
				alert(msg);
			}
			
			$('#searchCondition').change(function(){
				listForm.submit();
			});
			$('#searchCondition2').change(function(){
				listForm.submit();
			});
			$('#searchCondition3').change(function(){
				listForm.submit();
			});
			
			window.onresize = function() {
				location.reload();
			}
		});
			
		//그래프
		var chartDom = document.getElementById('graph');
		var myChart = echarts.init(chartDom);
		var option;
		let date = [];
		let year = [];
		
		let kpiOutputData = [];
		let kpiSalesData = [];
		
		let viewData = [];
	
		const outputMin = 0;
		const outputMax = 2000;
		const outputInterval = 400;
		const salesMin = 0;
		const salesMax = 20000;
		const salesInterval = 5000;
			
		<c:forEach items="${kpiGraphList}" var="list">
			date.push('${list.kiYear}년 ' + '${list.kiMonth}월');
			kpiOutputData.push('${list.kiQty}');
			/* kpiSalesData.push('${list.exTrgSales}'); */
			viewData.push('0');
		</c:forEach>
		
		
		if($('#searchCondition').val() == "1"){
			<c:forEach items="${dataList}" var="list">
				var index2 = ${list.kiMonth};
				if($('#searchCondition3').val() != ''){
					viewData[0] = ${list.totalCnt};
				}else{
					viewData[index2-1] = ${list.totalCnt};
				}
			</c:forEach>
		}else if($('#searchCondition').val() == "2"){
			<c:forEach items="${dataList}" var="list">
				var index3 = ${list.kiMonth};
				if($('#searchCondition3').val() != ''){
					viewData[0] = ${list.money};	
				}else{
					viewData[index3-1] = ${list.money};	
				}
			</c:forEach>
		}
		
		if($('#searchCondition').val() == "1"){
			option = {
					  tooltip: {
					    trigger: 'axis',
					    axisPointer: {
					    	type: 'cross',
					    	axis: "auto",
					    	crossStyle: {
					        	color: '#999'
					    	}
					    }
					  },
					  toolbox: {
					    feature: {
					      dataView: { show: false, readOnly: false },
					      magicType: { show: false, type: ['line', 'bar'] },
					      restore: { show: false },
					      saveAsImage: { show: true }
					    }
					  },
					  legend: {
					    data: ['목표치', '생산량']
					  },
					  xAxis: [
					    {
					      type: 'category',
					      data: date,
					      axisPointer: {
					        type: 'shadow'
					      }
					    }
					  ],
					  yAxis: [
					    {
					      type: 'value',
					      name: '절단',
					      axisLabel: {
					        formatter: '{value} kg'
					      }
					    },
					    {
			    		  type: 'value',
				      	  name: '생산량',
				      	  axisLabel: {
				            formatter: '{value} kg'
						  }
					    }
					  ],
					  series: [
					    {
					      name: '목표치',
					      type: 'bar',
					      tooltip: {
					        valueFormatter: function (value) {
					          return value + ' kg';
					        }
					      },
					      data: kpiOutputData
					    },
					    {
				    	name: '생산량',
					    type: 'bar',
					    tooltip: {
					      valueFormatter: function (value) {
					        return value + ' kg';
					      }
					    },
					    data: viewData
					    }
					  ]
					};
		}else if($('#searchCondition').val() == "2"){
			option = {
					  tooltip: {
					    trigger: 'axis',
					    axisPointer: {
					    	type: 'cross',
					    	axis: "auto",
					    	crossStyle: {
					        	color: '#999'
					    	}
					    }
					  },
					  toolbox: {
					    feature: {
					      dataView: { show: false, readOnly: false },
					      magicType: { show: false, type: ['line', 'bar'] },
					      restore: { show: false },
					      saveAsImage: { show: true }
					    }
					  },
					  legend: {
					    data: ['목표치', '생산량']
					  },
					  xAxis: [
					    {
					      type: 'category',
					      data: date,
					      axisPointer: {
					        type: 'shadow'
					      }
					    }
					  ],
					  yAxis: [
					    {
					      type: 'value',
					      name: '가공',
					      axisLabel: {
					        formatter: '{value} kg'
					      }
					    },
					    {
			    		  type: 'value',
				      	  name: '생산량',
				      	  axisLabel: {
				            formatter: '{value} kg'
						  }
					    }
					  ],
					  series: [
					    {
					      name: '목표치',
					      type: 'bar',
					      tooltip: {
					        valueFormatter: function (value) {
					          return value + ' kg';
					        }
					      },
					      data: kpiOutputData
					    },
					    {
				    	name: '생산량',
					    type: 'bar',
					    tooltip: {
					      valueFormatter: function (value) {
					        return value + ' kg';
					      }
					    },
					    data: viewData
					    }
					  ]
					};
		}
		
		option && myChart.setOption(option);
	</script>
</body>

</html>