# Domain Driven Design and Hexagonal Architecture

Jeremy Davis
jeremy.davis@redhat.com

"Domain Driven Design: Tackling Complexity in the Heart of Software" by Eric Evans
"Domain Driven Design Distilled" by Vaughn Vernon

## Why?
Why write this software at all?
Why not buy it off the shelf?
Why not outsource it?

## Good Answers
Because it makes us money. It creates competitive advantage. It increases customer satisfaction.
Because it is not on a shelf.
Because it is a product, not a project. The business logic is complicated and requires domain expertise.

## My Favorite Thing about DDD: Ubiquitous Language

## Domains and Subdomains
Domain: Conference
Subdomains:
-Attendees
-Sessions
-Catering
-Social Media
-Swag

## Domain Objects and Events
Domain Objects:
Attendee

Events (things the business cares about):
-AttendeeRegistered
-Catering
-Swag
-SessionRegistered

## Domain Objects
Attendee

## Entities
Persistent objects.  The business cares about these.

## Aggregates
Top level entities.  Everything goes through the aggregate.

## Value Objects
NOT persistent

## Infrastructure

### Repository
Handles all persistence

### Service
Services orchestrate everything




