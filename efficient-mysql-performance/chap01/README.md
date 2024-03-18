# 쿼리 응답 시간
- 쿼리 응답 시간
  - 쿼리를 실행하는 데 소요되는 시간
  - 소요 시간은 MySQL 이 쿼리를 받았을 때 시작되고 결과 세트를 클라이언트에 전송한 시점까지의 경과 시간

## 핵심 지표
- 의미있는 것 : 시간
- 실행 가능한 것 : 쿼리
- 종합 : 쿼리 응답 시간 향상에 집중 -> 하드웨어 문제로 돌리면 안됨
- 시끄러운 이웃 : multitenancy 에서 한 쪽 tenant 가 다른 쪽 tenant 에 영향을 줄만큼 과도하게 자원을 점유해서 사용하는 것

## 쿼리 보고
- 쿼리 메트릭은 응답 시간, 잠금 시간, 조회된 행 등 쿼리 실행에 관한 중요한 통찰력을 제공
  - 엔지니어에게 의미 있는 방식으로 수집, 집계 보고해야 하는 raw values
- 소스
  - 슬로 쿼리 로그
    - 슬로 쿼리 로그를 사용하기 보다 대신 성능 스키마를 사용할 것
  - 성능 스키마 : `performance_schema`
    - 쿼리 메트릭을 제공하는 가장 좋은 소스
- 집계
  - 쿼리를 어떻게 고유하게 식별하여 쿼리가 속한 그룹을 결정하는지
    - 정규화된 SQL 을 SHA-256 으로 해싱
  - explain 명령어
    - 샘플을 explain 한 다음 폐기하고 explain 계획을 보고
  - MySQL 은 SQL 문을 정규화하여 다이제스트 텍스트를 생성하여 정규화
- 보고
  - 쿼리 프로파일
    - slow query
    - 쿼리 총시간
      - 쿼리당 실행 시간의 총합
      - MySQL 이 실행하는 데 가장 많은 시간을 소비하는 쿼리가 어느 것인지에 대한 대답
      - e.g. A는 응답 시간이 1초이고 10번 실행되지만 B 는 0.1초이고 1000번 실행된다면 10초 대 100초, B 가 가장 느린 쿼리 -> B 를 최적화하여 MySQL 시간을 확보
    - 실행 시간 비율
      - 쿼리 총시간 / 실행 총시간
    - 쿼리 부하
      - 쿼리 총시간 / 클럭 타임 (clock time)
      - E 의 쿼리 총 시간이 250.2s 이고 시간 범위가 5분이면 250.2 / 300 = 0.83
      - 쿼리 부하가 높을수록 쿼리가 동일하거나 가까운 행에 접근할 때 경합할 가능성이 커짐
  - 쿼리 보고서
    - 쿼리에 대해 알아야 할 모든 것을 보여줌
