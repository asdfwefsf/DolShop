# DolShop 개발 일지 2024-04-11 ~ 
2024-04-11
1. 클린 아키텍처 구조 구성 완료 && 프로젝트 구조 모듈화 완료 (세부 모듈 미완료 : 개발 진행에 맞게 세부 모듈 세팅 예정)

2024-04-12
1. HILT 적용 완료

2024-04-13
1. 디자인 시스텐 세팅 완료
2. buildSrc 세팅 완료
3. DDD 설계 완료 (DDD 용어 정리 완료 , DDD에서 추가로 사용할 용어 정리 완료)

2024-04-17
1. BottomBar Navigation Setting 완료
2. Navigation UI Setting 완료

2024-04-20
1. kakao 로그인 연동 완료

2024-04-21
1. 상품 json 데이터 받아오기 + UI에 넣기 완료
2. SettingScreen -> MyPageScreen 완료

2024-04-23
1. Product Screen UI (ing day 1)
2. CoroutineWorker 앱 최초 실행 , 앱 빌드 실행 함수 : BaseProduct Screnn Data Room Db Save Function (완료)
3. Navigation Bar Counter ViewModel Setting (완료)
4. DDD 세팅 (-ing)
5. Figma Setting (-ing)

2024-04-24
1. BaseProduct Screen UI Room Db Save (완료)

2024-04-25
1. Product Screen UI Perfect Setting
2. add BaseProductDao function : insertBaseProductInfo , updateBaseProductInfo , provideProductExists
3. seperate logic : BaseProductInfo , GabyeonBaseProductInfo
4. ProductScreen Logic Perfect Setting Success

2024-04-26
1. Product Screen LazyColumn Logic Sujeong(완료)
2. Product Screen LazyColumn + innerPadding(완료)

2024-04-27 ~ 2024-04-28
1. 멀티모듈 구조 설정 (완료)

2024-04-30
1. Firebase RealtimeDB 개인정보 연동 (완료)
2. 주소 검색 웹뷰 설정 (완료)

2024-05-01
1. MyPageScreen에서 배송지 정보 저장
2. 배송지 정보 UI 렌더링

2024-05-02
1. AuthInfo (완료)
2. Address logic (완료)

2024-05-03
1. Dol's FireStoreDatabase -> FirebaseRealtimeDatabase 이미지 저장(완료)
2. Dol's 갤러리 선택 && 이미지 최적화(완료)
3. AddRockScreen (완료)
4. FirebaseStorage setting(완료)
5. FirebaseStorage Logic OK (완료)

2024-05-04
1. FirebaseRealtimeDatabase -> RockScreen 백그라운드(완료) -> 삭제 할 수 도 ... ?<2024-05-07>

2024-05-05
1. AddRockScreen -> FireStoreDatabase -> FirebaseRealtimeDatabase -> RockScreen (완료)

2024-05-06
1. RockScreen UI 수정 (완료)
2. RockScreen DiaryItem sort Logic (완료)
3. DiaryNumber FirebaseRealtimeDB ADD (완료)
4. DiaryNumber Room(완료)
5. 아키텍처 최적화 (완료)

2024-05-07
1. Communitiy Screen 기획(완료)
2. Announcement Screen 기획(완료)
3. RockScreen 오늘 날짜만 받아오기 -> 모든 날짜 받아오기 (완료)
4. RockScreen[ Today Diary , Spefic Day Diary , All Day Diary] : ImagePagingSource seperate ImagePagingSource1 , ImagePagingSource2 + Add CalanderDialog(완료)

2024-05-08
1. Community Screen : 좋아요 , 댓글 , 알림 : private diary -> public diary (완료))
2. Announcement Screen : 광고 , 공지 (완료)

2024-05-09
1. 멀티모듈 구조 수정(완료)

2024-05-10
1. 디자인 시스템 추가 (완료)
2. 커뮤니티 스크린 사진 클릭 이벤트 추가 (완료)
   
2024-05-12
1. 테스트 코드 제거 (완료)
2. 커뮤니티 스크린 좋아요 기능 추가 (완료)

2024-05-13
1. 커뮤니티 스크린 좋아요 기능 수정 (완료)
2. 파이어베이스 DB 구조에 PublicDiary 노드 추가 ( 좋아요 기능 : 추후에 댓글 기능 추가 예정) (완료)

