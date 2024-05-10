# SNSAPP

## 클린 아키텍처를 이용한 SNS 앱 개발 (2024/04/01 ~ 2024/05/08)

### :question: 프로젝트 설명

Compose UI를 이용하여 포스터를 올리고 삭제하는 CRUD 앱을 구현하는 프로젝트입니다.

MVI 아키텍처를 이용하여 앱을 구현했으며, 이 과정에서 Orbit 라이브러리를 이용하여 보일러 플레이트를 없애는 방향으로 구현했습니다.

## 🛠 기술 스택 및 도구

## <img src="https://img.shields.io/badge/android-34A853?style=for-the-badge&logo=android&logoColor=white"><img src="https://img.shields.io/badge/androidstudio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white"><img src="https://img.shields.io/badge/kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white"><img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"><img src="https://img.shields.io/badge/githubactions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/jetpackcompose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white">

| 기술 스택             |                                                              |
| --------------------- | ------------------------------------------------------------ |
| 안드로이드 라이브러리 | Compose UI<br />Orbit<br />Hilt (version 2.48)<br />Room(version 2.6.1)<br />Retrofit2(version 2.9.0)<br />Okhttp3<br />ksp<br />coil<br />datastore<br />Paging3 |
| 아키텍처              | MVI 아키텍처, 멀티모듈(data, domain, presentation 레이어 모듈로 분리) |
| CI/CD                 | Github Actions                                               |



## :pushpin: 구현 내용



### 1. 로그인 기능

이메일과 비밀번호를 이용하여 로그인을 진행, 회원가입을 통해 아이이를 생성할 수 있습니다.

| 로그인 | 로그 아웃 | 회원 가입 |
| ------ | --------- | --------- |
|        |           |           |



### 2. 프로필 이미지 설정 및 닉네임 변경 기능

| 프로필 이미지 변경 | 닉네임 변경 |
| ------------------ | ----------- |
|                    |             |



### 3. 포스터 업로드 및 삭제

| 포스터 생성 | 포스터 삭제 |
| ----------- | ----------- |
|             |             |



### 4. 댓글 추가 및 삭제



### 5. 포스터 본문 내용 텍스트 스타일 변경





### 6. 이외의 추가 기능들

+ 사용자가 만든 포스터, 댓글이 아닌 경우 취소 기능 삭제
+ 초기 업로드, 프로필 이미지 변경 시 카메라 권한 요청 다이얼로그 구현
+ 포스터 업로드를 서비스를 이용하여 백그라운드로 서버에 업로드 요청 구현

