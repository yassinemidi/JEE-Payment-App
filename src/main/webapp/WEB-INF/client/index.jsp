<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/includes/header.jsp" %>
<%@ include file="/WEB-INF/includes/navbar.jsp" %>

<div class="container col-md-8 mt-5">



<c:if test="${param.msg == 'failed' }">
    <div class="alert alert-danger" role="alert">
  Vérifier vos informations et ressayer !

</div>
</c:if>






    <div class="card">
        <div class="card-header">
            <h2> <c:out value="${sessionScope.r.title_invoice_user }"></c:out></h2>
            
        </div>

        <div class="card-body">
            <p><b>MR/Mme :</b>
                <c:out value="${sessionScope.user.nom} ${sessionScope.user.prenom}"></c:out>
            </p>
            <p><b><c:out value="${sessionScope.r.number_customer }"></c:out> :</b>
                <c:out value="${sessionScope.user.id}"></c:out>
            </p>
            
            <c:if test="${not should_pay}">
            <p><b><c:out value="${sessionScope.r.amount }"></c:out> :</b>
            
             <c:out value="${sessionScope.abonnement.price} DH  ${ sessionScope.r.already_paid}"></c:out></p>
            <p><b><c:out value="${sessionScope.r.next_paiement }"></c:out> :</b>
             <c:out value="${next_date}"></c:out> </p>
            </c:if>
            
            <c:if test="${should_pay}">
            <p><b><c:out value="${sessionScope.r.amount }"></c:out> :</b>
             <c:out value="${sessionScope.abonnement.price}"></c:out> DH</p>
            </c:if>
                

            <br>
            
            <c:if test="${should_pay}">

            <form method="post" action="client">
                <div class="form-group">
                    <label for="num_carte"><c:out value="${sessionScope.r.card_number }"></c:out></label>
                    <input required name="num_carte" type="number"  class="form-control" id="num_carte" aria-describedby="num_carteHelp">
                    <small id="num_carteHelp" class="form-text text-muted">Contient 16 chiffres</small>
                </div>
                <div class="form-group">
                    <label for="date_end"><c:out value="${sessionScope.r.card_end_date }"></c:out></label>
                    <input required type="date" class="form-control" id="date_end" name="date_end">
                </div>
                <div class="form-group">
                    <label for="last_num_carte"><c:out value="${sessionScope.r.card_last_numbers }"></c:out></label>
                    <input required name="last_num_carte" type="number" class="form-control" id="last_num_carte" aria-describedby="last_num_carteHelp">
                </div>
                <button type="submit" class="btn btn-primary"><c:out value="${sessionScope.r.validate }"></c:out></button>
            </form>
			</c:if>

        </div>

        <div class="card-footer">
            <small>ENSAS  Yassine MIDI</small>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/includes/footer.jsp" %>