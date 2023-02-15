/**
 * 
 */
function deletar(idcon) {
	let resposta = confirm("Confirmar a exclusao deste contato ?");
	if (resposta === true) {
		window.location.href = "delete?idcon=" + idcon;
		alert('ID ENVIADO: ' + idcon)
	}
}