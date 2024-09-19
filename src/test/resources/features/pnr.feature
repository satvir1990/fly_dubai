Feature: PNR Generation

  @IntegrationTest
  Scenario: Generate PNR reference with Pay Later option
    Given Home page of fly dubai Book flight and route information to search route
    And select departure flight tab and chose fare band
    And select return flight and chose fare band
    When baggae information displayed select Extra baggage
    And click continue to passenger details
    When passenger details page display fill passgenger details
    Then Reveiw booking
    And Generate Pnr with Pay Later Option
    
    

