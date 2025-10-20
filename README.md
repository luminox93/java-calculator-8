<div align="center">

![header](https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=12&height=280&section=header&text=String%20Calculator&fontSize=80&fontAlignY=35&desc=문자열%20덧셈%20계산기&descAlignY=58&descSize=25)

</div>

<br>

## 📋 과제 설명
입력한 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

<br>

## ✨ 기능 요구 사항

> **구분자 처리**
> - 쉼표(`,`) 또는 콜론(`:`)을 구분자로 가지는 문자열을 전달하는 경우 구분자를 기준으로 분리한 각 숫자의 합을 반환한다.
> - 앞의 기본 구분자(쉼표, 콜론) 외에 커스텀 구분자를 지정할 수 있다.
> - 커스텀 구분자는 문자열 앞부분의 `"//"와 "\n"` 사이에 위치하는 문자를 사용한다.
> - 빈 문자열을 입력할 경우 0을 반환한다.

> **예외 처리**
> - **null 입력**: 입력값이 null인 경우 `IllegalArgumentException` 발생
> - **음수 입력**: 음수가 포함된 경우 `IllegalArgumentException` 발생
> - **숫자가 아닌 값**: 숫자로 변환할 수 없는 값이 포함된 경우 `IllegalArgumentException` 발생
> - **잘못된 커스텀 구분자 형식**: `//`로 시작하지 않거나 `\n`이 없는 경우 `IllegalArgumentException` 발생
> - 예외 발생 시 애플리케이션은 종료되어야 한다.

<br>

---

## 🏗️ 프로젝트 구조 설계

### 📦 패키지 구조
```
calculator/
├── Application.java           (메인 실행)
├── controller/                (흐름 제어)
│   └── CalculatorController.java  (전체 실행 흐름 제어)
├── domain/                    (비즈니스 로직)
│   ├── Calculator.java        (덧셈 계산 담당)
│   ├── StringParser.java      (문자열 파싱 오케스트레이션)
│   ├── DelimiterHandler.java (구분자 추출 및 처리)
│   ├── DelimiterExtractor.java (구분자 추출)
│   ├── NumberParser.java      (숫자 파싱)
│   ├── NumberValidator.java   (숫자 검증)
│   └── ErrorMessages.java     (에러 메시지 상수)
└── view/                      (입출력)
    ├── InputView.java         (사용자 입력 처리)
    └── OutputView.java        (결과 출력 처리)
```

### 🎯 클래스 역할 및 책임

**`Application`**
- 프로그램의 시작점
- Controller를 생성하고 실행

**`controller/CalculatorController`**
- 전체 실행 흐름 제어 (의존성 관리)
- View와 Domain 계층 연결
- 입력 → 파싱 → 계산 → 출력 흐름 관리

**`domain/Calculator`**
- 숫자 리스트를 받아 덧셈 수행
- NumberValidator를 통한 검증 위임
- 순수 계산 로직만 담당

**`domain/StringParser`**
- 문자열 파싱 오케스트레이션
- DelimiterHandler와 NumberParser에게 위임
- Early return 패턴으로 예외 처리

**`domain/DelimiterHandler`**
- 구분자 추출 및 정규식 패턴 변환
- 숫자 부분 추출
- 구분자 관련 모든 로직 통합

**`domain/DelimiterExtractor`**
- 커스텀 구분자 형식 검증
- 기본 구분자 또는 커스텀 구분자 추출

**`domain/NumberParser`**
- 구분자로 분리된 문자열을 숫자 리스트로 변환
- Stream API를 활용한 함수형 파싱
- 음수 및 유효하지 않은 숫자 검증

**`domain/NumberValidator`**
- 비즈니스 로직 계층의 숫자 검증
- null 체크 및 음수 검증

**`domain/ErrorMessages`**
- 모든 에러 메시지 상수 중앙 관리
- 중복 제거 및 일관성 유지

**`view/InputView`**
- 사용자에게 입력 안내 메시지 출력
- `Console.readLine()`을 통한 입력 받기

