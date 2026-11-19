<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Collection, model.Cart, model.LineItems" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>

<h1>Your cart</h1>

<%
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null || cart.isEmpty()) {
%>
<p>Giỏ hàng của bạn hiện đang trống.</p>
<%
} else {
%>
<table>
    <tr>
        <th>Quantity</th>
        <th>Description</th>
        <th>Price</th>
        <th>Amount</th>
        <th></th>
    </tr>
    <%
        Collection<LineItems> items = cart.getItems();
        for (LineItems item : items) {
    %>
    <tr>
        <td class="quantity-cell">
            <form action="cart" method="post">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="description" value="<%= item.getProduct().getDescription() %>">
                <input type="text" name="quantity" value="<%= item.getQuantity() %>">
                <button type="submit">Update</button>
            </form>
        </td>
        <td><%= item.getProduct().getDescription() %></td>
        <td class="price">$<%= String.format("%.2f", item.getProduct().getPrice()) %></td>
        <td class="amount">$<%= String.format("%.2f", item.getTotal()) %></td>
        <td>
            <form action="cart" method="post">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="description" value="<%= item.getProduct().getDescription() %>">
                <button type="submit">Remove Item</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>

<p class="note"><strong>To change the quantity</strong>, enter the new quantity and click on the Update button.</p>
<%
    }
%>

<div class="buttons">
    <form action="cart" method="post">
        <input type="hidden" name="action" value="continueShopping">
        <button type="submit">Continue Shopping</button>
    </form>
    <form action="cart" method="post">
        <input type="hidden" name="action" value="checkout">
        <button type="submit">Checkout</button>
    </form>
</div>

</body>
</html>
