# IFSP - Multiple Sources Dynamic Repository Switching

Aplicativo Android que demonstra a troca dinâmica de fontes de dados (Repositórios) em tempo de execução usando **Clean Architecture**, **Kotlin Flow**, **Compose UI**, **Room**, **Retrofit**, **Firebase Firestore** e **Koin**.

## 🎯 Funcionalidades

- Listagem de transações financeiras.
- Troca da fonte de dados através da tela de configurações:
  - **Local (Room)** – Banco de dados SQLite.
  - **Remoto (API REST)** – Consumo de uma API mock via Retrofit (JSON Server ou Mocky.io).
  - **Firebase Firestore** – Armazenamento em nuvem do Firebase.
- Persistência da última fonte escolhida com **DataStore**.
- Injeção de dependência com **Koin**.
- Interface totalmente em **Jetpack Compose**.

## 🧱 Arquitetura

O projeto segue os princípios da **Clean Architecture** com separação de camadas:

- **Data**: Repositórios, fontes de dados locais/remotas, DTOs, DAOs.
- **Domain**: Entidades (`Transaction`) e contratos dos repositórios.
- **Presentation**: ViewModels e telas Compose.
- **DI**: Koin modules (`AppModule`).

### Camadas de repositório

Um `RepositoryProvider` centraliza os três repositórios e expõe um `StateFlow<TransactionRepository>` que é observado pelo `TransactionViewModel`. Ao trocar a fonte no Settings, o provider atualiza o fluxo e a UI reage automaticamente.

## 🛠️ Tecnologias e bibliotecas

| Categoria                     | Bibliotecas                                   |
| ----------------------------- | --------------------------------------------- |
| UI                            | Jetpack Compose (Material 3), Coil (imagens)  |
| Injeção de dependência        | Koin (koin-androidx-compose)                  |
| Banco local                   | Room, Kotlin Flow                             |
| API REST                      | Retrofit, Gson Converter, Coroutines          |
| Firebase                      | Firestore, Auth (BOM), google-services plugin |
| Persistência de configurações | DataStore Preferences                         |
| Testes                        | JUnit, Espresso, Compose UI Test              |

## 📋 Pré-requisitos

- Android Studio **Panda 4** (2025.3.4) ou superior.
- JDK 11 ou 17.
- Android SDK com API level **36** (compileSdk e targetSdk).
- Emulador ou dispositivo físico com Android 7.0 (API 24) ou superior.

## 🚀 Como executar

1. **Clone o repositório**:

```bash
   git clone  https://github.com/seuusuario/Ifsp-MultipleSourcesDynamicRepositorySwitching.git

   cd Ifsp-MultipleSourcesDynamicRepositorySwitching
```

2. **Crie um arquivo db.json com o conteúdo:**:
   ```bash
   {
   "transactions": [
    {
      "id": "1",
      "description": "Salário",
      "amount": 3500.00,
      "timestamp": 1704067200000
    },
    {
      "id": "2",
      "description": "Aluguel",
      "amount": -1200.00,
      "timestamp": 1704153600000
    }
   ]
   }
   ```

---

# 📁 Estrutura do Projeto

```text

biz.fabiotecnico1.ifsp_multiplesourcesdynamicrepositoryswitching
├── data
│   ├── local          // Room entities, DAOs, Database
│   ├── remote         // Retrofit, API service, DTOs
│   ├── firebase       // Firestore data source
│   └── repositoryimpl // Implementações concretas dos repositórios
├── domain
│   ├── model          // Transaction
│   └── repository     // Interfaces dos repositórios
├── presentation
│   ├── screens        // Composables (TransactionList, Settings)
│   └── viewmodel      // ViewModels
├── datasource         // DataStoreManager
├── repositoryprovider // RepositoryProvider (switch entre fontes)
├── di                 // Koin modules
└── ui.theme           // Tema Compose
```

## Backend

- Node.js

## 1 - Clonar Repositório

```bash
git clone  https://github.com/seuusuario/Ifsp-MultipleSourcesDynamicRepositorySwitching.git

cd Ifsp-MultipleSourcesDynamicRepositorySwitching
```

---

# 📲 Contato / Contact

<a href="https://www.linkedin.com/in/f%C3%A1bio-lu%C3%ADs-guia-da-concei%C3%A7%C3%A3o-77784741/"><img src="https://img.shields.io/badge/linkedin%20-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn"/></a>

---

<h5 align="center">
  &copy;06/2026 - <a href="https://github.com/Flgc/">Fábio Luis</a>
</h5>
