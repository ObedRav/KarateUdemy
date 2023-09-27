Feature: Articles

Background:
  * url apiUrl
  * def articleRequestBody = read('classpath:conduitApp/json/newArticleRequest.json')
  * def dataGenerator = Java.type('helpers.DataGenerator')
  * set articleRequestBody.article.title = dataGenerator.getRandomArticleValues().title
  * set articleRequestBody.article.description = __gatling.Description
  * set articleRequestBody.article.body = dataGenerator.getRandomArticleValues().body


Scenario: Delete Article
  * configure headers = {"Authorization": #('Token ' + __gatling.token)}
  Given path 'articles'
  And request articleRequestBody
  And header karate-name = 'Create Article ' + __gatling.Title
  When method POST
  Then status 201

  * def slug = response.article.slug

  * karate.pause(5000)

  And path 'articles', slug
  When method DELETE
  Then status 204
