<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">

  <%@ include file="../include/head2.jsp" %>
  
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="icon" href="${path }/img/favicon.ico">
  <title>회원가입 | 로얄파트너스</title>
  
  <!-- login css -->
  <link href="${path }/dist/css/join.css" rel="stylesheet">
  <!-- Bootstrap core CSS -->
  <link href="${path }/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="${path }/dist/css/agency.min.css" rel="stylesheet">
</head>

<body id="page-top">

  <!-- nav-->
  <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
    <div class="container">
      <a class="navbar-brand js-scroll-trigger" href="${path }/"><img src='${path }/img/logos/main_logo.png' width="200px" height="50px"></a>
      </div>
  </nav>

  <!-- header -->
  <div class="page-section">
  </div>

  <!-- section -->
  <div class="page-section" id="about">
    <form action="${path }/admin/register" method="get" onsubmit="return joinMSG()">
      <div class="container">
        <h2 class="section-heading text-uppercase">회원가입</h2>
        <div class="checkbox_join">
        <input type = "checkbox" id= "join_ag1"  class="form-box_join clearfix"><p class="info_join clearfix"><b>이용약관 동의</b>(필수)</p>
        </div>
        <div class="form-boxs_join"></div>
        <div class="checkbox_join">
        <input type = "checkbox"  id= "join_ag2" class="form-box_join clearfix"><p class="info_join clearfix"><b>개인정보 수집 및 이용동의</b>(필수)</p>
        </div>
        <div class="form-boxs_join"></div>
        <button class="btns_join" type="submit">확인</button>
        <button class="btns_join" type="button" onclick="self.location='${path }/'">취소</button>
      </div>
    </form>
  </div>


	<%@include file="../include/main_footer2.jsp" %>
  <script type="text/javascript">
    function joinMSG() {
      var chk1 = document.getElementById("join_ag1");
      var chk2 = document.getElementById("join_ag2");
      if (chk1.checked==false&&chk2.checked==false){
        alert("필수사항을 체크해주세요");
        return false;}
      else if (chk1.checked==false){
        alert("이용약관 동의에 체크해주세요");
        return false;}
      else if (chk2.checked==false){
        alert("개인정보수집 및 이용동의에 체크해주세요");
        return false;}
      else {
          return true;
         }
      }
  </script>
</body>

</html>
