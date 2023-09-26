Feature: Test for the home page

  Background:
    * url apiUrl

  Scenario: Testing the get all tags
    Given path 'tags'
    When method GET
    Then status 200
    And match response.tags == '#array'
    And match each response.tags == '#string'

  Scenario: Testing the get all articles
    Given params { limit: 10, offset: 0 }
    And path 'articles'
    When method GET
    Then status 200

    * def datesValidation = '#regex ^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z$'

    And match response == { 'articles': '#[10]', 'articlesCount': '#number' }
    And match each response..image contains 'https://api.realworld.io/images/'
    And match each response.articles ==
    """
        {
            "slug": "#string",
            "title": "#string",
            "description": "#string",
            "body": "#string",
            "tagList": "#[] #string",
            "createdAt": "#(datesValidation)",
            "updatedAt": "#(datesValidation)",
            "favorited": '#boolean',
            "favoritesCount": '#number',
            "author": {
                "username": "#string",
                "bio": '##string',
                "image": "#string",
                "following": '#boolean'
            }
        }
    """
 