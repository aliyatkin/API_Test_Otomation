Feature: Get Detections

  Scenario Outline: Detection controller service
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    And the user need to enter "<classificationType>"
    When the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize | classificationType |
      |  aselsan | aselsan   |  0   |     5    |         0          |

  Scenario Outline: Detection controller service, classificationTypeId
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    And the user need to enter "<classificationType>"
    When the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |  classificationType  |
      |  aselsan | aselsan   |  0   |     5    |      ACİL DURUM      |
      |  aselsan | aselsan   |  0   |     5    |      ARAÇ            |
      |  aselsan | aselsan   |  0   |     5    |      ARIZA           |
      |  aselsan | aselsan   |  0   |     5    |      DİĞER           |
      |  aselsan | aselsan   |  0   |     5    |      DOĞRU ALARM     |
      |  aselsan | aselsan   |  0   |     5    |      HAYVAN          |
      |  aselsan | aselsan   |  0   |     5    |      İNSAN           |
      |  aselsan | aselsan   |  0   |     5    |      SABOTAJ         |
      |  aselsan | aselsan   |  0   |     5    |      YETKİSİZ        |

    # For classification type parameter, you can enter these options:
    # "ACİL DURUM", "ARAÇ", "ARIZA", "DİĞER", "DOĞRU ALARM", "HAYVAN", "İNSAN", "SABOTAJ", "YETKİSİZ"