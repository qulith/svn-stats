

<%@page import="org.json.simple.JSONArray"%>
<%@page import="com.tusia.dto.PieChartCommits"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tusia.dto.UserSvnStat"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.tusia.service.TestService"%>
<%@page import="java.util.Collection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">-->
<title>SVN Stats</title>

	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
	<script type="text/javascript" src="../js/highcharts.js"></script>
	<script type="text/javascript">
	
	
	
			<%
			
			
			ApplicationContext context = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			TestService testService = (TestService)context.getBean("testService");
// 			Collection<UserSvnStat> stats = testService.getUserStats();	
		
			
// 			String statSize = stats.size() +""; 
			
// 			int count = 0;
			
			
// 			for(UserSvnStat stat : stats)
// 			{
// 				count+=stat.getNumberCommits();
// 			}
			

			JSONArray pieChartVals = testService.getPieChartCommits();
			String pieChartValsString = pieChartVals.toJSONString();
			
			%>
			
			
			
			
			var chart;
			
			
			$(document).ready(function() {
				
			   
				
			   chart = new Highcharts.Chart({
			      chart: {
			         renderTo: 'container',
			         plotBackgroundColor: null,
			         plotBorderWidth: null,
			         plotShadow: false
			      },
			      title: {
			         text: 'Number of Commits per person'
			      },
			      tooltip: {
			         formatter: function() {
			            return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
			         }
			      },
			      plotOptions: {
			         pie: {
			            allowPointSelect: true,
			            cursor: 'pointer',
			            dataLabels: {
			               enabled: false
			            },
			            showInLegend: true
			         }
			      },
			       series: [{
			         type: 'pie',
			         name: 'Browser share',
			         
			         data: <%= pieChartValsString %>
			      }]
			   });
			});
				
		</script>
</head>
<body>
	
	<p>This is a test and here is the result</p>
	
	<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
</body>
</html>