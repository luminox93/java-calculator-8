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

> **예외 처리**
> - 사용자가 잘못된 값을 입력할 경우 `IllegalArgumentException`을 발생시킨 후 애플리케이션은 종료되어야 한다.

<br>

---

## 🏗️ 프로젝트 구조 설계

### 📦 패키지 구조
```
calculator/
├── Application.java           (메인 실행)
├── domain/                    (비즈니스 로직)
│   ├── Calculator.java        (덧셈 계산 담당)
│   └── StringParser.java      (문자열 파싱 및 구분자 처리)
└── view/                      (입출력)
    ├── InputView.java         (사용자 입력 처리)
    └── OutputView.java        (결과 출력 처리)
```

### 🎯 클래스 역할 및 책임

**`Application`**
- 프로그램의 시작점
- 전체 실행 흐름 제어

**`domain/Calculator`**
- 숫자 리스트를 받아 덧셈 수행
- 음수 검증 로직
- 순수 계산 로직만 담당

**`domain/StringParser`**
- 커스텀 구분자 추출
- 구분자 기준으로 문자열 분리
- 문자열을 숫자 리스트로 변환
- 숫자 변환 시 예외 처리

**`view/InputView`**
- 사용자에게 입력 안내 메시지 출력
- `Console.readLine()`을 통한 입력 받기
- 입력값 반환

**`view/OutputView`**
- 계산 결과를 형식에 맞춰 출력
- "결과 : {숫자}" 형식 처리

<br>

---

## 📝 구현할 기능 목록

### 1️⃣ 입력 처리 (`InputView`)
- [ ] "덧셈할 문자열을 입력해 주세요." 출력
- [ ] 사용자로부터 문자열 입력 받기 (`Console.readLine()` 사용)

### 2️⃣ 문자열 파싱 (`StringParser`)
- [ ] 빈 문자열 또는 null 입력 시 빈 리스트 반환
- [ ] 커스텀 구분자 형식 확인 (`//`로 시작하는지)
- [ ] `"//"와 "\n"` 사이의 문자를 커스텀 구분자로 추출
- [ ] 커스텀 구분자가 있으면 해당 구분자 사용, 없으면 기본 구분자(`,`, `:`) 사용
- [ ] 구분자를 기준으로 문자열 분리
- [ ] 분리된 각 문자열을 정수로 변환
- [ ] 숫자가 아닌 값 입력 시 `IllegalArgumentException` 발생
- [ ] 빈 토큰(연속된 구분자) 처리

### 3️⃣ 계산 (`Calculator`)
- [ ] 숫자 리스트를 받아 합계 계산
- [ ] 음수가 포함되어 있는지 검증
- [ ] 음수 입력 시 `IllegalArgumentException` 발생

### 4️⃣ 출력 처리 (`OutputView`)
- [ ] 계산 결과를 `"결과 : {숫자}"` 형식으로 출력

### 5️⃣ 전체 흐름 (`Application`)
- [ ] InputView로 입력 받기
- [ ] StringParser로 문자열 파싱
- [ ] Calculator로 계산
- [ ] OutputView로 결과 출력
- [ ] 예외 발생 시 애플리케이션 종료

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

- [ ] 구분자 추출 책임 분리
- [ ] 숫자 변환 책임 분리
- [ ] 검증 로직 분리

</details>

<details>
<summary><b>2️⃣ 보다 구체화된 구분자 처리</b></summary>

- [ ] 여러 문자로 구성된 커스텀 구분자 지원
    - 예: `"//:::\n1:::2:::3"` ➜ `6`
- [ ] 다중 커스텀 구분자 지원
    - 예: `"//;,|\n1;2|3,4"` ➜ `10`
- [ ] 정규표현식 기반 구분자 파싱

</details>

<details>
<summary><b>3️⃣ 보다 구체화된 유효성 검증</b></summary>

- [ ] 정수 범위 초과 검증 (`Integer.MAX_VALUE` 초과)
- [ ] 빈 토큰 무시 또는 예외 처리 전략
- [ ] 공백 문자 처리 전략 (trim 여부)

</details>

<details>
<summary><b>4️⃣ 테스트 코드 작성</b></summary>

- [ ] 빈 문자열 및 null 입력 테스트
- [ ] 단일 숫자 입력 테스트
- [ ] 기본 구분자(쉼표, 콜론) 테스트
- [ ] 혼합 구분자 테스트
- [ ] 커스텀 구분자 테스트
- [ ] 음수 입력 예외 테스트
- [ ] 숫자가 아닌 값 예외 테스트
- [ ] 빈 토큰(연속된 구분자) 예외 테스트
- [ ] 잘못된 커스텀 구분자 형식 예외 테스트
- [ ] 정규식 특수문자 구분자 테스트 (`.`, `?`, `+` 등)
- [ ] 경계값 테스트 (`0`, `Integer.MAX_VALUE`)

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

<div align="center">

![footer](https://capsule-render.vercel.app/api?type=waving&color=gradient&customColorList=12&height=150&section=footer)

</div>
