<%-- 
    Document   : History
    Created on : Nov 8, 2018, 3:57:00 PM
    Author     : kao-tu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank</title>
    </head>
    <body>
        <h1>History!</h1>
        <table class="tableHistory">
            <tr>
                <th>Count</th>
                <th>Time</th>
                <th>Method</th>
                <th>Amount</th>
                <th>Money</th>
            </tr>
            
            <c:forEach items="${history}" var="h" varStatus="hc">
                <tr>
                    <td>${hc.count}</td>
                    <td>${h.createdate}</td>
                    <td>${h.method}</td>
                    <td>${h.amount}</td>
                    <td>${h.balance}</td>
                </tr>
            </c:forEach>
        </table>
        <h3><a href="Account">Back</a></h3>
    </body>
</html>
