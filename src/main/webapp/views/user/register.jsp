<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!doctype html>
<html lang="en">
<head>
    <title>register</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet"
    href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<style>
    .form-group .label {
    font-size: 12px;
    text-transform: uppercase;
    letter-spacing: 1px;
    color: #000;
    font-weight: 700;
    }

    button[type="submit"].form-control:hover {
    background: #b7b7a4;
    border: #b7b7a4;
    }

    .form-control {
    height: 48px;
    background: rgba(0, 0, 0, 0.05);
    color: #000;
    font-size: 16px;
    border-radius: 50px;
    -webkit-box-shadow: none;
    box-shadow: none;
    border: 1px solid transparent;
    padding-left: 20px;
    padding-right: 20px;
    -webkit-transition: all 0.2s ease-in-out;
    -o-transition: all 0.2s ease-in-out;
    transition: all 0.2s ease-in-out;
    }

    .ftco-section {
    padding: 12rem 0;
    }
</style>
</head>
<body>
<main style="margin-top: 50px">
    <div class="container">
        <div class="row">
            <h3 class="text-center">Register</h3>
            <form:form
                    action="${pageContext.request.contextPath}/dangky"
                    modelAttribute="accountModel" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-3">
                        <img class="img-fluid" id="imgPreview" src="" alt=""
                             style="border: 1px solid #ccc;"> <label>Hình ảnh</label>
                        <form:input type="file" path="imageFile" />
                        <form:errors path="photo" element="span"
                                     cssClass="text-danger d-block" />
                    </div>
                    <div class="col-9">
                        <div class="form-group">
                            <label>Tên tài khoản</label>
                            <form:input path="username" class="form-control" autocomplete="off"/>
                            <form:errors path="username" element="span"
                                         cssClass="text-danger d-block" />
                            <c:if test="${!empty sessionScope.errorUsername}">
                                <span class="text-danger"> ${sessionScope.errorUsername}</span>
                                <c:remove var="errorUsername" scope="session" />
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label>Tên người dùng</label>
                            <form:input path="fullname" class="form-control" />
                            <form:errors path="fullname" element="span"
                                         cssClass="text-danger d-block" />
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <form:password path="password" class="form-control" autocomplete="off"/>
                            <form:errors path="password" element="span"
                                         cssClass="text-danger d-block" />
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <form:input type="email" path="email" class="form-control" />
                            <form:errors path="email" element="span"
                                         cssClass="text-danger d-block" />
                            <c:if test="${!empty sessionScope.errorEmail}">
                                <span class="text-danger"> ${sessionScope.errorEmail}</span>
                                <c:remove var="errorEmail" scope="session" />
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label>Activated</label>
                            <form:select path="activated" class="form-select">
                                <option value="1">Activated</option>
                            </form:select>
                            <form:errors path="activated" element="span"
                                         cssClass="text-danger d-block" />
                        </div>
                        <div class="form-group">
                            <label>Vai trò</label>
                            <form:select path="admin" class="form-select">
                                <option value="0">User</option>
                            </form:select>
                            <form:errors path="admin" element="span"
                                         cssClass="text-danger d-block" />
                        </div>

                        <button class="btn btn-primary mt-3">Đăng ký</button>
                        <a class="btn btn-danger mt-3" href="${pageContext.request.contextPath}/home">Hủy</a>

                    </div>
                </div>
            </form:form>
        </div>
    </div>
</main>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>