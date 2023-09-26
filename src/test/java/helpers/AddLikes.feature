Feature: Add likes

Background:
    * url apiUrl

Scenario: Add like
    Given path 'articles/' + slug + '/favorite'
    And request {}
    When method POST
    Then status 200
    * def likesCount = response.article.favoritesCount
