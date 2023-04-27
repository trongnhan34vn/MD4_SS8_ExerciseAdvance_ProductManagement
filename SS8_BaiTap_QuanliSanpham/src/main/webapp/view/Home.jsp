<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nhan
  Date: 26/04/2023
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Home</title>
    <link href="../assets/css/home.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <header class="header">
        <h1>Home Admin</h1>
    </header>
    <section class="section">
        <div class="func-area">
            <div class="search-area">
                <input type="text">
                <button class="btn-success">Search</button>
            </div>
            <div class="create-area">
                <button class="btn-outline-success"><a href="">Create New Product</a> </button>
            </div>
        </div>
        <table class="list-product">
            <thead>
            <tr>
                <td width="10%">Product ID</td>
                <td>Product Name</td>
                <td>Description</td>
                <td>Price</td>
                <td width="10%">Quantity</td>
                <td>Status</td>
                <td width="20%">Action</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${catalogList}" var="catalog">
                <tr>
                    <td class="catalog-name" colspan="7">${catalog.getCatalogName()}</td>
                </tr>
                <c:forEach items="${productList}" var="product">
                    <c:if test="${product.getCatalog().getCatalogName() == catalog.getCatalogName()}">
                        <tr>
                            <td class="mid-text">${product.productId}</td>
                            <td class="product-name">
                                <img src="https://img.meta.com.vn/Data/image/2021/01/11/tai-hinh-anh-buon-anh-buon-tam-trang-20.jpg"
                                     alt="">
                                <span>${product.productName}</span>
                            </td>
                            <td>${product.description}</td>
                            <td class="mid-text">${product.price}</td>
                            <td class="mid-text">${product.quantity}</td>
                            <td class="mid-text">${product.status ? "còn hàng" : "hết hàng"}</td>
                            <td>
                                <button class="btn-primary">Edit</button>
                                <button class="btn-danger">Delete</button>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </section>
</div>
</body>
</html>
