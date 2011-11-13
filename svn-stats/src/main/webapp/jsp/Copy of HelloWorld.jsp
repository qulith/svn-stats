<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.tusia.service.TestService"%>
<%@page import="java.util.Collection"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
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
			
			ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
			TestService testService = (TestService)context.getBean("testService");
			Collection stats = testService.getUserStats();	
		
			String statSize = stats.size() +""; 
			
			for(UserSvnSta stat)
			
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
			         text: 'Browser market shares at a specific website, 2010'
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
			         data: [
			            ['Firefox',   45.0],
			            ['IE',       26.8],
			            {
			               name: 'Chrome',    
			               y: 12.8,
			               sliced: true,
			               selected: true
			            },
			            ['Safari',    8.5],
			            ['Opera',     6.2],
			            ['Others',   0.7]
			         ]
			      }]
			   });
			});
				
		</script>
</head>
<body>
	<%  %>

	
	<p>This is a test and here is the result</p>
	
	<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
</body>
</html>