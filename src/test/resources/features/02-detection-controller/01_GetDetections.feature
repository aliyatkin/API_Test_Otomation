Feature: Get Detections

  Scenario Outline: Detection controller service
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |
      |  aselsan | aselsan   |  0   |    10    |

  Scenario Outline: Detection controller service, filter classificationTypeId
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter classificationType: "<classificationType>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |  classificationType  |
      |  aselsan | aselsan   |  0   |    10    |      ACİL DURUM      |
      |  aselsan | aselsan   |  0   |    10    |      ARAÇ            |
      |  aselsan | aselsan   |  0   |    10    |      ARIZA           |
      |  aselsan | aselsan   |  0   |    10    |      DİĞER           |
      |  aselsan | aselsan   |  0   |    10    |      DOĞRU ALARM     |
      |  aselsan | aselsan   |  0   |    10    |      HAYVAN          |
      |  aselsan | aselsan   |  0   |    10    |      İNSAN           |
      |  aselsan | aselsan   |  0   |    10    |      SABOTAJ         |
      |  aselsan | aselsan   |  0   |    10    |      YETKİSİZ        |

    # For classification type parameter, you can enter these options:
    # "ACİL DURUM", "ARAÇ", "ARIZA", "DİĞER", "DOĞRU ALARM", "HAYVAN", "İNSAN", "SABOTAJ", "YETKİSİZ"

  Scenario Outline: Detection controller service, filter zoneId
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter zone: "<zone>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |    zone     |
      |  aselsan | aselsan   |  0   |    10    |    K11_     |
      |  aselsan | aselsan   |  0   |    10    |     Üs      |
      |  aselsan | aselsan   |  0   |    10    |  Hotel Loop |
      |  aselsan | aselsan   |  0   |    10    |  India Loop |
      |  aselsan | aselsan   |  0   |    10    |    2125     |
      |  aselsan | aselsan   |  0   |    10    |    2123     |
      |  aselsan | aselsan   |  0   |    10    |    2121     |
      |  aselsan | aselsan   |  0   |    10    |    2119     |
      |  aselsan | aselsan   |  0   |    10    |    2111     |

  Scenario Outline: Detection controller service, filter time parameter
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter start time: "<startTime>" and finish time: "<endTime>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |       startTime      |       endTime       |
      |  aselsan | aselsan   |  0   |    100   |  2024-11-10 00:00:00 | 2024-11-19 00:00:00 |

  Scenario Outline: Detection controller service, filter startTime
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter start time: "<startTime>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |       startTime      |
      |  aselsan | aselsan   |  0   |    100   |  2024-10-01 00:00:00 |

  Scenario Outline: Detection controller service, filter finishTime
    Given the user tries to logs in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter finish time: "<finishTime>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |      finishTime      |
      |  aselsan | aselsan   |  0   |    100   |  2024-11-18 00:00:00 |