<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>
<c:url value="/table" var="url"/>

<div uk-grid class="uk-padding-small">

    <div class="uk-width-1-4@m">
        <div class="uk-card uk-card-default uk-card-body uk-text-left">

            <form
                    class="uk-flex uk-flex-column"
                    id="display"
                    action="${url}"
                    method="get"
            >

                <legend class="uk-legend">Параметры отображения:</legend>

                <div class="uk-margin-small">Отображаемая продукция:</div>

                <div class="uk-margin-remove">
                    <select class="uk-select" name="type" >
                        <option <c:if test="${param.type eq 'ALL'}"> selected </c:if> value="ALL">Вся</option>
                        <option <c:if test="${param.type eq 'NEWSPAPER'}"> selected </c:if> value="NEWSPAPER">Газеты</option>
                        <option <c:if test="${param.type eq 'MAGAZINE'}">selected</c:if> value="MAGAZINE">Журналы</option>
                        <option <c:if test="${param.type eq 'BOOK'}">selected</c:if> value="BOOK">Книги</option>
                    </select>
                </div>

                <div class="uk-margin-small">Сортировать по:</div>

                <div class="uk-flex uk-flex-around uk-margin-remove">
                    <div>
                        <select class="uk-select uk-width-auto" name="parameter" >
                            <option <c:if test="${param.parameter eq 'NAME'}"> selected </c:if> value="NAME">Название</option>
                            <option <c:if test="${param.parameter eq 'PRICE'}"> selected </c:if> value="PRICE">Цена</option>
                            <option <c:if test="${param.parameter eq 'TYPE'}"> selected </c:if>value="TYPE">Тип</option>
                        </select>
                    </div>

                    <div>
                        <select class="uk-select uk-width-auto" name="direction" >
                            <option <c:if test="${param.direction eq 'ASC'}"> selected </c:if> value="ASC">По возрастанию</option>
                            <option <c:if test="${param.direction eq 'DESC'}"> selected </c:if> value="DESC">По убыванию</option>
                        </select>
                    </div>
                </div>

                <div class="uk-margin-small uk-width-expand">
                    <button
                            type="submit"
                            name="action"
                            value="display"
                            class="uk-button uk-button-secondary uk-width-expand"
                    >Отобразить</button>
                </div>

            </form>

        </div>
    </div>


    <div class="uk-width-3-4@m">
        <div class="uk-card uk-card-default uk-card-body uk-text-left">

            <table class="uk-table uk-table-middle uk-table-small">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Тип</th>
                    <th>Название</th>

                    <c:if test="${param.type eq 'NEWSPAPER' or param.type eq 'MAGAZINE'}">
                        <th>Номер</th>
                        <th>Дата</th>
                    </c:if>

                    <c:if test="${param.type eq 'BOOK'}">
                        <th>Автор</th>
                        <th>Издательство</th>
                    </c:if>

                    <c:if test="${param.type eq 'BOOK' or param.type eq 'MAGAZINE'}">
                        <th>Кол-во страниц</th>
                    </c:if>

                    <th>Цена</th>
                    <th></th>
                </tr>
                </thead>


                <tbody>
                <c:forEach var="pm" items="${printedMatters}">
                    <tr>

                        <td><c:out value="${pm.id}"/></td>
                        <td><c:out value="${pm.type.value}"/></td>
                        <td><c:out value="${pm.name}"/></td>


                        <c:if test="${param.type eq 'NEWSPAPER' or param.type eq 'MAGAZINE'}">
                            <td>№<c:out value="${pm.number}"/></td>
                            <td><f:formatDate pattern="dd.MM.yyyy" value="${pm.date.time}"/></td>
                        </c:if>

                        <c:if test="${param.type eq 'BOOK'}">
                            <td><c:out value="${pm.author}"/></td>
                            <td><c:out value="${pm.publishingHouse}"/></td>
                        </c:if>

                        <c:if test="${param.type eq 'BOOK' or param.type eq 'MAGAZINE'}">
                            <td><c:out value="${pm.numberOfPage}"/></td>
                        </c:if>


                        <f:setLocale value="ru_RU"/>
                        <td><f:formatNumber value="${pm.price}" type="currency"/></td>

                        <td class="uk-width-small">
                            <form action="${url}" method="post">

                                <input type="hidden" value="${pm.id}" name="id">
                                <button
                                        class="uk-button uk-button-danger uk-width-auto"
                                        type="submit"
                                        name="action"
                                        value="delete"
                                >
                                    Удалить
                                </button>
                            </form>

                        </td>

                    </tr>
                </c:forEach>
                </tbody>


            </table>
<%--

            <c:choose>
                <c:when test="${param.type eq 'NEWSPAPER'}">
                    <table class="uk-table uk-table-middle uk-table-small">
                        <thead>
                        <tr>
                            <td>id</td>
                            <td>Тип</td>
                            <td>Название</td>
                            <td>№</td>
                            <td>Дата</td>
                            <td>Цена</td>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="n" items="${newspapers}">
                            <tr>

                                <td><c:out value="${n.id}"/></td>
                                <td><c:out value="${n.type}"/></td>
                                <td><c:out value="${n.name}"/></td>
                                <td><c:out value="${n.number}"/></td>
                                <td><f:formatDate pattern="dd.MM.yyyy" value="${n.date.time}"/></td>
                                <td><c:out value="${n.price}"/></td>

                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </c:when>
                <c:when test="${param.type eq 'MAGAZINE'}">
                    <table class="uk-table uk-table-middle uk-table-small">
                        <thead>
                        <tr>
                            <td>id</td>
                            <td>Тип</td>
                            <td>Название</td>
                            <td>№</td>
                            <td>Дата</td>
                            <td>Кол-во страниц</td>
                            <td>Цена</td>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="m" items="${magazines}">
                            <tr>

                                <td><c:out value="${m.id}"/></td>
                                <td><c:out value="${m.type}"/></td>
                                <td><c:out value="${m.name}"/></td>
                                <td><c:out value="${m.number}"/></td>
                                <td><f:formatDate pattern="dd.MM.yyyy" value="${m.date.time}"/></td>
                                <td><c:out value="${m.numberOfPage}"/></td>
                                <td><c:out value="${m.price}"/></td>

                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </c:when>
                <c:when test="${param.type eq 'BOOK'}">
                    <table class="uk-table uk-table-middle uk-table-small">
                        <thead>
                        <tr>
                            <td>id</td>
                            <td>Тип</td>
                            <td>Название</td>
                            <td>Автор</td>
                            <td>Издательство</td>
                            <td>Кол-во страниц</td>
                            <td>Цена</td>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="b" items="${books}">
                            <tr>

                                <td><c:out value="${b.id}"/></td>
                                <td><c:out value="${b.type}"/></td>
                                <td><c:out value="${b.name}"/></td>
                                <td><c:out value="${b.author}"/></td>
                                <td><c:out value="${b.publishingHouse}"/></td>
                                <td><c:out value="${b.numberOfPage}"/></td>
                                <td><c:out value="${b.price}"/></td>

                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </c:when>
                <c:otherwise>
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

                                <f:setLocale value="ru_RU"/>
                                <td><f:formatNumber value="${pm.price}" type="currency"/></td>

                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </c:otherwise>
            </c:choose>
--%>



        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>
