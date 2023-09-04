var dataAtual = new Date().toISOString().split('T')[0];
var dataNova = document.getElementById('data').value;
document.getElementById("data").min = dataAtual;


function validarHorario() {
    var horaSelecionada = document.getElementById("hora").value;
    var horaAtual = new Date().toLocaleTimeString('en-US', {hour12: false});

    if (horaSelecionada < horaAtual && dataNova <= dataAtual) {
        alert("Por favor, selecione um horário válido.");
        document.getElementById("hora").value = horaAtual;
    }
}

document.getElementById("hora").addEventListener("change", validarHorario);