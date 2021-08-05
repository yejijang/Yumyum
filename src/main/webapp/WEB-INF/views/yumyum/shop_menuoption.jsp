<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

[
<c:forEach items="${oplist}" var="result" varStatus="status">
	<c:if test="${result.menu_seq == menuseq}">
		{
			"seq": "${result.seq}",
			"name": "${result.name}",
			"price": "${result.price}"
		}
		<c:if test="${oplist.size() - 1 > status.index}">
		,
		</c:if>
	</c:if>
</c:forEach>
]