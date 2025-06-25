// Objeto para guardar o estado dos itens selecionados
const prontuarioState = {
    doencasSelecionadas: [],
    vacinasSelecionadas: []
};

// Função de atalho para document.querySelector
const $ = (selector) => document.querySelector(selector);


// --- LÓGICA PARA ANIMAIS ---
function selecionarAnimal(id, nome) {
    console.log(`Função selecionarAnimal chamada com ID: ${id}, Nome: ${nome}`);
    try {
        const animalIdInput = $('#animalId');
        const animalNomeInput = $('#animalNome');

        animalIdInput.value = id;
        animalNomeInput.value = nome;

        // Remove a classe de erro ao selecionar um animal
        animalNomeInput.classList.remove('is-invalid');

        const modalElement = $('#modalAnimais');
        const modal = bootstrap.Modal.getInstance(modalElement);
        modal.hide();
    } catch (e) {
        console.error("ERRO DENTRO DE selecionarAnimal:", e);
    }
}

function filtrarAnimais() {
    const termoBusca = $('#pesquisaAnimalInput').value.toLowerCase();
    const linhasTabela = document.querySelectorAll('#tabelaAnimaisBody tr');
    linhasTabela.forEach(linha => {
        const nomeAnimal = linha.cells[0].textContent.toLowerCase();
        if (nomeAnimal.includes(termoBusca)) {
            linha.style.display = '';
        } else {
            linha.style.display = 'none';
        }
    });
}


// --- LÓGICA PARA DOENÇAS ---
function removerDoenca(index) {
    prontuarioState.doencasSelecionadas.splice(index, 1);
    atualizarTabelaDoencas();
}

function selecionarDoenca(id, cid, nome) {
    if (prontuarioState.doencasSelecionadas.some(d => d.id === id)) {
        alert('Esta doença já foi adicionada.');
        return;
    }
    prontuarioState.doencasSelecionadas.push({ id, cid, nome });
    atualizarTabelaDoencas();
    bootstrap.Modal.getInstance($('#modalDoencas')).hide();
}

function atualizarTabelaDoencas() {
    const tbody = $('#tabelaDoencasSelecionadas tbody');
    tbody.innerHTML = prontuarioState.doencasSelecionadas.map((d, index) => `
        <tr>
            <td>${d.cid}</td>
            <td>${d.nome}</td>
            <td><button type="button" class="btn btn-danger btn-sm" onclick="removerDoenca(${index})">Remover</button></td>
        </tr>
    `).join('');
    atualizarHiddenInputs();
}


// --- LÓGICA PARA VACINAS ---
function removerVacina(index) {
    prontuarioState.vacinasSelecionadas.splice(index, 1);
    atualizarTabelaVacinas();
}

function selecionarVacina(id, nome, codigo) {
    if (prontuarioState.vacinasSelecionadas.some(v => v.id === id)) {
        alert('Esta vacina já foi adicionada.');
        return;
    }
    prontuarioState.vacinasSelecionadas.push({ id, nome, codigo });
    atualizarTabelaVacinas();
    bootstrap.Modal.getInstance($('#modalVacinas')).hide();
}

function atualizarTabelaVacinas() {
    const tbody = $('#tabelaVacinasSelecionadas tbody');
    tbody.innerHTML = prontuarioState.vacinasSelecionadas.map((v, index) => `
        <tr>
            <td>${v.nome}</td>
            <td>${v.codigo}</td>
            <td><button type="button" class="btn btn-danger btn-sm" onclick="removerVacina(${index})">Remover</button></td>
        </tr>
    `).join('');
    atualizarHiddenInputs();
}


// --- LÓGICA COMUM E INICIALIZAÇÃO ---
function atualizarHiddenInputs() {
    const container = $('#hidden-inputs-container');
    const doencaInputs = prontuarioState.doencasSelecionadas
        .map(d => `<input type="hidden" name="doencaIds" value="${d.id}">`)
        .join('');
    const vacinaInputs = prontuarioState.vacinasSelecionadas
        .map(v => `<input type="hidden" name="vacinaIds" value="${v.id}">`)
        .join('');
    container.innerHTML = doencaInputs + vacinaInputs;
}

document.addEventListener('DOMContentLoaded', () => {
    // Event listener para a barra de busca de animais
    const pesquisaInput = $('#pesquisaAnimalInput');
    if (pesquisaInput) {
        pesquisaInput.addEventListener('keyup', filtrarAnimais);
    }

    // --- NOVA LÓGICA DE VALIDAÇÃO DE FORMULÁRIO ---
    const form = $('#form-prontuario');
    if (form) {
        form.addEventListener('submit', function(event) {
            const errors = []; // Lista para armazenar mensagens de erro

            // 1. Validação do campo Animal
            const animalIdInput = $('#animalId');
            const animalNomeInput = $('#animalNome');
            if (!animalIdInput.value || animalIdInput.value === '0') {
                errors.push('O campo "Animal" é obrigatório.');
                animalNomeInput.classList.add('is-invalid'); // Adiciona borda vermelha
            } else {
                animalNomeInput.classList.remove('is-invalid');
            }

            // 2. Validação do campo Castrado
            const castradoChecked = document.querySelector('input[name="castrado"]:checked');
            if (!castradoChecked) {
                 errors.push('É obrigatório informar se o animal é "Castrado".');
            }

            // 3. Se houver erros, impede o envio e mostra o alerta
            if (errors.length > 0) {
                event.preventDefault(); // Impede o envio do formulário
                alert('Por favor, corrija os seguintes erros:\n\n- ' + errors.join('\n- '));
            }
        });
    }

    // Remove as mensagens de sucesso/erro após 3 segundos
    setTimeout(() => document.querySelectorAll('.alert').forEach(alert => alert.remove()), 3000);
});