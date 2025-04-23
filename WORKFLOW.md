# Workshop Workflow

- Create an Attendee domain object with a registerAttendee method
-- Create an AttendeeEntity
-- Create an AttendeeRegistrationEvent
-- Create a CateringEvent
-- Create a SwagEvent
- Create an AttendeeRepository
- Create an AttendeeValueObject
- Create a RegisterAttendeeCommand
- Create an AttendeeService object with a registerAttendee method with a RegisterAttendeeCommand signature that calls Attendee.registerAntendee and returns an AttendeeValueObject
-- Send AttendeeRegistrationEvent, CateringEvent, and SwagEvent to Kafka
-- send AttendeeEntity to AttendeeRepository
- Create an AttendeeEndpoint with CRUD methods that call AttendeeService
-- Create an anti corruption layer for Sponsor Attendees
- Create a SponsorAttendeeAdapter that translates a SponsorAttendee
- Create a SponsorsEndpoint with CRUD methods

