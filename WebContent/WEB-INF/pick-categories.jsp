<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css"/>
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
<!-- FIGURE OUT HOW TO PREVENT THE USER FROM HOLDING THE SCROLL BUTTON DOWN AND INPUTTING CUSTOM VALUE IN TEXT BOX -->
<script type="text/javascript">
function dynInput(cbox){
	if(cbox.checked){
		var input = document.createElement("input");
		input.type = "number";
		input.name = "categoryValue";
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
		div.id = cbox.value;
		div.innerHTML = cbox.value + " ";
		div.appendChild(input);
		
		document.getElementById("point-input").appendChild(div);
	} else {
		document.getElementById(cbox.value).remove();
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
<body>
	<nav>
		<ul>
			<li><a href="index.jsp">Home</a>
			<li><a href="about.jsp">About</a>
		</ul>
	</nav>
<h1>Honest Earnings</h1>
<section>
<h2>Please select the categories you'd like to include in your budget!</h2>
	<form action="ProcessIncomeExpenses" method="post" role="form" onsubmit="return checkForm(this);">
		Net Income: 
		<input type="text" placeholder="per pay period" name="netIncome" id="netIncome" onkeypress="return isNumberKey(event)" />
		<br />
		Pay Period:<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="weekly"/>Weekly<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="bi-weekly"/>Bi-Weekly<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="semi-monthly"/>Semi-Monthly (Twice a month)<br>
		<input type="radio" name="payPeriod" id="payPeriod" value="monthly"/>Monthly
		<br />
		Known Monthly Expenses Combined:
		<input type="text" placeholder="e.g. mortgage, loans..." name="knownMonthlyExpenses" id="knownExpenses" onkeypress="return isNumberKey(event)"/>
		<br />
		Choose All Categories You Would Like To Include In Your Budget:
		<br />
		<table>
		<tr>
		<td><input type="checkbox" name="category" value="Auto Insurance" onclick="dynInput(this);">Auto Insurance</td>
		<td><input type="checkbox" name="category" value="Auto Maintenance" onclick="dynInput(this);">Auto Maintenance</td>
		<td><input type="checkbox" name="category" value="Babysitter" onclick="dynInput(this);">Babysitter</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Books" onclick="dynInput(this);">Books</td>
		<td><input type="checkbox" name="category" value="Cable" onclick="dynInput(this);">Cable</td>
		<td><input type="checkbox" name="category" value="Cleaning" onclick="dynInput(this);">Cleaning</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Clothes" onclick="dynInput(this);">Clothes</td>
		<td><input type="checkbox" name="category" value="Children" onclick="dynInput(this);">Children</td>
		<td><input type="checkbox" name="category" value="Donations" onclick="dynInput(this);">Donations</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Electricity" onclick="dynInput(this);">Electricity</td>
		<td><input type="checkbox" name="category" value="Entertainment" onclick="dynInput(this);">Entertainment</td>
		<td><input type="checkbox" name="category" value="Eating Out" onclick="dynInput(this);">Eating Out</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Fuel" onclick="dynInput(this);">Fuel</td>
		<td><input type="checkbox" name="category" value="Gas" onclick="dynInput(this);">Gas Bill</td>
		<td><input type="checkbox" name="category" value="Groceries" onclick="dynInput(this);">Groceries</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Gifts" onclick="dynInput(this);">Gifts</td>
		<td><input type="checkbox" name="category" value="Grooming" onclick="dynInput(this);">Grooming</td>
		<td><input type="checkbox" name="category" value="Home Repair" onclick="dynInput(this);">Home Repair</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Internet" onclick="dynInput(this);">Internet</td>
		<td><input type="checkbox" name="category" value="Medical" onclick="dynInput(this);">Medical</td>
		<td><input type="checkbox" name="category" value="Phone" onclick="dynInput(this);">Phone</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Retirement" onclick="dynInput(this);">Retirement</td>
		<td><input type="checkbox" name="category" value="Savings" onclick="dynInput(this);">Savings</td>
		<td><input type="checkbox" name="category" value="Spending" onclick="dynInput(this);">Spending</td>
		</tr>
		<tr>
		<td><input type="checkbox" name="category" value="Vacation" onclick="dynInput(this);">Vacation</td>
		<td><input type="checkbox" name="category" value="Misc" onclick="dynInput(this);">Miscellaneous</td>
		</tr>
		</table>
		<br />
		Allot points (percent amount) to each category for budgeting
		<p>Total points should not go above 100</p>
		<h2 id='total-points'>100</h2>
		<p id="point-input"></p>
		<br />
		&nbsp;
		<input type="submit" name="submit" value="Submit" id="submit"/>
	</form>
</section>
</body>
</html>