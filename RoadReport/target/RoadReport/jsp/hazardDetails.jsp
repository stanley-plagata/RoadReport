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
h1 {color: black; margin-left: 10px;font-size: 1.2em;}
h3 {color: black; margin-left: 10px;}
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

</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Road Report</title>

</head>

<body>
 <div id="page-background"><img src="lights.png" width="100%" height="100%" alt="bg"></div>
        <div id="content">

    <a href="map">
    <div class="header" style="border-radius: 10px 10px 0px 0px">
    <img src="cone.gif" alt="cone" class="imgFlip"style="width:20px;height:25px">
        ROAD REPORT
        <img src="cone.gif" alt="cone"style="width:20px;height:25px">
    </div>
    </a>
        <div class="topnav" style="border-radius: 0px 0px 10px 10px">
		<a href=map>Return to Map</a>
		<a href=home.jsp style="float: right;">Logout</a>
  	 </div>
  	 <div style="background-color: #bcbcbc; width: 20%; margin: 10px;	border-radius: 15px 50px; padding-left: 10px; padding-right: 10px;" >
	<h1 id="heading"><u> Hazard Details </u></h1>
	<h3 id= "hazardType"> ${hazardType} </h3>
	<h3 id="address"> ${address} </h3>
	<h3 id= "severity"> ${severity} </h3>
	<h3 id= "reportDate"> ${reportDate} </h3>
	<h3 id= "voteCount"> ${voteCount} </h3>

	<div style="text-align:center; padding-bottom: 10px;">
                <input id="upvoteBtn" type="button" value="Upvote"  />  
                	<input id="downvoteBtn" type="button" value="Downvote"  />
           </div>
		</div>	

	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
     $("#upvoteBtn").click(function(){
            $.ajax({
                cache : false,
                url : '${pageContext.request.contextPath}/upvote',
                method : 'GET',
                async : false,
                complete : function(data) {
                    var response = data.responseText;
                    var json = JSON.parse(response);
                    if (json.response === -9999) {
                        alert('Can Only Upvote Once');
                    }
                    else {
                        $("#voteCount").html("Vote Count : " + json.response);
                    }
                    
                }
            }); 

        });
     
     $("#downvoteBtn").click(function(){
            $.ajax({
                cache : false,
                url : '${pageContext.request.contextPath}/downvote',
                method : 'GET',
                async : false,
                complete : function(data) {
                    var response = data.responseText;
                    var json = JSON.parse(response);
                    if (json.response === -9999) {
                        alert('Can Only Downvote Once');
                    }
                    else {
                        $("#voteCount").html("Vote Count : " + json.response);
                    }
                }
            }); 
        });
    </script> 
	    </div>
</body>
</html>