- 쿼리 분석
  - 쿼리 실행을 이해하려는 것
  - 쿼리 메트릭
    - 성능 스키마는 9가지 필수 쿼리 메트릭을 모두 제공
  - 쿼리 시간
    - 가장 중요 메트릭
    - 쿼리 시간에는 lock time 도 포함됨
  - 잠금 시간
    - 쿼리를 실행하는 동안 잠금을 획득하여 사용한 시간
    - 성능 스키마의 잠금 시간에는 로우 락 대기가 포함되지 않고 테이블과 메타데이터 락 대기만을 포함
    - 대조적으로 슬로 쿼리 로그의 잠금 시간에는 메타데이터, 테이블, 로우 와 같은 모든 락 대기가 포함
    - 로우에 하나의 쿼리만 접근해야 한다면 잠금 시간 0 -> x lock, s lock 등이 없다면
    - 비잠금 읽기(nonlocking read) 는 로우 락을 획득하지 못하더라도 메타데이터 락, 테이블 락을 획득하므로 잠금 시간은 0이 아님
      - 이 2가지를 획득하는 것은 1ms 미만으로 매우 빠름
    - select 시에도 shared metadata lock 을 획득하므로 잠금 시간은 0이 아님
      - alter table 은 배타 메타데이터 락을 획득하는 일반적인 방법
      - pt-online-schema-change 도 rename 할때에는 배타 메타데이터 락을 획득
    - **lock 과 관련된 중요 사항**
      - innodb_lock_wa_timeout 시스템 변수가 각각의 로우 락에 적용되므로 잠금 시간은 이보다 상당히 클 수 있다.
      - 잠금과 트랜잭션 격리 수준은 서로 관련되어 있다.
      - InnoDB 는 쓰지 않는 행을 포함하여 접근하는 모든 행을 잠근다.
      - 잠금은 트랜잭션 커밋이나 롤백할 때 해제되며 때로는 쿼리 실행 중에도 해제된다.
      - InnoDB 에는 record, gap, next-key 등 다양한 유형이있다.
  - 조회된 행
    - 조회된 행, rows examined 는 MySQL 이 쿼리 조건 절에 일치하는 행을 찾으려고 접근한 행의 수를 나타낸다.
      - 쿼리와 인덱스의 선택도를 나타낸다, 쿼리와 인덱스의 선택도가 높을 수록 MySQL 이 일치하지 않는 행을 조회하는 데 낭비하는 시간이 줄어든다.
  - 보낸 행
    - 보낸 행, rows sent 는 클라이언트에 반환된 행의 수(결과 세트 크기)를 나타낸다.
    - 보낸 행 = 조회된 행
      - 이상적인 경우 보낸 행과 조회된 행이 같고 특히 전체 행의 백분율로 계산했을 때 상대적으로 값이 작고, 허용할 수 있는 쿼리 응답 시간일 때
      - e.g. 백만개 중의 1000 개는 0.1 응답 시간이 허용 가능하면 이상적, 만개 중의 1000 개는 응답 시간이 허용되더라도 비율 10% 문제의 소지
      - 비율과 관계 없이 보낸 행과 조회된 행이 같고 값이 의심스러울 정도로 높으면 쿼리가 테이블 스캔을 유발한다는 것
    - 보낸 행 < 조회된 행
      - 조회된 행보다 보낸 행이 작다는 것은 쿼리나 인덱스의 선택도가 좋지 않다는 신뢰할만한 신호
    - 보낸행 > 조회된 행
      - 가능하지만 드문 경우
    - 슬로 쿼리 로그에 보낸 바이트 메트릭이 있으면 확인해볼 수 있음
  - 영향받은 행
    - 영향박은 행, rows affected 는 삽입, 갱신, 삭제된 행의 수
  - 셀렉트 스캔
    - select scan 은 첫번째로 접근한 테이블에서 수행한 전체 테이블 스캔 횟수를 나타냄
    - 셀렉트 스캔이 0이 아니면 이는 쿼리가 인덱스를 사용하지 않는다는 것을 의미하므로 성능에 좋지 않음
    - index 걸어야됨
  - 셀렉트 풀 조인
    - select full join 은 조인된 테이블을 대상으로 전체 테이블을 스캔한 수
    - 셀렉스 스캔과 유사, 0이 아니면 쿼리 최적화
  - 디스크에 생성된 임시 테이블
    - created tmp disk table 은 디스크에 생성된 임시 테이블의 수를 나타냄
    - 과도할 때에는 쿼리 최적화 혹은 tmp_table_size 가 너무 작을 수 있음
  - 쿼리 카운트
    - query count : 쿼리 실행 횟수
    - 기준이 없고 임의적
  - 메타데이터와 애플리케이션
    - explain 계획, 테이블의 구조
  - 평균, 백분위수, 최대
    - 평균은 지나치게 낙관적
    - 백분위수는 어디까지나 추정
    - 최대는 최상의 표현
    - 평균
      - 쿼리 수가 적을 때는 에지케이스가 평균을 왜곡할 수 있음
    - 백분위수
      - 평균이 갖는 문제를 보완
      - 상위 N% 가 특잇값이 아닌 것으로 검증되기 전까지는 정상이 아니므로 관심을 가져야 하는 값
      - 가장 바람직한 백분위수는 P999(99.9%) -> 0.% 의 값을 무시하는 것이 특잇값이라고 추정하는것과 특잇값이 실제로 존재한다는 현실 사이에서 허용할 수 있는 절충점
    - 최대
      - 최대 쿼리 응답 시간
      - 버리지말고 분석하라

## 쿼리 응답 시간 개선
- 직접 쿼리 최적화
  - 쿼리와 인덱스를 변경
- 간접 쿼리 최적화
  - 데이터와 접근 패턴을 변경
  - 쿼리가 접근하는 대상과 방법을 변경

## 언제 쿼리를 최적화해야 할까?
- 성능이 고객에게 영향을 미칠때
- 코드 변경 전후
- 한 달에 한번

## 정리
- 성능은 쿼리 응답 시간, 즉 MySQL 이 쿼리를 실행하는 데 걸리는 시간
- 쿼리 응답 시간은 의미 있고 실행 가능하므로 MySQL 성능에서 핵심 지표
- 쿼리 메트릭은 슬로 쿼리 로그나 성능 스키마에서 비롯됨
- 성능 스키마는 쿼리 메트릭 중 최상의 소스
- 쿼리 메트릭은 다이제스트에 의해 그룹화되고 집계됨
- 쿼리 프로파일은 느린 쿼리를 보여 주며 느리다는 것은 정렬 메트릭과 관련
- 쿼리 보고서는 하나의 쿼리를 대상으로 사용할 수 있는 모든 정보를 보여 주며 쿼리 분석에 사용
- 쿼리 분석의 목표는 느린 응답 시간을 해결하는 것이 아니라 쿼리 실행을 이해하려는 것
- 쿼리 분석에는 쿼리 메트릭, 메타데이터, 애플리케이션 지식을 사용