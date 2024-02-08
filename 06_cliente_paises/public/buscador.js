let url = "http://localhost:8080/05_servicio_paises/";
function cargarContinentes() {
	var res = "<h3>Seleccione un continente</h3><div class='card''><select id='lista' onchange='cargarPaises()'>";
	$.get(url + "continentes", function(data) {
		$.each(data, function(i, e) {
			res += "<option value='" + e + "'>" + e + "</option>";
		});
		res += "</select></div><br>";
		$("#continentes").html(res);
	});
}
function cargarPaises() {
	var res = "<br><h2>Paises</h2>";
	res += "<br><table border='1'>";
	res += "<tr><th>Nombre</th><th>Capital</th><th>Población</th><th>Bandera</th></tr>";
	$.get(url + "paises/" + $("#lista").val(), function(data) {
		$.each(data, function(i, p) {
			res += "<tr><td>" + p.nombre + "</td><td>" + p.capital + "</td><td>" + p.poblacion + "</td><td align='center'><img src='" + p.bandera + "' alt='Bandera' width='32' height='16'></td></tr>";
		});
		res += "</table>";
		$("#paises").html(res);
	});
}


$(document).ready(function() {
	cargarContinentes();
});