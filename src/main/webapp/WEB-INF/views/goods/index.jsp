<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst"%>
<c:set var="actGood" value="${ForwardConst.ACT_GOOD.getValue()}" />
<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commDel" value="${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
	<c:param name="content">
		<h2>いいね詳細一覧ページ</h2>
		<table id="good_list">
			<tbody>
				<tr>
					<th class="good_name">氏名</th>
				</tr>
				<c:forEach var="good" items="${goods}" varStatus="status">
					<tr class="row${status.count % 2}">
						<td class="good_name"><c:out value="${good.employee.name}" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="pagination">
			（全 ${goods_count} 件）<br />
			<c:forEach var="i" begin="1"
				end="${((goods_count - 1) / maxRow) + 1}" step="1">
				<c:choose>
					<c:when test="${i == page}">
						<c:out value="${i}" />&nbsp;
                    </c:when>
					<c:otherwise>
						<a
							href="<c:url value='?action=${actGood}&command=${commIdx}&page=${i}' />"><c:out
								value="${i}" /></a>&nbsp;
                    </c:otherwise>
				</c:choose>
			</c:forEach>
		</div>

		<p>
			<a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧画面に戻る</a>
		</p>
	</c:param>
</c:import>

