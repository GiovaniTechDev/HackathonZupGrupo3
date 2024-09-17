// JavaScript for Accessibility Features

// Toggle Accessibility Menu
function toggleAccessibilityMenu() {
    const menu = document.getElementById('accessibility-menu');
    menu.classList.toggle('active');
}

// Change Font Size
function changeFontSize(size) {
    const root = document.documentElement;
    if (size === 'small') {
        root.style.fontSize = '90%';
    } else if (size === 'large') {
        root.style.fontSize = '110%';
    } else {
        root.style.fontSize = '100%';
    }
}

// Toggle High Contrast (Black and White)
function toggleContrast() {
    document.body.classList.toggle('high-contrast');
}

// Toggle Dark Mode
function toggleDarkMode() {
    document.body.classList.toggle('dark-mode');
}

// Invert Colors
function toggleInvertColors() {
    document.body.classList.toggle('invert-colors');
}

// Read Text Aloud
function readText(elementId) {
    const element = document.getElementById(elementId);
    let text = '';

    if (element.tagName.toLowerCase() === 'label') {
        text = element.textContent;
    } else {
        text = element.innerText || element.textContent;
    }

    const utterance = new SpeechSynthesisUtterance(text);
    window.speechSynthesis.speak(utterance);
}

// Accessibility CSS Classes Handling
document.addEventListener('DOMContentLoaded', () => {
    // High Contrast Mode
    const highContrastCSS = `
        body.high-contrast {
            filter: grayscale(100%);
        }
    `;

    // Dark Mode
    const darkModeCSS = `
        body.dark-mode {
            background-color: #121212;
            color: #e0e0e0;
        }
        body.dark-mode .header,
        body.dark-mode .footer {
            background-color: #1e1e1e;
        }
        body.dark-mode input,
        body.dark-mode select,
        body.dark-mode textarea {
            background-color: #2e2e2e;
            color: #e0e0e0;
            border-color: #555;
        }
    `;

    // Invert Colors
    const invertColorsCSS = `
        body.invert-colors {
            filter: invert(100%);
        }
    `;

    // Append Styles to Head
    const styleSheet = document.createElement('style');
    styleSheet.type = 'text/css';
    styleSheet.innerText = highContrastCSS + darkModeCSS + invertColorsCSS;
    document.head.appendChild(styleSheet);
});


function readText(elementId) {
    // Seleciona o elemento pelo ID passado no argumento
    var element = document.getElementById(elementId);

    // Verifica se o elemento existe
    if (element) {
        // Captura o texto associado ao elemento
        var text = element.innerText || element.textContent;

        // Cria um objeto SpeechSynthesis para fazer a leitura em voz alta
        var speech = new SpeechSynthesisUtterance();
        speech.text = text;
        speech.lang = 'pt-BR'; // Define o idioma (português)

        // Inicia a leitura
        window.speechSynthesis.speak(speech);
    }
}

// Função de validação
function validateForm(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    // Esconder todos os alertas antes de qualquer validação
    hideAlerts();

    // Coletar valores dos campos
    var nome = document.getElementById('nome').value.trim();
    var dataNascimento = document.getElementById('data-nascimento').value.trim();
    var sexo = document.getElementById('sexo').value;
    var nacionalidade = document.getElementById('nacionalidade').value;

    // Verifica se os campos estão vazios
    if (!nome || !dataNascimento || !sexo || !nacionalidade) {
        document.getElementById('validationAlert').style.display = 'block';
        return false;
    }

    // Sucesso, todos os campos foram preenchidos
    document.getElementById('successAlert').style.display = 'block';
    return true;
}

// Função para esconder todos os alertas
function hideAlerts() {
    document.getElementById('successAlert').style.display = 'none';
    document.getElementById('errorAlert').style.display = 'none';
    document.getElementById('validationAlert').style.display = 'none';
}
