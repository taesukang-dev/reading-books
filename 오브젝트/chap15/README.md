# 디자인 패턴과 프레임워크
- 디자인 패턴 : 소프트웨어 설계에서 반복적으로 발생하는 문제에 대해 적용할 수 있는 해결 방법
  - 다양한 변경을 다루기 위해 반복적으로 재사용할 수 있는 설계의 묶음
- 프레임워크 : 설계와 코드를 함께 재사용하기 위한 것
  - 프레임워크는 견고한 구현 코드를 쉽고 빠르게 재사용할 수 있게 한다.

## 디자인 패턴과 설계 재사용
- 소프트웨어 패턴
  - 패턴이란
    - 패턴은 반복적으로 발생하는 문제와 해법의 쌍으로 정의된다.
    - 패턴을 사용함으로써 이미 알려진 문제와 이에 대한 해법을 문서로 징리할 수 있으며 이 지식을 다른 사람과 의사소통할 수 있다.
    - 패턴은 추상적인 원칙과 실제 코드 작성 사이의 간극을 메워주며 실질적인 코드 작성을 돕는다.
    - 패턴의 요점은 패턴이 실무에서 탄생했다는 점이다
  - 패턴 언어 : 연관된 패턴들의 집합 == 패턴 시스템
- 패턴 분류
  - 아키텍처 패턴
    - 디자인 패턴의 상위에서 소프트웨어 전체적인 구조를 결정하고 사용
  - 분석 패턴
    - 도메인 내의 업무 모델링 시에 발견되는 공통적인 구조를 표현하는 개념들의 집합
  - 디자인 패턴
    - 협력하는 컴포넌트 사이에서 반복적으로 발생하는 구조
  - 이디엄
    - 디자인 패턴의 하위로 주어진 언어의 기능을 사용해 컴포넌트 혹은 컴포넌트 간의 특정 측면을 구현하는 방법을 서술한다.
- 패턴과 책임-주도 설계
  - 패턴은 공통으로 사용할 수 있는 역할, 책임, 협력의 템플릿이다.
    - 패턴은 반복적으로 발생하는 문제를 해결하기 위해 사용할 수 있는 공통적인 역할과 책임, 협력의 훌륭한 예제를 제공한다.
- 캡슐화와 디자인 패턴
  - 각 디자인 패턴은 특정한 변경을 캡슐화하기 위한 독자적인 방법을 정의하고 있다.
- 패턴은 출발점이다.
  - 패턴은 설계의 목표가 돼서는 안된다.
    - 나침반에 불과, 요구사항이나 적용 기술, 프레임워크에 적합하지 않다면 목적에 맞게 수정하라

## 프레임워크와 코드 재사용
- 코드 재사용 대 설계 재사용
  - 기존 컴포넌트를 조립해서 애플리케이션을 구축하는 방법 자체는 이상적이지만 실제로 적용하기 어렵다
  - 가상 이상적인 형태는 설계 재사용과 코드 재사용을 적절한 수준으로 조합하는 것
    - 프레임워크가 해주는 것
  - 프레임워크
    - 추상 클래스나 인터페이스를 정의하고 인스턴스 사이의 상호작용을 통해 시스템 전체 혹은 일부를 구현해 놓은 재사용 가능한 설계
      - 구조적 측면의 프레임워크
    - 애플리케이션 개발자가 현재의 요구사항에 맞게 커스터마이징할 수 있는 애플리케이션의 골격
      - 코드와 설계의 재사용 측면의 프레임워크
- 상위 정책과 하위 정책으로 패키지 분리하기
  - 상위 정책이 세부 사항 (자주 변하는)보다 더 다양한 상황에서 재사용될 수 있어야 한다는 것이다.
  - DIP 에 맞게 추상화 해야 한다.
    - 변하는 것과 변하지 않는 것을 서로 분리해야 한다.
    - 별도의 배포 단위로 분리해야 한다.
  - 변하는 부분과 변하지 않는 부분을 별도의 패키지로 분리하는 것
    - 상위 정책을 구현하는 패키지와 세부 사항을 구현하는 클래스들을 서로 다른 패키지에 분리
- 제어 역전 원리
  - 프레임워크의 가장 기본적인 설계 매커니즘
  - 제어 흐름의 주체 역시 역전시킨다.
    - 상위 정책이 구체적인 세부사항에 의존하는 전통적인 구조에서는 상위 정책의 코드가 하부의 구체적인 코드를 호출한다.
      - 애플리케이션의 코드가 재사용 가능한 라이브러리나 툴킷의 코드를 호출한다.
    - 객체지향 구조에서는 반대로 프레임워크가 애플리케이션에 속하는 서브클래스의 메서드를 호출한다.
      - 애플리케이션에서 프레임워크로 제어 흐름의 주체가 이동한다.
      - 의존성을 역전시키면 제어 흐름의 주체 역시 역전된다.
    - 우리는 프레임워크가 적절한 시점에 실행할 것으로 예상되는 코드를 작성할 뿐이다.
      - 프레임워크가 호출하는 코드를 작성해야 한다.
      - 제어가 우리에게서 프레임워크로 넘어간 것이다.
