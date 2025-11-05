# ⚽ Jejum de Títulos 🏆  
**Contagem de dias sem título dos 12 maiores clubes do futebol brasileiro.**  
Aplicação **Spring Boot + MySQL + HTML/CSS/JS (Frontend via Nginx)**, containerizada com **Docker Compose**.

---

## 📖 Sobre o projeto

O **Jejum de Títulos** é uma aplicação que calcula, em tempo real, há quantos dias cada clube brasileiro está sem conquistar um título.  
O sistema considera diferentes competições (como **Brasileirão**, **Copa do Brasil**, **Libertadores** e **Estaduais**) e exibe os dados de forma visual e simples.

---

## 🧩 Tecnologias utilizadas

**Backend:**
- ☕ Java 17  
- 🧱 Spring Boot 3  
- 🐬 MySQL  

**Frontend:**
- 🌐 HTML, CSS e JavaScript puro  
- 🖥️ Servido via Nginx

**Infraestrutura:**
- 🐳 Docker & Docker Compose  
- 📦 Multi-container setup (backend + frontend + banco de dados)

---

## 🏗️ Estrutura do projeto

```
jejum-de-titulos/
│
├── backend/
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/
│       ├── main/java/br/com/bruno/ultimos_campeoes/
│       │   ├── controller/TitulosController.java
│       │   ├── service/TitulosService.java
│       │   ├── repository/TitulosRepository.java
│       │   └── domain/Titulos.java
│       └── resources/
│           ├── application.properties
│           └── application-docker.properties
│
├── frontend/
│   ├── Dockerfile
│   ├── index.html
│   ├── script.js
│   └── style.css
│
└── docker-compose.yml
```

---

## 🚀 Como executar

### 🧠 Pré-requisitos
- Docker e Docker Compose instalados
- Java 17 e Maven

---

### 🛠️ Passos para execução

1️⃣ **Clone o repositório:**
```bash
git clone https://github.com/BrunoFerreira44/jejum-de-titulos.git
cd jejum-de-titulos
```

2️⃣ **Builde a aplicação**
```bash
cd backend
mvn clean package -DskipTests
```
> Isso irá gerar o arquivo `.jar` dentro de `backend/target/`.  

3️⃣ **Suba todos os serviços com Docker Compose:**
```bash
docker-compose up --build -d
```

4️⃣ **Acesse a aplicação:**
- 🌍 Frontend: [http://localhost:3000](http://localhost:3000)  
- ⚙️ Backend API: [http://localhost:8080/api](http://localhost:8080/<endpoint>)  
- 🐬 Banco de dados: `mysql` (acessível via `localhost:3306`)

---

## 📡 Endpoints disponíveis

| Método | Endpoint | Descrição |
|:------:|:----------|:----------|
| `GET` | `/api/geral` | Retorna dias sem título (todas as competições) |
| `GET` | `/api/geral-sem-estadual` | Retorna dias sem título excluindo estaduais |
| `GET` | `/api/libertadores` | Dias sem título da Libertadores |
| `GET` | `/api/cdb` | Dias sem título da Copa do Brasil |
| `GET` | `/api/brasileirao` | Dias sem título do Brasileirão |
| `GET` | `/api/estadual` | Dias sem título dos estaduais |

---

## 🧮 Arquitetura dos containers

O arquivo `docker-compose.yml` orquestra três serviços principais:

| Serviço | Porta | Descrição |
|----------|-------|-----------|
| `frontend` | 3000 | Servidor Nginx com HTML/CSS/JS |
| `backend` | 8080 | API Spring Boot |
| `mysql` | 3306 | Banco de dados relacional |

Todos compartilham uma **mesma rede Docker bridge**, permitindo comunicação via nomes de serviço (`backend`, `mysql`).

---

## ⚙️ Variáveis de ambiente

No ambiente Docker, o Spring Boot usa o perfil `docker`:
```properties
spring.profiles.active=docker
spring.datasource.url=jdbc:mysql://mysql:3306/ultimos_campeoes?createDatabaseIfNotExist=true
```

---

## 🔥 Dicas úteis

💡 **Atualizar containers após mudança no código:**
```bash
docker-compose down
docker-compose up --build
```

💡 **Ver logs do backend:**
```bash
docker logs -f backend
```

💡 **Executar comandos dentro do container MySQL:**
```bash
docker exec -it mysql mysql -u bfer -p
```

---


## 💬 Autor

👤 **Bruno Ferreira**  
📧 [brunoferreira1999@live.com](mailto:brunoferreira1999@live.com)  
💻 [GitHub: BrunoFerreira44](https://github.com/BrunoFerreira44)

