<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-heading bg-light">
	<div class="container">
		<div class="row align-items-end text-center">
			<div class="col-lg-7 mx-auto">
				<h1>Shop</h1>
				<p class="mb-4">
					<a href="${pageContext.request.contextPath}/home">Home</a> / <strong>Shop</strong>
				</p>
			</div>
		</div>
	</div>
</div>
<div class="untree_co-section pt-3">
	<div class="container">
		<div class="row">
			<div class="col-md-6 text-center">
				<div class="product-image mt-3">
					<img class="img-fluid"
						src="${pageContext.request.contextPath}/upload/${product.image}">
				</div>

			</div>
			<div class="col-md-6 mt-5 mt-md-2 text-md-left">
				<h2 class="mb-3 mt-0">${product.name}</h2>
				<p class="lead mt-2 mb-3 primary-color">
					<fmt:formatNumber value="${product.price}" pattern="#,###,###" />
				</p>
				<form action="${pageContext.request.contextPath}/add-to-cart"
					method="post">
					<input type="hidden" name="id" value="${product.id}">
					Số lượng: <input type="text" class="form-control quantity mb-4"
						name="quantity" value="1">
					<c:if test="${!empty sessionScope.errorQuantity}">
						<p class="text-danger">${sessionScope.errorQuantity}</p>
						<c:remove var="errorQuantity" scope="session" />
					</c:if>
					<button class="btn btn-full-width btn-lg btn-outline-primary">Thêm vào giỏ hàng</button>
				</form>
			</div>
		</div>
	</div>
	<!-- /.untree_co-section -->

	<div class="untree_co-section mt-5">
		<div class="container">
			<div class="row mb-5 align-items-center">
				<div class="col-md-6">
					<h2 class="h3">Mặt hàng phổ biến</h2>
				</div>
				<div class="col-sm-6 carousel-nav text-sm-right">
					<a href="#" class="prev js-custom-prev-v2"> <svg width="1em"
							height="1em" viewBox="0 0 16 16" class="bi bi-arrow-left-circle"
							fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
								d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd"
								d="M8.354 11.354a.5.5 0 0 0 0-.708L5.707 8l2.647-2.646a.5.5 0 1 0-.708-.708l-3 3a.5.5 0 0 0 0 .708l3 3a.5.5 0 0 0 .708 0z" />
                                <path fill-rule="evenodd"
								d="M11.5 8a.5.5 0 0 0-.5-.5H6a.5.5 0 0 0 0 1h5a.5.5 0 0 0 .5-.5z" />
                            </svg>
					</a> <a href="#" class="next js-custom-next-v2"> <svg width="1em"
							height="1em" viewBox="0 0 16 16" class="bi bi-arrow-right-circle"
							fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd"
								d="M8 15A7 7 0 1 0 8 1a7 7 0 0 0 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                <path fill-rule="evenodd"
								d="M7.646 11.354a.5.5 0 0 1 0-.708L10.293 8 7.646 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0z" />
                                <path fill-rule="evenodd"
								d="M4.5 8a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1H5a.5.5 0 0 1-.5-.5z" />
                            </svg>
					</a>
				</div>
			</div>
			<!-- /.heading -->
			<div class="owl-3-slider owl-carousel">
				<c:forEach items="${listProductsByCategoryId}" var="product">
					<div class="item">
						<div class="product-item">
							<a href="shop-single.html" class="product-img">
								<div class="label sale top-right">
									<div class='content'>Sale</div>
								</div> <img src="${pageContext.request.contextPath}/upload/${product.image}" alt="Image"
								class="img-fluid">
							</a>
							<h3 class="title">
								<a href="#">${product.name}</a>
							</h3>
							<h3 class="title">
								<a href="#">${product.quantity}</a>
							</h3>
							<div class="price">
								<span>${product.price}</span>
							</div>
						</div>
					</div>
					<!-- /.item -->
				</c:forEach>
			</div>
		</div>
		<!-- /.container -->
	</div>
</div>
<!-- /.untree_co-section -->