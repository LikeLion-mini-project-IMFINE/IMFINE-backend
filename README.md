# Im-Fine

로컬 테스팅을 위한 실행 방법입니다!

## Requirements

프로젝트 실행을 위해 필요한 요구사항:
* Java 17 이상
* Spring Boot 3.x
* Gradle 7.x 이상
* IntelliJ IDEA

## Installation & Local Setup

### 1. 프로젝트 클론
```bash
$ git clone https://github.com/LikeLion-mini-project-IMFINE/IMFINE-server.git
```

### 2. IntelliJ IDEA에서 프로젝트 열기
1. IntelliJ IDEA 실행
2. `File` → `Open` 선택
3. 클론한 프로젝트 디렉토리 선택 후 `Open`
4. 프로젝트를 처음 열 때 Gradle 빌드가 자동으로 시작됨 (우측 하단에 진행률 표시)

### 3. 환경 설정
- 프로젝트는 AWS RDS를 사용하고 있어 별도의 로컬 데이터베이스 설정이 필요하지 않습니다.
- 필요한 데이터베이스 접속 정보는 이미 설정되어 있습니다.

### 4. 프로젝트 실행
방법 1: IntelliJ UI 사용
1. 우측 상단의 실행 구성 드롭다운 메뉴에서 `ImFineBackendApplication` 선택
2. ▶️ (실행) 버튼 클릭

방법 2: 직접 메인 클래스 실행
1. `src/main/java/org/zerock/likelion/imfinebackend/ImFineBackendApplication.java` 파일 열기
2. 클래스 왼쪽의 ▶️ 버튼 클릭 → `Run 'ImFineBackendApplication'` 선택

### 5. 실행 확인
- 콘솔에서 Spring Boot 애플리케이션이 정상적으로 시작되는지 확인
- 기본적으로 `http://localhost:8080`에서 서버가 실행됨
- Swagger UI (API 문서)는 `http://localhost:8080/swagger-ui.html`에서 확인 가능

### 문제 해결

#### Gradle 빌드 실패 시
1. Gradle 새로고침
   - IntelliJ 우측의 Gradle 탭 → 새로고침 버튼 클릭
2. 프로젝트 클린 빌드
   ```bash
   ./gradlew clean build
   ```

#### JDK 설정 문제
1. `File` → `Project Structure` 선택
2. `Project` 탭에서 SDK가 Java 17 이상으로 설정되어 있는지 확인
3. 필요한 경우 `Download JDK` 버튼을 통해 적절한 버전 설치
