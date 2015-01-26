<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Форма загрузки XML</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" />
</head>

<body>


<form:form method="POST" commandName="myFile" class="main-form" enctype="multipart/form-data">
    <h2>Форма загрузки XML файла</h2>
    <input type="file" name="myFile" />
    <label for="sum">Минимальная сумма заказов клента:</label>
    <input type="text" name="sum" id="sum" placeholder="100">
    <input type="submit" value="Загрузить" />
    <form:errors path="myFile" cssStyle="color: #ff0000;" />
</form:form>

</body>
</html>
