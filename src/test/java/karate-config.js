function fn() {
  let env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }

  const config = {
    env: env,
    apiUrl: 'https://conduit.productionready.io/api/'
  }

  if (env == 'dev') {
    config.userEmail = 'kar1@test.com'
    config.userPassword = 'Karate123'
  } else if (env == 'QA') {
    config.userEmail = 'kar2@testQA.com'
    config.userPassword = 'Karate456'
  }

  const accessToken = karate.callSingle('classpath:/helpers/CreateToken.feature', config).authToken
  karate.configure('headers', { Authorization: 'Token ' + accessToken})

  return config;
}