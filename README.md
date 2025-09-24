# Spring Boot JWT RBAC Demo

## Features

- Spring Boot REST API
- JWT authenticatie
- Spring Security (met RBAC & permissions)
- Users, Roles, Permissions in database (H2)
- Caching van authorities/permissions per user na login
- Login endpoint (`/api/auth/login`)
- Endpoint beveiliging via `@PreAuthorize("hasAuthority('...')")` en `@PreAuthorize("hasPermission(...)"))
- Voorbeelddata (admin/user)

## Starten

```bash
mvn spring-boot:run
```

## Login

POST `/api/auth/login`
  Body:
```json
{ "username": "admin", "password": "admin123" }
```
Response:
`{ "token": "..." }`

## Voorbeeld endpoints

- `/api/admin/hello` — Alleen voor admins (ROLE_ADMIN)
- `/api/user/hello` — Voor users (ROLE_USER)
- `/api/permission/hello` — Alleen als user de permission `PERM_HELLO` heeft

## Database Console

H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
JDBC URL: `jdbc:h2:mem:testdb`

## Gebruikers

| Username | Password   | Rol    | Permissions        |
|----------|------------|--------|-------------------|
| admin    | admin123   | ADMIN  | ALLE              |
| user     | user123    | USER   | PERM_HELLO        |