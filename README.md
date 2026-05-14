# Selenium Automation Testing Framework

Ky projekt është një framework automatik testimi enterprise-style i ndërtuar me Java, Selenium, Cucumber, TestNG dhe Maven. Framework-u suporton testime UI, API dhe DB, si dhe raportim automatik profesional për bugs dhe evidenca testimi.

---

# Teknologjitë

- Java 22
- Selenium WebDriver
- Cucumber
- TestNG
- Maven
- REST Assured
- Apache POI
- Extent Reports
- Allure Reports
- Log4j2
- JDBC
- Docker
- Selenium Grid
- Healenium

---

# Funksionalitetet Kryesore

- UI Automation Testing
- API Automation Testing
- Database Validation Testing
- Parallel Execution
- Retry Mechanism
- Page Object Model
- Excel Data Driven Testing
- Screenshot Evidence
- Word Bug Report Generation
- Extent HTML Reports
- Allure Integration
- Logging System
- Selenium Grid Execution
- Docker Execution
- GitHub Actions CI/CD
- Environment Profiles
- Hybrid UI + API + DB Validation

---

# Struktura e Projektit

```text
src/test/java
├── core
│   ├── api
│   ├── assertions
│   ├── config
│   ├── context
│   ├── db
│   ├── driver
│   ├── hooks
│   ├── testdata
│   ├── utils
│   └── validations
├── pages
├── runners
└── stepdefinitions

src/test/resources
├── features
├── testdata
├── config-test.properties
├── config-uat.properties
└── log4j2.xml