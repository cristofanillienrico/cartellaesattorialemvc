<!doctype html>
<%@page import="java.text.SimpleDateFormat" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html lang="it">
<head>
    <jsp:include page="../header.jsp"/>
    <title>Cambia Stato elemento</title>

    <!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath }/assets/css/global.css" rel="stylesheet">


</head>
<body>
<jsp:include page="../navbar.jsp"/>

<main role="main" class="container">


    <div class='card'>
        <div class='card-header'>
            Visualizza dettaglio del film da eliminare
        </div>

        <div class='card'>
            <div class='card-header'>
                Visualizza dettaglio
            </div>

            <div class='card-body'>
                <dl class="row">
                    <dt class="col-sm-3 text-right">Stato:</dt>
                    <dd class="col-sm-9">${cambiaStato_cartellaEsattoriale_attr.stato}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Descrizione:</dt>
                    <dd class="col-sm-9">${cambiaStato_cartellaEsattoriale_attr.descrizione}</dd>
                </dl>

                <dl class="row">
                    <dt class="col-sm-3 text-right">Importo:</dt>
                    <dd class="col-sm-9">${cambiaStato_cartellaEsattoriale_attr.importo}</dd>
                </dl>

            </div>

            <div class='card-footer'>

                <!-- Trigger the modal with a button -->
                <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Esegui Operazione</button>

                <!-- Modal -->
                <div id="myModal" class="modal fade" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="confirmDeleteModalLabel">Conferma l'operazione</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>Vuoi confermare l'attivazione/disattivazione della cartella esattoriale?</p>
                            </div>

                            <form method="post" action="${pageContext.request.contextPath }/cartellaesattoriale/cambiaStato/executecambiaStato" >
                                <div class="modal-footer">
                                    <input type="hidden" name="idCartellaEsattoriale" id="idCartellaEsattoriale" value="${cambiaStato_cartellaEsattoriale_attr.id}">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
                                    <input type="submit" value="Continua"  class="btn btn-primary btn-danger">
                                </div>
                            </form>

                            </div>
                        </div>

                    </div>
                </div>


            </div>


        </div>


        <!-- end main container -->
</main>
<jsp:include page="../footer.jsp"/>

</body>
</html>