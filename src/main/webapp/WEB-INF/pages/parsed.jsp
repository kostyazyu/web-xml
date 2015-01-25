<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<body>
<h1>Файл "${fileName}" прочитан</h1>
Сумма всех заказов : ${allOrderSum}<br/>
Клиент сделавший максимальный заказ: ${maxSumCustomer}<br/>
Максимальная сумма заказа : ${maxOrderSum}<br/>
Минимальная сумма заказа : ${minOrderSum}<br/>
Общее количество заказов : ${ordersCount}<br/>
Средняя сумма заказа : ${avgOrderSum}<br/>
<h2>Список клиентов с суммой заказа больше чем ${maxOrderSumRequested}:</h2>

<c:forEach items="${customersWithOrderSumGraterThan}" var="item" >
    <ul>
        <li>${item.name}</li>
    </ul>
</c:forEach>

</body>
</html>


