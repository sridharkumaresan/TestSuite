<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>
 <div class="container">
  
    <h3>Test Summary</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <div class="table">
       <div class="row header">
          <div class="cell">Test ID</div>
          <div class="cell">Test Description</div>
          <div class="cell">Test Type</div>
          <div class="cell">Test Priority</div>
          <div class="cell">Date Key</div>
          <div class="cell">Platform Key</div>
          <!-- <div class="cell">Key ID</div>
          <div class="cell">Expected</div>
          <div class="cell">Actual</div> -->
          <div class="cell">Test Outcome</div>
          <div class="cell">Count</div>
       </div>
       <c:forEach items="${testDetails}" var="TestDetail" >
          <div class="row">
             <div class="cell">${TestDetail.testID}</div>
             <div class="cell">${TestDetail.testType}</div>
             <div class="cell">${TestDetail.testDescription}</div>
             <div class="cell">${TestDetail.testPriority}</div>
             <div class="cell">${TestDetail.datekey}</div>
             <div class="cell">${TestDetail.platformkey}</div>
             <%-- <div class="cell">${TestDetail.keyID}</div>
             <div class="cell">${TestDetail.expected}</div>
             <div class="cell">${TestDetail.actual}</div> --%>
             <div class="cell">${TestDetail.testOutcome}</div>
             <div class="cell">${TestDetail.recCount}</div>
          </div>
       </c:forEach>
    </div>
   
  </div>
<jsp:include page="footer.jsp"></jsp:include>
