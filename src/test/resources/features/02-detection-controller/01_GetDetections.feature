Feature: Get Detections

  Scenario Outline: Detection controller service
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    And the user need to enter "<classificationType>" and "<zone>"
    When the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize | classificationType | zone |
      |  aselsan | aselsan   |  0   |     5    |         0          |  0   |

  Scenario Outline: Detection controller service, classificationTypeId
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    And the user need to enter "<classificationType>" and "<zone>"
    When the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |  classificationType  |     zone    |
      |  aselsan | aselsan   |  0   |     5    |      ACİL DURUM      |     K11_    |
      |  aselsan | aselsan   |  0   |     5    |      ARAÇ            |      Üs     |
      |  aselsan | aselsan   |  0   |     5    |      ARIZA           |  Hotel Loop |
      |  aselsan | aselsan   |  0   |     5    |      DİĞER           |  India Loop |
      |  aselsan | aselsan   |  0   |     5    |      DOĞRU ALARM     |     2125    |
      |  aselsan | aselsan   |  0   |     5    |      HAYVAN          |     2123    |
      |  aselsan | aselsan   |  0   |     5    |      İNSAN           |     2121    |
      |  aselsan | aselsan   |  0   |     5    |      SABOTAJ         |     2119    |
      |  aselsan | aselsan   |  0   |     5    |      YETKİSİZ        |     2111    |

    # For classification type parameter, you can enter these options:
    # "ACİL DURUM", "ARAÇ", "ARIZA", "DİĞER", "DOĞRU ALARM", "HAYVAN", "İNSAN", "SABOTAJ", "YETKİSİZ"