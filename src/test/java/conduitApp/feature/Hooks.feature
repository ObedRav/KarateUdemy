@testUnique
Feature: Hooks

  Background: hooks
    # Before hooks are run before the first step of each scenario and are the usual definitions
    # After Hooks
    * configure afterScenario = function() { karate.call('classpath:helpers/Dummy.feature') }
    * configure afterFeature = 
    """
        function() {
            karate.log('after feature');
        }
    """


  Scenario: First Scenario
    * print 'first scenario'

  Scenario: Second Scenario
    * print 'second scenario'
