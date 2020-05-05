<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Muokkaa maksua</title>
</head>
<body>

	<h1>Maksun muokkaus</h1>
	
	<p>Muuta alla olevalle lomakkeelle maksetun maksun tietoja</p>
	
	<form action="muuta-laskua" method="post">
		
		<label>Laskun id:</label>
		<input type="text" name="id" size="1" value="<c:out value="${lasku.id}"/>" readonly/><br><br>
		
		<label>Kuvaus laskusta/kuitista:</label><br>
		<input type="text" name="kuvaus" size="50" value="<c:out value="${lasku.kuvaus}"/>"/><br><br>
		
		<label>Hinta:</label><br>
		<input type="text" name="hinta" size="50" value="<c:out value="${lasku.hintaDec}"/>"/><br><br>
		
		<label>P‰iv‰m‰‰r‰:</label><br>
		<input type="date" name="pvm" value="<c:out value="${lasku.pvm}"/>"/><br><br>
		
		<label>Maksun maksaja:</label><br>
		<select name="maksaja">
			<c:forEach items="${maksajat}" var="maksaja">
				<c:choose>
					<c:when test="${maksaja.id == lasku.maksaja}">
						<option value="<c:out value="${maksaja.id}" />" selected><c:out value="${maksaja.nimi}" /></option>
					</c:when>
					<c:otherwise>
  						<option value="<c:out value="${maksaja.id}" />"><c:out value="${maksaja.nimi}" /></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select><br><br>
		
		<button type="submit">Tallenna</button>
		<a href="maksulista" ><input type="button" value="Takaisin"/></a>
		
	</form>
	<p><c:out value="${param.viesti}" /></p>

</body>
</html>