# ADR 005: Ubiquitous Language Strategy

**Date:** 2025-05-06  
**Status:** Accepted  
**Deciders:** Development Team, Domain Experts  

## Context and Problem Statement

A core principle of Domain-Driven Design is establishing a ubiquitous language - a shared vocabulary used consistently by all team members across all artifacts (code, documentation, conversations). We need a clear strategy for developing, maintaining, and evolving this language as our understanding of the domain grows.

## Decision Drivers

* Need for consistent terminology across teams and artifacts
* Risk of miscommunication and translation errors between technical and business stakeholders
* Desire to capture domain expertise in the code and documentation
* Need for a process to evolve language as domain understanding matures

## Considered Options

1. Informal language development through team discussions
2. Centralized glossary maintained by a dedicated team
3. Decentralized context-specific glossaries with integration patterns
4. Language-driven development with automated enforcement

## Decision Outcome

**Chosen option: Decentralized context-specific glossaries with integration patterns**

We've decided to develop and maintain context-specific ubiquitous languages for each bounded context, with explicit translation and integration patterns between contexts. This approach acknowledges that different bounded contexts may naturally develop different terminologies, while still providing mechanisms for clear communication across boundaries.

### Implementation Strategy

1. **Context-Specific Glossaries**
   - Each bounded context will maintain its own glossary in a markdown file within the context's documentation
   - Terms will include definitions, examples, and relationships to other terms
   - Code within each context will strictly adhere to its glossary terminology

2. **Translation Maps**
   - Explicit translation maps will be created for terms that cross context boundaries
   - Anti-corruption layers will implement these translations in code
   - Documentation will clearly explain how concepts map between contexts

3. **Language Evolution Process**
   - Regular "Language Refinement" sessions will be held with domain experts and developers
   - Proposed changes will be reviewed by the team and domain experts
   - Approved changes will update the glossary, with corresponding code refactoring

4. **Language Artifacts**
   - Code: Class, method, and variable names will reflect the ubiquitous language
   - Documentation: All technical and business documentation will use the language
   - Tests: Test cases will be written using ubiquitous language terms
   - UI: User interfaces will reflect the language where appropriate for users

### Consequences

* **Positive:**
  * Natural alignment with bounded contexts
  * Flexibility for specialized terminology in different areas
  * Explicit handling of translation between contexts
  * Clear ownership of terminology by context teams

* **Negative:**
  * Overhead of maintaining multiple glossaries
  * Risk of inconsistency if translation maps are not well maintained
  * Potential for drift between contexts over time

## Validation

The success of this ubiquitous language strategy will be validated by:

* Reduction in miscommunications between technical and business stakeholders
* Consistency between code, documentation, and verbal discussions
* Ability for new team members to quickly understand domain concepts
* Effectiveness of cross-context communication and integration

## Practical Examples

### Attendee Management Context Glossary (Excerpt)

| Term | Definition | Example |
|------|------------|---------|
| Attendee | A person registered to attend the conference | "John Smith is an attendee with a full-access pass" |
| Registration | The process of an attendee signing up for the conference | "Registration opens 3 months before the conference" |
| Badge | Physical identification given to attendees for access control | "Each attendee receives a badge with their name and QR code" |

### Example Translation Map

| Attendee Context | Session Context | Translation Notes |
|------------------|-----------------|-------------------|
| Attendee | Participant | An Attendee in the Attendee context becomes a Participant in the Session context |
| Session Interest | Topic Preference | Session interests are mapped to topic preferences for recommendation algorithms |

## Related Decisions

* ADR 001: Project Goal and Value
* ADR 003: Bounded Context Definition
* ADR 004: Core Domain Identification
* ADR 006: Context Mapping (upcoming)

## References

* [Ubiquitous Language and the Joy of Naming](https://www.martinfowler.com/bliki/UbiquitousLanguage.html) - Martin Fowler's explanation of the importance of shared language in DDD
* [Creating a Ubiquitous Language](https://www.infoq.com/articles/ddd-ubiquitous-language/) - Practical guide for establishing and evolving the ubiquitous language
* [Context Mapping – Strategic Domain-Driven Design](https://www.infoq.com/articles/ddd-contextmapping/) - Techniques for managing multiple bounded contexts and their languages
* [Domain-Driven Design: Tackling Complexity in the Heart of Software](https://www.domainlanguage.com/ddd/) - Eric Evans' seminal book that introduced the concept of ubiquitous language