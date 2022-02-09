<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>Форум job4j</title>
</head>
<body>
<a href="<c:url value='/create?user=${user.username}'/>">Добавить Объявление</a>
<br>
<a href="<c:url value='/logout'/>">Logout</a>
<br>
<div>
    <li>&#9745;</li> Login as : ${user.username}
</div>
<br>
<div class="container mt-3">
    <div class="row">
        <h4>Форум job4j</h4>
    </div>
    <div class="card-body">
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Тема</th>
                <th scope="col">Описание</th>
                <th scope="col">Создан</th>
                <th scope="col">Автор</th>
                <th scope="col">Обновить</th>
                <th scope="col">Написать</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="post" items="${posts}">
                <tr>
                    <td><c:out value="${post.id}"/></td>
                    <td><a href="<c:url value='/post/discussionmain?id=${post.id}'/>"><c:out value="${post.name}"/></a> </td>
                    <td><c:out value="${post.description}"/></td>
                    <td><c:out value="${post.created.getTime()}"/></td>
                    <td><c:out value="${post.user.username}"/></td>
                    <td>
                        <c:if test="${post.user.username == user.username}">
                            <a href="<c:url value='/update?id=${post.id}'/>">Обновить объявление</a></td>
                        </c:if>
                        <c:if test="${post.user.username != user.username}">
                            <c:out value=" "/>
                        </c:if>
                    <td><a href="<c:url value='/discussions?id=${post.id}'/>">send message <li>&#128386;</li></a> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</div>
</body>
</html>
