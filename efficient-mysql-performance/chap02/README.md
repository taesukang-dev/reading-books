## 인덱스와 인덱싱

- 최고의 선택도
  - 선택도는 카디널리티를 테이블의 행 수로 나눈 값
  - 선택도가 매우 낮은 인덱스는 각 고윳값이 많은 수의 행과 일치할 수 있으므로 거의 영향력이 없다.
    - record 가 100,000 일 때 99,999 가 `coffee` 이고 한 행만 `tee` 라면 tee 에는 훌륭하지만 coffee 에는 끔찍!
    - 위와 같은 경우 더 나은 선택도를 갖는 인덱스를 사용하도록 쿼리를 작성
    - 또는 스키마 변경
  - 선택도가 매우 높은 인덱스는 과도하게 활용될 수 있다.
    - unique key 와 비슷하거나 pk 로 사용될 수 있도록 쿼리를 다시 작성
    - 선택도가 매우 높은 세컨더리 인덱스가 많은 경우 다른 기준이나 차원으로 전체 테이블을 보거나 검색하는 접근 패턴을 나타낼 가능성이 높다.
      - 다양한 기준으로 검색하는 제품 인벤토리
      - 이 때에는 엘라스틱 서치가 더 적합할 수 있다.
- 테이블 조인 알고리즘
  - nested-loop join -> 중첩 foreach (n^n)
  - ```sql
    from
        t1 join t2 on t1.id = t2.id
        join t3 on t2.id = t3.id
    ```
    - 위와 같은 쿼리가 있을 때 t3 에 너무 자주 접근한다.
    - t1 의 일치하는 모든 행에 t2 의 모든 일치하는 행을 곱한 횟수로 접근
    - t1 과 t2 에 모두 10개 행이 일치하면 t3 에는 100번 접근
  - block nested-loop join -> 위의 t1, t2 에서 일치하는 행의 조인 열값을 조인 버퍼에 저장
    - `join_buffer_size` 만큼의 메모리를 사용
    - 조인 버퍼가 가득 차면 t3 를 스캔하고 조인 버퍼의 조인 열값과 일치하는 각 t3 행에 조인
  - hash join -> 블록 중첩 반복 조인 알고리즘 대체
    - t3 와 같은 조인 테이블을 메모리 내에 해시 테이블로 생성
    - 해시테이블을 사용하여 조인테이블의 행을 조회 (상수시간)