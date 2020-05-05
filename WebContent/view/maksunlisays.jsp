<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Lis‰‰ maksu</title>
</head>
<body>

	<h1>Maksun lis‰ys</h1>
	
	<p>Lis‰‰ alla olevalle lomakkeelle uuden maksetun maksun tiedot</p>
	
	<form action="maksunlisays" method="post">
	
		<label>Kuvaus laskusta/kuitista:</label><br>
		<input type="text" name="kuvaus" size="50"/><br><br>
		
		<label>Hinta:</label><br>
		<input type="text" name="hinta" size="50"/><br><br>
		
		<label>P‰iv‰m‰‰r‰:</label><br>
		<input type="date" name="pvm"/><br><br>
		
		<label>Maksun maksaja:</label><br>
		<select name="maksaja">
			<c:forEach items="${maksajat}" var="maksaja">
  				<option value="<c:out value="${maksaja.id}" />"><c:out value="${maksaja.nimi}" /></option>
			</c:forEach>
		</select>

		&nbsp;<a href="maksajanlisays" ><input type="button" value="Lis‰‰ uusi maksaja"/></a><br><br>
		
		<br><button type="submit">Tallenna</button>
		<button type="reset">Tyhjenn‰ lomake</button>
		
	</form>
	<br><a href="maksulista" ><input type="button" value="Maksulista"/></a>
	<p><c:out value="${param.viesti}" /></p>

</body>
</html>