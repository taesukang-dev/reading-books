# 설계 품질과 트레이드오프
- 역할, 책임, 협력
  - 협력은 애플리케이션의 기능을 구현하기 위해 메시지를 주고받는 객체들 사이의 상호 작용
  - 책임은 객체가 다른 객체와 협력하기 위해 수행하는 행동
  - 역할은 대체 가능한 책임의 집합
  - 책임이 객체지향 app 전체의 품질을 결정
- 객체지향 설계
  - 올바른 책임을 할당하면서 낮은 결합도와 높은 응집도를 가진 구조
    - 객체의 행동에 초점을 맞춘다.
- 이번 장에서는 데이터 중심의 설계(나쁜 설계)를 알아봄
- 캡슐화
  - 내부 구현을 외부로부터 감춘다
    - 구현 : 나중에 변경될 가능성이 높은 것
    - 인터페이스 : 상대적으로 안정적인 부분
    - 한 곳에서 일어난 변경이 전체에 영향을 끼치지 않도록 파급효과를 적절하게 조절
    - 변경 가능성이 높은 부분은 내부에 숨기고 외부에는 상대적으로 안정적인 부분만 공개
    - 불안정한 구현 세부사항을 안정적인 인터페이스 뒤로 캡슐화
- 응집도와 결합도
  - 응집도 : 모듈에 포함된 내부 요소들이 연관돼 있는 정도
    - 모듈 내의 요소들이 하나의 목적을 위해 긴밀하게 협력한다면 그 모듈은 높은 응집도를 가진다.
    - 모듈 내의 요소들이 서로 다른 목적을 추구한다면 낮은 응집도를 가진다.
    - 응집도는 객체 또는 클래스에 얼마나 관련 높은 책임들을 할당했는지를 나타낸다.
  - 결합도 : 의존성의 정도, 다른 모듈에 대해 얼마나 많은 지식을 갖고 있는지
    - 꼭 필요한 지식만 알고 있다면 낮은 결합도를 가진다.
  - 높은 응집도와 낮은 결합도
    - 설계를 변경하기 쉽게 만든다.
    - 응집도 : 변경이 발생할 때 모듈 내부에서 발생하는 변경의 정도
    - 결합도 : 한 모듈이 변경되지 위해서 다른 모듈을 변경을 요구하는 정도
