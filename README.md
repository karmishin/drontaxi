Веб-приложение, симулирующее работу сервиса беспилотного такси.

## Запуск

### При помощи Docker Compose
```shell script
# Клонируем репозиторий
git clone https://github.com/karmishin/drontaxi.git
cd drontaxi

# Собираем JAR-файл со всеми зависимостями
./gradlew build

# Поднимаем контейнеры с базой данных и приложением
sudo docker-compose build
sudo docker-compose up
```

### Без Docker
Клонируйте репозиторий и отредактируйте файл `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:postgresql://db/drontaxi
spring.datasource.username=drontaxi
spring.datasource.password=drontaxi
```
Вместо `db` укажите адрес запущенной PostgreSQL (например, `jdbc:postgresql://localhost/drontaxi`).
В полях `username` и `password` укажите имя пользователя и пароль для доступа к базе данных.

Соберите проект:
```
./gradlew build
```

Исполните JAR-файл:
```
java -jar build/libs/drontaxi-web-0.0.1-SNAPSHOT.jar
```


