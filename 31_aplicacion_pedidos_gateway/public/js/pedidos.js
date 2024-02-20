function buscarResultados() {
	//let url = "http://localhost:8761/eureka/apps/servicio-pedidos/pedidos";
	let url = "http://localhost:8030/spedidos/pedidos";
	$.get(url, procesarResultados)
}

function procesarResultados(data) {
	let res = "<tr>";
	$.each(data, function(i, e) {
		res += "<td>" + e.idPedido + "</td><td>" + e.codigoProducto + "</td>";
		res += "<td>" + e.total + "</td><td>" + e.fechaPedido + "</td></tr>";
	});
	$("#pedidos").html(res);
}

$(document).ready(function() {
	buscarResultados();
});