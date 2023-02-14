/**
 * 
 */
function validar(){
	let nome = frmContato.nome.value
	let fone = frmContato.fone.value

	if(nome === ""){
		alert('Preencher Campo Nome')
		frmContato.nome.focus()
		return false
	} else if(fone === ""){
		alert('Preencher Campo Fone')
		frmContato.nome.focus()
		return false
	} else {
		 document.forms["frmContato"].submit();
	}
}