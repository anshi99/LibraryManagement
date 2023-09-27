<%@page import="com.nagarro.message.DisplayMessageToJSP"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

    <title>login</title>
</head>
<body>

</div>
<div class="container">
    <div class="row">
        <div class="col col-sm col-md col-lg col-xl col-xxl"></div>
        <div class="col col-sm col-md col-lg col-xl col-xxl border mt-5">
            <h6 style="margin-top: -10px;">Login</h6>
                    <%
            				DisplayMessageToJSP message = (DisplayMessageToJSP) session.getAttribute("message");
            				if (message != null) {
            			   %>
            			  <div class="alert <%=message.getCssClass()%>" role="alert"><%=message.getMessageDetails()%></div>
            			   <%
            				session.removeAttribute("message");
            				}
            		%>
            <form action=" " method="post" id="formLogin">
            <table>
                <!-- userid input -->
                <div class="form-outline mb-4">


                 <tr>

                 <td> <label class="form-label" for="form2Example1">Username: </label> </td>
                  <td> <span style="color: #f41f1f;">*</span> </td>
                  <td> <input type="text" name="username" id="usernames" class="form-control" /> </td>
                  <p id="usercheck" style="color: red;font-size:15px;">**This field cannot be empty</p>
                  <span> </span>

                  </tr>
                </div>
                <!-- Password input -->
                <div class="form-outline mb-4">
                <tr>
                 <td> <label class="form-label" for="form2Example2">Password: </label> </td>
                 <td>   <span style="color: #f41f1f;">*</span> </td>
                 <td> <input type="password" name="password" id="password" class="form-control" /> </td>
                  <p id="passcheck" style="color: red;font-size:15px;">**This field cannot be empty</p>

                  </tr>
                </div>

                </table>
                <button type="submit" id="submitbtn" class="btn btn-primary btn-block mb-4">Submit</button>
            </form>

        </div>
        <div class="col col-sm col-md col-lg col-xl col-xxl"></div>

    </div>
</div>


</body>
<script>
    // Document is ready
    $(document).ready(function () {
        // Validate Username
        $("#usercheck").hide();
        let usernameError = true;
        $("#usernames").keyup(function () {
            validateUsername();
        });

        function validateUsername() {
            let usernameValue = $("#usernames").val();
            if (usernameValue.length == "") {
            $("#usercheck").show();
            usernameError = false;
            return false;
            } else if (usernameValue.length < 5 || usernameValue.length > 50) {
            $("#usercheck").show();
            $("#usercheck").html("**length of username must be between 5 and 50");
            usernameError = false;
            return false;
            } else {
            $("#usercheck").hide();
            }
        }

        // Validate Email


        // Validate Password
        $("#passcheck").hide();
        let passwordError = true;
        $("#password").keyup(function () {
            validatePassword();
        });
        function validatePassword() {
            let passwordValue = $("#password").val();
            if (passwordValue.length == "") {
            $("#passcheck").show();
            passwordError = false;
            return false;
            }
            if (passwordValue.length < 5 || passwordValue.length > 50) {
            $("#passcheck").show();
            $("#passcheck").html(
                "**length of your password must be between 5 and 50"
            );
            $("#passcheck").css("color", "red");
            passwordError = false;
            return false;
            } else {
            $("#passcheck").hide();
            }
        }




        // Submit button
        $("#submitbtn").click(function () {
            validateUsername();
            validatePassword();
            validateConfirmPassword();
            validateEmail();
            if (
            usernameError == true &&
            passwordError == true &&
            confirmPasswordError == true &&
            emailError == true
            ) {
            return true;
            } else {
            return false;
            }
        });
        });

    </script>

</html>
