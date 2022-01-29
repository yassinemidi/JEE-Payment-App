<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ include file="/WEB-INF/includes/header.jsp" %>


        
	<section class="h-100 my-login-page" style="margin-top: 6em;" >
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						
						
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title"><c:out value="${sessionScope.r.login }"></c:out></h4>
							<form method="POST" class="my-login-validation" novalidate action="login">
								<div class="form-group">
									<label for="email"><c:out value="${sessionScope.r.email_address }"></c:out></label>
									<input id="email" type="email" class="form-control" name="email" value="" required autofocus>
									<div class="invalid-feedback">
										Email is invalid
									</div>
								</div>

								<div class="form-group">
									<label for="password"><c:out value="${sessionScope.r.password }"></c:out>
										
									</label>
									<input id="password" type="password" class="form-control" name="password" required data-eye>
								    <div class="invalid-feedback">
								    	Password is required
							    	</div>
								</div>

								

								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block">
										<c:out value="${sessionScope.r.login }"></c:out>
									</button>
								</div>
								<div class="mt-4 text-center">
									<c:out value="${sessionScope.r.dont_have_account }"></c:out>? <a href="register"><c:out value="${sessionScope.r.create_one }"></c:out></a>
									
								</div>
								<div class="mt-4 text-center">
								<c:out value="${sessionScope.r.select_langue }"></c:out> :
									<a href="changeLangue?langue=fr"><c:out value="${sessionScope.r.french }"></c:out></a>, 
									<a href="changeLangue?langue=en"><c:out value="${sessionScope.r.english }"></c:out></a>,
									<a href="changeLangue?langue=ar"><c:out value="${sessionScope.r.arabic }"></c:out></a>
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
