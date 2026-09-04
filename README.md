# ShopScrap

ShopScrap is a price-comparison platform. It collects product listings from multiple retailers into one searchable catalog so shoppers can compare prices, then links out to the original seller to buy.

The core catalog is working: domain model, REST API, PostgreSQL persistence, Flyway migrations, and a full test suite. Scraping, search, and AWS deployment are next — see roadmap below.

## Architecture

The backend uses hexagonal architecture (ports & adapters). Business logic depends only on the domain; the database — and later the scrapers, search engine, cache, and auth provider — sit behind ports so they can be swapped through Spring configuration. The goal is to be able to change infrastructure, even cloud providers (AWS ↔ Azure), without touching business logic.

Request flow:
```
REST controller -> use-case port -> service -> repository port -> JPA adapter -> PostgreSQL
```

Update Flow:
- Every schema change is a Flyway migration; Hibernate runs in `validate` mode, so schema drift fails at startup instead of at runtime.
- Features are built as vertical slices (domain → database → endpoint) and tested before moving on.

## Tech stack

- Java 21, Spring Boot 4, Maven
- PostgreSQL 16, Spring Data JPA (Hibernate), Flyway
- JUnit 5, Mockito, Testcontainers
- Docker Compose for local development
- React + Vite frontend (in progress)
- Planned: AWS (ECS Fargate, RDS, S3 + CloudFront, OpenSearch, ElastiCache/Redis, SQS), Terraform

## Running locally

Needs JDK 21, Docker, and Maven.

```bash
docker compose up -d # Starts PostgreSQL.
cd backend
mvn spring-boot:run # Flyway migrates the schema on startup.
```

## Tests

```bash
cd backend
mvn verify
```
Unit tests use Mockito with no Spring context. Integration tests use Testcontainers to start a real PostgreSQL container and replay the Flyway migrations.

## Roadmap

- [x] Product catalog — domain, REST API, persistence, migrations, tests
- [x] CI — GitHub Actions running `mvn verify` on every push
- [ ] Retailer scraping adapters (Jsoup, Playwright) with SQS job queues
- [ ] OpenSearch product search
- [ ] Redis caching for trending products
- [ ] User accounts and watchlists
- [ ] AWS deployment with Terraform

## Copyright

© 2026 David Simonov. All rights reserved.

This code is public for portfolio review and hiring evaluation. You're welcome to read it and run it locally to evaluate my work. No license is granted for any other use without my written consent.
