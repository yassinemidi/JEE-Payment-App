<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/includes/header.jsp" %>
<%@ include file="/WEB-INF/includes/navbar.jsp" %>

<div class="container mt-5">
	<div class="card">
		<div class="card-header">
		<c:out value="${sessionScope.r.title_card_agent }"></c:out>
		</div>
		
		<div class="card-body">
		<h3><c:out value="${sessionScope.r.list_abns }"></c:out></h3> 
		<table class="table">
		  <thead class="thead-light">
		    <tr>
		      <th scope="col"><c:out value="${sessionScope.r.user_id }"></c:out></th>
		      <th scope="col"><c:out value="${sessionScope.r.name_all }"></c:out></th>
		      <th scope="col"><c:out value="${sessionScope.r.email_address }"></c:out></th>
		      
		      <th scope="col"><c:out value="${sessionScope.r.abn_name }"></c:out></th>
		      <th scope="col"><c:out value="${sessionScope.r.price }"></c:out></th>
		       <th scope="col"><c:out value="${sessionScope.r.is_paid }?"></c:out></th>
		         <th scope="col">Actiont</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach var="item" items="${sessionScope.list_abonnees }">
		  
		  <tr>
		      <th scope="row">${item.user_id}</th>
		      <td>${item.user_prenom} ${item.user_nom}</td>
		      <td>${item.user_email}</td>
		      <td>${item.abonnement_nom}</td>
		      <td>${item.abonnement_prix} DH/mois</td>
		      <td>
		      <c:if test="${item.ispayee==1}">
		      <c:out value="Yes"></c:out>
		      </c:if>
		      <c:if test="${item.ispayee==0}">
		      <c:out value="No"></c:out>
		      </c:if>
		      
		      </td>
		      <td>
		      <form action="agent" method="post">
		      <input hidden="true" type="text" name="id_facture" value="${item.facture_id}">
		      
		      <button class="btn btn-primary" type="submit"><c:out value="${sessionScope.r.pay }"></c:out></button>
		      </form>
		      
		      </td>
		    </tr>
		  </c:forEach>
		    
		   
		  </tbody>
		</table>
		</div>
		
		<div class="card-footer">
		<small>ENSAS YassineMIDI</small>
		</div>
	</div>
</div>


	<%@ include file="/WEB-INF/includes/footer.jsp" %>