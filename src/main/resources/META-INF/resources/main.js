// Recupera el contador desde localStorage o empieza en 0
let requestCounter = Number(localStorage.getItem('requestCounter') || 0);
const chatHistory = document.getElementById('chatHistory');
const mensajeForm = document.getElementById('mensajeForm');
const input = document.getElementById('input');

// Añadir mensaje al chat (ya no guarda en localStorage)
function addMessage(text, sender) {
    const msgDiv = document.createElement('div');
    msgDiv.className = 'message ' + sender;
    msgDiv.textContent = text;
    chatHistory.appendChild(msgDiv);
    chatHistory.scrollTop = chatHistory.scrollHeight;
}

mensajeForm.addEventListener('submit', async function (e) {
    e.preventDefault();
    const inputTexto = input.value.trim();
    if (!inputTexto) return;

    addMessage(inputTexto, 'user');
    input.value = '';
    input.disabled = true;

    // Incrementa el contador y guárdalo en localStorage
    const jsonData = {
        type: "taskRequest",
        id: "req-" + (++requestCounter),
        role: "user",
        input: inputTexto,
        timestamp: new Date().toISOString()
    };
    localStorage.setItem('requestCounter', requestCounter);

    try {
        const response = await fetch('/mensaje', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonData)
        });

        const data = await response.json();
        addMessage(JSON.stringify(data, null, 2), 'bot');
    } catch (err) {
        addMessage('Error al enviar el mensaje.', 'bot');
    } finally {
        input.disabled = false;
        input.focus();
    }
});