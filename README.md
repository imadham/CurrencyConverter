# instructions
программа подключается к БД postgreSQL:

url=jdbc:postgresql://localhost:5432/postgres

username=postgres

password=imad

change username/password in application.properties

для начала: mvnw spring-boot:run

# Currencies
Класс для передачи данных из формы ввода пользователя в контроллер

# IndexController
Класс контроллера с POST и GET и одним шаблоном html
проверить актуальность данных в БД
если не актуальные удалить старые данные и получить новые данные
с использованием dataloader из  веб-источника

## currencies1
Используеться этот параметр для сохранения валют, которые будут конвертированы из / в
и сохранения конвертируемой сумме
если это первая загрузка страницы пользователем до
ввод значений даеюм значение по умолчанию
1 доллара США в евро

## formPost
для проверки если это первая загрузка страницы пользователем



# ConvertDataModelImpl
преобразовать значения, полученные из Интернета,
в системные модели и наоборот

# DataLoader
получить данные из класса GettingDataImplementation и сохранить в БД

# GettingDataImplementation
получить XML-данные с веб-страницы и преобразовать их
в объекты Java с помощью jaxb

форматировать дату в объект LocalDate

# ValCurs
модель, представляющая корневой элемент xml с дочерними элементами
используется jaxb для преобразования xml в объект этого класса

# Valute
модель для преобразования данных из xml в объект этого класса

# ValuteModel, HistoryModel
модель БД

# ValuteServiceImplementation, HistoryServiceImpl
сервис для сохранения, поиска и удаления данных в БД
