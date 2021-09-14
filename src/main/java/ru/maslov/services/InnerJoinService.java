package ru.maslov.services;

import ru.maslov.services.joiners.Joiner;

/**
 * Интерфейс вычисления inner join. Подразумевается, что результат будет сохранен в какой либо внешний
 * носитель - файл (как предполагается в задаче) или таблицу sql и т.п., в связи с чем возвращаемый
 * тип данных void
 */
public interface InnerJoinService {
    void innerJoin(Joiner joiner, String outputFileName);
}
