<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>
<c:url value="/main" var="url"/>

<div uk-grid>

    <div class="uk-width-1-2@m uk-padding-small uk-align-center">
        <div class="uk-card uk-card-default uk-card-body uk-text-left">

            <button
                    class="uk-button uk-button-default uk-width-expand uk-margin"
                    onclick="window.location.href='/add'"
            >
                Добавить продукцию
            </button>

            <button
                    class="uk-button uk-button-default uk-width-expand uk-margin"
                    onclick="window.location.href='/table'"
            >
                Посмотреть продукцию
            </button>

        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>
