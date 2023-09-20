
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="stile-table.css" type="text/css">
    <title>Transports</title>
</head>

<body>
<table>
    <caption>Транспорт, пригодный к диагностике, отсортированный по ${nameSorting}</caption>
    <thead>
    <tr>
        <th>type transport</th>
        <th>model transport</th>
        <th>price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="transport" items="${successTransport}">
        <tr>
            <td>${transport.type}</td>
            <td>${transport.model}</td>
            <td>${transport.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<table>
    <caption>Транспорт, непригодный к диагностике</caption>
    <thead>
    <tr>
        <th>type transport</th>
        <th>model transport</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="transport" items="${invalidTransport}">
        <tr>
            <td>${transport.type}</td>
            <td>${transport.model}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
