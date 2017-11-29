<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title></title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="panel panel-default">

            <div class="panel-heading">
                <h3>查询</h3>
            </div>
            <div class="panel-body">
                <form method="get" class="form-inline">
                    <input type="text" name="productName" class="form-control" placeholder="商品名称" VALUE="${param.productName}">
                    <select class="form-control" name="place">
                        <option value="">--请选择产地--</option>
                        <c:forEach items="${placeList}" var="place">
                            <option value="${place}" ${param.place == place ? 'selected' : ''}>
                                    ${place}
                            </option>
                        </c:forEach>
                    </select>
                    <select name="typeId" class="form-control">
                        <option value="">--请选择类型--</option>
                        <c:forEach items="${typeList}" var="type">
                            <option value="${type.id}" ${param.typeId == type.id ? 'selected' : ''}>${type.typeName}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-default">搜索</button>
                </form>

            </div>
        </div>
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
        <a href="/product/new" class="btn btn-primary pull-right" >新增</a>
        <table class="table">
            <thead>
                <tr>
                    <th>商品名称</th>
                    <th>所属类型</th>
                    <th>产地</th>
                    <th>考拉价</th>
                    <th>市场价</th>
                    <th>评论数量</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pageInfo.list}" var="product">
                    <tr>
                        <td><a href="/product/${product.id}">${product.productName}</a></td>
                        <td>${product.type.typeName}</td>
                        <td>${product.place}</td>
                        <td>${product.price}</td>
                        <td>${product.marketPrice}</td>
                        <td>${product.commentNum}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <ul id="pagination" class="pagination pagination-lg"></ul>

    </div>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/jquery.twbsPagination.min.js"></script>
    <script>
        $(function () {
            $("#pagination").twbsPagination({
                totalPages:"${pageInfo.pages}",
                visiblePages:8,
                href:"?place="+ encodeURIComponent('${param.place}') +"&productName=" + encodeURIComponent('${param.productName}') + "&typeId=" +encodeURIComponent(${param.typeId}) + "&p={{number}}",
                first: "首页",
                prev: "上一页",
                next:"下一页",
                last:"末页"
            });
        });
    </script>
</body>
</html>