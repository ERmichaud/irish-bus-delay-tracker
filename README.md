# irish-bus-delay-tracker
A Java Spring Boot application that calculates the average predicted delay for bus routes across Ireland using live data from the National Transport Authority

## Features
- Retrieves live bus predictions from the National Transport Authority API
- Calculates average predicted delays
- Filters by all routes or given a route

## Tech
- Java 21
- Spring Boot
- Maven
- JUnit 5
- National Transport Authority (NTA) GTFS-Realtime API

## Data Source
Live transport predictions are retrieved from the National Transport Authority (NTA) GTFS-Realtime API. <br>
To sign up for an API key and learn more: https://developer.nationaltransport.ie

GTFS Data is provided "as is". The NTA is not responsible for any errors or inaccuracies. <br>
Delay statistics are calculated from live arrival predictions and do not represent confirmed arrival times

## Getting Started
To run this project you will need:
- Java 21
- Maven
- A National Transport Authority (NTA) API key

### 1. Clone Repository
Clone the repository from GitHub and open the project in your preferred Java IDE

### 2. Get an NTA API Key
Register for an API key through the NTA developer portal and subscribe to the GTFS-Realtime API

### 3. Configure API key
The Application reads the API key from an environment variable named NTA_API_KEY

### 4. Run the application
Run BusReliabilityApplication.java from your IDE <br>
You can test by visiting:
- http://localhost:8080/api/status - check that the application is running
- http://localhost:8080/api/delays - view average predicted delay across all buses in Ireland
- http://localhost:8080/api/delays?route=x - view predictions for a specific route by replacing x with the route name <br>
This application supports routes from Dublin Bus, Bus Éireann, and Go-Ahead

## Limitations
- Delays based on live arrival predictions rather than confirmed arrival times
- negative delays represent predictions of early arrivals and are included in the average
