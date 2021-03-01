<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Report C</title>
    <link href="webjars/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" />
    <script src="webjars/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
    <script src="webjars/jquery/3.0.0/jquery.min.js" ></script>
</head>
<body>

<div class="container">
    <h1>TOP 5 most used forms</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Form ID</th>
            <th scope="col">Number of uses</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="entry" items="${topFormsMap}">
            <tr>
                <td><c:out value="${entry.key}"/></td>
                <td><c:out value="${entry.value}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="button"  class="btn btn-dark" onclick="location.href='/hello'" value="back" >
</div>
</body>

</html>