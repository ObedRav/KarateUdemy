# Karate and Gatling API Testing Project

This project was created as part of an Udemy course to learn how to automate API testing using Karate and perform performance testing using Gatling.

## Table of Contents

1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
   - [Prerequisites](#prerequisites)
   - [Installation](#installation)
   - [Configuration](#configuration)
3. [Running Tests](#running-tests)
   - [API Testing with Karate](#api-testing-with-karate)
   - [Performance Testing with Gatling](#performance-testing-with-gatling)
4. [Test Scenarios](#test-scenarios)
   - [Articles](#articles)
   - [Home Page](#home-page)
   - [Sign Up User](#sign-up-user)
   - [Work with Database](#work-with-database)
   - [Hooks](#hooks)
5. [Configuration Files](#configuration-files)
   - [gatling-akka.conf](#gatling-akkaconf)
6. [Process](#process)
7. [Authors](#authors)

## Introduction

This project serves as a practical learning experience for automating API testing using Karate and conducting performance testing with Gatling. It was developed as part of an Udemy course with the goal of enhancing proficiency in API testing.

## Getting Started

### Prerequisites

Before you begin, make sure you have the following software/tools installed:

- Maven 3.6.3
- Java JDK 11

### Installation

1. Clone this repository to your local machine.
2. Navigate to the project directory.
3. Run the following command to download dependencies and generate the Maven dependency cache:

   ```bash
   mvn dependency:go-offline -B
   ```

You're now ready to start using the project for API and performance testing.

### Configuration

The project relies on configuration files and settings. Please refer to the provided `karate-config.js` file for environment-specific configurations.

## Running Tests

### API Testing with Karate

To execute the API tests using Karate, run the following command:

```bash
mvn test
```

### Performance Testing with Gatling

Performance tests are conducted using Gatling. Refer to the `PerfTest.scala` file in the `src/test/java/conduitApp/performance` directory for details on how performance tests are set up.

## Test Scenarios

### Articles

This set of tests focuses on interacting with articles.

### Home Page

These tests validate functionality related to the home page.

### Sign Up User

This suite of tests covers user sign-up scenarios.

### Work with Database

These tests demonstrate database operations, including seeding the database with a new job and retrieving job levels.

### Hooks

Hooks are used to perform actions before and after scenarios or features.

## Configuration Files

### gatling-akka.conf

The `gatling-akka.conf` file contains configuration settings for Akka, which is used by Gatling for distributed computing.

## Process

If you want to watch the building process, you can type this in your console

```bash
git log --all --graph --decorate --oneline
```

## Authors

- [**Obed Rayo**](https://github.com/ObedRav) <a href="https://github.com/ObedRav" rel="nofollow"><img align="center" alt="github" src="https://www.vectorlogo.zone/logos/github/github-tile.svg" height="24" /></a>
