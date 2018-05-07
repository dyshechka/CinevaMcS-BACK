# spring-cloud-demo #

## Описание ##
Демонстрация организации инфраструктуры микросервисной архитектуры при помощи [Spring Cloud](https://github.com/spring-cloud)  

## Перечень микросервисов ##

Название | Описание | Конфигурация | Версия Spring | Версия Spring Cloud
:---:|---|:---:|:---:|:---:
[auth-service](auth-service) | Сервис аутентификации и авторизации | [application.yml](auth-service/src/main/resources/application.yml) | 1.3.5.RELEASE | Brixton.RELEASE
[discovery-service](discovery-service) | Сервис для обнаружения других сервисов | [application.yml](discovery-service/src/main/resources/application.yml) | 1.5.12.RELEASE | Edgware.SR3
[gateway-service](gateway-service) | Единая точка входа в приложение | [application.yml](gateway-service/src/main/resources/application.yml) | 1.5.12.RELEASE | Edgware.SR3
[test-service-1](test-service-1) | Демонстрация простого REST-сервиса | [application.yml](test-service-1/src/main/resources/application.yml) | 1.5.12.RELEASE | Edgware.SR3
[test-service-2](test-service-2) | Демонстрация взаимодействия с другим сервисом| [application.yml](test-service-2/src/main/resources/application.yml) | 1.5.12.RELEASE | Edgware.SR3

## Postman ##
Проект содержит коллекцию для приложения Postman, содержащую примеры запросов для демонстрации работы микросервисов

### Импорт проекта ###
1. *File > Import...*
1. Выбрать файл [коллекции](postman-project/spring-cloud-demo.postman_collection.json)
1. Создать окружение с произвольным именем (*Шестеренка с правой стороны окна > Add*)
1. Перед выполнением любого запроса необходимо выполнить авторизацию, для этого выполните один из запросов:

* Авторизоваться напрямую
* Авторизоваться через gateway (**пока не работает**)
