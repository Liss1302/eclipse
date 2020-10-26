const corpoPagina = document.querySelector("#corpoPagina");
const corpoTabela = document.querySelector("#corpoTabela");
corpoPagina.setAttribute("onload","carregaJSON()");
//Função para receber dados do BackEnd no formato JSON através de uma requisição GET do Servlet Java
function carregaJSON() {
	let url = "carteirarest";
	fetch(url, { method: "GET" })
		.then(function(resposta) {
			if (!resposta.ok)
				throw new Error("Erro ao executar requisição: " + resposta.status);
			return resposta.json();
		})
		.then(function(jsonArray) {
			jsonArray.forEach((json) => {
				const linha = document.createElement("tr");
				const col1 = document.createElement("td");
				const col2 = document.createElement("td");
				const col3 = document.createElement("td");
				const col4 = document.createElement("td");
				const col5 = document.createElement("td");
				const col6 = document.createElement("td");
				col1.innerText = json.id;
				col2.innerText = json.nome;
				col3.innerText = json.lucroEsperado;
				col4.innerText = json.prejuisoMaximo;
				col5.innerText = json.perfilDeInvestimento;
				col6.innerHTML = "<button onclick='alterar(this)'>Editar</button>"
				col6.innerHTML += "<button onclick='excluir(this)'>Excluir</button>"
				linha.appendChild(col1);
				linha.appendChild(col2);
				linha.appendChild(col3);
				linha.appendChild(col4);
				linha.appendChild(col5);
				linha.appendChild(col6);
				corpoTabela.appendChild(linha);
			});
		})
		.catch(function(erro) {
			console.error(erro.message);
		});
}

//Funções JavaScript para Alterar e Excluir
function excluir(elemento) {
	//Abre uma janela confirmando a edição
	if (window.confirm("Confirma Exclusão?")) {
		//Envia a requisição de DELETE via XML utilizando o verbo correto "DELETE"
		let xhr = new XMLHttpRequest();
		let id = elemento.parentNode.parentNode.cells[0].innerText;
		xhr.addEventListener("readystatechange", function() {
			if (this.readyState === this.DONE) {
				window.location.reload();
			}
		});
		let url = "carteirarest?id=" + id; //Monta a URL concatenando o ID
		xhr.open("DELETE", url);
		xhr.send(); //Envia a requisição ao Servlet REST
	}
}
function alterar(elemento) {
	//Converte as colunas desta linha da tabela para "EDITÁVEL"
	elemento.parentNode.parentNode.cells[1].setAttribute(
		"contenteditable", "true");
	elemento.parentNode.parentNode.cells[2].setAttribute(
		"contenteditable", "true");
	elemento.parentNode.parentNode.cells[3].setAttribute(
		"contenteditable", "true");
	elemento.parentNode.parentNode.cells[4].setAttribute(
		"contenteditable", "true");
	//Coloca somente um botão "Concluir" nesta linha
	elemento.parentNode.parentNode.cells[5].innerHTML = "<input type='button' value=' Concluir ' onclick='concluirEdicao(this)'/>";
}

//Conclui a edição e envia os dados através do verbo PUT
function concluirEdicao(elemento) {
	let id = "?id=" + elemento.parentNode.parentNode.cells[0].innerText;
	let nome = "&nome="
		+ elemento.parentNode.parentNode.cells[1].innerText;
	let lucro = "&lucro_esperado="
		+ elemento.parentNode.parentNode.cells[2].innerText;
	let prejuiso = "&prejuiso_maximo="
		+ elemento.parentNode.parentNode.cells[3].innerText;
	let perfil = "&perfil_investimento="
		+ elemento.parentNode.parentNode.cells[4].innerText;
	let xhr = new XMLHttpRequest();
	xhr.addEventListener("readystatechange", function() {
		if (this.readyState === this.DONE) {
			window.location.reload();
		}
	});
	let url = "carteirarest" + id + nome + lucro + prejuiso + perfil; //Monta a URL concatenando os campos
	xhr.open("PUT", url); //Define o verbo REST correto "PUT"
	xhr.send(); //Envia a requisição ao Servlet REST
}