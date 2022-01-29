<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
  <a class="navbar-brand" href="#">PaymentApp</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-expanded="false">
          <c:out value="${sessionScope.r.select_langue }"></c:out>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="changeLangue?langue=fr"><c:out value="${sessionScope.r.french }"></c:out></a>
          <a class="dropdown-item" href="changeLangue?langue=en"><c:out value="${sessionScope.r.english }"></c:out></a>
          <a class="dropdown-item" href="changeLangue?langue=ar"><c:out value="${sessionScope.r.arabic }"></c:out></a>
        </div>
      </li>
      
    </ul>

    <ul class="navbar-nav ml-auto">

    <li class="nav-item">
        <a class="nav-link active" href="#"><c:out value="${sessionScope.user.nom} ${sessionScope.user.prenom}"></c:out></a>
    </li>

    <li class="nav-item">
        <a class="nav-link" href="logout"><c:out value="${sessionScope.r.logout }"></c:out></a>
    </li>
      
    </ul>
  </div>
</nav>    
    
    
    
    
    
