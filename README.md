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
4. 파이버에스 로그인 -> 파이어베이스 리얼타임 데이터 베이스에 카카오 로그인과 동일하게 정보 저장 , 그 이후로는 카카오 로그인 했을 때 와 앱 내부의 활동이 동일하다.(완료)

2024-05-31 , 2024-06-01
1. 파이어베이스 다이나믹 링크 : 회원가입 화면2 : 이메일 인증 -> 이메일 받아서 링크 누르면 -> 회원가입 화면 2로 다시 돌아와서 회원가입 진행 : 인증X -> 회원가입 진행 못함
2. 파이어베이스 다이나믹 링크 : 암시적 딥링크 , 명시적 딥링크 둘 다 사용해서 시간이 오래 걸렸으나 명시적 딥링크 , Activity 시작모드를 signletop으로 선언함으로서 해결
3. 딥링크 처리는 onNewIntent에서 , 스플래시 ㅘ면은 onCreate에서 처리함으로서 스플래시 화면과 딥링크의 크래시 해결


2024-06-02
1. 로그인 화면 카카오이메일 , 비밀번호 -> UI 수정하기(완료)
2. 회원가입1 화면 약관보기 버튼 UI 수정하기(완료)
3. 회원가입2 화면 이메일 중복 확인하기
4. 회원가입2 화면 비밀번호 값 조건 알려주기 : 6글자 이상아니면 에러 발생(완료)
5. 회원가입2 이메일 인증하기 버튼 , 입력칸 수정하기 (완료)
6. 회원가입2 이메일 인증하기 버튼 누르면 이메일 확인해달라고 토스트 메시지 띄우기(완료)
7. 전화번호 입력칸 제약 조건 설정하기(완료)
8. 로그인2 화면 추가 : 로그인 로직 이동 : 로그인1 화면 -> 로그인2 화면 (기존의 로그인 화면 삭제)
9. 로그인2 화면 : 계정없으면 회원가입 화면 이동 (완료)
10. 로그인2 화면 : 안드로이드 -> 파이어베이스 디비 저장 (수정 및 완료)

2024-06-03
1. KakaoAuthViewModel && FirebaseAuthViewModel Merge -> AuthViewModel (로그인 및 회원가입 변수 병합 , 함수 로직은 분리 , 로그인 및 회원가입은 무조건 AuthViewModel에서 실행한다.) (완료)
2. AuthViewModel과 롼련된 UseCase , Repository , RepositoryImpl 들을 역할에 맞게 이름 수정 및 주석 달았음. (완료)
3. 퍼블릭 다이어리에서 삭제 버튼 보이는 여부를 다이어리 작성자의 이름의 동일 여부에서 작성자의 유저 식별 번호가 동일 여부로 변경하였음. (완료)

2024-06-04
1. 퍼블릭 다이어리 스크롤 시 패깅3를 통해서 데이터를 불러올 때 인디케이터가 무한 로딩 -> 풀 투 리케스트 삭제하고 패깅3의 레이지아이템의 상태를 refresh로 호출하는 함수를 , 다이어리 삭제 로직에 추가하였음. (완료)
2. 내 다이어리 -> 공개 다이어리 에서 내가 삭제하고 싶을 때 삭제하는 버튼 아이콘 수정 (완료)
3. 마이페이지 화면 -> 로그아웃 화면 : 로그아웃 버튼 수정 (완료)
4. 마이페이지 화면 -> 로그아웃 화면 : 로그아웃 다이아로그 색상 , 버튼 색상 , 텍스트 색상 수정 (완료)
5. 마이페이지 화면 -> 저장 일기 화면 유아이 수정 및 navcontroller 추가 (완료)
6. 상품 화면 : 내 친구 돌돌이 가운데 정렬 , 색상 변경 (완료)
7. 상품화면 : 돌 상세 정보 화면 유아이 수정 (완료)
8. 구매화면 : 은행 이미지 유아이 사이즈100 && 균등분재로 수정 , (출금은행 , 계좌번호 , 입금자명) : (명령문1 , 명령문2 , 명령문3) -> 1:4 비율로 분배 (완료)
9. 웹뷰에서 우편번호 가져와 -> InputAddressInfoScreen 경로에 encodedProductInfo 기본 값 추가 완료

