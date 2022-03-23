<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@ page import="com.logistics.prod.Entities.Partner" %>
<%@ page import="com.logistics.prod.Servlets.ServletListPackages" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>Listar Lotes</title>
</head>
<body>


<form action="ListPackages" method="post">

    <h2>Mostrar todos los lotes</h2>



    <div class="form-group">
        <button id="button-1" type="submit"  class="button">Buscar</button>

    </div>
    <div>

        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col"># Lote</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Fecha Creaci�n</th>
                <th scope="col">Status</th>
                <th scope="col">Expiracion</th>
                <th scope="col">Disponibles</th>
            </tr>
            </thead>
            <tbody>

            <% List<Partner> lista = (List<Partner>) request.getAttribute("lista");
                if (lista != null){
                    for(Partner partner : lista){

            %>

            <tr>

                <td><%out.print(partner.getPackageStatus());%></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <% }%>
            <% }%>
            </tbody>
        </table>

    </div>


    </label>
</form>
</body>
</html>