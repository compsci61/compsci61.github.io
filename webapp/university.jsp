<%@ page import="controller.Controller,java.util.ArrayList,models.University"%>
<html>
<head>
	<!-- Get the name of the uni -->
	<%
		String uniName = request.getParameter("uniName");
	%>

	<!-- Head defines title and links to CSS -->
	<title><% out.println(uniName); %> - Unidata</title>
	<link href="css/styles.css" rel="stylesheet" type="text/css" />
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />

</head>

<body>
		
	<!-- Includes other files (code containing the header and navbar which are the same on each page) -->
	<%@include  file="includes/header.jsp" %>
	<%@include  file="includes/navbar.jsp" %>

	<!-- Main body -->
	<main>

		<h1><%out.println(uniName);%></h1>

		<br>

		<h2>List of courses offered:</h2>
			<%
				String uniID = request.getParameter("uniID");
				String newString = uniID.replaceAll("\\s+","");
				int uid = Integer.parseInt(newString);
				ArrayList<String> courseNames = new ArrayList<String>();
				courseNames = models.DAO.getAllCoursesOfferedByUni(uid);
				String courseName;

				for (int i=0;i<courseNames.size();i++) {
					courseName = courseNames.get(i);
			%>
					<form action="course-at-uni.jsp" method="POST">
						<input type="hidden" name="uniName" value="<%out.println(uniName);%>"/>
						<input type="hidden" name="courseName" value="<%out.println(courseName);%>"/>
					  	<button type="submit"><% out.println(courseName); %></button>
				  	</form>
			<%
				}
			%>
			

	</main>

</body>
</html>