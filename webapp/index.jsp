<html>
<head>
	<!-- Head defines title and links to CSS -->
	<title>Website Title</title>
</head>

<body>

	<!-- Top of page: Login button -->
	<div class="login">
		<a href="login.jsp">Login</a>
	</div>
		
	<!-- Website title, navigation and search bar are centered on the page -->
	<center>
	
		<!-- Header includes title -->
		<div class="header">
			<h1>Website Title</h1>
		</div>

		<!-- Navigation links to different areas of the site -->
		<div class="navigation">
			<a href="">Home</a>
			<a href="university.jsp">Universities</a>
			<a href="course.jsp">Courses</a>
		</div>

		<!-- Main body, large area with search bar -->
		<div class="search">
			<form action="/results.jsp">
				<input type="text" placeholder="Search" name="searchQuery">
				<input type="submit" value="Search">
			</form>
		</div>

		<!-- Footer containing references and about information -- >
		<div class="footer">
			<p>References go here</p>
		</div>

	</center>
</body>
</html>
