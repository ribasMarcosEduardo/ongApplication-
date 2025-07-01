document.addEventListener('DOMContentLoaded', () => {

    const prontuarioState = {
        doencasSelecionadas: new Map(),
        vacinasSelecionadas: new Map()
    };

    const modais = {
        animais: bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalAnimais')),
        doencas: bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalDoencas')),
        vacinas: bootstrap.Modal.getOrCreateInstance(document.querySelector('#modalVacinas'))
    };

    // --- FUNÇÕES DE ATUALIZAÇÃO DE TELA ---
    function atualizarTabelaDoencas() {
        const tbody = document.querySelector('#tabelaDoencasSelecionadas tbody');
        tbody.innerHTML = '';
        prontuarioState.doencasSelecionadas.forEach((d) => {
            tbody.innerHTML += `<tr>
                <td>${d.cid}</td>
                <td>${d.nome}</td>
                <td><button type="button" class="btn btn-danger btn-sm btn-remover-doenca" data-id="${d.id}">Remover</button></td>
            </tr>`;
        });
        atualizarHiddenInputs();
    }

    function atualizarTabelaVacinas() {
        const tbody = document.querySelector('#tabelaVacinasSelecionadas tbody');
        tbody.innerHTML = '';
        prontuarioState.vacinasSelecionadas.forEach((v) => {
            tbody.innerHTML += `<tr>
                <td>${v.nome}</td>
                <td>${v.codigo || ''}</td>
                <td><button type="button" class="btn btn-danger btn-sm btn-remover-vacina" data-id="${v.id}">Remover</button></td>
            </tr>`;
        });
        atualizarHiddenInputs();
    }

    function atualizarHiddenInputs() {
        const container = document.querySelector('#hidden-inputs-container');
        let inputsHTML = '';
        prontuarioState.doencasSelecionadas.forEach(d => {
            inputsHTML += `<input type="hidden" name="doencaIds" value="${d.id}">`;
        });
        prontuarioState.vacinasSelecionadas.forEach(v => {
            inputsHTML += `<input type="hidden" name="vacinaIds" value="${v.id}">`;
        });
        container.innerHTML = inputsHTML;
    }

    // --- LÓGICA DE INICIALIZAÇÃO PARA EDIÇÃO ---
    function inicializarFormularioDeEdicao() {
        if (!prontuarioData || !prontuarioData.id || prontuarioData.id === 0) return;

        if (prontuarioData.doencaIds && todasAsDoencas) {
            prontuarioData.doencaIds.forEach(id => {
                const doenca = todasAsDoencas.find(d => d.id === id);
                if (doenca) prontuarioState.doencasSelecionadas.set(id, doenca);
            });
            atualizarTabelaDoencas();
        }

        if (prontuarioData.vacinaIds && todasAsVacinas) {
            prontuarioData.vacinaIds.forEach(id => {
                const vacina = todasAsVacinas.find(v => v.id === id);
                if (vacina) prontuarioState.vacinasSelecionadas.set(id, vacina);
            });
            atualizarTabelaVacinas();
        }
    }

    // --- OUVINTES DE EVENTOS (EVENT LISTENERS) ---

    // Ouve cliques no corpo do modal de animais
    document.querySelector('#modalAnimais .modal-body').addEventListener('click', function (event) {
        const target = event.target.closest('.btn-selecionar-animal');
        if (target) {
            document.querySelector('#animalId').value = target.dataset.id;
            document.querySelector('#animalNome').value = target.dataset.nome;
            modais.animais.hide();
        }
    });

    // Ouve cliques no corpo do modal de doenças
    document.querySelector('#modalDoencas .modal-body').addEventListener('click', function (event) {
        const target = event.target.closest('.btn-selecionar-doenca');
        if (target) {
            const id = parseInt(target.dataset.id, 10);
            if (prontuarioState.doencasSelecionadas.has(id)) {
                alert('Esta doença já foi adicionada.');
                return;
            }
            prontuarioState.doencasSelecionadas.set(id, {
                id: id,
                cid: target.dataset.cid,
                nome: target.dataset.nome
            });
            atualizarTabelaDoencas();
            modais.doencas.hide();
        }
    });

    // Ouve cliques na tabela de doenças selecionadas para remover
    document.querySelector('#tabelaDoencasSelecionadas').addEventListener('click', function (event) {
        const target = event.target.closest('.btn-remover-doenca');
        if (target) {
            const id = parseInt(target.dataset.id, 10);
            prontuarioState.doencasSelecionadas.delete(id);
            atualizarTabelaDoencas();
        }
    });

    // Ouve cliques no corpo do modal de vacinas
    document.querySelector('#modalVacinas .modal-body').addEventListener('click', function (event) {
        const target = event.target.closest('.btn-selecionar-vacina');
        if (target) {
            const id = parseInt(target.dataset.id, 10);
            if (prontuarioState.vacinasSelecionadas.has(id)) {
                alert('Esta vacina já foi adicionada.');
                return;
            }
            prontuarioState.vacinasSelecionadas.set(id, {
                id: id,
                nome: target.dataset.nome,
                codigo: target.dataset.codigo
            });
            atualizarTabelaVacinas();
            modais.vacinas.hide();
        }
    });

    // Ouve cliques na tabela de vacinas selecionadas para remover
    document.querySelector('#tabelaVacinasSelecionadas').addEventListener('click', function (event) {
        if (event.target.classList.contains('btn-remover-vacina')) {
            const id = parseInt(event.target.dataset.id, 10);
            prontuarioState.vacinasSelecionadas.delete(id);
            atualizarTabelaVacinas();
        }
    });

    // Filtro de pesquisa de animal
    const pesquisaInput = document.querySelector('#pesquisaAnimalInput');
    if (pesquisaInput) {
        pesquisaInput.addEventListener('keyup', () => {
            const termoBusca = pesquisaInput.value.toLowerCase();
            document.querySelectorAll('#tabelaAnimaisBody tr').forEach(linha => {
                const nomeAnimal = linha.cells[0].textContent.toLowerCase();
                linha.style.display = nomeAnimal.includes(termoBusca) ? '' : 'none';
            });
        });
    }

    // Inicializa o formulário (seja para edição ou não)
    inicializarFormularioDeEdicao();
});