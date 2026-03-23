<img width="1346" height="913" alt="Screenshot 2026-03-23 192808" src="https://github.com/user-attachments/assets/b1cd561d-1f76-43f4-b9df-c7f7d4a5a180" />

# Multilevel Parking Lot System (Low Level Design)

## Overview

This project implements a multilevel parking lot system using object-oriented design principles. The system supports different vehicle types, multiple parking levels, and multiple entry gates. It efficiently assigns the nearest available parking slot and calculates billing based on slot type.

The design focuses on scalability, extensibility, and separation of concerns, making it suitable for real-world systems.

---

## Functional Requirements

* Supports three types of parking slots:

  * Small (for bikes)
  * Medium (for cars)
  * Large (for buses)

* Vehicles can park in compatible or larger slots:

  * Bike → Small, Medium, Large
  * Car → Medium, Large
  * Bus → Large only

* Multiple parking levels are supported.

* Multiple entry gates are supported.

* The system assigns the nearest available slot based on the entry gate.

* Generates a parking ticket on entry with:

  * Vehicle details
  * Slot details
  * Entry time

* On exit:

  * Calculates parking duration
  * Generates bill based on slot type (not vehicle type)

* Provides current parking status.

---

## Design Approach

### 1. Core Entities

* **ParkingLot**

  * Acts as the central coordinator.
  * Maintains parking levels, entry gates, active tickets.
  * Exposes APIs: park, exit, status.

* **ParkingLevel**

  * Represents a single floor in the parking lot.
  * Contains a list of parking slots.

* **ParkingSlot**

  * Represents an individual slot.
  * Stores slot type, occupancy status, and distance from each gate.

* **Vehicle**

  * Contains vehicle number and type.

* **Ticket**

  * Generated at entry.
  * Stores vehicle, allocated slot, and entry time.

* **EntryGate**

  * Represents a gate.
  * Maintains a min-heap of parking slots ordered by distance.

---

### 2. Slot Allocation Strategy

A strategy pattern is used to decouple slot allocation logic.

* **SlotStrategy (interface)**
* **NearestSlotStrategy (implementation)**

The nearest slot is selected using a min-heap (priority queue) per gate.

Each gate maintains a heap of all parking slots sorted by distance from that gate. This allows efficient retrieval of the closest available compatible slot.

Time complexity for allocation: O(log N)

---

### 3. Multi-Level Handling

* Each level manages its own slots.
* During initialization, all slots from all levels are added to each gate’s heap.
* This ensures that the nearest slot is selected globally across all levels.

---

### 4. Billing System

* Billing is handled by a separate **FareCalculator** class.
* Charges are based on slot type, not vehicle type.
* Duration is calculated using entry and exit timestamps.
* Minimum billing duration is one hour.

---

### 5. Data Structures Used

* **HashMap**

  * Stores active tickets
  * Maps gates

* **PriorityQueue (Min-Heap)**

  * Maintained per gate
  * Sorted by slot distance

* **List**

  * Stores levels and slots

---

## APIs

### park(vehicle, entryTime, gateId)

* Finds nearest compatible slot
* Marks slot occupied
* Generates and returns ticket

---

### exit(ticketId, exitTime)

* Frees slot
* Reinserts slot into all gate heaps
* Calculates fare
* Returns bill amount

---

### status()

* Returns available slots grouped by slot type

---

## Key Design Decisions

* Used Strategy Pattern for flexible slot allocation.
* Used Min-Heap per gate for efficient nearest slot lookup.
* Separated billing logic for better maintainability.
* Designed for extensibility (new slot types, pricing strategies, etc.).

---

## Complexity

* Slot allocation: O(log N)
* Exit operation: O(log N)
* Status: O(N)

---

## Possible Enhancements

* Add concurrency control for multi-threaded access.
* Introduce reservation system.
* Add real-time distance calculation using coordinates.
* Persist data using a database.
* Build REST APIs using a framework like Spring Boot.

---

## How to Run

Compile all files:

javac *.java

Run the program:

java Main

---

## Conclusion

This system demonstrates a scalable and modular design for a multilevel parking lot. It uses appropriate data structures and design patterns to ensure efficiency and maintainability.
