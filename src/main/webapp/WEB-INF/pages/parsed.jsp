<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
    <head>
        <title>XML</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/parsed.css"/>
    </head>


    <body>
        <div class="wrap">
            <h1>Анализ файла <span class="file-name">${fileName}</span></h1>
            <div class="table">
                <div class="table-row table-header">
                    <div class="header-cell cell-1">Наименование</div>
                    <div class="header-cell cell-2">Значение</div>
                </div>
                <div class="table-row">
                    <div class="table-cell cell-1">
                        Сумма всех заказов
                    </div>
                    <div class="table-cell cell-2">
                        ${allOrderSum}
                    </div>
                </div>

                <div class="table-row">
                    <div class="table-cell cell-1">
                        Клиент сделавший максимальный заказ
                    </div>
                    <div class="table-cell cell-2">
                        ${maxSumCustomer}
                    </div>
                </div>
                <div class="table-row">
                    <div class="table-cell cell-1">
                        Максимальная сумма заказа
                    </div>
                    <div class="table-cell cell-2">
                        ${maxOrderSum}
                    </div>
                </div>
                <div class="table-row">
                  <div class="table-cell cell-1">
                         Минимальная сумма заказа
                  </div>
                  <div class="table-cell cell-2">
                     ${minOrderSum}
                  </div>
                </div>

                <div class="table-row">
                    <div class="table-cell cell-1">
                        Общее количество заказов
                    </div>
                    <div class="table-cell cell-2">
                        ${ordersCount}
                    </div>
                </div>
                <div class="table-row">
                    <div class="table-cell cell-1">
                        Средняя сумма заказа
                    </div>
                    <div class="table-cell cell-2">
                        ${avgOrderSum}
                    </div>
                </div>




            </div>

            <h1>Список клиентов с суммой заказа больше ${maxOrderSumRequested} рублей:</h1>

            <c:forEach items="${customersWithOrderSumGraterThan}" var="item">
               <ul>
                    <li>${item.name}</li>
               </ul>
            </c:forEach>
        </div>

    </body>
</html>


