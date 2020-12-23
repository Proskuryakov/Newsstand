<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>

<div uk-grid>

    <div class="uk-width-3-4@m uk-padding-small uk-align-center">
        <div class="uk-card uk-card-default uk-card-body uk-text-left">

            <table class="uk-table uk-table-middle uk-table-small">
                <thead>
                <tr>
                    <td>id</td>
                    <td>Тип</td>
                    <td>Название</td>
                    <td>Цена</td>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="pm" items="${printedMatters}">
                    <tr>

                        <td><c:out value="${pm.id}"/></td>
                        <td><c:out value="${pm.type}"/></td>
                        <td><c:out value="${pm.name}"/></td>
                        <td><c:out value="${pm.price}"/></td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>

