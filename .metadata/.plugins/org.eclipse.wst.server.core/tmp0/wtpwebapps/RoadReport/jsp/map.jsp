<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
      html *
        {
   font-size: 1em;
   font-family: "Arial Black";
}
body {background-color: white;}
h1 {color: white; text-align: center}
p {color: white;}
a {text-decoration: none}
.header{ 
    background-color: #f49542;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    }
.topnav {
    overflow: hidden;
    background-color: #bcbcbc;
}    
.topnav a {
    float: left;
    display: block;
    color: black;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
    }
.topnav a:hover {
    background-color: #ddd;
    color: black;
    }
.imgFlip {
        -moz-transform: scaleX(-1);
        -o-transform: scaleX(-1);
        -webkit-transform: scaleX(-1);
        transform: scaleX(-1);
        filter: FlipH;
        -ms-filter: "FlipH";
	}
	 /* Remove margins from the 'html' and 'body' tags, and ensure the page takes up full screen height. */
            html, body {height:100%; margin:0; padding:0;}

            /* Set the position and dimensions of the background image. */
            #page-background {position:fixed; top:0; left:0; width:100%; height:100%;}

            /* Specify the position and layering for the content that needs to appear in front of the background image. Must have a higher z-index value than the background image. Also add some padding to compensate for removing the margin from the 'html' and 'body' tags. */
            #content {position:relative; z-index:1; padding:10px;}
	}
}
</style>
<head>
<title>Road Report</title>
<meta name="viewport" content="initial-scale=1.0"></meta>
<meta charset="utf-8"></meta>
</head>

<body>
<div id="page-background"><img src="lights.png" width="100%" height="100%" alt="bg"></div>
	<div id="content">
	<a href=map>
    <div class="header" style="border-radius: 10px 10px 0px 0px">
    <img src="cone.gif" alt="cone" class="imgFlip"style="width:20px;height:25px">
        ROAD REPORT
        <img src="cone.gif" alt="cone"style="width:20px;height:25px">
    </div>
    </a>
        <div class="topnav" style="border-radius: 0px 0px 10px 10px">
		<a href=map>Map</a>
		        <a href=reportHazard>Report Hazard</a>
        	<c:if test="${session eq 'Resolver' }">
    		<a href="reportConstructionHazard">Report Construction Hazard</a>
    		<a href="resolveHazard">Resolve Hazard</a>
			</c:if>
		<a href="home.jsp" style="float: right;">Logout</a>
    </div>
    </div>
    <div id="map" style="width: 99%; height: 85%; margin: 10px"></div>
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
		var map;
		function initMap() {
			map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : 37.338208,
					lng : -121.886329
				},
				zoom : 8
			});

			ajaxGet();
			
		}


				// GET REQUEST
				$("#testAjaxGET").click(function(event) {
					event.preventDefault();
					ajaxGet();
				});

				$("#testAjaxPOST").click(function(event) {
					event.preventDefault();
					ajaxPost();
				});

				// DO GET
				function ajaxGet() {
					$.ajax({
						cache : false,
						type : "GET",
						url : '${pageContext.request.contextPath}/hazardGet',
						success : function(hazards) {
												
							var infowindow = new google.maps.InfoWindow();

							for (var i = 0; i < hazards.length; i++) {
								var hazard = hazards[i];
								var myLatlng = new google.maps.LatLng( hazard.lat, hazard.lng);
								var marker = new google.maps.Marker(
								{
									position : myLatlng,
									title : hazard.address,
									severity : hazard.severity,
									hazardType : hazard.hazardType,
									reportID : hazard.reportID,
									hazardID : hazard.hazardID,
									resolutionStatus : hazard.resolutionStatus
											
								});
								if (hazard.resolutionStatus !== 'resolved') {
								google.maps.event.addListener(marker, 'click', (function (marker, i) {
						            return function () {
							       		var address = marker.get("title");
							       		var reportID = marker.get("reportID");
							       		var hazardID = marker.get("hazardID");
							 		   	var content = "Address : " + marker.get("title") + 
								      	"<br> Hazard Type : " + marker.get("hazardType") + 
							         	"<br> Severity : " + marker.get("severity") +
							         	"<br> Status : " + marker.get("resolutionStatus") +
						            	'<br><input type="button" value="View more details"' + 
						            	'onClick="goToHazardDetails(\'' + address + '\' , \'' + reportID + '\' , \'' + hazardID + '\' )" />';
							        	infowindow.setContent(content);
							        	infowindow.open(map, marker);
						        }})(marker, i)); 

									// To add the marker to the map, call setMap();
									marker.setMap(map);
													
								}
							}
		
					},
					error : function(e) {
					console.log("ERROR: ", e);
					}
				});
			}

							
			// DO POST
			function ajaxPost() {

				var data = {
					address: "15532 Calgary Street San Leandro",
					hazardType: "Bump",
					severity: 1,
					reportDate : 2017-11-15,
					voteCount : 0
				};

				$.ajax({
					type : "POST",
					url : '${pageContext.request.contextPath}/hazardPost',
					data : {
						json : JSON.stringify(data)
					},
					success : function(response) {
						alert(response);
						//location.href = "map" acts like a href, refreshes map view if anything
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			}
						

			 	
		function goToHazardDetails(address, reportID, hazardID)	{

			var data = {
				address : address,
				reportID : reportID,
				hazardID : hazardID
			};

			$.ajax({
				type : "POST",
				url : '${pageContext.request.contextPath}/hazardPost',
				data : {
					json : JSON.stringify(data)
				},
				success : function(jsp) {
					location.href = jsp;
				},
				error : function(e) {
					alert("ERROR " + jsp);
				}
			}); 
		}
										

						
	</script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCka5tJuOaFcsKFvOrJdocRYu3sqwH7Tgg&callback=initMap"
		async defer></script>
</body>
</html>