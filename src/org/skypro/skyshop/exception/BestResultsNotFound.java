package org.skypro.skyshop.exception;

public class BestResultsNotFound extends Exception {
    public BestResultsNotFound(String searchTerm) {
        super("Не найден подходящий результат для поискового запроса: " + searchTerm);
    }
}
