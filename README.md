User Storing Application
Project Overview

The User Storing Application is a full-stack web application built using Spring Boot and MySQL to store and retrieve user information efficiently. The application follows a layered backend architecture and demonstrates proper frontend-to-backend communication using REST APIs.

This project was entirely designed and developed to showcase backend development skills, API handling, database integration, and clean application flow.

Technology Stack

Backend: Spring Boot (Java)

Frontend: HTML, CSS, JavaScript

Database: MySQL

API Type: RESTful Web Services

Build Tool: Maven

Application Architecture

The application follows a 3-tier architecture:

Controller Layer

Accepts HTTP requests from the frontend

Exposes REST API endpoints

Returns appropriate HTTP status codes and responses

Service Layer

Contains all business logic

Validates incoming user data

Handles duplicate user checks and processing rules

Repository Layer

Interacts with the MySQL database

Performs CRUD operations using JPA/Hibernate

Frontend to Backend Flow

User data is collected from the frontend UI.

JavaScript validates and processes the data.

The data is sent to the backend via REST API calls.

The Controller receives the request and forwards it to the Service layer.

The Service layer processes business logic.

The Repository layer stores data in the MySQL database.

Response with HTTP status is sent back to the frontend.

Key Features

User data storage and retrieval

REST API-based communication

Layered Spring Boot architecture

MySQL database integration

Duplicate user prevention

Proper HTTP response handling

Clean and scalable code structure

Database Design

Uses MySQL for persistent storage

Structured user table with unique constraints

Ensures data integrity and consistency

Learning Outcomes

Hands-on experience with Spring Boot architecture

Strong understanding of REST APIs

Real-world database integration using MySQL

Clear separation of concerns using layered design

End-to-end full-stack application development

Future Enhancements

Authentication and authorization (JWT / Spring Security)

Pagination and sorting APIs

Input validation using Hibernate Validator

Logging and exception handling

Deployment on cloud platform
