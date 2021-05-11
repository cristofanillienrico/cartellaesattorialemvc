<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it">
<head>
    <jsp:include page="../header.jsp"/>
    <title>Pagina dei risultati</title>

    <!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">

</head>
<body>
<jsp:include page="../navbar.jsp"/>

<main role="main" class="container">

    <div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
        ${successMessage}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
        ${errorMessage}
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>
    <div class="alert alert-info alert-dismissible fade show d-none" role="alert">
        Aggiungere d-none nelle class per non far apparire
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
            <span aria-hidden="true">&times;</span>
        </button>
    </div>

    <div class='card'>
        <div class='card-header'>
            <h5>Lista dei risultati</h5>
        </div>
        <div class='card-body'>
            <a class="btn btn-primary " href="${pageContext.request.contextPath }/cartellaesattoriale/insert">Add
                New</a>
            <a href="${pageContext.request.contextPath }/cartellaesattoriale/search" class='btn btn-outline-secondary'>
                <i class='fa fa-chevron-left'></i> Torna alla Ricerca
            </a>

            <div class='table-responsive'>
                <table class='table table-striped '>
                    <thead>
                    <tr>
                        <th>Descrizione</th>
                        <th>Importo</th>
                        <th>Stato</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cartellaEsattoriale_list_attribute }" var="cartellaEsattorialeItem">
                        <tr class=${cartellaEsattorialeItem.stato=="IN_CONTENZIOSO"?"table-danger":""}>
                            <td>${cartellaEsattorialeItem.descrizione }</td>
                            <td>${cartellaEsattorialeItem.importo }</td>
                            <td>${cartellaEsattorialeItem.stato }</td>
                            <td>
                                <a class="btn  btn-sm btn-outline-secondary"
                                   href="${pageContext.request.contextPath }/cartellaesattoriale/show/${cartellaEsattorialeItem.id }">Visualizza</a>
                                <a class="btn  btn-sm btn-outline-primary ml-2 mr-2"
                                   href="${pageContext.request.contextPath }/cartellaesattoriale/edit/${cartellaEsattorialeItem.id }">Edit</a>
                                

                                <c:if test="${cartellaEsattorialeItem.stato!='CANCELLATA'}">
                                    <a class="btn btn-outline-danger btn-sm"
                                       href="${pageContext.request.contextPath }/cartellaesattoriale/cambiaStato/${cartellaEsattorialeItem.id }">Disabilita
                                    </a>

                                </c:if>
                                <c:if test="${cartellaEsattorialeItem.stato=='CANCELLATA'}">
                                    <a class="btn btn-outline-success btn-sm"
                                       href="${pageContext.request.contextPath }/cartellaesattoriale/cambiaStato/${cartellaEsattorialeItem.id }">Abilita</a>

                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <!-- end card-body -->
        </div>
    </div>


    <!-- end container -->
</main>
<jsp:include page="../footer.jsp"/>

</body>
</html>