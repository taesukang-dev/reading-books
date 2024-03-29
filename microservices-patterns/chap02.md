# 분해 전략

## 2.1 마이크로서비스 아키텍처란 무엇인가?
- 관리성, 테스트성, 배포성이 높은 애플리케이션을 구축하는 아키텍처 스타일

## 2.1.1 소프트웨어 아키텍처의 정의와 중요성
- 소프트웨어 아키텍처의 정의
  - 소프트웨어 엘리먼트와 그들 관의 관계 그리고 이 둘의 속성으로 구성된 시스템을 추론하는 데 필요한 구조의 집합
  - 여러 파트로의 분해와 파티 간의 관계
    - 업무와 지식을 분리
    - 소프트웨어 엘리먼트가 어떻게 상호작용하는지 밝힘
  - 소프트웨어 아키텍처의 4 + 1 뷰 모델
    - 논리 뷰
      - 개발자가 작성하는 것
      - 상속, 연관, 의존 등 클래스와 패키지의 관계
      - 엘리먼트 : 클래스 및 패키지
      - 관계 : 클래스와 패키지의 관계
    - 구현 뷰
      - 빌드 시스템이 만들어 내는 것
      - 엘리먼트 : 모듈(JAR) 및 컴포넌트(WAR 또는 실행 파일)
      - 관계 : 모듈 / 컴포넌트 간 디펜던시
    - 프로세스 뷰
      - 런타임 컴포넌트
      - 엘리먼트 : 프로세스
      - 관계 : 프로세스 간 통신
    - 배포 뷰
      - 머신에서 실행 중인 프로세스
      - 엘리먼트 : 머신 및 프로세스
      - 관계 : 네트워킹
  - 아키텍처의 중요성
    - 애애플리케이션의 두 요건을 충족해야 함
      - 애플리케이션이 해야 할 일을 정의한 기능 요건
      - '~성' 으로 끝나는 서비스 품질 요건

## 2.1.2 아키텍처 스타일 개요
- 계층화 아키텍처 스타일
  - mvc?
  - presentation layer
    - 사용자 인터페이스 또는 외부 API
  - business logic layer
    - 비즈니스 로직
  - persistence layer
    - db logic
- 융각형 아키텍처 스타일 (헥사고날)
  - 인바운드 어댑터
    - 외부에서 들어온 요청을 처리하는 
  - 아웃바운드 어댑터
    - 외부 애플리케이션을 호출
  - 비즈니스 로직이 어댑터에 전혀 의존하지 않음
    - 어댑터가 비즈니스 로직에 의존
  - 비즈니스 로직에는 하나 이상의 포트가 있음
    - 포트는 비즈니스 로직이 외부와 상호작용하는 방법이 정의된 작업
    - 인바운드 포트
    - 아웃바운드 포트
  - 비즈니스 로직에 있던 표현/데이터 접근 로직이 어댑터와 분리되었기 때문에 비즈니스 로직이 표현/데이터 접근 로직 어디에도 의존하지 않는다.
    - 테스트에 용이하다

## 2.1.3 마이크로서비스 아키텍처는 일종의 아키텍처 스타일이다.
- 모놀리식 아키텍처
  - 애플리케이션을 실행/배포 가능한 단일 컴포넌트로 구성한다.
- 마이크로서비스 아키텍처
  - 애플리케이션을 느슨하게 결합된, 독립적으로 배포 가능한 여러 서비스로 구성한다.
- 서비스?
  - 어떤 기능이 구현되어 단독 배포가 가능한 소프트웨어 컴포넌트
- 느슨한 결합

## 2.2 마이크로서비스 아키텍처 정의
1. 애플리케이션 요건을 핵심 요청으로 추출
2. 어떻게 여러 서비스로 분해할지 결정
3. 서비스별로 API 정의

## 2.2.1 시스템 작업 식별
- 사용자 시나리오 (사용자가 어떻게 사용할 것인지)
- 합의된 어휘를 제공하는 핵심 클래스로 구성된 도메인 모델을 생성
  - 합의된 어휘 -> 이해관계자들이 공유할 수 있는 용어
- 정리된 용어와 기능들로 클래스를 도출하고 도출된 클래스로 도메인 모델 생성
  - 생성된 이후 각 클래스들의 역할 정의

## 2.2.2 서비스 정의: 비즈니스 능력 패턴별 분해
- 분해하는 방법
  - 비즈니스에 따라 나누는 방법
  - 하위 도메인 패턴별로 분해하는 방법
    - DDD
    - Bounded Context

## 2.2.5 서비스 분해의 장애물
- 네트워크 지연
- 동기 통신으로 인한 가용성 저하
- 여러 서비스에 걸쳐 데이터 일관성 유지
- 데이터의 일관된 뷰 확보
- 분해를 저해하는 만능 클래스