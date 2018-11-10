<%-- 
    Document   : Account
    Created on : Nov 8, 2018, 3:56:37 PM
    Author     : kao-tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank</title>
    </head>
    <body>
        <h1>Hello ${account.name}!</h1>
        <h1>Balance :  ${account.balance}</h1>
        <h2>${message}</h2>
        <h2><a href="DepositF">Deposit</a></h2>
        <h2><a href="WithdrawF">Withdraw</a></h2>
        <h2><a href="HistoryF">History</a></h2>
        <br>
        <br>
        <h3><a href="Logout">Logout</a></h3>
    </body>
</html>