**`view/OutputView`**
- 계산 결과를 형식에 맞춰 출력
- "결과 : {숫자}" 형식 처리

<br>

---

## 📝 구현할 기능 목록

### 1️⃣ 입력 처리 (`InputView`)
- [x] "덧셈할 문자열을 입력해 주세요." 출력
- [x] 사용자로부터 문자열 입력 받기 (`Console.readLine()` 사용)

### 2️⃣ 문자열 파싱 (`StringParser`)
- [x] 빈 문자열 또는 null 입력 시 빈 리스트 반환
- [x] 커스텀 구분자 형식 확인 (`//`로 시작하는지)
- [x] `"//"와 "\n"` 사이의 문자를 커스텀 구분자로 추출
- [x] 커스텀 구분자가 있으면 해당 구분자 사용, 없으면 기본 구분자(`,`, `:`) 사용
- [x] 구분자를 기준으로 문자열 분리
- [x] 분리된 각 문자열을 정수로 변환
- [x] 숫자가 아닌 값 입력 시 `IllegalArgumentException` 발생
- [x] 빈 토큰(연속된 구분자) 처리

### 3️⃣ 계산 (`Calculator`)
- [x] 숫자 리스트를 받아 합계 계산
- [x] 음수가 포함되어 있는지 검증
- [x] 음수 입력 시 `IllegalArgumentException` 발생

### 4️⃣ 출력 처리 (`OutputView`)
- [x] 계산 결과를 `"결과 : {숫자}"` 형식으로 출력

### 5️⃣ 전체 흐름 (`Application`)
- [x] InputView로 입력 받기
- [x] StringParser로 문자열 파싱
- [x] Calculator로 계산
- [x] OutputView로 결과 출력
- [x] 예외 발생 시 애플리케이션 종료

<br>

---

## 🚀 보다 구체화된 구현 목록 (선택)

<details>
<summary><b>1️⃣ 세분화된 클래스 설계</b></summary>

**추가 클래스 분리**
```
calculator/
├── domain/
│   ├── Calculator.java
│   ├── DelimiterExtractor.java    (구분자 추출만 담당)
│   ├── NumberParser.java           (숫자 변환만 담당)
│   └── NumberValidator.java        (숫자 검증만 담당)
└── view/
    ├── InputView.java
    └── OutputView.java
```

- [x] 구분자 추출 책임 분리
- [x] 숫자 변환 책임 분리
- [x] 검증 로직 분리

</details>

<details>
<summary><b>2️⃣ 보다 구체화된 구분자 처리</b></summary>

- [x] 여러 문자로 구성된 커스텀 구분자 지원
    - 예: `"//:::\n1:::2:::3"` ➜ `6`
- [x] 다중 커스텀 구분자 지원
    - 예: `"//;,|\n1;2|3,4"` ➜ `10`
- [x] 정규표현식 기반 구분자 파싱

</details>

<details>
<summary><b>3️⃣ 보다 구체화된 유효성 검증</b></summary>

- [x] 정수 범위 초과 검증 (`Integer.MAX_VALUE` 초과)
- [x] 빈 토큰 무시 또는 예외 처리 전략
- [x] 공백 문자 처리 전략 (trim 여부)
- [x] 구분자에 숫자 포함 금지 (비즈니스 로직 혼란 방지)

</details>

<details>
<summary><b>4️⃣ 테스트 코드 작성</b></summary>

- [x] 빈 문자열 및 null 입력 테스트
- [x] 단일 숫자 입력 테스트
- [x] 기본 구분자(쉼표, 콜론) 테스트
- [x] 혼합 구분자 테스트
- [x] 커스텀 구분자 테스트
- [x] 음수 입력 예외 테스트
- [x] 숫자가 아닌 값 예외 테스트
- [x] 빈 토큰(연속된 구분자) 예외 테스트
- [x] 잘못된 커스텀 구분자 형식 예외 테스트
- [x] 정규식 특수문자 구분자 테스트 (`.`, `?`, `+` 등)
- [x] 경계값 테스트 (`0`, `Integer.MAX_VALUE`)
- [x] 구분자에 숫자 포함 예외 테스트

