<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Note List page</title>
</head>
<body>
<h1>Note List page</h1>
<table style="text-align: center;" border="1px" cellpadding="0" cellspacing="0" >
<thead>
<tr>
<th width="25px">id</th>
<th width="450px">Message</th>
<th width="250px">CreatedTime</th>
<th width="250px">UpdatedTime</th>
</tr>
</thead>
<tbody>
<c:forEach var="note" items="${noteList}">
<tr>
<td>${note.id}</td>
<td>${note.message}</td>
<td>${note.createdTime}</td>
<td>${note.updatedTime}</td>
<td>
<a href="${pageContext.request.contextPath}/note/edit/${note.id}.html">Edit</a><br/>
<a href="${pageContext.request.contextPath}/note/delete/${note.id}.html">Delete</a><br/>
</td>
</tr>
</c:forEach>
</tbody>
</table>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>