2024-05-14
1. 퍼블릭 다이어리 좋아요 선택 시에 따른 UI 업데이트 -> UI 업데이트와 파이어베이스 데이터베이스 실행 분리 (진행중)

2024-05-15
1. 퍼블릭 다이어리 좋아요 선택 시에 따른 UI 업데이트 -> UI 업데이트와 파이어베이스 데이터베이스 실행 분리 (완료)
2. 저장버튼 누른 퍼블릭 다이어리 룸 DB 저장 (완료)
3. 마이페이지->저장한 퍼블릭 다이어리 확인 (완료)

2024-05-16
1. 삭제버튼 누른 퍼블릭 다이어리 룸 DB 삭제 (완료)
2. Announcement 스크린 -> 공지 , 광고 스크린 변환 (완료)
3. 공지 , 광고 api 만들기 (완료)
4. 공지 , 광고 api 받아서 UI 업데이트 하기 && 딥링크 걸기 (완료)

2024-05-18
1. 마이페이지 -> 저장일기 -> UI 수정
   
2024-05-19

2024-05-20 [배포전 에러 테스트 , 기능 추가]
1. 유저정보가 기본값으로 파이어베이스 저장 -> 로그인 성공시 플로우 콜렉트 해서 비동기 처리에 조건문 설정하여 해결 (완료)
2. 좋아요 클릭시 joayo 노드를 삭제하던 기존의 방식 -> 해당 유저 식별번호만 joayo 노드에서 삭제 (완료)
3. 마이 다이어리 -> 퍼블릭 다이어리 이동 시 유저 네임 안 보여 -> PublicDiary 객체에 별도로 username 넣어주어 해결 (완료)
4. 퍼블릭 다이어리에 있는 내 다이어리 삭제 기능 추가 (완료)
5. 앱 아이콘 변경 및 추가 (완료)
6. 커뮤니티 스크린에서 다이아로그에서 특정 퍼블릭 삭제 시 다이아로그 창 유지 -> 커뮤니티 스크린에서 다이아로그에서 특정 퍼즐릭 다이어리 삭제 시 다이아로그 값 false , selected diary = null 설정하여 해결 (완료)

2024-05-21
1. 바텀네비게이션 아이콘 변경 (완료)
2. 쿠폰 스크린 생성 (완료)

2024-05-22
1. 쿠폰 저장 여부에 따라서 쿠폰 스크린 여부에 보이는 화면이 다름 (완료)
2. 쿠폰 저장 O -> 쿠폰함으로 이동 (완료)
3. 쿠폰1 , 쿠폰2 각각 쇼유 여부에 따른 렌더링 (완료)

2024-05-24
1. 상품화면 -> 상품 상세화면 : 데이터 전달 완료 : Json URL Encoding Decoding 문제 킹받네 (완료)
2. 상품 상세화면에 데이터 표시하기 (상품 사진 및 상품 설명 및 상품 이름) (완료)

2024-05-25
1. 상품 상세화면 UI 기깔나게 만들기(완료)
2. 결제 화면 만들기 (완료)
3. 결제 화면UI 기깔나게 만들기(완료)
4. 마이페이지 -> 결제 정보 -> 주문내역(상품명 , 상품 갯수 , 가격 , 보내야 되는 계좌번호) 렌더링 (완료)
5. 구매화면 -> 기존 주소 가져오기 + a : 애니메이션 (완료)

2024-05-26
1. 주문자의 입금관련 정보 (송신 은행명 , 송신 계좌번호 , 송신자 성함) -> Firebase 저장하기 (완료)
2. 주문자의 입금관련 정보 (송신 은행명 , 송신 계좌번호 , 송신자 성함) -> Android System 가져오기 (완료)
3. 구매화면 -> 구매 내역 확인 다이아로그 (완료)

2024-05-27
1. 파이어베이스 -> 마이페이지 -> 주문내역 : 파이어베이스에 저장된 정보 가져오기 (완료)
2. 파이어베이스 -> 마이페이지 -> 주문내역 유아이 작성 1차 (완료)
3. 구매 화면에 [ 상품 이름 추가 , 상품 사진 추가 , 상품 주문 갯수 추가] (완료)
4. 안드로이드 -> 파이어베이스 : 상품 갯수 , 상품 이름 (완료)

