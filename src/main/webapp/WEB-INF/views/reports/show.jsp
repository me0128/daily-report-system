<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actGood" value="${ForwardConst.ACT_GOOD.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commGood" value="${ForwardConst.CMD_GOOD.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<h2>日報詳細ページ</h2>

		<table>
			<tbody>
				<tr>
					<th>氏名</th>
					<td><c:out value="${report.employee.name}" /></td>
				</tr>
				<tr>
					<th>日付</th>
					<fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd"
						var="reportDay" type="date" />
					<td><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
				</tr>
				<tr>
					<th>内容</th>
					<td><pre>
							<c:out value="${report.content}" />
						</pre></td>
				</tr>
				<tr>
					<th>登録日時</th>
					<fmt:parseDate value="${report.createdAt}"
						pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
					<td><fmt:formatDate value="${createDay}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>更新日時</th>
					<fmt:parseDate value="${report.updatedAt}"
						pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
					<td><fmt:formatDate value="${updateDay}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th>いいね数</th>
					<td><a
						href="<c:url value='?action=${actGood}&command=${commIdx}&id=${report.id}' />"><c:out
								value="${goods_count}" /></a></td>
				</tr>

			</tbody>
		</table>


		<%-- いいね機能追加中 --%>
		<c:if test="${sessionScope.login_employee.id != report.employee.id}">
			<%--自分以外の時表示される --%>

			<c:if test="${good != null}">
			<p>いいね済みです</p>
			<%-- <form method="POST"action="<c:url value='?action=${actGood}&command=${commDel}'/>">
			<input type="hidden" name="${AttributeConst.GOOD_ID.getValue()}" value="${good.id}" />
			<input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
			<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
			<button type="submit" ><i class="fa-solid fa-thumbs-up" style="color:red;"></i></button><%--いいね削除 --%>
		    <%--</form> --%>
			</c:if>
			<c:if test="${good == null}">
		<form method="POST" action="<c:url value='?action=${actGood}&command=${commGood}&id=${report.id}'/>">
		<input type="hidden" name="${AttributeConst.GOOD_ID.getValue()}" value="${good.id}" />
		<input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
        <button type="submit"><i class="fa-solid fa-thumbs-up" style="color: blue;"></i></button><%--いいね登録 --%>
		</form>
		</c:if>
		</c:if>

		<%--↑いいね機能追加中↑ --%>

		<c:if test="${sessionScope.login_employee.id == report.employee.id}">
			<p>
				<a
					href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
			</p>
		</c:if>

		<p>
			<a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
		</p>
	</c:param>
</c:import>