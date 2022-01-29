<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/includes/header.jsp" %>
    <h3 class="h3 mx-auto mt-5" style="width: max-content;"><c:out value="${sessionScope.r.title_invoice_user }"></c:out></h3>
    <div class="container mt-5">

        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <c:out value="${sessionScope.r.title_card_user }"></c:out>
                    </div>
                    <div class="card-body">
                        <p><b><c:out value="${sessionScope.r.last_name }"></c:out> :</b> <c:out value="${sessionScope.user.nom}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.first_name }"></c:out> :</b> <c:out value="${sessionScope.user.prenom}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.user_id }"></c:out> :</b> <c:out value="${sessionScope.user.id}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.email_address }"></c:out> :</b> <c:out value="${sessionScope.user.email}"></c:out></p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <c:out value="${sessionScope.r.title_card_subscription }"></c:out>
                    </div>
                    <div class="card-body">
                        <p><b><c:out value="${sessionScope.r.abn_name }"></c:out> :</b> <c:out value="${sessionScope.abonnement.nom}"></c:out></p>
                        <p><b>ID Abonnement :</b> <c:out value="${sessionScope.abonnement.id}"></c:out></p>
                        <p><b><c:out value="${sessionScope.r.price }"></c:out> :</b> <c:out value="${sessionScope.abonnement.price}"></c:out> DH/Mois</p>
                        <p><b><c:out value="${sessionScope.r.begin_date }"></c:out> :</b> <c:out value="${sessionScope.facture.date_debut}"></c:out></p>
                        
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
                        <p><b><c:out value="${sessionScope.r.amount }"></c:out> :</b> <c:out value="${sessionScope.abonnement.price}"></c:out> DH</p>
                        <p><b><b><c:out value="${sessionScope.r.card_number }"></c:out> :</b> :</b> <c:out value="${sessionScope.num_carte}"></c:out></p>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        window.print();
    </script>
	<%@ include file="/WEB-INF/includes/footer.jsp" %>
