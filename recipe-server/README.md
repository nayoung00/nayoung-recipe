# 46_7 - 필터를 사용하여 사용자 접근 제어하기 


## 작업 소스 및 결과

- src/main/java/kny/cook/filter/AuthFilter.java 추가


### 작업1: 로그인 여부를 검사하는 필터를 추가한다.

- kny.cook.filter.AuthFilter 추가
  - 로그인 하지 않은 사용자는 add/delete/update를 수행할 수 없다.
  - 로그인 페이지로 보낸다.
  
