<%-- 
    Document   : Login
    Created on : Nov 8, 2018, 3:56:26 PM
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
        <h1>Login!</h1>
        <form action="Login">
            <table class="tablelogin">
                <h2>${message}</h2>
                <tr>
                <h2><td>User ID : <input type="text" name="userid"></td></h2>
                </tr>
                <tr>
                <h2><td>PIN : <input type="password" name="pin"></td></h2>
                </tr>
                <tr>
                <td><input type="submit" value="Login"></td>
                </tr>
            </table>
            <h3><a href="index.html">Back</a></h3>
        </form>
    </body>
</html>
