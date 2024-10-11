# Review System
상품에 대한 리뷰를 작성하고, 상품별 리뷰 점수, 개수, 그리고 리뷰 내용을 조회 가능한 시스템

## 빌드 및 실행

### Docker 이미지 빌드
1. 환경 변수 설정
```
IMAGE_NAME = (빌드할 Docker 이미지 이름)
```
2. Gradle `jibDockerBuild` 실행

### Docker Compose 실행
1. 환경 변수 파일 생성
   1. `.env.db`
    ```
    MYSQL_ROOT_PASSWORD = (MySQL 루트 비밀번호)
    MYSQL_DATABASE = (생성할 데이터베이스 이름)
    ```
   2. `.env.backend`
    ```
    DB_URL = jdbc:mysql://review-system-db:3306/{데이터베이스 이름}
    DB_USERNAME = root
    DB_PASSWORD = (MySQL 루트 비밀번호)
    ```
2. 실행
```
docker compose up -d
```