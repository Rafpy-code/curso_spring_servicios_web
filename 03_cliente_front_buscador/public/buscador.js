function buscarResultados(){
	let url = "http://localhost:8080/01_servicio_buscador/buscar";
	let params = {"tematica":$("#tematica").val()};
	$.get(url, params, procesarResultados)
}

function procesarResultados(data){
	let res = "<h1>Resultados de la busqueda</h1>";
	$.each(data, function(i,e){
		res += "<a href='" + e.url+ "'>" + e.url+ "</a><br>";
		res += "<b>" +e.descripcion+ "</b><br><hr>"
	});
	$("#tablaResultados").html(res);
}

$(document).ready(function() {
	$("#enviar").click(buscarResultados);
});