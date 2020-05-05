<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Maksut</title>
</head>
<body>
	
	<h1>Tallennetut maksut</h1>
	<a href="maksunlisays"><button>Lis‰‰ uusi maksu</button></a><br><br>
	<table>
		<tr>
			<th>ID</th>
			<th>Kuvaus</th>
			<th>Hinta</th>
			<th>P‰iv‰m‰‰r‰</th>
			<th>Maksaja</th>				
		</tr>	
		<c:forEach items="${laskut}" var="lasku">		
			<tr>
				<td><b><c:out value="${lasku.id}" /></b></td>
				<td><c:out value="${lasku.kuvaus}" /></td>
				<td><b><c:out value="${lasku.hintaDec}" />&nbsp;&euro;</b></td>
				<td><c:out value="${lasku.pvmDMY}" /></td>
				<td><b><c:out value="${lasku.nimi}" /></b></td>
				<td><a href="poista-lasku?id=<c:out value="${lasku.id}"/>"><button>Poista</button></a></td>
				<td>&nbsp;<a href="muuta-laskua?id=<c:out value="${lasku.id}"/>"><button>Muokkaa</button></a></td>
			<tr/>
		</c:forEach>
	</table>
	
</body>
</html>