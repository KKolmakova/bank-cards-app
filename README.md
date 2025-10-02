# Bank Cards App

Приложение для работы с банковскими картами.

---

## Требования

* Java 17+
* Maven
* Docker

---

## Запуск MySQL-контейнера через Docker

1. Перейдите в корень проекта:

```bash
cd path/to/bank-cards-app
```

2. Запустите контейнер с MySQL:

```bash
docker-compose up -d
```

3. После успешного создания контейнера с готовой структурой базы данных и начальными данными можно запускать приложение.

---

## Авторизация

Пароли пользователей и администратора в базе хранятся в закодированном виде.
Для проверки работоспособности предоставлены тестовые учетные записи:

| Роль          | Имя пользователя | Пароль       |
| ------------- | ---------------- | ------------ |
| Администратор | `admin`          | `adminpass`  |
| Пользователь  | `ksenia`         | `kseniapass` |

> После запуска контейнера MySQL эти пользователи уже созданы и готовы к использованию в приложении.

---

# Отправка запросов

Ниже приведены инструкции по отправке запросов на все эндпоинты, доступные через контроллеры AdminController и UserCardController.

> Базовый URL приложения: `http://localhost:8095`
>
> Все защищенные эндпоинты требуют JWT-токен авторизации в заголовке `Authorization: Bearer <token>`.

---

## AdminController (/admin)

### 1. Создать карту

**POST** `/admin/cards`

```bash
POST http://localhost:8095/admin/cards
Authorization: Bearer <token>
Content-Type: application/json

{
  "number": "1234 5678 9012 3456",
  "holder": "John Doe",
  "expiry": "12/26"
}
```

### 2. Активировать карту

**PUT** `/admin/cards/{cardId}/activate`

```bash
PUT http://localhost:8095/admin/cards/1/activate
Authorization: Bearer <token>
```

### 3. Удалить карту

**DELETE** `/admin/cards/{cardId}`

```bash
DELETE http://localhost:8095/admin/cards/1
Authorization: Bearer <token>
```

### 4. Получить все карты

**GET** `/admin/cards`

```bash
GET http://localhost:8095/admin/cards
Authorization: Bearer <token>
```

### 5. Получить запросы на блокировку карт

**GET** `/admin/block-requests`

* Все запросы:

```bash
GET http://localhost:8095/admin/block-requests
Authorization: Bearer <token>
```

* Только pending:

```bash
GET http://localhost:8095/admin/block-requests?status=pending
Authorization: Bearer <token>
```

### 6. Одобрить запрос на блокировку карты

**POST** `/admin/block-requests/{requestId}/approve`

```bash
POST http://localhost:8095/admin/block-requests/1/approve
Authorization: Bearer <token>
```

### 7. Отклонить запрос на блокировку карты

**POST** `/admin/block-requests/{requestId}/reject`

```bash
POST http://localhost:8095/admin/block-requests/1/reject
Authorization: Bearer <token>
```

### 8. Создать пользователя

**POST** `/admin/users`

```bash
POST http://localhost:8095/admin/users
Authorization: Bearer <token>
Content-Type: application/json

{
  "username": "ksenia",
  "password": "kseniapass",
  "role": "USER"
}
```

### 9. Удалить пользователя

**DELETE** `/admin/users/{userId}`

```bash
DELETE http://localhost:8095/admin/users/1
Authorization: Bearer <token>
```

### 10. Получить всех пользователей

**GET** `/admin/users`

```bash
GET http://localhost:8095/admin/users
Authorization: Bearer <token>
```

---

## UserCardController (/cards)

### 1. Получить свои карты

**GET** `/cards`

```bash
GET http://localhost:8095/cards?search=&page=0&size=10
Authorization: Bearer <token>
```

> Параметры `page` и `size` опциональны.

### 2. Запросить блокировку карты

**POST** `/cards/block/{cardId}`

```bash
POST http://localhost:8095/cards/block/1
Authorization: Bearer <token>
```

### 3. Перевод между картами

**POST** `/cards/transfer`

```bash
POST http://localhost:8095/cards/transfer
Authorization: Bearer <token>
Content-Type: application/json

{
  "fromCardId": 1,
  "toCardId": 2,
  "amount": 100.50
}
```

### 4. Получить баланс карты

**GET** `/cards/{cardId}/balance`

```bash
GET http://localhost:8095/cards/1/balance
Authorization: Bearer <token>
```

> Используется аутентифицированный пользователь.