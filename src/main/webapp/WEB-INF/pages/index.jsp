<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<body>

<h2>Форма загрузки XML файла</h2>
<form:form method="POST" commandName="myFile"	enctype="multipart/form-data">

    <input type="file" name="myFile" /><br/><br/>
    <label for="sum">Минимальная сумма заказов клента:</label>
    <input type="text" name="sum" id="sum"><br/><br/>
    <input type="submit" value="Загрузить" /><br/><br/>
    <form:errors path="myFile" cssStyle="color: #ff0000;" />
</form:form>

</body>
</html>