</details>

<br>

---

## 💻 실행 결과 예시

### ✅ 정상 실행
```
덧셈할 문자열을 입력해 주세요.
1,2:3
결과 : 6
```

### ✅ 커스텀 구분자 사용
```
덧셈할 문자열을 입력해 주세요.
//;\n1;2;3
결과 : 6
```

### ❌ 예외 발생
```
덧셈할 문자열을 입력해 주세요.
-1,2,3
Exception in thread "main" java.lang.IllegalArgumentException: 음수는 입력할 수 없습니다.
```

<br>

---

## ⚙️ 프로그래밍 요구 사항

| 항목 | 요구 사항 |
|------|-----------|
| **JDK 버전** | JDK 21 |
| **시작점** | `Application`의 `main()` |
| **빌드 설정** | `build.gradle` 변경 금지 |
| **외부 라이브러리** | 제공된 라이브러리 외 사용 금지 |
| **종료 처리** | `System.exit()` 사용 금지 |
| **코드 스타일** | Google Java Style Guide |
| **입력 처리** | `camp.nextstep.edu.missionutils.Console.readLine()` 사용 |

<br>

---

`## 🔧 추가 개선사항

### 1. 에러 메시지 상수 중앙화
**현재 문제:**
- `ERROR_NULL_INPUT`이 여러 클래스에 중복 정의됨
  - `StringParser`, `DelimiterExtractor`, `NumberParser`
- `ERROR_NEGATIVE_NUMBER`가 `NumberParser`와 `NumberValidator`에 중복

**개선 방안:**
- [x] `ErrorMessages` 클래스를 생성하여 모든 에러 메시지 상수 중앙 관리
- [x] 중복 제거 및 일관성 있는 에러 메시지 유지

---

### 2. 구분자 관련 로직 통합
**현재 문제:**
- `StringParser.extractNumbers()`: 숫자 부분 추출
- `DelimiterExtractor.extract()`: 구분자 추출
- `StringParser.escapeRegexSpecialCharacters()`: 정규식 이스케이프
- 위 세 메서드가 모두 동일한 커스텀 구분자 형식(`//...\n`)을 파싱
- 로직이 분산되어 있고 책임이 불명확함

**개선 방안:**
- [x] `DelimiterHandler` 클래스 도입
  - 구분자 추출 및 정규식 패턴 변환 (`extractDelimiterPattern`)
  - 숫자 부분 추출 (`extractNumbers`)
  - 정규식 이스케이프 처리 (`escapeRegexCharacter`)
- [x] 구분자 관련 모든 로직을 한 곳에서 처리
- [x] `StringParser`에서 분산된 로직 제거 및 `DelimiterHandler` 위임

---

### 3. StringParser 책임 과다 문제
**현재 문제:**
- `StringParser`가 너무 많은 책임을 가짐:
  - 입력 검증
  - `\n` 이스케이프 처리
  - 구분자 추출 위임
  - 숫자 부분 추출
  - 정규식 처리
  - 숫자 파싱 위임

**개선 방안:**
- [x] `StringParser`는 orchestration만 담당하도록 단순화
- [x] 각 세부 작업은 전문 클래스에 위임
  - `DelimiterHandler`: 구분자 관련 모든 처리
  - `NumberParser`: 순수 숫자 파싱만
- [x] Early return 패턴 적용
- [x] Stream API로 반복문 제거
- [x] 삼항 연산자로 조건문 간소화

---

### 4. 정규식 이스케이프 로직 개선
**현재 문제:**
- `escapeRegexSpecialCharacters()` 메서드가 기본 구분자(`",|:"`)를 하드코딩으로 체크
- 정규식 처리 로직이 `StringParser`에 위치하여 응집도가 낮음

**개선 방안:**
- [x] 정규식 처리를 `DelimiterHandler`로 이동
- [x] 메서드명을 명확하게 변경 (`toRegexPattern`, `escapeRegexCharacter`)

---

<br>

<div align="center">

![footer](https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=12&height=150&section=footer)

</div>
