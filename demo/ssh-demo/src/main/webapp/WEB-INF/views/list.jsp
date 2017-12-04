<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <link rel="stylesheet" href="/static/bootstrap.min.css">
</head>
<body>
<div class="container">
    <ul id="pagination" class="pagination pagination-lg"></ul>

    <div class="panel panel-default">
        <div class="panel-body">
            <form action="" class="form-inline">
                <input type="text" placeholder="商品名称" name="q_like_s_productName" class="form-control">
                <input type="text" placeholder="价格或市场价格" name="q_eq_bd_marketPrice_or_price" class="form-control">
                <button class="btn btn-default">搜索</button>
            </form>
        </div>
    </div>
    <a href="/product/new" class="btn btn-success">添加</a>
    <table class="table">
        <thead>
        <tr>
            <th>商品名称</th>
            <th>价格</th>
            <th>市场价</th>
            <th>产地</th>
            <th>评论数量</th>
            <th>分类</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageList.items}" var="product">
            <tr>
                <td><a href="/product/${product.id}">${product.productName}</a> </td>
                <td>${product.price}</td>
                <td>${product.marketPrice}</td>
                <td>${product.place}</td>
                <td>${product.commentNum}</td>
                <td>${product.kaolaType.typeName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<script src="/static/jquery.twbsPagination.js"></script>
<script>
    $(function () {
        //分页插件
        $("#pagination").twbsPagination({
            totalPages:8,
            visiblePages:5,
            href:"?p={{number}}",
            first: "首页",
            prev: "上一页",
            next:"下一页",
            last:"末页"
        });
    });
</script>
</body>
</html>
