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