2024-05-28
1. 구매 화면 -> 새로운 주소 입력하고 싶으면 새로운 주소 입력 화면 : previousBackStackEntry 활용해서 이전 루트에 따른 로직 변경
2. 회원가입1 , 회원가입2 , 회원가입3 화면 구성
3. 로그인 화면 수정 : 회원가입 , 계정찾기 , 문의하기

2024-05-29
1. 로그인 화면 -> 회원가입 화면 -> 동의 할 때 개인정보 처리방침 , 서비스 이용약관 누르면 : 각각 웹뷰로 이동 (완료) : 노션X : 내 사이트에 링크를 웹뷰로 설정하였음.
2. 회원가입1 : 개인정보 처리방침 , 서비스 이용약관 동의해야지 회원가입2 화면으로 넘어갈 수 있도록. (완료)

2024-05-30
1. 회원가입2 : 사용자 정보 입력 후 파이어베이스에서 회원가입 하는 로직 (완료)
2. 회원가입3 : 회원가입 완료 시 보여주는 화면에서 로그인 관련 안내 (완료)
3. 파이어베이스 회원가입 && 카카오 회원가입 로직 분리 (완료)
4. 파이버에스 로그인 -> 파이어베이스 리얼타임 데이터 베이스에 카카오 로그인과 동일하게 정보 저장 , 그 이후로는 카카오 로그인 했을 때 와 앱 내부의 활동이 동일하다.

2024-05-31 , 2024-06-01
1. 파이어베이스 다이나믹 링크 : 회원가입 화면2 : 이메일 인증 -> 이메일 받아서 링크 누르면 -> 회원가입 화면 2로 다시 돌아와서 회원가입 진행 : 인증X -> 회원가입 진행 못함
2. 파이어베이스 다이나믹 링크 : 암시적 딥링크 , 명시적 딥링크 둘 다 사용해서 시간이 오래 걸렸으나 명시적 딥링크 , Activity 시작모드를 signletop으로 선언함으로서 해결
3. 딥링크 처리는 onNewIntent에서 , 스플래시 ㅘ면은 onCreate에서 처리함으로서 스플래시 화면과 딥링크의 크래시 해결


2024-06-02
1. 로그인 화면 카카오이메일 , 비밀번호 -> UI 수정하기
2. 회원가입1 화면 약관보기 버튼 UI 수정하기
3. 회원가입2 화면 아이디 중복 확인하기
4. 회원가입2 화면 비밀번호 값 조건 알려주기
5. 회원가입2 이메일 인증하기 버튼 , 입력칸 수정하기
6. 회원가입2 이메일 인증하기 버튼 누르면 이메일 확인해달라고 토스트 메시지 띄우기
7. 전화번호 입력칸 제약 조건 설정하기
8. 로그인2 화면 추가 : 로그인 로직 이동 : 로그인1 화면 -> 로그인2 화면 (기존의 로그인 화면 삭제)
9. 로그인2 화면 : 계정없으면 회원가입 화면 이동

# 적용된 안드로이드 개념
1. Presentation -> Domain <- Data : 공식문서에서 지향하는 클린 아키텍처와 다른 안드로이드 클린아키텍처
2. MVVM
3. Hilt
4. Compose
5. MultiModule
6. Navigation
7. Retrofit
8. KaKao SDK
9. DataStore Vs SharedPreferences
10. Coroutine
11. Flow
12. BottomNavigation
13. 웹뷰
14. Pagging
# 적용된 안드로이드 개념 이론 정리


# Network
1. Firebase Realtime DataBase
2. Firebase Firestore Database
3. JSONBin.io

# TODO
1. Navigation Setting(-ing)
2. buildSrc Setting(O)
3. design System Setting(O)
4. ui set setting(not yet)
5. DDD 설계(-ing)
6. DDD 용어 정리(O)
7. DDD에서 추가로 사용할 용어 정리(O)
8. Navigation UI Setting (-ing)
9. kakao 로그인 연동(O)
10. kakao 로그인 정보 저장
11. Firebase DB 연동
12. 상품 json 데이터 받아오기 + UI에 넣기(O)
13. BottomBar Navigation Setting(O)
14. MyPageScreen -> SettingScreen(O)
15. Product Screen UI 
16. BaseProduct Screen UI Room Db Save
17. CoroutineWorker 앱 최초 실행 , 앱 빌드 실행 함수 : BaseProduct Screnn Data Room Db Save Function
18. Navigation Bar Counter ViewModel Setting


















