<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thong ke</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<!--========== HEADER ==========-->
<header class="header">
    <div class="header__container">
        <%--        <img src="/Assignment_Java4/assets/admin/img/perfil.jpg" alt="" class="header__img">--%>
        <%-- 	<a href="#" class="header__logo">Admin</a> <span>Xin chào,
        ${sessionScope.user.fullName}</span> --%>
        <div class="header__toggle">
            <i class='bx bx-menu' id="header-toggle"></i>
        </div>
    </div>
</header>
<!--========== NAV ==========-->
<div class="nav1" id="navbar">
    <nav class="nav__container">
        <div>
            <a href="#" class="nav__link nav__logo"> <i
                    class='bx bxs-disc nav__icon'></i> <span class="nav__logo-name">Admin</span>
            </a>

            <div class="nav__list">
                <div class="nav__items">
                    <h3 class="nav__subtitle">Quản lý</h3>
                    <a href="${pageContext.request.contextPath}/admin/home"
                       class="nav__link active"> <i class='bx bx-home nav__icon'></i>
                        <span class="nav__name">Trang chủ</span>
                    </a>

                    <div class="nav__dropdown">
                        <a href="#" class="nav__link"> <i
                                class='bx bx-package nav__icon'></i> <span class="nav__name">Sản
									phẩm</span> <i class='bx bx-chevron-down nav__icon nav__dropdown-icon'></i>
                        </a>

                        <div class="nav__dropdown-collapse">
                            <div class="nav__dropdown-content">
                                <a
                                        href="${pageContext.request.contextPath}/admin/category/index"
                                        class="nav__dropdown-item">Danh mục</a> <a
                                    href="${pageContext.request.contextPath}/admin/product/index"
                                    class="nav__dropdown-item">Sản phẩm</a>
                            </div>
                        </div>
                    </div>

                    <a href="${pageContext.request.contextPath}/admin/account/index"
                       class="nav__link"> <i class='bx bx-user nav__icon'></i> <span
                            class="nav__name">Người dùng</span>
                    </a> <a href="${pageContext.request.contextPath}/admin/order/index"
                            class="nav__link"> <i class='bx bx-cart nav__icon'></i> <span
                        class="nav__name">Đơn hàng</span>
                </a>

                    <div class="nav__dropdown">
                        <a href="#" class="nav__link">
                            <i class='bx bx-package nav__icon'></i> <span class="nav__name">TOP</span> <i class='bx bx-chevron-down nav__icon nav__dropdown-icon'></i>
                        </a>

                        <div class="nav__dropdown-collapse">
                            <div class="nav__dropdown-content">
                                <a
                                        href="${pageContext.request.contextPath}/admin/statistical/index"
                                        class="nav__dropdown-item">TOP 10 mặt hàng bán chạy nhất</a> <a
                                    href="${pageContext.request.contextPath}/admin/statistical/tonkho"
                                    class="nav__dropdown-item">TOP 10 mặt hàng tồn lâu nhất</a>
                            </div>
                        </div>
                    </div>


                </div>
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/logout"
           class="nav__link nav__logout"> <i
                class='bx bx-log-out nav__icon'></i> <span class="nav__name">Đăng
					xuất</span>
        </a>
    </nav>
</div>



<div class="container mt-3">
    <h2>Top 10 mat hang ban chay nhat</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Tên Sản phẩm</th>
            <th>Số lượng mua </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${hangBanChayList.getContent()}" var="order">
            <tr>
                <td>${order.tenSanPham}</td>
                <td>${order.soLuongDaBan}</td>

                <td>
                    <a href="/admin/order/order-detail/${order.id}">Xem chi tiet</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
