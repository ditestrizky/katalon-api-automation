# Katalon API Automation

API automation testing portfolio menggunakan Katalon Studio + Groovy.  
Target API: [dummyjson.com](https://dummyjson.com) (simulasi authentication & user management).

## Tech Stack
- Katalon Studio 10.2.4
- Groovy
- CI/CD: GitHub Actions, Configured for Azure Pipelines 
(requires Katalon Runtime Engine license)

## Test Coverage

### Authentication — `/auth/login`
| Test Case | Scenario | Expected |
|-----------|----------|----------|
| TC-AUTH-001 | Login valid credentials | 200 + accessToken |
| TC-AUTH-002 | Login invalid password | 400 + error message |
| TC-AUTH-003 | Login unregistered username | 400 + error message |
| TC-AUTH-004 | Login blank username | 400 + error message |
| TC-AUTH-005 | Login blank password | 400 + error message |

### User — `/auth/me`
| Test Case | Scenario | Expected |
|-----------|----------|----------|
| TC-USER-001 | Get user dengan valid token | 200 + user data |
| TC-USER-002 | Get user tanpa token | 401 |
| TC-USER-003 | Get user dengan expired token | 401 |
| TC-USER-004 | Get user ID tidak ada | 404 |
| TC-USER-005 | Response time validation | < 1000ms |

### Validation
| Test Case | Scenario |
|-----------|----------|
| TC-VAL-001 | JSON schema check (required keys present) |
| TC-VAL-002 | Required fields not null/empty |
| TC-VAL-003 | Data type validation (id: Integer, name: String, dll) |

## Key Patterns
- **API Chaining** — login dulu, ambil token, inject ke request berikutnya
- **Response Validation** — status code, schema, data type, response time
- **Negative Testing** — invalid/blank/unregistered credentials, missing/expired token

## How to Run
1. Clone repo
2. Buka di Katalon Studio
3. Jalankan: `Test Suites/TS-Regression_Full`
