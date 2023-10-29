
Вторая часть дипломного проекта по курсу Автоматизатор тестирования на Java

**Тестирование API**


Протестированы ручки API для [Stellar Burgers](https://stellarburgers.nomoreparties.site/)
[Документация API](https://code.s3.yandex.net/qa-automation-engineer/java/cheatsheets/paid-track/diplom/api-documentation.pdf)

1. Создание пользователя
2. Логин пользователя
3. Изменение данных пользователя
4. Создание заказа
5. Получение заказов конкретного пользователя

В проекте использованы такие технологии, как:

* Java 11;
* junit 4.13.2;
* maven 4.0.0;
* rest-assured 5.3.0;
* allure-junit4 2.23.0;

Чтобы выполнить автотесты и сгенерировать отчет, необходимо запустить в консоли команду "mvn clean test" и "allure serve target/surefire-reports"
