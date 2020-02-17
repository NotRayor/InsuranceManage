/**
 * 
 */
var submitForm = $("form[role='form']");
var submitBtn = submitForm.find($("button[type='submit']"));
var isSubmited = false;

function doubleSubmitCheck(){
	if(isSubmited == false){
		isSubmited = true;
		submitBtn.attr("disabled", "disabled");
		submitBtn.text("동작중...");
		return isSubmited;
	}else{
		return isSubmited;
	}
}
