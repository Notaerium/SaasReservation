function ShowNewOption(option, newOption){
	let selectOption = document.getElementById(option)
	let newOptionDOM = document.getElementById(newOption)

	if(selectOption.value == ""){
		newOptionDOM.style.display = '';
	}
	else{
		newOptionDOM.style.display  = 'none';
	}
}

function VerifyDateInput(){
		MinReturnDate()
		let startPeriod = document.getElementById("start")
		let endPeriod = document.getElementById("end")
		let submitBtn = document.getElementById("borrowInput")
		
		if(startPeriod.value == ""){
			startPeriod.style.border = "solid #FF0000"
		}else{
			startPeriod.style.border = "none" 
			}
			
		if(endPeriod.value == ""){
			endPeriod.style.border = "solid #FF0000"
		}else{
			endPeriod.style.border = "none" 
			}
			
		if(startPeriod.value == "" || endPeriod.value == "" || startPeriod.value == null || endPeriod.value == null){
			submitBtn.disabled = true
		}
		else {
			submitBtn.disabled = false
		}
}

function MinReturnDate(){
	let getStartDate = document.getElementById("start").value
	let setEndDate = document.getElementById("end")
	setEndDate.min = getStartDate;
}
