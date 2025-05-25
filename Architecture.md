# Conference Management System Architecture

## Overview
This project implements a conference management system using Domain-Driven Design (DDD) and Hexagonal Architecture principles. The system is designed to handle attendee registration and management for conferences, with integration points to other systems.

## Architectural Style
The application follows a Hexagonal Architecture (also known as Ports and Adapters) within a DDD context:

- **Domain Layer**: Contains the core business logic, entities, value objects, and domain services
- **Application Layer**: Orchestrates the use cases of the application
- **Infrastructure Layer**: Provides implementations for persistence, API endpoints, and integration with external systems
- **Anti-Corruption Layer**: Translates between our domain model and external systems

## Key Components

### Domain Layer
- **Aggregates**: `Attendee` - the main aggregate root
- **Value Objects**: `Name`, `Email`, `Badge`, `Address`, `TShirtSize`
- **Domain Services**: `AttendeeService` - handles business operations on attendees

### Application Layer
- **Commands**: `RegisterAttendeeCommand` - represents intent to register an attendee
- **Events**: `AttendeeRegistrationEvent` - domain events triggered by registration

### Infrastructure Layer
- **Persistence**: JPA entities and repositories for storing attendee data
- **API Endpoints**: REST endpoints for attendee registration and lookup
- **Event Publishers**: Components that publish domain events

### Anti-Corruption Layer
- **Salesteam Integration**: Translates between the sales team's model and our domain model

## Technology Stack
- **Framework**: Quarkus
- **Persistence**: JPA/Hibernate with Panache
- **API**: JAX-RS REST endpoints
- **Messaging**: Reactive messaging for event handling
- **Containerization**: Docker with OpenShift deployment support
- **Build Tool**: Maven

## Bounded Contexts
The system is organized around the following bounded contexts:
- **Attendees**: Manages registration and information about conference attendees
- **Shared Kernel**: Contains shared components used across bounded contexts

## Integration Points
- **Sales Team System**: Integration via an anti-corruption layer to register attendees from external sales data

## Deployment
The application is designed to be deployed on OpenShift 4.18, with various container options available (JVM, native, etc.).
