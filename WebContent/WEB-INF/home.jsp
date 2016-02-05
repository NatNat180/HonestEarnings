<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mysql.*" %>
<%@ page import="java.sql.*" %> 

<c:import url="/WEB-INF/header-logged-in.html"/>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script>
$(document).ready(function(){
	$("#total-allowance.hide").fadeIn(500).removeClass('hide');
});

</script>

<script>
$(document).ready(function(){
	$(".table-bordered.hide").fadeIn(1000).removeClass('hide');
});

</script>

<script type="text/javascript">
	function paidConfirmation(){
		return confirm("By clicking this, you're saying you've just been paid.");
	}
</script>

<script type="text/javascript">
	function expenseConfirmation(){
		return confirm("By clicking this, you'll apply changes to your allowance amount.");
	}
</script>

<script type="text/javascript">
	function resetBudget(){
		return confirm("By clicking this, you'll discard your current budget plan to start up a new one. Is this okay?");
	}
</script>
  
<script type="text/javascript">
	function recordExpense(cbox){
		if(cbox.checked){
			var input = document.createElement("input");
			input.type = "text";
			input.value = "0.0";
			input.name = cbox.value;
			input.id = "expense-amount";
			
			var div = document.createElement("div");
			<!--div.id = cbox.name;-->
			div.id = cbox.value;
			div.innerHTML = cbox.value + " expense: ";
			div.appendChild(input);
			
			document.getElementById("expense-input").appendChild(div);	
		} else {
			document.getElementById(cbox.value).remove();
	}
}
</script>

<script type="text/javascript">
function isNumberKey(evt) {
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 46 || charCode > 57)){
	   return false;
   }
   return true;
}
</script>
<body>

<section class="container">

<h3 style="text-align: center; font-family: 'Pacifico', cursive; font-size: 32px;">Total Allowance</h3>
<h2 style="text-align: center;" id="total-allowance" class="hide"><span class="label label-success">$<c:out value="${totalAllowance['Total Allowance']}"></c:out></span></h2>

<br>
<form method="post" action="just-got-paid" onsubmit="return paidConfirmation();">
<h2 style="text-align: center; margin-top: -10px;"><button class="paid-button"><span class="glyphicon glyphicon-thumbs-up"></span>&nbsp;Just Got Paid!</button></h2>
</form>
<div id="dialog-confirm" title="You've just been paid?"></div>

<h4><a href="register-user" title="adjust your budget" style="float: right; margin-top: -25px;" onclick="return resetBudget();"><span class="glyphicon glyphicon-tasks"></span></a></h4>
	<table class="table-bordered hide" style="width: 100%;">
	<tr>
		<th>Category</th>
		<th class="align-center">Allowance</th>
		<th class="align-center">Record Expense&nbsp;<span class="glyphicon glyphicon-pencil"></span></th>
	</tr>
	<!-- This is what you had originally -->
	<c:forEach var="category" items="${categoryMap}">
	<tr>
	<!-- test originally category.value -->
		<c:if test="${category.value != 0.0}">
		<td><c:out value="${category.key}"/></td>
		<!-- If value is negative, display as red -->
		<c:choose>
		<c:when test="${category.value < 0.0}">
		<td style='color: #980009;' class="align-center">$<c:out value="${category.value}"/></td>
		</c:when>
		<c:otherwise> <!-- otherwise, display as normal -->
		<td class="align-center">$<c:out value="${category.value}"/></td>
		</c:otherwise>
		</c:choose>
		<td class="align-center"><input type="checkbox" name="category" value="${category.key}" onclick="recordExpense(this);"></td>
		</c:if> 
	</tr>
	</c:forEach><!-- end of original code -->
	</table>	
<hr class="faded-line">
<form method="post" action="record-expense">
	
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h4 class="panel-title"><span class="glyphicon glyphicon-minus"></span>&nbsp;Expenses</h4>
	</div>
  		<div class="panel-body">
  			<div id="expense-input" onkeypress="return isNumberKey(event)"></div>
  			<hr class="dotted-line">
  			<input type="submit" value="Record Expenses" onclick="return expenseConfirmation();">
  		</div>
	</div>
	
</form>
<br>
<form method="post" action="additional-income" onsubmit="return paidConfirmation()">

	<div class="panel panel-success-darker">
	<div class="panel-heading">
		<h4 class="panel-title"><span class="glyphicon glyphicon-plus"></span>&nbsp;Additional Income</h4>
	</div>
		<div class="panel-body">
			<input type="text" id= "additional-income" name="additional-income" class="form-control" 
			placeholder="record additional income received here" onkeypress="return isNumberKey(event)">
			<h5><button>Record Additional Income</button></h5>
		</div>
	</div>
</form>



</section>
</body>
</html>