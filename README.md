# Like a Pro recordings

Bancolombia reactive programming course - **Task #1**

**Author:** Héctor Andrés Hoyos Ceballos

**C.C.:** 1039466317

**Description:** Project for the administration of customers, events, recordings and statistics of the Like a Pro sports analysis platform.

## Entities

---
* ### Customer
  * **id**: Double
  * **name**: String
  * **email**: String
  * **password**: String
  * **role**: String
  * **phone**: String
  * **status**: Boolean
  * **created_at**: Timestamp
  * **updated_at**: Timestamp
* ### Event
  * **id**: Double
  * **name**: String
  * **description**: String
  * **date**: DateTime
  * **status**: Boolean
  * **customers**: List(Customer)
  * **created_at**: Timestamp
  * **updated_at**: Timestamp
* ### Recording
  * **id**: Double
  * **name**: String
  * **event**: Event
  * **duration**: Time
  * **status**: Boolean
  * **created_at**: Timestamp
  * **updated_at**: Timestamp
* ### Statistics
  * **id**: Double
  * **timestamp**: Timestamp
  * **recording**: Recording
  * **data**: Customer
  * **created_at**: Timestamp
  * **updated_at**: Timestamp

## Endpoints

---
* ### Customer
  * Create: `POST /customer`
  * Read: `GET /customer/{id}`
  * Read All: `GET /customer`
  * Update: `PUT /customer/{id}`
  * Delete: `DELETE /customer/{id}`
  * Get customer from Kafka topic: `GET /customer/kafka/{topic}/{partition}/{offset}`
* ### Event
  * Create: `POST /event`
  * Read: `GET /event/{id}`
  * Read All: `GET /event`
  * Update: `PUT /event/{id}`
  * Delete: `DELETE /event/{id}`
  * Get event from Kafka topic: `GET /event/kafka/{topic}/{partition}/{offset}`
* ### Recording
  * Create: `POST /recording`
  * Read: `GET /recording/{id}`
  * Read All: `GET /recording`
  * Update: `PUT /recording/{id}`
  * Delete: `DELETE /recording/{id}`
  * Get recording from Kafka topic: `GET /recording/kafka/{topic}/{partition}/{offset}`
* ### Statistics
  * Create: `POST /statistics`
  * Read: `GET /statistics/{id}`
  * Read All: `GET /statistics`
  * Update: `PUT /statistics/{id}`
  * Delete: `DELETE /statistics/{id}`
  * Get statistics from Kafka topic: `GET /statistics/kafka/{topic}/{partition}/{offset}`
* ### Actuator health check
  * `GET /actuator/health`
* ### Current MVP (Most Valuable Player)
  * Player (or players) with the most highlights in all the events: `GET /statistics/mvp`
* ### Current most highlights event
  * Event with the most highlights: `GET /statistics/best-event`
