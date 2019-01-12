<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="<c:url value="/res/style.css"/>" rel="stylesheet" type="text/css"/>
    <link rel="icon" type="image/png" href="<c:url value="/res/icon.png"/>" />
    <title>Computer parts</title>
</head>
<body>
<table class="style">
    <caption class="heading">Computer parts</caption>
    <caption class="filter">
        <form action="<c:url value="/"/>">
            <input type="hidden" name="page" value="${page}">
            <input type="hidden" name="required" value="${required}">
            <input type="search" name="search" placeholder="title" value="${search}" maxlength="100" title="search by title">
            <input type="submit" value="" title="click to search"><span class="icon icon-find"></span>
        </form>
        <div>
            <span class="sign">Filter:&emsp;</span>
            <c:set value="selected" var="selected"/>
            <c:set value="" var="unselected"/>
            <c:url value="/" var="url">
                <c:param name="page" value="${page}"/>
                <c:if test="${!empty search}">
                    <c:param name="search" value="${search}"/>
                </c:if>
            </c:url>
            <a class="${empty required ? selected : unselected}" href="${url}" title="all parts">
                <span class="icon icon-all"></span>
            </a>
            <c:url value="/" var="url">
                <c:param name="page" value="1"/>
                <c:param name="required" value="true"/>
                <c:if test="${!empty search}">
                    <c:param name="search" value="${search}"/>
                </c:if>
            </c:url>
            <a class="${required == true ? selected : unselected}" href="${url}" title="only required parts">
                <span class="icon icon-required"></span>
            </a>
            <c:url value="/" var="url">
                <c:param name="page" value="1"/>
                <c:param name="required" value="false"/>
                <c:if test="${!empty search}">
                    <c:param name="search" value="${search}"/>
                </c:if>
            </c:url>
            <a class="${required == false ? selected : unselected}" href="${url}" title="only optional parts">
                <span class="icon icon-optional"></span>
            </a>
        </div>
    </caption>
    <tr>
        <th class="left-side">№</th>
        <th style="width: 100%">title</th>
        <th>required</th>
        <th>quantity</th>
        <th colspan="2" class="right-side">action</th>
    </tr>
    <c:if test="${partsCount > 0}">
        <c:forEach var="part" items="${parts}" varStatus="i">
            <tr>
                <td class="left-side">${i.index + 1 + (page - 1) * 10}</td>
                <td class="title">${part.title}</td>
                <td>
                    <c:if test="${part.required == true}">
                        <span class="icon icon-required" title="required part"></span>
                    </c:if>
                </td>
                <c:set value="null-quantity" var="zero"/>
                <c:set value="" var="quantity"/>
                <td class="${part.quantity == 0 ? zero : quantity}">${part.quantity}</td>
                <td>
                    <a href="/edit/${part.id}" title="edit this part">
                        <span class="icon icon-edit"></span>
                    </a>
                </td>
                <td class="right-side">
                    <a href="/delete/${part.id}" title="delete this part">
                        <span class="icon icon-delete"></span>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${partsCount == 0}">
        <tr>
            <td colspan="7" style="font-size: 150%" class="left-side right-side">
                <c:if test="${allPartsCount == 0}">
                    the list is empty but you can add a new part
                </c:if>
                <c:if test="${allPartsCount != 0}">
                    no data found for such parameters
                </c:if>
            </td>
        </tr>
    </c:if>
    <tr>
        <td colspan="7" class="left-side link right-side">
            <a style="font-size: 100%" href="<c:url value="/add"/>" title="click to add a new part">
                <span class="icon icon-add"></span>Add new part
            </a>
            <c:if test="${pagesCount > 1}">
                <c:set value="disabled" var="disabled"/>
                <c:set value="" var="active"/>
                <c:url value="/" var="url">
                    <c:param name="page" value="1"/>
                    <c:if test="${!empty required}">
                        <c:param name="required" value="${required}"/>
                    </c:if>
                    <c:if test="${!empty search}">
                        <c:param name="search" value="${search}"/>
                    </c:if>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-first"></span>&nbsp
                </a>
                <c:url value="/" var="url">
                    <c:param name="page" value="${page - 1}"/>
                    <c:if test="${!empty required}">
                        <c:param name="required" value="${required}"/>
                    </c:if>
                    <c:if test="${!empty search}">
                        <c:param name="search" value="${search}"/>
                    </c:if>
                </c:url>
                <a class="${page == 1 ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-prev"></span>&nbsp
                </a>
                <c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
                    <c:url value="/" var="url">
                        <c:param name="page" value="${i.index}"/>
                        <c:if test="${!empty required}">
                            <c:param name="required" value="${required}"/>
                        </c:if>
                        <c:if test="${!empty search}">
                            <c:param name="search" value="${search}"/>
                        </c:if>
                    </c:url>
                    <c:set value="current-page" var="current"/>
                    <c:set value="" var="perspective"/>
                    <a class="${page == i.index ? current : perspective}" href="${url}">${i.index}</a>
                </c:forEach>
                <c:url value="/" var="url">
                    <c:param name="page" value="${page + 1}"/>
                    <c:if test="${!empty required}">
                        <c:param name="required" value="${required}"/>
                    </c:if>
                    <c:if test="${!empty search}">
                        <c:param name="search" value="${search}"/>
                    </c:if>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-next"></span>&nbsp
                </a>
                <c:url value="/" var="url">
                    <c:param name="page" value="${pagesCount}"/>
                    <c:if test="${!empty required}">
                        <c:param name="required" value="${required}"/>
                    </c:if>
                    <c:if test="${!empty search}">
                        <c:param name="search" value="${search}"/>
                    </c:if>
                </c:url>
                <a class="${page == pagesCount ? disabled : active}" href="${url}">
                    &nbsp<span class="icon icon-last"></span>&nbsp
                </a>
            </c:if>
            <span style="font-size: 120%"> ${partsCount} matches found</span>
        </td>
    </tr>
    <caption class="sign" align="bottom">
        <span style="float: left; margin-left: 30px;">Computers can be assembled: ${computersCount}</span>
        <span style="float: right; margin-right: 30px;">Total number of unique parts: ${allPartsCount}</span>
    </caption>
</table>
</body>
</html>