2024-06-05
1. 웹뷰에서 우편번호 가져오는 로직 수정 : (1) 기본 꼴 [ 마이페이지 화면 -> 개인정보 화면 -> ( 저장하기 ) 주소 입력화면 -> 웹뷰 -> ""값 넣은 DomainProductModel 세팅 -> 주소 입력화면 ]
2. 웹뷰에서 우편번호 가져오는 로직 수정 : (2) 변홍 꼴 [ 상품화면 -> 구매화면 -> ( 새로운 주송 입력 클릭 후 ) ( 새로운 주소 저장하기 ) 주소 입력화면 -> 웹뷰 -> ( 여기서 ""값 넣은 DomainProductModel이 세팅 되면서 구매하려는 상품 정보가 빈값이라 문제 발생 )-> 구매화면 :: 따라서 새로운 주소를 저장 할 때는 구매화면에서 웹뷰로 특정 DetailProductModel을 넣어주고 그걸 받아서 그대로 구매화면으로 전달해야 한다. ::: 따라서 네비게이션에서 arguments를 전달할때 네비게이션 스택의 이전 스택의 목적지에 따라서 DomainProductModel의 객체를 생성 할 때 , 기본꼴이면 ""값으로 세팅된 DomainProductModel을 넣어주고 , 특별한 케이스인 구매화면에서 새로운 주소를 입력할 때에는 해당 구매 정보를 넘겨주면 된다. (완료)
3. 구매화면 : 우편번호 , 주소 , 상세주소 , 전화번호 관련 UI DesignSystem으로 만들어서 사용 : DefaultRowTwoOutLinedTextField 함수 사용
4. 주소 입력화면 -> 빈 값 있으면 토스트 메시지 , 빈 값 없으면 로직 그대로 수행
5. 구매화면 : 주문하기 버튼 : 빈 값없으면 파베에 데이터 업로드 , 구매 내역 스크린에 데이터 렌더링 , BaseDialog를 통해서 케이스별 토스트 메시지 및 네비게이션 경로 설정(완료)

2024-06-06
1. 마이페이지 -> 구매내역 화면 : 상품 정보 / 배송지 정보 / 결제 정보 : 3가지의 섹션별로 분리해서 사용자에게 알려줌. (완료)
2. 마이페이지 -> 구매내역 화면 : 출발여부 , 위치추적 : users/baesongNaeYeock의 하위 노드에 startBoolean , productTrace 노드 두개 추가해서 받아오기 (완료)
3. 마이페이지 -> 구매내역 확인 : users/baesongNaeYeockl의 하위 노드에 arrivedTime(도착 예정 시간) , location(현재 위치 추적) 노드 추가 -> 파이어베이스에서 데이터 업데이트가 되어졌을 때에만 구매내역 화면에 [ 출발여부 , 도착날짜 , 위치추적 ] 가능 (완료)
4. 마이페이지 -> 구매내역 확인 : 현재 배송위치의 색상은 빨간색으로 표시되는 것으로 변경 (완료)
5. 마이페이지 -> 내 쿠폰 화면 : 쿠폰 없으면 받으러 가는 네비게이션 버튼 설정  , 쿠폰 있을 때 유아이 수정 및 추가 (완료)

2024-06-07
1. 멀티모듈 최적화 : [ data:data-impl ] 모듈 삭제 -> 모듈을 너무 세분화 시키는 것은 좋지 않다고 판단하였음 (완료) : 내용물은 일단 data 모듈로 보냄 (완료)
2. 멀티모듈 최적화 : [ data:data-mapper ] 모듈 삭제 -> 모듈을 너무 세분화 시키는 것은 좋지 않다고 판단하였음 (완료) : 내용물은 일단 data 모듈로 보냄 (완료)
3. 멀티모듈 최적화 : [ data:data-datasource ] 모듈 삭제 -> 모듈을 너무 세분화 시키는 것은 좋지 않다고 판단하였음 (완료) : 내용물은 일단 data 모듈로 보냄 (완료)
4. 로그인 할 때 : 만약 앱을 다시 다운받았따면 로컬에 userinfo가 저장되어 있지 않아서 에러가 발생한다. 따러서 로그인 할 때에도 유저정보를 로컬에 저장하는 로직 추가 (완료)
5. 다오에서 업데이트 로직 아이디 0 -> 아이디 1 수정해서 그동안 하나의 휴대폰에서 삭제시키지 않고 동일하게 유지되는 앱에서 여러 계정사용해서 로그인해도 계정별 식별 가능하게 하였음. (완료)

2024-06-08
1. 멀티모듈 최적화 : Core모듈 -> NetWork모듈 -> 세부모듈은 폴더화 : 네트워크 모듈에서 각각의 모듈을 별도로 빌드할 일 없을 것 같아서 그냥 폴더로 변경 후 Core-NetWork 단일모듈에서 관리하는 것이 좋겠다고 판단하였음.


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


















