@dockerignore
Feature: Work with Database

Background: Connect to Database
    * def databaseManager = Java.type('helpers.DatabaseManager')

Scenario: Seed Database with a new job
    * eval databaseManager.addNewJob('QA2', 250, 100)

Scenario: Get level job
    * def level = databaseManager.getMinAndMaxLevelsForJob('QA2')
    * match level.min_lvl == '250'
    * match level.max_lvl == '100'
