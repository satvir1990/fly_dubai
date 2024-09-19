Feature: fetch Highest winning streak

  @IntegrationTest
  Scenario: fetch Highest winning streak
    Given Home page of ipl and fetch data
    
     Scenario Outline: <scen_out_row_num>
    Given Step from '<scen_out_row_num>' in 'scenario-outlines' feature file

    Examples: 
      | scen_out_row_num       |
      | Scenario Outline Row 1 |
      | Scenario Outline Row 2 |
    
    
    
    

