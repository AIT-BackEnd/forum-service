Русский текст смотри ниже

# Information Security #

**AAA** (Authentication, Authorization, Accounting) is the general name of processes related to ensuring data protection
in information systems, including ensuring authentication, authorization and audit, but without ensuring data
availability (protection from DOS attacks).

- Authentication (authentication) - matching a person (request) to an existing account in the security system. This is
  done using a login, password, certificate, smart card, etc.

- Authorization (authorization, check of authority, check of access level) - comparison of an account in the system (and
  a person who has passed authentication) and certain powers (or access denial). In the general case, authorization can
  be “negative” (user A is denied access to the company’s servers).

- Accounting (accounting) - monitoring the consumption of resources (mainly network) by the user. Accounting also
  includes recording the facts of gaining access to the system (access logs).

# Filter #

``public interface Filter``



A filter is an object that performs filtering tasks on either the request to a resource (a servlet or static content),
or on the response from a resource, or both.

Filters perform filtering in the doFilter method. Every Filter has access to a FilterConfig object from which it can
obtain its initialization parameters, and a reference to the ServletContext which it can use, for example, to load
resources needed for filtering tasks.

Filters are configured in the deployment descriptor of a web application.

Examples that have been identified for this design are:

- Authentication Filters
- Logging and Auditing Filters
- Image conversion Filters
- Data compression Filters
- Encryption Filters
- Tokenizing Filters
- Filters that trigger resource access events
- XSL/T filters
- Mime-type chain Filter

<br/><hr/><br/>

# Информационная безопасность #

**AAA** (от англ. Authentication, Authorization, Accounting) — общее название процессов, связанных с обеспечением защиты
данных в информационных системах, включая обеспечение аутентификации, авторизации и аудита, но без обеспечения
доступности данных (защиты от DOS-атак).

- Authentication (аутентификация) — сопоставление персоны (запроса) существующей учётной записи в системе безопасности.
  Осуществляется по логину, паролю, сертификату, смарт-карте и т. д.

- Authorization (авторизация, проверка полномочий, проверка уровня доступа) — сопоставление учётной записи в системе (и
  персоны, прошедшей аутентификацию) и определённых полномочий (или запрета на доступ). В общем случае авторизация может
  быть «негативной» (пользователю А запрещён доступ к серверам компании).

- Accounting (учёт) — слежение за потреблением ресурсов (преимущественно сетевых) пользователем. В accounting включается
  также и запись фактов получения доступа к системе (англ. access logs).

# Filter #

``public interface Filter``

Фильтр — это объект, который выполняет задачи фильтрации либо запроса к ресурсу (сервлет или статическое содержимое),
либо ответа от ресурса, либо того и другого.

Фильтры выполняют фильтрацию в методе doFilter. Каждый фильтр имеет доступ к объекту FilterConfig, из которого он может
получить параметры инициализации, а также ссылку на ServletContext, которую он может использовать, например, для
загрузки ресурсов, необходимых для задач фильтрации.

Фильтры настраиваются в дескрипторе развертывания веб-приложения.

Примеры, которые были определены для этой конструкции:

- Фильтры аутентификации
- Фильтры регистрации и аудита
- Фильтры преобразования изображений
- Фильтры сжатия данных
- Фильтры шифрования
- Токенизация фильтров
- Фильтры, запускающие события доступа к ресурсам.
- Фильтры XSL/T
- Цепной фильтр пантомимного типа