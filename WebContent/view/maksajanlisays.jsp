<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Lisää maksaja</title>
</head>
<body>

	<h1>Uuden maksajan lisäys</h1>
	
	<form action="maksajanlisays" method="post">
	
		<label>Uusi maksaja</label><br>
		<input type="text" name="maksaja" size="50"/><br><br>
		
		<button type="submit">Tallenna</button>
		<a href="maksunlisays"><input type="button" value="Takaisin"/></a>
		
	</form>
	<p><c:out value="${param.viesti}" /></p>

</body>
</html>