# 비즈니스 로직 설계
- MSA 에서는 복잡한 비즈니스 로직을 개발하기가 까다로움
  1. DDD 는 상호 연관된 클래스가 얽혀있으므로 MSA 에서는 서비스를 넘나드는 객체 레퍼런스를 제거해야 함
  2. MSA 특유의 트랜잭션 관리 제약 조건하에서도 작동되는 비즈니스 로직을 설계해야 함
  3. 위의 두 문제는 DDD 애그리거트 패턴으로 해결할 수 있음
- 애그리거트를 사용하면 객체 레퍼런스가 서비스를 넘나들 일이 없음, 객체 참조 대신 기본키를 이용
- 한 트랜잭션으로 하나의 애그리거트만 생성/수정

## 5.1 비즈니스 로직 구성 패턴
- 인바운드 어댑터
  - 클라이언트 요청을 받아 비즈니스 로직 호출
- 아웃바운드 어댑터
  - 다른 서비스 및 애플리케이션을 실행

## 5.1.1 비즈니스 로직 설계: 트랜잭션 스크립트 패턴
- 절차적인 코드
- 한 트랜잭션에 비즈니스 로직 다 넣는거

## 5.1.2 비즈니스 로직 설계: 도메인 모델 패턴
- 객체 지향 설계
  - 설계를 이해/관리하기 쉬움
  - 테스트 하기 쉬움
  - 확장하기 쉬움

## 5.1.3 도메인 주도 설계 개요
- DDD
  - entity
    - 영속적 신원을 가진 객체
  - 밸류 객체
    - 여러 값을 모아 놓은 객체
  - 팩토리
    - 일반 생성자로 만들기 복잡한 객체 생성 로직이 구현된 객체 혹은 메서드
  - repository
    - 엔터티를 저장하는 db 접근 로직을 캡슐화한 객체
  - service
    - entity, value object 에 속하지 않는 비즈니스 로직 구현 객체

## 5.2 DDD 애그리거트 패턴

## 5.2.1 불분명한 경계 문제

## 5.2.2 애그리거트는 경계가 분명하다.
- 애그리거트 : 한 단위로 취급 가능한 경계 내부의 도메인 객체
- 루트 엔터티(1) + (기타 엔터티 + 밸류 객체)(N)
- 주문, 소비자, 음식점 같은 명사
- 애그리거트는 일관된 경계
- 애그리거트를 식별하는 일이 고나건

## 5.2.3 애그리거트 규칙
1. 애그리거트 루트만 참조하라
2. 애그리거트 간 참조는 반드시 기본키를 사용하라
    - fk 쓰면 안됨, 결합도 높아짐
3. 하나의 트랜잭션으로 하나의 애그리거트를 생성/수정하라
   - saga 가 적합

## 5.2.4 애그리거트 입도
- 애그리거트는 작으면 작을 수록 좋다
  - 애플리케이션이 동시 처리 가능한 요청 개수가 늘고 확장성이 좋아짐
- 다만 어떤 업데이트를 원자적으로 처리하려면 크게 잡아도 좋음

## 5.2.5 비즈니스 로직 설계: 애그리거트

## 5.3 도메인 이벤트 발행
- 도메인 이벤트 -> 애그리거트에 발생한 사건
  - 대부분 상태 변경

## 5.3.1 변경 이벤트를 발행하는 이유
- 데이터 일관성 유지
- 사가에서 다음 단계를 진행하도록 알림

## 5.3.2 도메인 이벤트란 무엇인가?

## 5.3.3 이벤트 강화
- 필요한 정보를 이벤트가 갖고 다니는 것

## 5.3.4 도메인 이벤트 식별
- x 가 일어나면 y 를 수행하라
- 이벤트 스토밍
  - 어떤 이벤트를 실행할지 포스트잇으로 적는 것
- 이벤트 스토밍 3단계
  1. 이벤트 브레인 스토밍 : 오렌지색 점착식 메모지로 도메인 이벤트를 모델링 화면에 대략 그려 타임라인에 배치
  2. 이벤트 트리거 식별
     - 사용자 액션 : 파란색 점착식 메모지로 커맨드를 표시
     - 외부 시스템 : 자주색 점착식 메모지로 표시
     - 기타 도메인 이벤트
     - 시간 경과
  3. 애그리거트 식별
     - 각 커맨드 소비 후 적절한 이벤트를 발생시키는 애그리거트를 식별해서 노란색 점착식 메모지로 표시

## 5.3.5 도메인 이벤트 생성 및 발행
- 비즈니스 로직이 도메인 이벤트를 메시지 브로커에 발행하려면 먼저 도메인 이벤트를 생성해야 한다.
- 도메인 이벤트 생성
  - 도메인 이벤트는 애그리거트가 발행
  - 자신의 상태가 변경되는 시점과 그 결과로 어떤 이벤트를 발행할지 알고 있음
  - 애그리거트와 호출하는 서비스의 책임을 분리하는 것이 좋다.
  - 상태 전이 시 이벤트를 발행하고 서비스에 반환
- 도메인 이벤트를 확실하게 발행하는 방법
  - 트랜잭셔널 메시징을 사용해야 한다.

## 5.3.6 도메인 이벤트 소비
- consumer 로 받는다.

