<%@page import="kny.cook.domain.PhotoBoard"%>
<%@page import="kny.cook.domain.Recipe"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<jsp:include page="/header.jsp"/>

<jsp:useBean id="recipe" class="kny.cook.domain.Recipe" scope="request"/>

  <h1>레시피사진<a href='../recipe/detail?no=<%=recipe.getNo()%>'><%=recipe.getCook()%></a></h1>  
  <a href='add?recipeNo=<%=recipe.getNo()%>'>새 사진</a><br>
  <table border='1'>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>등록일</th>
    <th>조회수</th>
  </tr>
  
<jsp:useBean id="list" 
  type="java.util.List<PhotoBoard>"
  class="java.util.ArrayList"
  scope="request"/>  
<% 
  for(PhotoBoard item : list) {
%>
  <tr>
    <td><%=item.getNo()%></td> 
    <td><a href='detail?no=<%=item.getNo()%>'><%=item.getTitle()%></a></td> 
    <td><%=item.getCreatedDate()%></td> 
    <td><%=item.getViewCount()%></td>
  </tr>
<%
  }
%>
  </table>

<jsp:include page="/footer.jsp"/>
    