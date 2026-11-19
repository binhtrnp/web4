package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import data.ProductDB;
import model.Cart;
import model.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showCart(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add": {
                String description = request.getParameter("description");
                Product product = ProductDB.getProduct(description);
                if (product != null) {
                    cart.addItem(product);
                }
                break;
            }
            case "update": {
                String description = request.getParameter("description");
                String quantityParam = request.getParameter("quantity");
                try {
                    int quantity = Integer.parseInt(quantityParam.trim());
                    cart.updateItem(description, quantity);
                } catch (NumberFormatException e) {
                    // bỏ qua nếu người dùng nhập giá trị không hợp lệ
                }
                break;
            }
            case "remove": {
                String description = request.getParameter("description");
                cart.removeItem(description);
                break;
            }
            case "continueShopping":
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                return;

            case "checkout": {
                // Lưu lại danh sách sản phẩm và tổng tiền để hiển thị trang xác nhận
                request.setAttribute("orderItems", cart.getItems());
                request.setAttribute("orderTotal", cart.getTotal());
                // Xoá giỏ hàng trong session vì đơn hàng đã được "đặt"
                session.removeAttribute("cart");
                request.getRequestDispatcher("/checkout.jsp").forward(request, response);
                return;
            }

            default:
                break;
        }

        showCart(request, response);
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}