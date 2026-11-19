<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, model.Product, data.ProductDB" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>

<h1>CD list</h1>

<table>
    <tr>
        <th>Description</th>
        <th class="price">Price</th>
        <th></th>
    </tr>
    <%
        List<Product> products = ProductDB.getProducts();
        for (Product product : products) {
    %>
    <tr>
        <td><%= product.getDescription() %></td>
        <td class="price">$<%= String.format("%.2f", product.getPrice()) %></td>
        <td class="action">
            <form action="cart" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="description" value="<%= product.getDescription() %>">
                <button type="submit">Add To Cart</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>

</body>
</html>
