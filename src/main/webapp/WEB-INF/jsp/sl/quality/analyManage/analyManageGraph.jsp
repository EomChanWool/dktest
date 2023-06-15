<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<style>
	#graph {
		width: 100%;
	}
</style>
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

                        <!-- Nav analyManage - User Information -->
                        <%@ include file="../../menu/logout/nav_user.jsp" %>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                   	<div class="btn_bottom_wrap">
                    	<h1 class="h3 mb-2 text-gray-800" style="display: inline-block;">검사현황</h1>
                    	<div style="display: inline-block; float: right; margin-top: -5px;">
                   			<button type="button" class="btn btn-success btn-icon-split" style="border:none;"
                   								 onclick="location.href='/sl/quality/analyManage/analyManageList.do'">
                   				<span class="text">목록</span>
                   			</button>
                   		</div>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <div class="table-responsive">
                            	<div id="graph" style="width: 100%; height: 500px;"></div>
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
		$('#qualityMenu').addClass("active");
		$('#quality').addClass("show");
		$('#analyManageList').addClass("active");
		
		let msg = '${msg}';
		if(msg) {
			alert(msg);
		}
		
		window.addEventListener('resize', ()=>{
			location.reload();
		});
	});
	
	var chartDom = document.getElementById('graph');
	var myChart = echarts.init(chartDom);
	var option;
	
	let total = ${list.total};
	let analysisCnt = ${list.analysisCnt};
	let processingCnt = ${list.processingCnt};
	let cnt = total - analysisCnt - processingCnt;

	option = {
	  title: {
	    text: '검사 현황',
	    left: 'center'
	  },
	  tooltip: {
	    trigger: 'item'
	  },
	  legend: {
	    orient: 'vertical',
	    left: 'left'
	  },
	  textStyle: {
		fontSize: 15
	  },
	  series: [
	    {
	      name: '검사 현황',
	      type: 'pie',
	      radius: '50%',
	      data: [
	        { value: total, name: '총 검사건수' },
	        { value: cnt, name: '검사 대기' },
	        { value: processingCnt, name: '검사 진행중' },
	        { value: analysisCnt, name: '검사 완료' }
	      ]
	    }
	  ]
	};

	option && myChart.setOption(option);
	</script>
</body>

</html>