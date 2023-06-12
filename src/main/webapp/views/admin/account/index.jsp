<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account</title>
<!--========== BOX ICONS ==========-->
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
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style type="text/css">
a {
	text-decoration: none;
}
</style>
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

	<!--========== CONTENTS ==========-->
	<main style="margin-top: 50px">
		<div class="row">
			<h3 class="p-0">Quản lý người dùng</h3>
		</div>
		<div class="row mt-2 mb-4">
			<div class="col-6 p-0">
				<div class="header__search">
					<input type="search" placeholder="Tìm kiếm" class="header__input">
					<i class='bx bx-search header__icon mt-1'></i>
				</div>
				<div class="header__toggle">
					<i class='bx bx-menu' id="header-toggle"></i>
				</div>
			</div>
			<div class="col-6 justify-content-end">
				<div class="row align-items-center">
					<div class="col-4">
						<form method="GET"
							action="${pageContext.request.contextPath}/admin/account/index">
							<div class="form-group">
								<select class="form-select" name="sort_by" onchange="this.form.submit()">
									<option value="id" ${sortBy == 'id' ? 'selected' : ''}>Mặc định</option>
									<option value="username" ${sortBy == 'username' ? 'selected' : ''}>Họ tên</option>
									<option value="email" ${sortBy == 'email' ? 'selected' : ''}>Email</option>
									<option value="admin" ${sortBy == 'admin' ? 'selected' : ''}>Tài khoản</option>
								</select>
							</div>
						</form>
					</div>
					<div class="col-4">
						<a href="${pageContext.request.contextPath}/admin/account/create"
							class="text-dark"> <i class='bx bx-folder-plus fs-5'></i>
							Thêm người dùng
						</a>
					</div>
					<div class="col">
						<a href="${pageContext.request.contextPath}/admin/account/export"
							class="text-dark"> <i class='bx bx-export fs-5'></i>
							Xuất excel
						</a>
					</div>
					<div class="col">
						<a href="${pageContext.request.contextPath}/admin/account/import"
							class="text-dark"> <i class='bx bx-import fs-5'></i>
							Nhập excel
						</a>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<table class="table">
				<thead class="table-dark">
					<th>STT</th>
					<th>Username</th>
					<th>Họ tên</th>
					<th>Email</th>
					<th>Photo</th>
					<th>Activated</th>
					<th>Vai trò</th>
					<th>Hành động</th>
				</thead>
				<tbody>
					<c:forEach items="${listAccounts.content}" var="account"
						varStatus="counter">
						<tr class="align-middle">
							<td>${counter.count}</td>
							<td>${account.username}</td>
							<td>${account.fullName}</td>
							<td>${account.email}</td>
							<td><img
								src="${pageContext.request.contextPath}/upload/${account.photo}"
								width="70px" alt=""></td>
							<td>${account.activated == 1 ? 'Activated' : 'Inactivated'}</td>
							<td>${account.admin == 1 ? 'Admin' : 'User'}</td>
							<td><a class="btn btn-warning"
								href="${pageContext.request.contextPath}/admin/account/edit/${account.id}">Sửa</a>
								<a data-bs-toggle="modal"
								data-bs-target="#exampleModal${account.id}"
								class="btn btn-danger">Xóa</a> <!-- Modal -->
								<div class="modal fade" id="exampleModal${account.id}"
									tabindex="-1" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="exampleModalLabel">Xóa</h5>
												<button type="button" class="btn-close"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">Bạn có muốn xóa người dùng
												${account.fullName} không ?</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>
												<a type="button" id="btnDelete" class="btn btn-primary"
													href="${pageContext.request.contextPath}/admin/account/delete/${account.id}">Xóa</a>
											</div>
										</div>
									</div>
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="...">
				<ul class="pagination justify-content-center">
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/admin/account/index">First</a>
					</li>
					<li
						class="page-item ${listAccounts.number == 0 ? 'disabled' : '' }"><a
						class="page-link"
						href="${pageContext.request.contextPath}/admin/account/index?p=${listAccounts.number-1}">
							<< </a></li>
					<li
						class="page-item ${listAccounts.number == listAccounts.totalPages-1 ? 'disabled' : '' }"
						aria-current="page"><a class="page-link"
						href="${pageContext.request.contextPath}/admin/account/index?p=${listAccounts.number+1}">
							>> </a></li>
					<li class="page-item"><a class="page-link"
						href="${pageContext.request.contextPath}/admin/account/index?p=${listAccounts.totalPages-1}">Last</a>
					</li>
				</ul>
			</nav>
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
</body>
</html>