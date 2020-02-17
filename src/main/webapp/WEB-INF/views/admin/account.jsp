<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<%@ include file="../include/head2.jsp"%>

<body class=" skin-blue">

<div class="wrapper">

    <!-- Main Header -->
    <%@ include file="../include/main_header2.jsp"%>

    <!-- Content Wrapper. Contains page content -->
    <div class="container">
        <!-- Main content -->
        <div class="content container-fluid">
            <div class="col-xl-12">
                <div class="card card-primary">
                    <form role="form" id="writeForm" method="post" action="${path}/admin/update">
                    	<input type="text" value="${login.adminId }" name="adminId" hidden="true">
	                    <div class="card-header">
	                    	<h3 class="card-title">개인 정보 </h3>
	                    </div><!--/.card-header-->
	                    <div class="card-body">	
	                    	<div class="form-group">
	                    		<label for="email">이메일 주소</label>
	                    		<input class="form-control" id="adminEmail" name="adminEmail" placeholder="이메일" value="${adminVO.adminEmail }">
	                    	</div>
	                    	<div class="form-group">
	                    		<label for="adminPw">이름</label>
	                    		<input class="form-control" id="adminName" name="adminName" placeholder="이름" value="${adminVO.adminName }">
	                    	</div>
	                    </div><!--/.card-body-->
	                    <div class="card-footer">
	                    	<button type="button" class="btn btn-primary" id="emailUpdateBtn"><i class="fa fa-save"></i>정보 수정</button>
	                    	<button type="button" class="btn btn-primary" id="updatePwBtn">비밀번호 변경</button>
	                    	<button type="button" class="btn btn-primary" id="memberDeleteBtn"> 회원탈퇴</button>
	                    </div><!--/.card-footer-->
	                </form> 
                </div><!-- /.card card-primary card-outline-->
            </div><!-- /.col-lg-12 -->
        </div>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <%@ include file="../include/main_footer2.jsp"%>

</div>
<!-- ./wrapper -->
<%@ include file="../include/plugin_js.jsp"%>
<script type="text/javascript">


$(document).ready(function(){

	var formObj = $("form[role='form']");
	var reEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; // 이메일이 적합한지 검사할 정규식
	var msg = "";
	
	// 메시지 
	var result = "${msg}";
	if (result == "regSuccess") {
	    alert("게시글 등록이 완료되었습니다.");
	} else if (result == "modSuccess") {
	    alert("게시글 수정이 완료되었습니다.");
	} else if (result == "delSuccess") {
	    alert("게시글 삭제가 완료되었습니다.");
	}

	$("#emailUpdateBtn").click(function(){
		if(emailValidate()){
			alert(msg);
			return;
		}
		AjaxDuplicateEmail().then(function(data){
			if(data == true){
				msg="이미 등록된 이메일 입니다.";
				alert(msg);
			}
			else{
				formObj.submit();
			}
		});
	});
	
	function emailValidate(){
		var adminEmail = $("#adminEmail").val();
		if(!reEmail.test(adminEmail)){
			msg = "이메일을 형식이 맞지 않습니다.";
			return true;
		}
		return false;
	}

  $("#updatePwBtn").click(function(){
		self.location = "${path }/admin/updatePw?adminId=${login.adminId}&authKey=${login.adminAuthKey}";
  });
  
  $("#memberDeleteBtn").click(function(){

	var confirm_val = confirm("회원 탈퇴하시겠습니까?");

	if(confirm_val){
		formObj.attr("method","post");
		formObj.attr("action","${path }/admin/delete");
		formObj.submit();
	}

  });

	function AjaxDuplicateEmail(){
		return new Promise(function (resolve, reject){
		    var adminEmail = $('#adminEmail').val();
		    $.ajax({
		      url : "${path }/admin/checkEmail"+"?adminEmail=" + adminEmail,
		      type : "get",
		      success : function(data){
		        if(data>=1){
		          	resolve(true);
		        }
		        else{
			        resolve(false);
			    }
		      },
		      error : function(data){
			    alert("이메일 중복 체크 실패");
				reject();
			  }
		    });
		});
	}

	
	
});

</script>
</body>
</html>