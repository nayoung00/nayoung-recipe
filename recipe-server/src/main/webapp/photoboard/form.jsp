<%@page import="kny.cook.domain.Recipe"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/header.jsp"/>

     <h1>사진 입력</h1>  
       <form action='add' method='post' enctype='multipart/form-data'>  
      레시피번호: <input name='recipeNo' type='text' value='%d' readonly><br>
      요리명: <%=recipe.getCook()%><br>
       내용:<br>  
       <textarea name='title' rows='5' cols='60'></textarea><br>  
       <hr>  
       사진: <input name='photo1' type='file'><br>  
       사진: <input name='photo2' type='file'><br>  
       사진: <input name='photo3' type='file'><br>  
       사진: <input name='photo4' type='file'><br>  
       사진: <input name='photo5' type='file'><br>  
       <button>제출</button>  
       </form>  
   <jsp:include page="/footer.jsp"/>
     