# Domain-Driven Design & Hexagonal Architecture Workshop

<img src="https://img.shields.io/badge/Status-Active-green" alt="Status: Active">
<img src="https://img.shields.io/badge/Technology-Java%20%7C%20Quarkus-blue" alt="Technology: Java | Quarkus">
<img src="https://img.shields.io/badge/Platform-Red%20Hat%20OpenShift-red" alt="Platform: Red Hat OpenShift">

> A reference implementation of a conference management system using Domain-Driven Design (DDD) and Hexagonal Architecture principles, deployed on Red Hat OpenShift Container Platform.

## 📋 Overview

This project demonstrates the practical application of Domain-Driven Design and Hexagonal Architecture through a conference management system. It serves as both a learning tool and a reference implementation for developers interested in applying these architectural principles using Red Hat's enterprise open source solutions.

**Project Lead:** Jeremy Davis (jeremy.davis@redhat.com)

**References:**
- "Domain Driven Design: Tackling Complexity in the Heart of Software" by Eric Evans
- "Domain Driven Design Distilled" by Vaughn Vernon

## 🎯 Why This Project Exists

### Business Value

- **Competitive Advantage**: Creates distinctive value through domain expertise
- **Customer Satisfaction**: Delivers a tailored experience aligned with business needs
- **Domain Complexity**: Addresses complex business logic that requires specialized knowledge
- **Strategic Asset**: Represents a product (not just a project) that generates ongoing value

### Technical Value

- **Learning Platform**: Demonstrates DDD and Hexagonal Architecture in action
- **Reference Implementation**: Shows best practices for Red Hat technology integration
- **Architectural Showcase**: Illustrates clean separation of concerns and maintainable design
- **Integration Example**: Provides patterns for system integration across bounded contexts

## 🏗️ Architecture

This project follows a hexagonal architecture (ports & adapters) with a focus on domain-driven design principles:

```
                       ┌─────────────────┐
                       │  Domain Layer   │
                       │                 │
┌──────────────┐       │ ┌─────────────┐ │       ┌──────────────┐
│ Primary Ports │◄─────┤►│  Domain     │◄├───────┤► Secondary   │
│ (API Layer)   │       │ │  Objects   │ │       │  Ports       │
└──────────────┘       │ └─────────────┘ │       └──────────────┘
       ▲               └─────────────────┘              ▲
       │                        ▲                       │
       ▼                        │                       ▼
┌──────────────┐               │               ┌──────────────┐
│ Primary      │               │               │ Secondary    │
│ Adapters     │               │               │ Adapters     │
│ (Controllers)│               │               │ (Repository) │
└──────────────┘               │               └──────────────┘
       ▲                        │                       ▲
       │                        ▼                       │
       │               ┌─────────────────┐              │
       │               │ Anti-Corruption │              │
       └───────────────┤     Layer      ├──────────────┘
                       └─────────────────┘
```

### Core Domain Concepts

#### Ubiquitous Language

A shared vocabulary used consistently across all artifacts (code, documentation, conversations) to create a unified understanding between technical and business stakeholders.

#### Bounded Contexts

The project is organized into several bounded contexts:

- **Attendee Management** - Core domain for registration and profiles
- **Session Management** - Handles talks, schedules, and room assignments
- **Catering Management** - Manages meal planning and dietary accommodations
- **Swag Distribution** - Controls inventory and distribution logistics
- **Social Media Integration** - Manages external engagement and promotion

#### Domain Objects

| Type | Description | Examples |
|------|-------------|----------|
| **Entities** | Objects with identity and lifecycle | `AttendeeDomainObject`, `Badge` |
| **Value Objects** | Immutable objects without identity | `AddressValueObject`, `MealPreference` |
| **Aggregates** | Clusters of domain objects treated as a unit | `Attendee` aggregate |
| **Domain Events** | Records of business-significant occurrences | `AttendeeRegistrationEvent`, `CateringEvent` |

#### Architectural Decision Records (ADRs)

For detailed explanations of key architectural decisions, please refer to our [ADRs](docs/adrs):

1. [Project Goal and Value](docs/adrs/001-project-goal-and-value.md)
2. [File Relationships and Interaction](docs/adrs/002-file-relationships-and-interaction.md)
3. [Bounded Context Definition](docs/adrs/003-bounded-context-definition.md)
4. [Core Domain Identification](docs/adrs/004-core-domain-identification.md)
5. [Ubiquitous Language Strategy](docs/adrs/005-ubiquitous-language-strategy.md)
6. [Context Map Integration with Sales Team System](docs/adrs/006-context-map-integration-with-sales-team-system.md)
7. [Aggregate Design for Attendee Entity](docs/adrs/007-aggregate-design-for-attendee-entity.md)
8. [Deployment Strategy for OpenShift 4.18](docs/adrs/008-deployment-strategy-for-openshift-4.18.md)
9. [Red Hat OpenShift Dev Spaces Integration](docs/adrs/009-codeready-workspaces-integration.md)

## 🔧 Technical Stack

- **Backend**: Java with Quarkus
- **Database**: PostgreSQL
- **Messaging**: Red Hat AMQ (Apache Kafka)
- **Deployment**: Red Hat OpenShift Container Platform 4.18
- **Development Environment**: Red Hat OpenShift Dev Spaces

## 🚀 Getting Started

### Prerequisites

- Red Hat OpenShift Container Platform 4.18 or higher
- Red Hat OpenShift Dev Spaces
- Java 11+
- Maven 3.8+

### Quick Start

1. Clone the repository:
   ```bash
   git clone https://github.com/your-organization/ddd-hexagonal-workshop.git
   ```

2. For local development:
   ```bash
   ./mvnw compile quarkus:dev
   ```

3. For Red Hat OpenShift Dev Spaces:
   - Open the project in Red Hat OpenShift Dev Spaces
   - Run the `run-quarkus-dev` command defined in the devfile

### Deployment

For deployment to OpenShift, see our [Deployment Strategy](docs/adrs/008-deployment-strategy-for-openshift-4.18.md) and execute:

```bash
./scripts/bootstrap_env.sh
```

## 🤝 Contributing

Contributions are welcome! Please refer to our [WORKFLOW.md](WORKFLOW.md) for development guidelines and processes.

## 📄 License

This project is licensed under the Apache License 2.0 - see the LICENSE file for details.

---

*This project is maintained as part of Red Hat's commitment to enterprise-grade open source solutions.*




