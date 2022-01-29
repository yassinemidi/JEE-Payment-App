<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/includes/header.jsp" %>
    <h3 class="h3 mx-auto mt-5" style="width: max-content;"><c:out value="${sessionScope.r.paid_with_agent }"></c:out></h3>
    <div class="container mt-5">

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <c:out value="${sessionScope.r.paid_with_agent }"></c:out>
                    </div>
                    <div class="card-body">
                        <p><b><c:out value="${sessionScope.r.last_name }"></c:out> :</b> <c:out value="${tmp_user.nom}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.first_name }"></c:out> :</b> <c:out value="${tmp_user.prenom}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.number_customer }"></c:out> :</b> <c:out value="${tmp_user.id}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.email_address }"></c:out> :</b> <c:out value="${tmp_user.email}"></c:out></p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <c:out value="${sessionScope.r.title_card_subscription }"></c:out>
                    </div>
                    <div class="card-body">
                        <p><b><c:out value="${sessionScope.r.abn_name }"></c:out> :</b> <c:out value="${tmp_ab.nom}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.abn_id }"></c:out> :</b> <c:out value="${tmp_ab.id}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.amount }"></c:out> :</b> <c:out value="${tmp_ab.price}"></c:out> DH/mois</p>
                        <p><b><c:out value="${sessionScope.r.begin_date }"></c:out> :</b> <c:out value="${tmp_fac.date_debut}"></c:out></p>
                        
                    </div>
                </div>
            </div>

        </div>
        <div class="row mt-5">
            <div class="col-md-10 mx-auto">
                <div class="card">
                    <div class="card-header">
                        <c:out value="${sessionScope.r.title_card_payment }"></c:out>
                    </div>
                    <div class="card-body">
                        <p><b><c:out value="${sessionScope.r.payement_date }"></c:out> :</b> <c:out value="${today }"></c:out> </p>
                        <p><b><c:out value="${sessionScope.r.amount }"></c:out> :</b> <c:out value="${tmp_ab.price}"></c:out> DH</p>
                        <h4><c:out value="${sessionScope.r.paid_with_agent }"></c:out> : <c:out value="${user.nom }"></c:out></h4>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        window.print();
    </script>
	<%@ include file="/WEB-INF/includes/footer.jsp" %>
