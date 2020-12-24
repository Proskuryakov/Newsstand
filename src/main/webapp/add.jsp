<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>
<c:url value="/add" var="url"/>

<div class="uk-padding-small" uk-grid>
    <div class="uk-width-1-2@m uk-align-center">
        <div class="uk-card uk-card-default uk-card-body uk-text-left">

            <c:if test="${requestScope.error eq 'true'}">
                <div class="uk-text-center uk-text-bold uk-text-danger">
                    Ошибка при добавлении.
                </div>
            </c:if>

            <c:if test="${requestScope.error eq 'false'}">
                <jsp:useBean id="createdPrintedMatter" scope="request"
                             type="ru.vsu.cs.newsstand.core.domain.PrintedMatter"/>

                <div class="uk-text-center uk-text-bold uk-text-success">
                    Добавлена <c:out value="${createdPrintedMatter.type.value}"/>
                    "<c:out value="${createdPrintedMatter.name}"/>"
                </div>
            </c:if>


            <ul uk-accordion>
                <li>
                    <a class="uk-accordion-title">Добавить газету</a>
                    <div class="uk-accordion-content">

                        <form action="${url}" method="post" class="uk-form-stacked">

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Название</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="text" name="name" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Номер</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="number" name="number" min="1" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Дата</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="date" name="date" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Цена</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input"
                                           type="text"
                                           pattern="\d+(\.\d)?\d?"
                                           name="price"
                                           required>
                                </div>
                            </div>

                            <div class="uk-margin-top">
                                <label class="uk-form-label">Количество экземпляров</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="number" name="count" min="1" required>
                                </div>
                            </div>

                            <button
                                    class="uk-button uk-button-secondary uk-width-expand uk-margin-small"
                                    type="submit"
                                    name="action"
                                    value="addNewspaper"
                            >
                                Добавить
                            </button>

                        </form>

                    </div>
                </li>
                <li>
                    <a class="uk-accordion-title">Добавить журнал</a>
                    <div class="uk-accordion-content">

                        <form action="${url}" method="post" class="uk-form-stacked">

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Название</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="text" name="name" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Номер</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="number" name="number" min="1" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Дата</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="date" name="date" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Количество страниц</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="number" name="pageCount" min="1" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Цена</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input"
                                           type="text"
                                           pattern="\d+(\.\d)?\d?"
                                           name="price"
                                           required>
                                </div>
                            </div>

                            <div class="uk-margin-top">
                                <label class="uk-form-label">Количество экземпляров</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="number" name="count" min="1" required>
                                </div>
                            </div>

                            <button
                                    class="uk-button uk-button-secondary uk-width-expand uk-margin-small"
                                    type="submit"
                                    name="action"
                                    value="addMagazine"
                            >
                                Добавить
                            </button>

                        </form>

                    </div>
                </li>
                <li>
                    <a class="uk-accordion-title">Добавить книгу</a>
                    <div class="uk-accordion-content">
                        <form action="${url}" method="post" class="uk-form-stacked">

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Название</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="text" name="name" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Автор</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="text" name="author" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Издательство</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="text" name="publishingHouse" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Количество страниц</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="number" name="pageCount" min="1" required>
                                </div>
                            </div>

                            <div class="uk-margin-small">
                                <label class="uk-form-label">Цена</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input"
                                           type="text"
                                           pattern="\d+(\.\d)?\d?"
                                           name="price"
                                           required>
                                </div>
                            </div>

                            <div class="uk-margin-top">
                                <label class="uk-form-label">Количество экземпляров</label>
                                <div class="uk-form-controls">
                                    <input class="uk-input" type="number" name="count" min="1" required>
                                </div>
                            </div>

                            <button
                                    class="uk-button uk-button-secondary uk-width-expand uk-margin-small"
                                    type="submit"
                                    name="action"
                                    value="addBook"
                            >
                                Добавить
                            </button>

                        </form>

                    </div>
                </li>
            </ul>

        </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>
