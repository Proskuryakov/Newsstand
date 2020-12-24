<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>
<c:url value="/table" var="url"/>

<div class="uk-padding-small" uk-grid>

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
                    <select class="uk-select" name="type">
                        <option <c:if test="${param.type eq 'ALL'}"> selected </c:if> value="ALL">Вся</option>
                        <option <c:if test="${param.type eq 'NEWSPAPER'}"> selected </c:if> value="NEWSPAPER">Газеты
                        </option>
                        <option
                                <c:if test="${param.type eq 'MAGAZINE'}">selected</c:if> value="MAGAZINE">Журналы
                        </option>
                        <option
                                <c:if test="${param.type eq 'BOOK'}">selected</c:if> value="BOOK">Книги
                        </option>
                    </select>
                </div>

                <div class="uk-margin-small">Сортировать по:</div>

                <div class="uk-flex uk-flex-around uk-margin-remove">
                    <div>
                        <select class="uk-select uk-width-auto" name="parameter">
                            <option <c:if test="${param.parameter eq 'NAME'}"> selected </c:if> value="NAME">Название
                            </option>
                            <option <c:if test="${param.parameter eq 'PRICE'}"> selected </c:if> value="PRICE">Цена
                            </option>
                            <option <c:if test="${param.parameter eq 'TYPE'}"> selected </c:if>value="TYPE">Тип</option>
                        </select>
                    </div>

                    <div>
                        <select class="uk-select uk-width-auto" name="direction">
                            <option <c:if test="${param.direction eq 'ASC'}"> selected </c:if> value="ASC">По
                                возрастанию
                            </option>
                            <option <c:if test="${param.direction eq 'DESC'}"> selected </c:if> value="DESC">По
                                убыванию
                            </option>
                        </select>
                    </div>
                </div>

                <div class="uk-margin-small uk-width-expand">
                    <button
                            type="submit"
                            name="action"
                            value="display"
                            class="uk-button uk-button-secondary uk-width-expand"
                    >Отобразить
                    </button>
                </div>

                <div>
                    <label><input
                            class="uk-checkbox"
                            type="checkbox"
                            name="updateMode"
                            value="true"
                    <c:if test="${not(param.type eq 'NEWSPAPER' or param.type eq 'MAGAZINE' or param.type eq 'BOOK')}">
                            disabled
                    </c:if>
                    <c:if test="${param.updateMode eq 'true'}"> checked </c:if>
                    > Режим редактирования</label>
                </div>

            </form>
        </div>
    </div>


    <div class="uk-width-3-4@m">
        <div class="uk-card uk-card-default uk-card-body uk-text-left">
            <div class="uk-overflow-auto">

                <c:if test="${requestScope.updateResult eq 'true'}">
                    <div class="uk-text-center uk-text-bold uk-text-success">
                        Товар успешно отредактирован.
                    </div>
                </c:if>
                <c:if test="${requestScope.updateResult eq 'false'}">
                    <div class="uk-text-center uk-text-bold uk-text-danger">
                        Ошибка при редактировании.
                    </div>
                </c:if>


                <table class="uk-table uk-table-middle uk-table-small uk-table-striped">
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

                        <c:if test="${not(param.updateMode eq 'true') or param.type eq 'ALL'}">
                            <tr>

                                <td class="uk-table-shrink"><c:out value="${pm.id}"/></td>
                                <td class="uk-table-shrink"><c:out value="${pm.type.value}"/></td>
                                <td><c:out value="${pm.name}"/></td>


                                <c:if test="${param.type eq 'NEWSPAPER' or param.type eq 'MAGAZINE'}">
                                    <td class="uk-table-shrink">№<c:out value="${pm.number}"/></td>
                                    <td class="uk-table-shrink"><f:formatDate pattern="dd.MM.yyyy"
                                                                              value="${pm.date.time}"/></td>
                                </c:if>

                                <c:if test="${param.type eq 'BOOK'}">
                                    <td><c:out value="${pm.author}"/></td>
                                    <td><c:out value="${pm.publishingHouse}"/></td>
                                </c:if>

                                <c:if test="${param.type eq 'BOOK' or param.type eq 'MAGAZINE'}">
                                    <td class="uk-table-shrink"><c:out value="${pm.numberOfPage}"/></td>
                                </c:if>


                                <f:setLocale value="ru_RU"/>
                                <td class="uk-table-shrink"><f:formatNumber value="${pm.price}" type="currency"/></td>

                                <td class="uk-table-shrink">
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
                        </c:if>

                        <c:if test="${param.updateMode eq 'true' and not(param.type eq 'ALL')}">
                            <form action="${url}" method="post">
                                <tr>

                                    <input type="hidden" value="${pm.id}" name="id">
                                    <td class="uk-table-shrink"><c:out value="${pm.id}"/></td>
                                    <input type="hidden" value="${pm.type}" name="type">
                                    <td class="uk-table-shrink"><c:out value="${pm.type.value}"/></td>
                                    <td>
                                        <input
                                                type="text"
                                                name="name"
                                                class="uk-input"
                                                value="<c:out value='${pm.name}'/>"
                                                required
                                        >
                                    </td>

                                    <c:if test="${param.type eq 'NEWSPAPER' or param.type eq 'MAGAZINE'}">
                                        <td>
                                            <input
                                                    class="uk-input"
                                                    type="number"
                                                    name="number"
                                                    min="1"
                                                    required
                                                    value="<c:out value='${pm.number}'/>"
                                            >
                                        </td>
                                        <td>
                                            <input
                                                    class="uk-input"
                                                    type="date"
                                                    name="date"
                                                    required
                                                    value="<f:formatDate pattern="yyyy-MM-dd" value="${pm.date.time}"/>"
                                            >
                                        </td>
                                    </c:if>

                                    <c:if test="${param.type eq 'BOOK'}">
                                        <td>
                                            <input
                                                    class="uk-input"
                                                    type="text"
                                                    name="author"
                                                    required
                                                    value="<c:out value='${pm.author}'/>"
                                            >
                                        </td>
                                        <td>
                                            <input
                                                    class="uk-input"
                                                    type="text"
                                                    name="publishingHouse"
                                                    required
                                                    value="<c:out value='${pm.publishingHouse}'/>"
                                            >
                                        </td>
                                    </c:if>

                                    <c:if test="${param.type eq 'BOOK' or param.type eq 'MAGAZINE'}">
                                        <td>
                                            <input
                                                    class="uk-input"
                                                    type="number"
                                                    name="pageCount"
                                                    min="1"
                                                    required
                                                    value="<c:out value='${pm.numberOfPage}'/>"
                                            >
                                        </td>
                                    </c:if>

                                    <td>
                                        <input class="uk-input"
                                               type="text"
                                               pattern="\d+(\.\d)?\d?"
                                               name="price"
                                               required
                                               value="<c:out value="${pm.price}"/>"
                                        >
                                    </td>

                                    <td class="uk-table-shrink">
                                        <button
                                                class="uk-button uk-button-primary"
                                                type="submit"
                                                name="action"
                                                value="update"
                                        >
                                            Редактировать
                                        </button>
                                    </td>

                                </tr>
                            </form>
                        </c:if>
                    </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>
    </div>

</div>

<jsp:include page="footer.jsp"/>
