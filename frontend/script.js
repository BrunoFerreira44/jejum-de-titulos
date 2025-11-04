const lista = document.getElementById('listaTimes');
const menuButtons = document.querySelectorAll('.menu button');
const toggle = document.getElementById('toggle');

let currentEndpoint = '/api/geral-sem-estadual';
let considerarEstadual = false;

async function carregarTimes(endpoint) {
  try {
    lista.innerHTML = '<li>Carregando dados...</li>';
    const response = await fetch(`http://localhost:8080${endpoint}`);
    if (!response.ok) throw new Error('Erro ao buscar dados');
    const data = await response.json();

    // let data = [
    //   {
    //     "time": "Palmeiras",
    //     "campeonato": "Libertadores",
    //     "imgUrl": "https://upload.wikimedia.org/wikipedia/commons/1/10/Palmeiras_logo.svg",
    //     "qtdDiasSemTitulo": "654"
    //   },
    //   {
    //     "time": "Cortinas",
    //     "campeonato": "Libertadores",
    //     "imgUrl": "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Cruzeiro_Esporte_Clube_%28logo%29.svg/474px-Cruzeiro_Esporte_Clube_%28logo%29.svg.png",
    //     "qtdDiasSemTitulo": "5214"
    //   },
    // ];

    data.sort((a, b) => Number(b.qtdDiasSemTitulo) - Number(a.qtdDiasSemTitulo));

    lista.innerHTML = data.map(item => `
      <li>
        <div class="team-container">
          <div>
            <img src="${item.imgUrl}" class="img-time">
          </div>
          <div>
            <div class="team">${item.time}</div>
            <div class="champ">${item.campeonato}</div>
          </div>
        </div>
        <div class="days-container">
          <div class="days">${item.qtdDiasSemTitulo}</div>
          <div class="days-complement">dias</div>
        </div>
      </li>
    `).join('');
  } catch (err) {
    lista.innerHTML = `<li>Erro ao carregar: ${err.message}</li>`;
  }
}

// Controle do menu lateral
menuButtons.forEach(btn => {
  btn.addEventListener('click', () => {
    menuButtons.forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    currentEndpoint = btn.dataset.endpoint;
    carregarTimes(currentEndpoint);
  });
});

// Toggle "Considerar estadual"
toggle.addEventListener('click', () => {
  considerarEstadual = !considerarEstadual;
  toggle.classList.toggle('active');

  // Se ativo → /api/geral | se não → /api/geral-sem-estadual
  currentEndpoint = considerarEstadual ? '/api/geral' : '/api/geral-sem-estadual';
  carregarTimes(currentEndpoint);
});

// Carrega inicial
carregarTimes(currentEndpoint);
