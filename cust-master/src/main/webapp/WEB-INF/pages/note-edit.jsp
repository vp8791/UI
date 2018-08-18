<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Edit Note page</title>
</head>
<body>
<h1>Edit Note page</h1>
<form:form method="POST" commandName="note" action="${pageContext.request.contextPath}/note/edit/${note.id}.html" >
<table>
<tbody>
<tr>
<td>Note name:</td>
<td><form:input path="message" /></td>
<td><form:errors path="message" cssStyle="color: red;"/></td>
</tr>
<tr>
<td><input type="submit" value="Update" /></td>
<td></td>
<td></td>
</tr>
</tbody>
</table>
</form:form>
<a href="${pageContext.request.contextPath}/">Home page</a>
</body>
</html>
