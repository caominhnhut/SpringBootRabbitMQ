# Spring Boot RabbitMQ Example

A Spring Boot application demonstrating RabbitMQ integration for message queue processing.

## Prerequisites

- Java 17
- Maven
- Docker and Docker Compose

## Project Structure

This project consists of two Spring Boot applications:

1. Producer Application (Port: 8086)
   - Produces RabbitMQ messages
   - Contains the test endpoint for message publishing
   - Located in `/producer` module

2. Consumer Application (Port: 8080)
   - Consumes RabbitMQ messages
   - Located in `/projector` module

## Setup

1. Start RabbitMQ using Docker Compose:
```bash
docker compose up -d
```

This will start:
- RabbitMQ Server
- Management UI (accessible at http://localhost:15672)
  - Default credentials: guest/guest

2. Set up RabbitMQ components:
   - Login to RabbitMQ Management UI (http://localhost:15672)
   - Create Exchange: `test_exchange` (type: direct)
   - Create Queue: `test_queue`
   - Create Binding between:
     - Exchange: `test_exchange`
     - Queue: `test_queue`
     - Routing Key: `test_routing_key`

3. Build the project:
```bash
mvn clean install
```

4. Run both applications with desired profile:
```bash
# Start Producer (Port 8086)
cd producer
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Start Projector (Port 8080)
cd projector
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

## Configuration

The application uses Spring profiles for different environments:
- `dev` - Development environment
- `uat` - UAT environment

Each profile has its own configuration in:
- `application-dev.yaml`
- `application-uat.yaml`

## Test API Endpoint

The producer application provides a test endpoint to publish messages to RabbitMQ:

```bash
POST http://localhost:8086/api/v1/publish
```

This endpoint accepts a JSON payload with the following structure:
```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe"
}
```

Example curl command:
```bash
curl -X POST http://localhost:8086/api/v1/publish \
     -H "Content-Type: application/json" \
     -d '{"id":1,"firstName":"John","lastName":"Doe"}'
```

## RabbitMQ Configuration

The application uses the following RabbitMQ components that must be created manually in RabbitMQ Management UI before running the applications:

- Exchange: `test_exchange` (type: direct)
- Queue: `test_queue`
- Binding: 
  - From: `test_exchange`
  - To: `test_queue`
  - Routing Key: `test_routing_key`

You can create these components through:
1. RabbitMQ Management UI (http://localhost:15672)
2. RabbitMQ CLI commands
3. Programmatically using Spring AMQP (recommended for production)

### Manual Setup via Management UI
1. Create Exchange:
   - Go to "Exchanges" tab
   - Add new exchange named "test_exchange"
   - Set type as "direct"

2. Create Queue:
   - Go to "Queues" tab
   - Add new queue named "test_queue"

3. Create Binding:
   - Go to queue "test_queue"
   - Under "Bindings" section
   - Select "test_exchange" and set routing key to "test_routing_key"

## Technologies

- Spring Boot
- RabbitMQ
- Docker
- Maven
- Lombok
