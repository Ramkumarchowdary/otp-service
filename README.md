# OTP Service

A Spring Boot-based OTP (One Time Password) service that supports mobile number validation, OTP generation, and OTP verification. The system also supports international phone numbers using Google's libphonenumber and stores OTP data in a PostgreSQL database.

---

## ✅ Features

- Generates mixed-case 4-character OTPs
- Validates international phone numbers using Google libphonenumber
- Prevents duplicate OTP generation for the same number
- Validates OTPs and auto-deletes them upon verification
- Scheduled job to purge expired OTPs every 5 minutes
- Region info stored alongside mobile numbers

---

## 🛠️ Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- libphonenumber (Google)

---

## ⚙️ Setup

### 1. Clone the repository

```bash
git clone https://github.com/Ramkumarchowdary/otp-service.git
cd otp-service
