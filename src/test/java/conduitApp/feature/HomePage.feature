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

  Scenario: Conditional Logic
    Given params { limit: 10, offset: 0 }
    Given path 'articles'
    When method GET
    Then status 200

    * def favoritesCount = response.articles[0].favoritesCount
    * def firstArticle = response.articles[0]

    # * if (favoritesCount == 0) karate.call('classpath:helpers/AddLikes.feature', firstArticle)

    * def result = favoritesCount == 0 ? karate.call('classpath:helpers/AddLikes.feature', firstArticle) : favoritesCount

    Given params { limit: 10, offset: 0 }
    Given path 'articles'
    When method GET
    Then status 200
    # * def favCount = response.articles[0].favoritesCount
    # * if (favCount <= 0) karate.fail('favoritesCount should be greater than 0')
    And match response.articles[0].favoritesCount == result

  Scenario: Retry call
    * configure retry = { count: 2, interval: 5000 }

    Given params { limit: 10, offset: 0 }
    Given path 'articles'
    When retry until response.articles[0].favoritesCount >= 1
    When method GET
    Then status 200

  Scenario: Sleep call
    * def sleep = function(pause) { java.lang.Thread.sleep(pause) }

    Given params { limit: 10, offset: 0 }
    Given path 'articles'
    * print 'wait for sleep'
    * eval sleep(5000)
    When method GET
    Then status 200

  Scenario: Number to String
    * def foo = 10
    * def json = { 'bar': #(foo+'') }
    * match json == { 'bar': '10' }

    @testUnique 
  Scenario: String to Number
    * def foo = '10'
    * def json = { 'bar': #(foo*1) }
    # This will be double
    * def json2 = { 'bar': #(parseInt(foo)) }
    # This will be integer
    * def json3 = { 'bar': #(~~parseInt(foo)) }
    * match json == { 'bar': 10 }
    * match json2 == { 'bar': 10 }
