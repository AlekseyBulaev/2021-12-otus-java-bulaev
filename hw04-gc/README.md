Домашнее задание

Определение нужного размера хипа
Цель:

на примере простого приложения понять какое влияние оказывают сборщики мусора
Описание/Пошаговая инструкция выполнения домашнего задания:

Есть готовое приложение (модуль homework)
Запустите его с размером хипа 256 Мб и посмотрите в логе время выполнения.
Пример вывода:
spend msec:18284, sec:18
Увеличьте размер хипа до 2Гб, замерьте время выполнения.
Результаты запусков записывайте в таблицу.
Определите оптимальный размер хипа, т.е. размер, превышение которого,
не приводит к сокращению времени выполнения приложения.
Оптимизируйте работу приложения.
Т.е. не меняя логики работы (но изменяя код), сделайте так, чтобы приложение работало быстро с минимальным хипом.
Повторите измерения времени выполнения программы для тех же значений размера хипа.
Критерии оценки:

Система оценки максимально соответсвует привычной школьной:
3 и больше - задание принято (удовлетворительно).
ниже - задание возвращается на доработку.
Задание не принимается, если основной функционал не работает или есть критические недостатки (например, copy-past кода, классы на 100500 строк, sql-конкатенация, race condition и т.д.).
Если все работает и критических недостатоков нет, то:

    3 - можно мержить, но есть неприятные недочеты, которые хорошо бы поправить.
    4 - можно мержить, но есть недочеты.
    5 - можно мержить, отличная работа. (при этом могут быть мелкие субъективные шероховатости)

Рекомендуем сдать до: 07.02.2022