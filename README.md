# aws-sns

### 개발환경
- Spring Boot 3.1.0
- Spring Cloud AWS 3.0.1 
- Spring Cloud OpenFeign 4.0.3
- Java 17
- H2
- Spring Data Jpa & QueryDsl


### API

[더미 주문 완료]
- PATCH http://localhost:8080/v1/orders/{orderId}/completion
    - -H ContentType: application/json
    - orderId : 임의의 값

[First Benefit 조회]
- GET http://localhost:8060/v1/first-benefits/{userId}
  - userId : testUserId

[Second Benefit 조회]
- GET http://localhost:8070/v1/second-benefits/{userId}
  - userId : testUserId
