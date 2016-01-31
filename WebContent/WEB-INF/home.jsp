<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.mysql.*" %>
<%@ page import="java.sql.*" %> 

<c:import url="/WEB-INF/header-logged-in.html"/>
<head>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script>
$(document).ready(function(){
	$("#total-allowance.hide").fadeIn(1000).removeClass('hide');
});

</script>

<script>
$(document).ready(function(){
	$(".table-bordered.hide").fadeIn(2000).removeClass('hide');
});

</script>

<script type="text/javascript">
	function paidConfirmation(){
		return confirm("By clicking this, you're saying you've just been paid.");
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
			div.id = cbox.name;
			div.innerHTML = cbox.value + " expense: ";
			div.appendChild(input);
			
			document.getElementById("expense-input").appendChild(div);	
		} else {
			document.getElementById(cbox.name).remove();
	}
}
</script>

<script type="text/javascript">
function checkExpense(){
	var amount = document.getElementById("expense-input");
	var regex  = /^\d+(?:\.\d{0,2})$/;
	if (regex.test(amount)) {
		return amount;
	} else {
		alert("Input a numeric dollar amount with a decimal.");
	} 
	return false;
}
</script>

<script type="text/javascript">
	function recordIncome(){
	var amount = document.getElementById("additional-income");
	var regex  = /^\d+(?:\.\d{0,2})$/;
	if(amount != null){
		if (regex.test(amount)) {
			return confirm("By clicking this, you're saying you've just been paid.");
		} else {
			alert("Input a numeric dollar amount only.");
		}
	} 
	return false;
}
</script>
<body>
<section class="push-down">
<h3 style="text-align: center; font-family: 'Pacifico', cursive;">Total Allowance Per Pay Period</h3>
<h2 style="text-align: center;" id="total-allowance" class="hide">$<c:out value="${totalAllowance['Total Allowance']}"></c:out></h2>
<form method="post" action="just-got-paid" onsubmit="return paidConfirmation();">
<h2 style="text-align: center; margin-top: -10px;"><button class="paid-button">Just Got Paid!</button></h2>
</form>
	<table class="table-bordered hide">
	<tr>
		<th>Category</th>
		<th>Allowance</th>
		<th class="align-center">Record Expense</th>
	</tr>
	
	<c:forEach var="category" items="${categoryMap}">
	<tr>
	<c:if test="${category.value != 0.0}">
		<td><c:out value="${category.key}"/></td>
		<td id="${category.key}">$<c:out value="${category.value}"/></td>
		<td class="align-center"><input type="checkbox" name="category" value="${category.key}" onclick="recordExpense(this);"></td>
	</c:if>
	</tr>
	</c:forEach>
	</table>	
<br />
<form method="post" action="record-expense" onsubmit="return checkExpense();">
	<div id="expense-input"></div>
	<input type="submit" value="Record Expenses">
</form>
<br>
<form method="post" action="additional-income" onsubmit="return paidConfirmation()">
Additional Income: <input type="text" id= "additional-income" name="additional-income" class="form-control" placeholder="record addiontal income received here">
<h4><button>Record Additional Income</button></h4>
</form>


</section>
</body>
</html>