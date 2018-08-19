<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>System</title>

<!-- CSS Bootstrap -->
<link rel="stylesheet" href="/system/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/system/resources/css/main.css">
<!-- jQuery, Bootstrap JS -->
<script src="/system/resources/js/jquery-3.2.1.min.js"></script>
<script src="/system/resources/js/popper.min.js"></script>
<script src="/system/resources/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container text-center">
		<h1 class="display-3 m-5">System</h1>
	    <p class="lead">
	      Login
	    </p>
		<div class="row">
			<div class="col-sm-6 col-md-12 col-md-offset-4">
				<div class="account-wall">
					<form id="form-signin" class="form-signin" method="post">
						<input type="text" id="login" name="login" class="form-control" placeholder="Email" required autofocus /> 
						<input type="password" id="password" name="password" class="form-control" placeholder="Password" required />
						<button id="btn-signin" class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
						<label class="checkbox pull-left"> <input type="checkbox" value="remember-me"> Remember me
						</label> <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
					</form>
				</div>
				<div id="signin-message-success" class="alert alert-success" style="display: none;"></div>
				<div id="signin-message-danger" class="alert alert-danger" style="display: none;"></div>
				<a href="#" class="text-center new-account">Create an account </a>
			</div>
		</div>
	</div>
	
	<script type='text/javascript'>
		$(document).ready(function () {
	
		    $("#form-signin").submit(function (event) {
	
		        //stop submit the form, we will post it manually.
		        event.preventDefault();
	
		        ajax_login_submit();
		    });
	
		});
	</script>

	<script type='text/javascript'>
		function ajax_login_submit() {

			$("#btn-signin").prop("disabled", true);

			var url = "/system/login/doLogin";

			$.ajax({
				type : "POST",
				url : url,
				//contentType: "application/json",
				data : {
					login : $("#login").val(),
					password : $("#password").val()
				},
				//dataType : 'json',
				cache : false,
				timeout : 600000,
				error : function(e) {
					var json = JSON.stringify(e);

					if (e.responseJSON != null) {
						//alert(JSON.stringify(e.responseJSON));
						for (i = 0; i < e.responseJSON.length; i++) {
							//alert(e.responseJSON[i]);
							if (e.responseJSON[i] != null && e.responseJSON[i].message != null) {
								$('#signin-message-danger').html(e.responseJSON[i].message);
								$('#signin-message-danger').show();
							}
						}
					}
					
					console.log("error: "+json)
					
					$("#btn-signin").prop("disabled", false);
				},
				success : function(data) {
					var url = JSON.stringify(data);
					console.log("success: "+url);
					$('#signin-message-success').html("Success");
					$('#signin-message-success').show();
					$(location).attr("href", url);
				}
			});
		}
	</script>
</body>
</html>