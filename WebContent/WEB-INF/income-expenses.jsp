<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="eng">
<head>
<c:import url="/WEB-INF/header-data-check.html"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<!--<script type="text/javascript">
function dynInput(cbox){
	if(cbox.checked){
		var input = document.createElement("input");
		input.type = "text";
		input.name = "categoryValue";
		var div = document.createElement("div");
		div.id = cbox.value;
		div.innerHTML = cbox.value + " ";
		div.appendChild(input);
		document.getElementById("point-input").appendChild(div);
	} else {
		document.getElementById(cbox.value).remove();
	}
}
</script>-->
<script type="text/javascript">
function dynInput(cbox){
	if(cbox.checked){
		var input = document.createElement("input");
		input.type = "number";
		<!--input.name = "categoryValue";-->
		input.name = cbox.value;
		input.value = "0";
		input.min = "0";
		input.max = "100";
		input.id = "placeholder-amount";
		input.onkeypress = "return false";
		$(input).keypress(function(event){
			return false;
		});
		var lastValue = 0;
		input.onchange = function(){
			var points = parseInt($('#total-points').text().trim(), 10);
			if(input.value > lastValue){
				points --;
				document.getElementById('total-points').innerHTML = " " + points;
				lastValue ++;
				while(input.value > lastValue){
					input.value --;
				}
			} 
			else if (input.value < lastValue){
				points++;
				document.getElementById('total-points').innerHTML = " " + points;
				lastValue--;
				while(input.value < lastValue){
					input.value++;
				}
				
			}
			if(points < 0){
				alert("You cannot allot more than 100 points total to your categories!");
				points++;
				document.getElementById('total-points').innerHTML = " " + points;
				lastValue--;
				while(input.value > lastValue){
					input.value--;
				}
			}
		}
		var div = document.createElement("div");
		<!--div.id = cbox.value;-->
		div.id = cbox.name;
		div.innerHTML = cbox.value + " ";
		div.appendChild(input);
		
		document.getElementById("point-input").appendChild(div);
	} else {
		document.getElementById(cbox.name).remove();
	}
}
</script>
<script type="text/javascript">
function checkForm(form){
	if(form.netIncome.value==""){
		alert("Please provide how much you're paid per pay period");
		form.netIncome.focus();
		return false;
	} else if(form.payPeriod.value==""){
		alert("Please provide how often you're paid");
		return false;
	} else if(form.knownExpenses.value == ""){
		alert("Please provide a combined total of all known monthly expenses");
		form.knownExpenses.focus();
		return false;
	}
	return true;
}
</script>
<script type="text/javascript">
function isNumberKey(evt) {
   var charCode = (evt.which) ? evt.which : event.keyCode;
   if (charCode > 31 && (charCode < 48 || charCode > 57)){
	   return false;
   }
   return true;
}
</script>
<!-- <script type="text/javascript">
function dynInput(cbox){
	if(cbox.checked){	
		var amount = document.createElement("p");
		amount.type = "text";
		amount.name = "categoryValue";
		amount.id = "placeholder-amount";
		amount.value = "0";
		amount.innerHTML = " " + amount.value;
		
		var plusBtn = document.createElement("input");
		plusBtn.type = "button";
		plusBtn.value = "+";
		plusBtn.id = "plus-button";
		plusBtn.onclick = function incrementValue(){
			var value = parseInt(document.getElementById('placeholder-amount').value, 10);
			value = isNaN(value) ? 0 : value;
			value++;
			document.getElementById('placeholder-amount').value = value;
			document.getElementById('placeholder-amount').innerHTML = " " + value;
			
			if(value > 100){
				window.alert("You cannot allot more than 100 points to a category.");
				value--;
				document.getElementById('placeholder-amount').value = value;
				document.getElementById('placeholder-amount').innerHTML = " " + value;
			}
		}

		var minusBtn = document.createElement("input");
		minusBtn.type = "button";
		minusBtn.value = "-";
		minusBtn.id = "minus-button";
		minusBtn.onclick = function decrementValue(){
			var value = parseInt(document.getElementById('placeholder-amount').value, 10);
			value = isNaN(value) ? 0 : value;
			value--;
			document.getElementById('placeholder-amount').value = value;
			document.getElementById('placeholder-amount').innerHTML = " " + value;
			
			if(value < 0){
				window.alert("You cannot allot less than 0 points to a category.");
				value++;
				document.getElementById('placeholder-amount').value = value;
				document.getElementById('placeholder-amount').innerHTML = " " + value;
			}
		}
		
		var div = document.createElement("div");
		<!--div.id = cbox.value;-->
		<!--div.innerHTML = cbox.value + " ";-->
		<!--div.id = "point-system";-->
		<!--$(div).append(minusBtn, amount, plusBtn);
		
		document.getElementById("point-input").appendChild(div);
	} else {
		document.getElementById("point-system").remove();
	}
}-->
<title>Log Income and Known Expenses</title>
</head>
<section class="push-down">
<h2>Provide income and pay-period</h2>
	<form action="process-income-expenses" method="post" role="form" onsubmit="return checkForm(this);" id="income-expenses-form">
		Net Income: 
		<input type="text" placeholder="per pay period" name="netIncome" id="netIncome" onkeypress="return isNumberKey(event)" class="form-control" required/>
		<br />
		Pay Period:<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="weekly"/>Weekly<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="bi-weekly"/>Bi-Weekly<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="semi-monthly"/>Semi-Monthly (Twice a month)<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="monthly"/>Monthly
		<br />
		<br />
		Known Monthly Expenses Combined:
		<input type="text" placeholder="e.g. mortgage, rent, loans, debt..." name="knownMonthlyExpenses" id="knownExpenses" onkeypress="return isNumberKey(event)" class="form-control" required/>
		<br />
		Choose All Categories You Would Like To Include In Your Budget:
		<br />
		<table>
		<tr>
		<td><input type="checkbox" name="category" value="autoInsurance" onclick="dynInput(this);">Auto Insurance</td>
		<td><input type="checkbox" name="category" value="autoMaintenance" onclick="dynInput(this);">Auto Maintenance</td>
		<td><input type="checkbox" name="category" value="babysitter" onclick="dynInput(this);">Babysitter</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="books" onclick="dynInput(this);">Books</td>
		<td><input type="checkbox" name="category" value="cable" onclick="dynInput(this);">Cable</td>
		<td><input type="checkbox" name="category" value="cleaning" onclick="dynInput(this);">Cleaning</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="clothes" onclick="dynInput(this);">Clothes</td>
		<td><input type="checkbox" name="category" value="children" onclick="dynInput(this);">Children</td>
		<td><input type="checkbox" name="category" value="donations" onclick="dynInput(this);">Donations</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="electricity" onclick="dynInput(this);">Electricity</td>
		<td><input type="checkbox" name="category" value="entertainment" onclick="dynInput(this);">Entertainment</td>
		<td><input type="checkbox" name="category" value="eatingOut" onclick="dynInput(this);">Eating Out</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="fuel" onclick="dynInput(this);">Fuel</td>
		<td><input type="checkbox" name="category" value="gas" onclick="dynInput(this);">Gas Bill</td>
		<td><input type="checkbox" name="category" value="groceries" onclick="dynInput(this);">Groceries</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="gifts" onclick="dynInput(this);">Gifts</td>
		<td><input type="checkbox" name="category" value="grooming" onclick="dynInput(this);">Grooming</td>
		<td><input type="checkbox" name="category" value="homeRepair" onclick="dynInput(this);">Home Repair</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="internet" onclick="dynInput(this);">Internet</td>
		<td><input type="checkbox" name="category" value="medical" onclick="dynInput(this);">Medical</td>
		<td><input type="checkbox" name="category" value="phone" onclick="dynInput(this);">Phone</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="retirement" onclick="dynInput(this);">Retirement</td>
		<td><input type="checkbox" name="category" value="savings" onclick="dynInput(this);">Savings</td>
		<td><input type="checkbox" name="category" value="spending" onclick="dynInput(this);">Spending</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="vacation" onclick="dynInput(this);">Vacation</td>
		<td><input type="checkbox" name="category" value="misc" onclick="dynInput(this);">Miscellaneous</td>
		</tr>
		</table>
		<br />
		Allot points (percent amount) to each category for budgeting
		<p>Total points should not go above 100</p>
		<h2 id='total-points'>100</h2>
		<div id="point-input"></div>
		<br />
		&nbsp;
		<input type="submit" name="submit" value="Submit" id="submit"/>
	</form>
</section>
</body>
</html>