# ADR 004: Core Domain Identification

**Date:** 2025-05-06  
**Status:** Accepted  
**Deciders:** Development Team  

## Context and Problem Statement

In Domain-Driven Design, it's crucial to identify which parts of the domain are core to the business and which are supporting or generic. Core domains are where we should focus our most creative design efforts and allocate the most resources, as they provide competitive advantage and are central to the business value proposition.

## Decision Drivers

* Need to allocate design and development resources effectively
* Desire to identify areas of strategic differentiation
* Recognition that not all parts of the system have equal business value
* Need for clear prioritization in development efforts

## Considered Options

* Treat all domains with equal priority
* Focus primarily on attendee management as the core
* Focus primarily on session management as the core
* Identify multiple core domains with different priorities

## Decision Outcome

We've identified the following domain classifications for our conference management system:

### Core Domain: Attendee Experience

The core domain of our conference management system is the end-to-end attendee experience, which integrates elements from multiple bounded contexts but focuses on the attendee journey from registration through the entire conference experience.

**Key Elements**:
- Personalized attendee registration and profile management
- Curated session recommendations based on attendee interests
- Seamless integration of catering preferences with meal planning
- Targeted swag distribution based on attendee attributes
- Integrated social media engagement throughout the attendee journey

**Justification**:
This is our core domain because the competitive advantage of our conference management system lies in providing an exceptional, personalized attendee experience that differentiates us from generic conference management tools. The attendee journey cuts across multiple bounded contexts but represents our primary value proposition.

### Supporting Domains

#### Session Management
While important, the session management functionality provides support to the core attendee experience. It includes standard features like scheduling, room allocation, and speaker management, but with custom integrations to support the personalized attendee journey.

#### Catering Management
This supporting domain handles the logistics of meal planning and dietary accommodations. It's essential for attendee satisfaction but doesn't represent our primary competitive advantage.

### Generic Domains

#### Payment Processing
We'll use existing payment solutions rather than building custom payment processing, as this is not an area where we need to differentiate.

#### Basic Authentication
Standard authentication mechanisms will be used, potentially with integration to external identity providers.

#### Email Notifications
While important for communication, the mechanism for sending emails is generic and can leverage existing solutions.

## Resource Allocation

Based on this identification:

* **Core Domain (Attendee Experience)**: 60% of development resources
* **Supporting Domains**: 30% of development resources
* **Generic Domains**: 10% of development resources (mostly integration)

## Validation

The success of this core domain identification will be validated by:

* Business stakeholder agreement on the strategic importance of the identified core domain
* Measurable improvements in attendee satisfaction and engagement
* Ability to differentiate our product from competitors based on the core domain features
* Efficient use of resources across the different domain types

## Related Decisions

* ADR 001: Project Goal and Value
* ADR 003: Bounded Context Definition
* ADR 005: Ubiquitous Language Strategy (upcoming)

## References

* [DDD: Strategic Design: Core, Supporting, and Generic Subdomains](https://blog.jonathanoliver.com/ddd-strategic-design-core-supporting-and-generic-subdomains/) - Comprehensive explanation of domain types in strategic design
* [DDD Part 1: Strategic Domain-Driven Design](https://vaadin.com/blog/ddd-part-1-strategic-domain-driven-design) - Introduction to strategic domain-driven design concepts
* [Domain-Driven Design (DDD): Strategic Design Explained](https://medium.com/@lambrych/domain-driven-design-ddd-strategic-design-explained-55e10b7ecc0f) - Practical guide to implementing strategic design in DDD