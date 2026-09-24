# Citi Technology Software Development Job Simulation

Work completed as part of Citi's Technology Software Development
Job Simulation through Forage in September 2026.

The project involved software-system modelling and retrieving,
processing and visualising financial-market data.

## Financial Market Data Project

I developed a Java application that retrieves financial-market price
data from the Twelve Data API at regular intervals.

For this implementation, the DIA ETF was used as the market symbol.

Each API request records:

- The retrieved market price
- The timestamp at which the request was made

The price and timestamp are stored together as `RequestResult` objects
within a Java queue.

The application makes a new request every 15 seconds, creating a
sequence of price observations over time.

## Data Visualisation

Under the constraints of the simulation, the Java output is provided
to the Python program as a string.

The Python program:

1. Parses the alternating price and timestamp values.
2. Loads the observations into a Pandas DataFrame.
3. Converts the prices to numerical values.
4. Converts the timestamps to datetime values.
5. Uses Matplotlib to plot the market price against time.

This produces a visual representation of the collected financial data.

## Technologies Used

- Java
- Python
- Twelve Data API
- Java HTTP Client
- Pandas
- Matplotlib
- REST API requests
- Data processing and visualisation

## Software Design Task

As part of the simulation, I also:

- Modelled a loan-management workflow using a UML state diagram.
- Produced a feature proposal based on business requirements.
- Translated business requirements into a structured software design.

## Skills Demonstrated

- API integration
- Java programming
- Python programming
- Data collection and processing
- Financial-market data handling
- Data visualisation
- Object-oriented programming
- UML modelling
- Requirements analysis

## Project Context

This work was completed as part of Citi's Technology Software
Development Job Simulation provided through Forage.
