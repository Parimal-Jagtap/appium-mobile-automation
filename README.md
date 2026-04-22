# 📱 Appium Mobile Automation Framework

> Mobile test automation for **Android & iOS** banking/fintech applications  
> Built with **Appium + Java + TestNG** | UIAutomator2 | XCUITest | CI/CD via **GitHub Actions**

---

## 🧱 Framework Architecture
---

## ⚙️ Tech Stack

| Layer | Tool |
|---|---|
| Language | Java |
| Mobile Automation | Appium 2.x |
| Android Driver | UIAutomator2 |
| iOS Driver | XCUITest |
| Test Framework | TestNG |
| Build Tool | Maven |
| Reporting | Allure Reports |
| CI/CD | GitHub Actions |
| Cloud Testing | BrowserStack |

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 11+
- Maven 3.8+
- Appium Server 2.x installed
- Android Studio + Android SDK (for Android)
- Xcode (for iOS — Mac only)
- Node.js 18+

### Install Appium

```bash
npm install -g appium
appium driver install uiautomator2
appium driver install xcuitest
```

### Run Tests

```bash
# Run Android tests
mvn test -Dsuite=android

# Run iOS tests
mvn test -Dsuite=ios

# Run smoke tests only
mvn test -Dgroups=smoke

# Run on BrowserStack
mvn test -Denv=browserstack

# Generate Allure report
mvn allure:serve
```

---

## 🔑 Key Framework Features

- ✅ **Separate Page Objects** for Android and iOS — platform-specific locators
- ✅ **UIAutomator2** for Android — handles dynamic UI, gestures, deep links
- ✅ **XCUITest** for iOS — Accessibility ID, Class Chain, iOS Predicate locators
- ✅ **DriverManager** — centralized Appium driver factory for Android/iOS
- ✅ **Parallel Execution** — Android and iOS run simultaneously via TestNG
- ✅ **BrowserStack Integration** — cloud device execution across real devices
- ✅ **Data-driven Tests** — JSON test data for multiple user scenarios
- ✅ **Allure Reporting** — screenshots, logs, and device info per test
- ✅ **GitHub Actions CI/CD** — automated pipeline on every push

---

## 📱 Test Coverage — Fintech Mobile App

| Module | Android | iOS |
|---|---|---|
| Authentication | Login, biometric, invalid creds | Login, Face ID, invalid creds |
| Wallet | View balance, add money | View balance, add money |
| Transactions | Fund transfer, history | Fund transfer, history |
| Payments | Bill pay, mobile recharge | Bill pay, mobile recharge |
| Profile | View, update details | View, update details |

---

## 👤 Author

**Parimal Jagtap** — SDET | 4+ Years  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-parimaljagtap-0A66C2?style=flat-square&logo=linkedin)](https://www.linkedin.com/in/parimaljagtap)
