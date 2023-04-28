<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="config.Config" %>
<%--
  Created by IntelliJ IDEA.
  User: nhan
  Date: 27/04/2023
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Delete</title>
    <link href="../assets/css/delete-form.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <h1 class="header">Delete</h1>
</header>
<section>
    <c:if test="${message != null}">
        <p style="color:green; text-align: center">
                ${message}
        </p>
    </c:if>
    <c:if test="${deleteProduct != null}">
        <table class="product-info">
            <tr>
                <td>Product ID:</td>
                <td>${deleteProduct.getProductId()}</td>
            </tr>
            <tr>
                <td>Product Name:</td>
                <td>${deleteProduct.getProductName()}</td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>
                    <c:if test="${deleteProduct.description != null}">
                        ${deleteProduct.description}
                    </c:if>
                </td>
            </tr>
            <tr>
                <td>Catalog:</td>
                <td>${deleteProduct.getCatalog().getCatalogName()}</td>
            </tr>
            <tr>
                <td>Price:</td>
                <td>
                    <c:set value="${deleteProduct.getPrice()}" var="price"/>
                    <c:out value="${Config.numberFormat.format(price)}"/>
                </td>
            </tr>
            <tr>
                <td>Quantity:</td>
                <td>${deleteProduct.getQuantity()}</td>
            </tr>
            <tr>
                <td>Status:</td>
                <td>${deleteProduct.isStatus() ? "còn hàng" : " hết hàng"}</td>
            </tr>

        </table>
        <form action="" method="post">
            <h4>Do you want to delete this product?</h4>
            <div class="answer">
                <input type="hidden" name="id" value="${deleteProduct.getProductId()}">
                <input type="submit" value="Yes">
                <a href="catalogServlet">No</a>
            </div>
        </form>
    </c:if>
    <div class="back-area">
        <a class="back" href="catalogServlet">Back to Home</a>
    </div>
</section>
</body>
</html>
