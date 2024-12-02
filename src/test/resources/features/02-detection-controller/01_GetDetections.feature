Feature: Get Detections
  As a user, I want to log into the system with different credentials and validate the responses. Then I want to GET the Detection List.

  Scenario Outline: Detection controller service
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter createdByMe: "<createdByMe>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize | createdByMe |
      |  aselsan | aselsan   |  0   |    10    |    true     |

  Scenario Outline: Detection controller service, filter classificationTyp
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter classificationType: "<classificationType>"
    And the user need to enter createdByMe: "<createdByMe>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |  classificationType  | createdByMe |
      |  aselsan | aselsan   |  0   |    10    |      ACİL DURUM      |    true     |
      |  aselsan | aselsan   |  0   |    10    |      ARAÇ            |    true     |
      |  aselsan | aselsan   |  0   |    10    |      ARIZA           |    true     |
      |  aselsan | aselsan   |  0   |    10    |      DİĞER           |    true     |
      |  aselsan | aselsan   |  0   |    10    |      DOĞRU ALARM     |    true     |
      |  aselsan | aselsan   |  0   |    10    |      HAYVAN          |    true     |
      |  aselsan | aselsan   |  0   |    10    |      İNSAN           |    true     |
      |  aselsan | aselsan   |  0   |    10    |      SABOTAJ         |    true     |
      |  aselsan | aselsan   |  0   |    10    |      YETKİSİZ        |    true     |
      |  aselsan | aselsan   |  0   |    10    |      YETKİLİ         |    true     |

    # For classification type parameter, you can enter these options:
    # "ACİL DURUM", "ARAÇ", "ARIZA", "DİĞER", "DOĞRU ALARM", "HAYVAN", "İNSAN", "SABOTAJ", "YETKİSİZ"

  Scenario Outline: Detection controller service, filter zone
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter zone: "<zone>"
    And the user need to enter createdByMe: "<createdByMe>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |    zone     | createdByMe |
      |  aselsan | aselsan   |  0   |    10    |    K11_     |    true     |
      |  aselsan | aselsan   |  0   |    10    |     Üs      |    true     |
      |  aselsan | aselsan   |  0   |    10    |  Hotel Loop |    true     |
      |  aselsan | aselsan   |  0   |    10    |  India Loop |    true     |
      |  aselsan | aselsan   |  0   |    10    |    2125     |    true     |
      |  aselsan | aselsan   |  0   |    10    |    2123     |    true     |
      |  aselsan | aselsan   |  0   |    10    |    2121     |    true     |
      |  aselsan | aselsan   |  0   |    10    |    2119     |    true     |
      |  aselsan | aselsan   |  0   |    10    |    2111     |    true     |

  Scenario Outline: Detection controller service, filter time parameters
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter start time: "<startTime>" and finish time: "<endTime>"
    And the user need to enter createdByMe: "<createdByMe>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |       startTime      |       endTime       | createdByMe |
      |  aselsan | aselsan   |  0   |    10   |  2024-11-10 00:00:00 | 2024-11-19 00:00:00 |    true     |

  Scenario Outline: Detection controller service, filter startTime
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter start time: "<startTime>"
    And the user need to enter createdByMe: "<createdByMe>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |       startTime      | createdByMe |
      |  aselsan | aselsan   |  0   |    10   |  2024-10-01 00:00:00 |    true     |

  Scenario Outline: Detection controller service, filter finishTime
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter finish time: "<finishTime>"
    And the user need to enter createdByMe: "<createdByMe>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize |      finishTime      | createdByMe |
      |  aselsan | aselsan   |  0   |    10   |  2024-11-18 00:00:00 |    true     |

  Scenario Outline: Detection controller service, filter createdByMe
    Given the user tries to log in with a valid "<username>" and "<password>"
    And the system verifies the required response parameters
    And the access token is saved
    When the user need to enter createdByMe: "<createdByMe>"
    Then the user gets Detections for "<page>" and "<pageSize>"
    Examples:
      | username | password  | page | pageSize | createdByMe |
      |  aselsan | aselsan   |  0   |    10    |    true     |
      |  aselsan | aselsan   |  0   |    10    |    false    |