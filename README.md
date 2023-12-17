# Like a Pro

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
* ### Event
  * Create: `POST /event`
  * Read: `GET /event/{id}`
  * Read All: `GET /event`
  * Update: `PUT /event/{id}`
  * Delete: `DELETE /event/{id}`
* ### Recording
  * Create: `POST /recording`
  * Read: `GET /recording/{id}`
  * Read All: `GET /recording`
  * Update: `PUT /recording/{id}`
  * Delete: `DELETE /recording/{id}`
* ### Statistics
  * Create: `POST /statistics`
  * Read: `GET /statistics/{id}`
  * Read All: `GET /statistics`
  * Update: `PUT /statistics/{id}`
  * Delete: `DELETE /statistics/{id}`
* ### Actuator health check
  * `GET /actuator/health`
* ### Current MVP (Most Valuable Player)
  * Player (or players) with the most highlights in all the events: `GET /statistics/mvp`
* ### Current most highlights event
  * Event with the most highlights: `GET /statistics/best-event`
