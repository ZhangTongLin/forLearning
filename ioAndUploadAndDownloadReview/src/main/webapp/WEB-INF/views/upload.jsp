<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="/static/bootstrap/css/bootstrap.min.css">
    <title></title>
</head>
<body>

    <div class="container">
        <div>
            <c:if test="${not empty message}">
                <span class="alert alert-success">${message}</span>
            </c:if>
        </div>

        <form method="post" enctype="multipart/form-data">
            <input type="file" name="file">

            <button>开始上传</button>
        </form>
    </div>

</body>
</html>

