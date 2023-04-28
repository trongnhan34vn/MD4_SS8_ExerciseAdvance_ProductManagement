<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhan
  Date: 27/04/2023
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Create Product Form</title>
    <link href="../assets/css/create-form.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <h1 class="header">Create Product Form</h1>
</header>
<section>
    <form action="" method="post">
        <c:if test="${message != null}" >
            <p style="color:green;">${message}</p>
        </c:if>
        <div class="form-item">
            <label for="name">Product Name: </label>
            <input type="text" name="name" id="name">
        </div>
        <div class="form-item">
            <label for="catalog">Select Catalog: </label>
            <select name="catalog" id="catalog">
                <c:forEach items="${listCatalogs}" var="catalog">
                    <option value="${catalog.getCatalogId()}">${catalog.getCatalogName()}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-item">
            <label for="description">Description: </label>
            <input type="text" name="description" id="description">
        </div>
        <div class="form-item">
            <label for="price">Price: </label>
            <input type="text" name="price" id="price">
        </div>
        <div class="form-item">
            <label for="quantity">Quantity: </label>
            <input type="text" name="quantity" id="quantity">
        </div>
        <div class="form-item">
            <label for="status">Status: </label>
            <select name="status" id="status">
                <option value="true">Còn hàng</option>
                <option value="false">Hết hàng</option>
            </select>
        </div>
        <div class="form-item">
            <input class="btn-success" type="submit" value="Create">
        </div>
        <a href="catalogServlet">Back to Home</a>
    </form>
</section>
</body>
</html>
