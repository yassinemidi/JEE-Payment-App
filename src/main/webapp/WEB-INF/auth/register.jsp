<%@page import="java.util.ArrayList"%>
<%@page import="com.myapp.models.abonnement"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%@ include file="/WEB-INF/includes/header.jsp" %>
	
	
<section class="h-100 my-login-page" style="margin-top: 6em;">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						
						
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title"><c:out value="${sessionScope.r.register }"></c:out></h4>
							<form method="POST" class="my-login-validation" novalidate="">
								<div class="form-group">
									<label for="first_name"><c:out value="${sessionScope.r.first_name }"></c:out></label>
									<input id="first_name" type="text" class="form-control" name="first_name" required autofocus>
									<div class="invalid-feedback">
										What's your First Name?
									</div>
								</div>
								
								<div class="form-group">
									<label for="last_name"><c:out value="${sessionScope.r.last_name }"></c:out></label>
									<input id="last_name" type="text" class="form-control" name="last_name" required autofocus>
									<div class="invalid-feedback">
										What's your Last Name?
									</div>
								</div>
								
								

								<div class="form-group">
									<label for="email"><c:out value="${sessionScope.r.email_address }"></c:out></label>
									<input id="email" type="email" class="form-control" name="email" required>
									<div class="invalid-feedback">
										Your email is invalid
									</div>
								</div>

								<div class="form-group">
									<label for="password"><c:out value="${sessionScope.r.password }"></c:out></label>
									<input id="password" type="password" class="form-control" name="password" required data-eye>
									<div class="invalid-feedback">
										Password is required
									</div>
								</div>
								
								<div class="input-group mb-3">
								  <div class="input-group-prepend">
								    <label class="input-group-text" for="abonnement"><c:out value="${sessionScope.r.select_abn }"></c:out></label>
								  </div>
								  <select class="custom-select" id="abonnement" name="abonnement">
								    <option selected><c:out value="${sessionScope.r.choose }"></c:out>...</option>	
								    <c:forEach var="ab" items="${list_abn }">
								    	<option value="${ab.id }">${ab.nom } ${ab.price } DH/mouth</option>
								    
								    </c:forEach>			     
								 	 
								  </select>
								</div>

								

								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block">
										<c:out value="${sessionScope.r.register }"></c:out>
									</button>
								</div>
								<div class="mt-4 text-center">
									<c:out value="${sessionScope.r.already_have_account }"></c:out>? <a href="login"><c:out value="${sessionScope.r.login }"></c:out></a>
								</div>
							</form>
						</div>
					</div>
					<div class="footer" style="width: max-content;margin: auto;">
						Copyright &copy; 2022 &mdash; MIDI Yassine 
					</div>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
	'use strict';

	$(function() {


	    

	    $("input[type='password'][data-eye]").each(function(i) {
	        var $this = $(this),
	            id = 'eye-password-' + i,
	            el = $('#' + id);

	        $this.wrap($("<div/>", {
	            style: 'position:relative',
	            id: id
	        }));

	        $this.css({
	            paddingRight: 60
	        });
	        $this.after($("<div/>", {
	            html: 'Show',
	            class: 'btn btn-primary btn-sm',
	            id: 'passeye-toggle-' + i,
	        }).css({
	            position: 'absolute',
	            right: 10,
	            top: ($this.outerHeight() / 2) - 12,
	            padding: '2px 7px',
	            fontSize: 12,
	            cursor: 'pointer',
	        }));

	        $this.after($("<input/>", {
	            type: 'hidden',
	            id: 'passeye-' + i
	        }));

	        var invalid_feedback = $this.parent().parent().find('.invalid-feedback');

	        if (invalid_feedback.length) {
	            $this.after(invalid_feedback.clone());
	        }

	        $this.on("keyup paste", function() {
	            $("#passeye-" + i).val($(this).val());
	        });
	        $("#passeye-toggle-" + i).on("click", function() {
	            if ($this.hasClass("show")) {
	                $this.attr('type', 'password');
	                $this.removeClass("show");
	                $(this).removeClass("btn-outline-primary");
	            } else {
	                $this.attr('type', 'text');
	                $this.val($("#passeye-" + i).val());
	                $this.addClass("show");
	                $(this).addClass("btn-outline-primary");
	            }
	        });
	    });

	    $(".my-login-validation").submit(function() {
	        var form = $(this);
	        if (form[0].checkValidity() === false) {
	            event.preventDefault();
	            event.stopPropagation();
	        }
	        form.addClass('was-validated');
	    });
	});
</script>
	
	<%@ include file="/WEB-INF/includes/footer.jsp" %>
