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

## 📝 구현할 기능 목록

### 1️⃣ 입력 처리
- [ ] 사용자로부터 문자열 입력 받기 (`Console.readLine()` 사용)
- [ ] 빈 문자열 또는 null 입력 시 0 반환

### 2️⃣ 기본 구분자 처리
- [ ] 쉼표(`,`)를 구분자로 숫자 분리
- [ ] 콜론(`:`)을 구분자로 숫자 분리
- [ ] 쉼표와 콜론이 혼합된 경우 처리
    - 예: `"1,2:3"` ➜ `6`

### 3️⃣ 커스텀 구분자 처리
- [ ] `"//"와 "\n"` 사이의 문자를 커스텀 구분자로 인식
- [ ] 커스텀 구분자로 숫자 분리
    - 예: `"//;\n1;2;3"` ➜ `6`
- [ ] 커스텀 구분자 추출 후 나머지 문자열 파싱

### 4️⃣ 숫자 추출 및 계산
- [ ] 구분자로 분리된 문자열을 숫자로 변환
- [ ] 추출된 모든 양수의 합 계산
- [ ] 결과를 `"결과 : {숫자}"` 형식으로 출력

### 5️⃣ 예외 처리
- [ ] 음수 입력 시 `IllegalArgumentException` 발생
- [ ] 숫자가 아닌 값 입력 시 `IllegalArgumentException` 발생
- [ ] 잘못된 형식의 커스텀 구분자 입력 시 `IllegalArgumentException` 발생
- [ ] 빈 토큰(연속된 구분자) 처리

<br>

---

## 🚀 보다 구체화된 구현 목록

<details>
<summary><b>1️⃣ 객체지향 설계</b></summary>

- [ ] 입력 처리 책임을 가진 `InputHandler` 클래스 분리
- [ ] 구분자 파싱 책임을 가진 `DelimiterParser` 클래스 분리
- [ ] 계산 로직을 담당하는 `StringCalculator` 클래스 분리
- [ ] 출력 처리를 담당하는 `OutputHandler` 클래스 분리

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
