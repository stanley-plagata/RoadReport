<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
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

</style>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Road Report</title>

</head>

<body>
 <div id="page-background"><img src="lights.png" width="100%" height="100%" alt="bg"></div>
        <div id="content">

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
		<a href=home.jsp style="float: right;">Logout</a>
    </div>
        <table>

            <tr>

                <td  style="color: white;">Welcome ${firstname}, choose an option in the navigation bar above to get started!</td>

            </tr>
           
        </table>
    </div>
    </body>

    </html>