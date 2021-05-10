<!doctype html>
<%@page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html lang="it">
<head>
    <jsp:include page="../header.jsp"/>
    <title>Elimina elemento</title>

    <!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">

</head>
<body>
<jsp:include page="../navbar.jsp"/>

<main role="main" class="container">


    <div class='card'>
        <div class='card-header'>
            Visualizza dettaglio del contribuente da eliminare
        </div>

        <div class="card card-body">
            <dl class="row">
                <dt class="col-sm-3 text-right">Nome:</dt>
                <dd class="col-sm-9">${elimina_contribuente_attr.nome}</dd>
            </dl>
            <dl class="row">
                <dt class="col-sm-3 text-right">Cognome:</dt>
                <dd class="col-sm-9">${elimina_contribuente_attr.cognome}</dd>
            </dl>
            <dl class="row">
                <dt class="col-sm-3 text-right">Indirizzo:</dt>
                <dd class="col-sm-9">${elimina_contribuente_attr.indirizzo}</dd>
            </dl>
            <dl class="row">
                <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
                <dd class="col-sm-9">${elimina_contribuente_attr.codiceFiscale}</dd>
            </dl>
            <dl class="row">
                <dt class="col-sm-3 text-right">Data di nascita:</dt>
                <dd class="col-sm-9"><fmt:formatDate type="date" pattern="dd/MM/yyyy"
                                                     value="${elimina_contribuente_attr.dataDiNascita}"/></dd>
            </dl>


        </div>

        <div class='card-footer'>

            <form action="executedelete" method="post">
                <a href="${pageContext.request.contextPath }/contribuente/" class='btn btn-outline-secondary'
                   style='width:80px'>
                    <i class='fa fa-chevron-left'></i> Back
                </a>

                <input type="hidden" name="idContribuente" value="${elimina_contribuente_attr.id }">
                <button type="submit" class='btn btn-outline-secondary' style='color: black;background-color: red'>
                    Elimina
                </button>

            </form>


        </div>


    </div>


    <!-- end main container -->
</main>
<jsp:include page="../footer.jsp"/>

</body>
</html>