Feature: Articles

  Background:
    * url apiUrl
    * def articleRequestBody = read('classpath:conduitApp/json/newArticleRequest.json')
    * def dataGenerator = Java.type('helpers.DataGenerator')
    * set articleRequestBody.article.title = dataGenerator.getRandomArticleValues().title
    * set articleRequestBody.article.description = dataGenerator.getRandomArticleValues().description
    * set articleRequestBody.article.body = dataGenerator.getRandomArticleValues().body

  Scenario:  Create Articles
    Given path 'articles'
    And request articleRequestBody
    When method POST
    Then status 201
    And match response.article.title == articleRequestBody.article.title

  Scenario: Delete Article
    Given path 'articles'
    And request articleRequestBody
    When method POST
    Then status 201

    * def slug = response.article.slug

    And path 'articles', slug
    When method DELETE
    Then status 204

    Given path 'articles'
    When method GET
    Then status 200
    And match response.[*].title !contains articleRequestBody.article.title
