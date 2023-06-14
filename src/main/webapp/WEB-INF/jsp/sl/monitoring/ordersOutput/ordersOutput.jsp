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

                        <!-- Nav member - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">수주대실적 현황</h1>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
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
	$(function() {
		$('#monitoringMenu').addClass("active");
		$('#monitoring').addClass("show");
		$('#ordersOutput').addClass("active");
		
		window.onresize = function() {
			location.reload();
		}
	});
	
	var chartDom = document.getElementById('graph');
	var myChart = echarts.init(chartDom);
	var option;
	
	let date = [];
	
	let orderCnt = [];
	let sales = [];
	
	let item_760N = [];
	let item_760O = [];
	let item_760W = [];
	let item_560 = [];
	let prodCnt = [];
	
	const cntMin = 0;
	const cntMax = 5000;
	const cntInterval = 1000;
	const salesMin = 0;
	const salesMax = 50000;
	const salesInterval = 10000;

	var num = 0;
	var year = 0;
	var maxMon = 0;
	<c:forEach items="${prodCntList}" var="list">
		year = ${list.years};
		maxMon = ${list.month};
	</c:forEach>
	for(var i=1;i<=maxMon;i++){
		date.push(year+"년 "+i+"월");
		orderCnt.push(0);
		item_760N.push(0);
		item_760O.push(0);
		item_760W.push(0);
		item_560.push(0);
		sales.push(0);
	}
	
	<c:forEach items="${orderCntList}" var="list">
		orderCnt[${list.month}-1] = ${list.orderCnt};
	</c:forEach>
	
	<c:forEach items="${prodCntList}" var="list">
		if('${list.itemName}' == '760N'){
			item_760N[${list.month}-1] = ${list.prodCnt}; 
		}else if('${list.itemName}' == '760O'){
			item_760O[${list.month}-1] = ${list.prodCnt};
		}else if('${list.itemName}' == '760W'){
			item_760W[${list.month}-1] = ${list.prodCnt};
		}else if('${list.itemName}' == '560'){
			item_560[${list.month}-1] = ${list.prodCnt};
		}
	</c:forEach>
	
	<c:forEach items="${salesList}" var="list">
		sales[${list.month}-1] = ${list.money};
	</c:forEach>

	option = {
			  tooltip: {
			    trigger: 'axis',
			    axisPointer: {
			      type: 'cross',
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
			      saveAsImage: { show: false }
			    }
			  },
			  legend: {
			    data: ['수주량', '760N', '760O', '760W', '560', '매출액']
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
			      name: '개수',
			      min: cntMin,
			      max: cntMax,
			      interval: cntInterval,
			      axisLabel: {
			        formatter: '{value} EA'
			      }
			    },
			    {
			      type: 'value',
			      name: '금액',
			      min: salesMin,
			      max: salesMax,
			      interval: salesInterval,
			      axisLabel: {
			        formatter: '{value} 만원'
			      }
			    }
			  ],
			  series: [
			    {
			      name: '수주량',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: orderCnt
			    },
			    {
			      name: '760N',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_760N
			    },
			    {
			      name: '760O',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_760O
			    },
			    {
			      name: '760W',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_760W
			    },
			    {
			      name: '560',
			      type: 'bar',
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' EA';
			        }
			      },
			      data: item_560
			    },
			    {
			      name: '매출액',
			      type: 'line',
			      yAxisIndex: 1,
			      tooltip: {
			        valueFormatter: function (value) {
			          return value + ' 만원';
			        }
			      },
			      data: sales
			    }
			  ]
			};
	option && myChart.setOption(option);
	</script>
</body>

</html>