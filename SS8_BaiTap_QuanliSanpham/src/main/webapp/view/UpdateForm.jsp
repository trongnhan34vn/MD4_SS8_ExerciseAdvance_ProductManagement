<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="config.Config" %>
<%--
  Created by IntelliJ IDEA.
  User: nhan
  Date: 28/04/2023
  Time: 07:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Update Form</title>
    <link href="../assets/css/update-form.css" rel="stylesheet" type="text/css">
</head>
<body>
<form action="" method="post">
    <header>
        <h1 class="header">Update</h1>
    </header>
    <c:if test="${message != null}">
        <p style="color:green; text-align: center">
                ${message}
        </p>
    </c:if>
    <c:if test="${updateProduct != null}">
        <table class="product-info">
            <tr>
                <td width="25%"><label for="id">Product ID:</label></td>
                <td>${updateProduct.getProductId()}</td>
                <input type="text" id="id" name="id" hidden="hidden" value="${updateProduct.getProductId()}">
            </tr>
            <tr>
                <td><label for="name">Product Name:</label></td>
                <td>
                    <p onclick="convertInput(event, '#name')">${updateProduct.getProductName()}</p>
                    <input id="name" name="name" style="display:none;" type="text"
                           value="${updateProduct.getProductName()}"/>
                </td>
            </tr>
            <tr>
                <td><label for="description">Description:</label></td>
                <td>
                    <c:if test="${updateProduct.getDescription() != null}">
                        <p onclick="convertInput(event, '#description')">${updateProduct.getDescription()}</p>
                    </c:if>
                    <c:if test="${updateProduct.getDescription() == null}">
                        <p style="opacity: 0" onclick="convertInput(event, '#description')">null</p>
                    </c:if>
                    <input name="description" id="description" style="display:none;" type="text"
                           value="${updateProduct.getDescription()}"/>
                </td>
            </tr>
            <tr>
                <td><label for="catalog">Catalog</label></td>
                <td>
                    <select name="catalog" id="catalog">
                        <c:forEach items="${listCatalog}" var="catalog">
                            <option
                                    <c:if test="${updateProduct.getCatalog().getCatalogId() == catalog.getCatalogId()}">selected=selected</c:if>
                                    value="${catalog.getCatalogId()}">${catalog.getCatalogName()}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="price">Price: </label></td>
                <td>
                    <p onclick="convertInput(event, '#price')">${Config.numberFormat.format(updateProduct.getPrice())}</p>
                    <input id="price" name="price" style="display:none;" type="text"
                           value="${updateProduct.getPrice()}"/>
                </td>
            </tr>
            <tr>
                <td><label for="quantity">Quantity: </label></td>
                <td>
                    <p onclick="convertInput(event, '#quantity')">${updateProduct.getQuantity()}</p>
                    <input id="quantity" name="quantity" style="display:none;" type="text"
                           value="${updateProduct.getQuantity()}"/>
                </td>
            </tr>
            <tr>
                <td><label for="status">Status</label></td>
                <td>
                    <select name="status" id="status">
                        <option value="true">còn hàng</option>
                        <option value="false">hết hàng</option>
                    </select>
                </td>
            </tr>
        </table>
        <div class="form-item">
            <input class="btn-success" type="submit" value="Update">
        </div>
    </c:if>
</form>
<div class="back-area">
    <a class="back" href="catalogServlet">Back to Home</a>
</div>

<script src="../assets/script/index.js"></script>
</body>
</html>
