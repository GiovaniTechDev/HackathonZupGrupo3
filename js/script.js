// Função para alternar o menu de acessibilidade
function toggleAccessibilityMenu() {
    const menu = document.getElementById('accessibility-menu');
    menu.style.display = menu.style.display === 'block' ? 'none' : 'block';
}

// Função para alterar o tamanho da fonte
function changeFontSize(size) {
    const body = document.body;
    switch(size) {
        case 'small':
            body.style.fontSize = '14px';
            break;
        case 'large':
            body.style.fontSize = '18px';
            break;
        case 'default':
            body.style.fontSize = '';
            break;
    }
}

// Função para alternar o contraste preto e branco
function toggleContrast() {
    const body = document.body;
    if (body.classList.contains('high-contrast')) {
        body.classList.remove('high-contrast');
    } else {
        body.classList.add('high-contrast');
    }
}

// Função para alternar o modo noturno
function toggleDarkMode() {
    const body = document.body;
    if (body.classList.contains('dark-mode')) {
        body.classList.remove('dark-mode');
    } else {
        body.classList.add('dark-mode');
    }
}

// Função para inverter as cores
function toggleInvertColors() {
    const body = document.body;
    if (body.classList.contains('invert-colors')) {
        body.classList.remove('invert-colors');
    } else {
        body.classList.add('invert-colors');
    }
}

// Adiciona evento para clicar fora do menu e fechá-lo
document.addEventListener('click', function(event) {
    const menu = document.getElementById('accessibility-menu');
    const button = document.querySelector('.accessibility-icon');

    if (!menu.contains(event.target) && !button.contains(event.target)) {
        menu.style.display = 'none';
    }
});
