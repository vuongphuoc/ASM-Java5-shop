<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Mulish:ital,wght@0,300;0,400;0,700;1,700&family=Playfair+Display:ital,wght@0,400;0,700;1,400;1,700&display=swap"
	rel="stylesheet">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/animate.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery.fancybox.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fonts/icomoon/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fonts/flaticon/font/flaticon.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/aos.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

<title>ASM Trang Sức</title>
<style type="text/css">
a {
	text-decoration: none;
}
</style>
</head>
<body>

	<!-- Menu -->
	<div class="search-form" id="search-form">
		<form action="">
			<input type="search" class="form-control"
				placeholder="Enter keyword to search...">
			<button class="button">
				<svg width="1em" height="1em" viewBox="0 0 16 16"
					class="bi bi-search" fill="currentColor"
					xmlns="http://www.w3.org/2000/svg">
					<path fill-rule="evenodd"
						d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z" />
					<path fill-rule="evenodd"
						d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z" />
				</svg>
			</button>
			<button class="button">
				<div class="close-search">
					<span class="icofont-close js-close-search"></span>
				</div>
			</button>

		</form>
	</div>

	<div class="site-mobile-menu">
		<div class="site-mobile-menu-header">
			<div class="site-mobile-menu-close">
				<span class="icofont-close js-menu-toggle"></span>
			</div>
		</div>
		<div class="site-mobile-menu-body"></div>
	</div>



	<nav class="site-nav mb-5">
		<div class="sticky-nav js-sticky-header">

			<div class="container position-relative">
				<div class="site-navigation text-center dark">
					<a href="${pageContext.request.contextPath}/home"
						class="logo menu-absolute m-0">UntreeStore<span
						class="text-primary">.</span></a>

					<ul class="js-clone-nav pl-0 d-none d-lg-inline-block site-menu">
						<li><a href="${pageContext.request.contextPath}/home">Home</a></li>
						<li><a href="${pageContext.request.contextPath}/shop">Shop</a></li>
					</ul>
					<div class="menu-icons">
						<c:if test="${!empty sessionScope.account}">
							<ul
								class="user-profile js-clone-nav pl-0 d-none d-lg-inline-block site-menu">
								<li class="has-children">
									<a href="${pageContext.request.contextPath}/profile">${sessionScope.account.fullName}</a>
									<ul class="dropdown">
									<c:if test="${sessionScope.account.admin == 1}">
										<li><a href="${pageContext.request.contextPath}/admin/home">Admin page</a></li>
									</c:if>
										<li><a href="${pageContext.request.contextPath}/order-history">Lịch sử đơn hàng</a></li>
										<li><a href="${pageContext.request.contextPath}/change-password">
											Đổi mật khẩu</a></li>
										<li><a href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
									</ul>
								</li>
							</ul>
						</c:if>
						<c:if test="${empty sessionScope.account}">
							<ul
								class="user-profile js-clone-nav pl-0 d-none d-lg-inline-block site-menu">
								<li><a href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
							</ul>
						</c:if>

						<a href="${pageContext.request.contextPath}/shopping-cart"
							class="cart"> <span class="item-in-cart"> <c:if
									test="${!empty sessionScope.count}">
							${sessionScope.count}
							</c:if> <c:if test="${empty sessionScope.count}">
							0
							</c:if>

						</span> <svg width="1em" height="1em" viewBox="0 0 16 16"
								class="bi bi-cart" fill="currentColor"
								xmlns="http://www.w3.org/2000/svg">
								<path fill-rule="evenodd"
									d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm7 0a1 1 0 1 0 0 2 1 1 0 0 0 0-2z" />
							</svg>
						</a>

					</div>

					<a href="#"
						class="burger ml-auto float-right site-menu-toggle js-menu-toggle d-inline-block d-lg-none"
						data-toggle="collapse" data-target="#main-navbar"> <span></span>
					</a>

				</div>
			</div>
		</div>
	</nav>


	<!--------------------------Menu-------------------------------->



	<!--------------------------View-------------------------------->
	<jsp:include page="${view}" />

	<!--------------------------View-------------------------------->




	<div id="overlayer"></div>
	<div class="loader">
		<div class="spinner-border" role="status">
			<span class="sr-only">Loading...</span>
		</div>
	</div>

	<!--  Footer-->
	<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.animateNumber.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.waypoints.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.fancybox.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.sticky.js"></script>
	<script src="${pageContext.request.contextPath}/js/aos.js"></script>
	<script src="${pageContext.request.contextPath}/js/custom.js"></script>

	<footer class=" text-center text-lg-start py-3 fp" style="background-color: #FFCDA8;color: white">
		<section class="">
			<div class="container text-center text-md-start mt-5">
				<div class="row mt-3">
					<div class="col-md-4 col-lg-4 col-xl-4 mx-auto mb-4">
						<h6 class="text-uppercase fw-bold mb-4">
							<img src="/images/logo2.png" height="90px"/>
						</h6>
					</div>

					<div class="col-md-8 col-lg-8 col-xl-8 mx-auto mb-8">
						<h6>THÔNG TIN LIÊN HỆ</h6>
						<p>VVP</p>
						<p>Điện thoại: +8433345045</p>
						<p>Email: phuocvvph23075@fpt.edu.vn</p>
					</div>
				</div>
			</div>
		</section>
		<div
				class="text-center py-4"
				style="background-color: hsla(0, 100%, 50%, 0.3)";
		>
			© 2023 Copyright: >phuocvvph23075@fpt.edu.vn</a
				>
		</div>
	</footer>

</body>
</html>