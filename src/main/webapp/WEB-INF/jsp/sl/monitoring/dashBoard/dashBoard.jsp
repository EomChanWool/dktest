<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@ include file="../../header.jsp" %>
<style>
	.scroll{
		overflow-y: scroll;
		-ms-overflow-style: none;
		scrollbar-width: none;
		scroll-behavior: smooth;
	}
	
	.notice, .stockState{
		height: 320px !important;
		margin-top: 15px;
	}
</style>

<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
	<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts@5.4.1/dist/echarts.min.js"></script>
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
                <div class="container-fluid" style="overflow: hidden;">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Dashboard</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                                <form name ="listForm" class="listForm" action="${pageContext.request.contextPath}/sl/monitoring/dashBoard.do" method="post">
								<input type="hidden" name="noIdx" id="noIdx">
								<div class="cont_wrap">
									<div class="cont">
								      <h1>수주대실적현황</h1>
								      <div id="ordersOutputGraph" style="width: 100%; height:300px;"></div>
								    </div>
								    
								    <div class="cont">
								      <h1>라인가동현황</h1>
                            		<div id="lineRunningGraph" style="width: 100%; height:300px;"></div>
						    	</div>
						    	<div class="cont">
								      <h1>생산집계현황</h1>
                            		<div id="ordersOutputGraph" style="width: 100%; height:300px;"></div>
						    	</div>
						    	<div class="cont">
								      <h1>공정능력추이현황</h1>
                            		<div id="ordersOutputGraph" style="width: 100%; height:300px;"></div>
						    	</div>
						    	 </div>
					    	</form>
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
	function fn_detail_notice(noIdx){
		$('#noIdx').val(noIdx);
		listForm.action = "${pageContext.request.contextPath}/sl/monitoring/detailNotice.do";
		listForm.submit();
	}

	$(function() {
		$('#dashboard').addClass("active");
		
// 		document.getElementById("sidebarToggle").click();

		window.onresize = function() {
			location.reload();
		}
		
		setTimeout(function(){
			location.reload();
		}, 60000);
		
		const stockArea = document.querySelector(".stockState");
		const noticeArea = document.querySelector(".notice");
		autoScroll(stockArea, 2000);
		autoScroll(noticeArea, 3000);
	});
	
	function autoScroll(obj, interval){
		setInterval(function(){
			obj.scrollTop = obj.scrollTop + 50;
			if(obj.offsetHeight + obj.scrollTop >= obj.scrollHeight){
				obj.scrollTop = 0;
			}
		}, interval);
	}
	</script>
	<script>
	//수주대실적 현황
	
	
	var chartDom = document.getElementById('ordersOutputGraph');
	var myChart = echarts.init(chartDom);
	var option;
	let date = [];
	let year = [];
	
	let OutputData = [];
	
	let viewData = [];
	
	let viewData2 = [];

	var num = 0;
	var years = 0;
	var maxMon = 0;
	
	<c:forEach items="${orderCntList}" var="list">
	
	years = ${list.years};
	maxMon = ${list.months};
	</c:forEach>
	for(var i=1;i<=maxMon;i++){
		date.push(years+"년 "+i+"월");
		OutputData.push(0);
		viewData.push(0);
		viewData2.push(0);
	}
	<c:forEach items="${prodCntList}" var="list">
	viewData[${list.months-1}] = ${list.prodCnt};
	viewData2[${list.months-1}] = ${list.relPrice};
	</c:forEach>
	<c:forEach items="${orderCntList}" var="list">
	OutputData[${list.months-1}] = ${list.orderCnt};
	</c:forEach>
	

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
			    data: ['수주량', '생산량','매출액']
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
			      name: 'EA',
			      axisLabel: {
			        formatter: '{value} EA'
			      }
			    },
			    {
	    		  type: 'value',
		      	  name: '매출액',
		      	  position: 'right',
		      	  axisLabel: {
		            formatter: '{value} 원'
				  }
			    }
			  ],
			  series: [
			    {
			      name: '수주량',
			      type: 'bar',
			      /* label: {
			          show: true,
			          position: 'inside',
			          formatter: function(d){
			        	  return d.data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+" EA";
			          }
			          
			        }, */
			      tooltip: {
			        valueFormatter: function (value) {
			        	let result = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');  
				        return result + ' EA';
			        }
			      },
			      data: OutputData
			    },
			    {
		    	name: '생산량',
			    type: 'bar',
			    /* label: {
			          show: true,
			          position: 'inside',
			          formatter: function(d){
			        	  return d.data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+" EA";
			          }
			          
			        }, */
			    tooltip: {
			      valueFormatter: function (value) {
			    	  
			    	let result = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');  
			        return result + ' EA';
			      }
			    },
			    data: viewData
			    },
			    {
			    	name: '매출액',
			    	yAxisIndex: 1,
				    type: 'line',
				    label: {
				          show: true,
				          position: 'inside',
				          formatter: function(d){
				        	  return d.data.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',')+" 원";
				          }
				        },
				    tooltip: {
				      valueFormatter: function (value) {
				    	  if(value == undefined){
				    		 value = 0;
				    	  }
				    	  let result = value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
				    	 
				        return result + ' 원';
				      }
				    },
				    data: viewData2
				    }
			  ]
			};
	option && myChart.setOption(option);
	</script>
	<script>
	//생산실적 현황
	var chartDom = document.getElementById('actualOutputGraph');
	var myChart = echarts.init(chartDom);
	var option;
	
	let date_2 = [];
	let item_760N_2 = [];
	let item_760O_2 = [];
	let item_760W_2 = [];
	let item_560_2 = [];
	let prodCnt_2 = [];
	
	const actualOutputDataMin = 0;
	const actualOutputDataMax = 2000;
	const actualOutputDataInterval = 400;
	
	var num = 0;
	var year = 0;
	var maxMon = 0;
	<c:forEach items="${prodCntList}" var="list">
		year = ${list.years};
		maxMon = ${list.month};
	</c:forEach>
	for(var i=1;i<=maxMon;i++){
		date_2.push(year+"년 "+i+"월");
		item_760N_2.push(0);
		item_760O_2.push(0);
		item_760W_2.push(0);
		item_560_2.push(0);
	}
	<c:forEach items="${prodCntList}" var="list">
		if('${list.itemName}' == '760N'){
			item_760N_2[${list.month}-1] = ${list.prodCnt}; 
		}else if('${list.itemName}' == '760O'){
			item_760O_2[${list.month}-1] = ${list.prodCnt};
		}else if('${list.itemName}' == '760W'){
			item_760W_2[${list.month}-1] = ${list.prodCnt};
		}else if('${list.itemName}' == '560'){
			item_560_2[${list.month}-1] = ${list.prodCnt};
		}
	</c:forEach>

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
			    data: ['760N', '760O', '760W', '560']
			  },
			  xAxis: [
			    {
			      type: 'category',
			      data: date_2,
			      axisPointer: {
			        type: 'shadow'
			      }
			    }
			  ],
			  yAxis: [
			    {
			      type: 'value',
			      name: '개수',
			      min: actualOutputDataMin,
			      max: actualOutputDataMax,
			      interval: actualOutputDataInterval,
			      axisLabel: {
			        formatter: '{value} EA'
			      }
			    }
			  ],
			  series: [
			    {
			      name: '760N',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_760N_2
			    },
			    {
			      name: '760O',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_760O_2
			    },
			    {
			      name: '760W',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_760W_2
			    },
			    {
			      name: '560',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_560_2
			    }
			  ]
			};
	option && myChart.setOption(option);
	</script>
</body>

</html>