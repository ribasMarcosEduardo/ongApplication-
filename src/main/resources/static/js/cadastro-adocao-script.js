document.addEventListener('DOMContentLoaded', () => {

    const modais = {
        animais: bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalAnimais')),
        usuarios: bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalUsuarios'))
    };

    // --- LÓGICA PARA SELEÇÃO DE ANIMAL ---
    const tabelaAnimaisBody = document.querySelector('#tabelaAnimaisBody');
    if (tabelaAnimaisBody) {
        tabelaAnimaisBody.addEventListener('click', function (event) {
            const target = event.target.closest('.btn-selecionar-animal');
            if (target) {
                document.querySelector('#animalId').value = target.dataset.id;
                document.querySelector('#animalNome').value = target.dataset.nome;
                modais.animais.hide();
            }
        });
    }

    // --- LÓGICA PARA FILTRAR ANIMAL ---
    const pesquisaAnimalInput = document.querySelector('#pesquisaAnimalInput');
    if (pesquisaAnimalInput) {
        pesquisaAnimalInput.addEventListener('keyup', () => {
            const termoBusca = pesquisaAnimalInput.value.toLowerCase();
            document.querySelectorAll('#tabelaAnimaisBody tr').forEach(linha => {
                const nome = linha.cells[0].textContent.toLowerCase();
                linha.style.display = nome.includes(termoBusca) ? '' : 'none';
            });
        });
    }

    // --- LÓGICA PARA SELEÇÃO DE USUÁRIO ---
    const tabelaUsuariosBody = document.querySelector('#tabelaUsuariosBody');
    if (tabelaUsuariosBody) {
        tabelaUsuariosBody.addEventListener('click', function (event) {
            const target = event.target.closest('.btn-selecionar-usuario');
            if (target) {
                document.querySelector('#usuarioId').value = target.dataset.id;
                document.querySelector('#usuarioNome').value = target.dataset.nome;
                modais.usuarios.hide();
            }
        });
    }

    // --- LÓGICA PARA FILTRAR USUÁRIO ---
    const pesquisaUsuarioInput = document.querySelector('#pesquisaUsuarioInput');
    if (pesquisaUsuarioInput) {
        pesquisaUsuarioInput.addEventListener('keyup', () => {
            const termoBusca = pesquisaUsuarioInput.value.toLowerCase();
            document.querySelectorAll('#tabelaUsuariosBody tr').forEach(linha => {
                const nome = linha.cells[0].textContent.toLowerCase();
                const cpf = linha.cells[1].textContent.toLowerCase();
                if (nome.includes(termoBusca) || cpf.includes(termoBusca)) {
                    linha.style.display = '';
                } else {
                    linha.style.display = 'none';
                }
            });
        });
    }
});