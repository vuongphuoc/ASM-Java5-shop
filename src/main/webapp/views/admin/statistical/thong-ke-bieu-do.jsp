<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thống kê</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/admin_styles.css">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/admin_styles.scss">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.css"
          integrity="sha512-oe8OpYjBaDWPt2VmSFR+qYOdnTjeV9QPLJUeqZyprDEQvQLJ9C5PCFclxwNuvb/GQgQngdCXzKSFltuHD3eCxA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style type="text/css">
        a {
            text-decoration: none;
        }
    </style>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

    <script>
        // Tải thư viện Google Chart
        google.charts.load('current', {packages: ['corechart']});

        // Khi thư viện đã tải xong, tạo biểu đồ
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            // Dữ liệu sản phẩm bán chạy
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Tên Sản phẩm');
            data.addColumn('number', 'Số lượng mua');
            data.addRows([
                <c:forEach items="${hangBanChayList.getContent()}" var="order">
                ['${order.tenSanPham}', ${order.soLuongDaBan}],
                </c:forEach>
            ]);

            // Tùy chỉnh biểu đồ
            var options = {
                title: 'Top 10 mặt hàng bán chạy nhất',
                chartArea: {width: '50%'},
                hAxis: {
                    title: 'Số lượng mua',
                    minValue: 0
                },
                vAxis: {
                    title: 'Tên Sản phẩm'
                }
            };

            // Vẽ biểu đồ cột
            var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>

    <script>
        google.charts.load('current', {packages: ['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('string', 'Tên Sản phẩm');
            data.addColumn('number', 'Số lượng tồn kho');
            data.addRows([
                <c:forEach items="${HangE.getContent()}" var="item">
                ['${item.tenSanPham}', ${item.soLuongTonKho}],
                </c:forEach>
            ]);

            var options = {
                title: 'Top 10 mặt hàng tồn kho nhiều nhất',
                chartArea: {width: '50%'},
                hAxis: {
                    title: 'Số lượng tồn kho',
                    minValue: 0
                },
                vAxis: {
                    title: 'Tên Sản phẩm'
                }
            };

            var chart = new google.visualization.BarChart(document.getElementById('chart_div2'));
            chart.draw(data, options);
        }
    </script>

</head>
<body>

<header class="header">
    <div class="header__container">
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

<main style="magin-top:50px">
    <br>
    <br>
    <br>
    <div class="container">
        <div class="row">
            <h3 class="text-center">Top 10 mặt hàng bán chạy nhất</h3>
            <div class="col-lg-6">
                <div id="chart_div"></div>
            </div>
            <div class="col-lg-6">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Tên Sản phẩm</th>
                        <th>Số lượng mua</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hangBanChayList.getContent()}" var="order">
                        <tr>
                            <td>${order.tenSanPham}</td>
                            <td>${order.soLuongDaBan}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <h3 class="text-center">Top 10 mặt hàng tồn kho nhiều nhất</h3>
        <div class="row">
            <div class="col-lg-6">
                <div id="chart_div2"></div>
            </div>
            <div class="col-lg-6">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Tên sản phẩm</th>
                        <th>Số lượng tồn kho</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${HangE.getContent()}" var="item">
                        <tr>
                            <td>${item.tenSanPham}</td>
                            <td>${item.soLuongTonKho}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


</main>
<script type="text/javascript"
src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"
        integrity="sha512-lbwH47l/tPXJYG9AcFNoJaTMhGvYWhVM9YI43CT+uteTRRaiLCui8snIgyAN8XWgNjNhCqlAUdzZptso6OCoFQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
    $(document).ready(() => {
        $('#imageFile').change(function () {
            const file = this.files[0];
            if (file) {
                let reader = new FileReader();
                reader.onload = function (event) {
                    console.log(event.target.result);
                    $('#imgPreview').attr('src', event.target.result);
                }
                reader.readAsDataURL(file);
            }
        });
    });
</script>
</body>
</html>
