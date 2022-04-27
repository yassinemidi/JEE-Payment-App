<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/includes/header.jsp" %>
<%@ include file="/WEB-INF/includes/navbar.jsp" %>

<div class="container col-md-8 mt-5">




<div class="alert alert-success" role="alert">
  
  <c:out value="${sessionScope.r.payment_succeed }"></c:out>
  
  

</div>






    <div class="card">
        <div class="card-header">
            <h2><c:out value="${sessionScope.r.title_invoice_user }"></c:out></h2>
            
        </div>

        <div class="card-body">
        	<h4><c:out value="${sessionScope.r.thanks}"></c:out></h4>
        	<h3><c:out value="${sessionScope.r.payment_is_validated}"></c:out></h3>
            <a class="btn btn-primary" href="getrecu"><c:out value="${sessionScope.r.recu_download}"></c:out></a>
  			<a class="btn btn-danger" href="logout"><c:out value="${sessionScope.r.logout}"></c:out></a>
			
        </div>

        <div class="card-footer">
            <small>ENSAS  Yassine MIDI</small>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/includes/footer.jsp" %>