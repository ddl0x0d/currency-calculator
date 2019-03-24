.DEFAULT_GOAL := build

# Build

.PHONY: clean build build-backend build-frontend

clean:
	(cd backend && ./gradlew clean)
	(cd frontend && yarn clean)

build: build-backend build-frontend

build-backend:
	(cd backend && ./gradlew build)

build-frontend:
	(cd frontend && yarn && yarn build && yarn build:docker)

# Docker Compose

.PHONY: up ps logs down

COMPOSE := docker-compose -f devops/docker-compose.yml

up:
	$(COMPOSE) up -d

ps:
	$(COMPOSE) ps

logs:
	$(COMPOSE) logs -f

down:
	$(COMPOSE) down
