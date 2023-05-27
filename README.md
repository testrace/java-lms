# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 리팩토링 목록
- [ ] QnaService#deleteQuestion의 서비스 로직을 도메인 객체로 이동한다
  - [ ] 질문 삭제 로직을 Question 으로 이동
  - [x] 답변 삭제 로직을 Answer 으로 이동
    - [x] 여러개의 답변을 처리하기 위한 일급 컬렉션 추가
