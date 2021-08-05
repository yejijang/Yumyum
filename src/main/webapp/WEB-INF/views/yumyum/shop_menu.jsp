<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

[
<c:forEach items="${mlist}" var="result" varStatus="status">
	<c:if test="${result.seq == menuseq}">
		{
			"picture": "${result.picture}",
			"name": "${result.name}",
			"explanation": "${result.explanation}",
			"price": "${result.price}"
		}
	</c:if>
</c:forEach>
]