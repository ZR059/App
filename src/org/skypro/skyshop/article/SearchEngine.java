package org.skypro.skyshop.article;

import org.skypro.skyshop.exception.BestResultsNotFound;

public class SearchEngine {

    public Searchable[] items;
    private int size;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
        this.size = 0;
    }

    public Searchable[] search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new Searchable[0];
        }

        Searchable[] results = new Searchable[5];
        int findCount = 0;

        for (int i = 0; i < size && findCount < 5; i++) {
            Searchable item = items[i];
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[findCount++] = item;
            }
        }
        return results;
    }

    public void add(Searchable item) {
        if (size >= items.length) {
            throw new IllegalStateException("Больше места нет!");
        }
        items[size++] = item;
    }

    public Searchable findBestMatch(Searchable[] items, String search) throws BestResultsNotFound {
        if (items == null || items.length == 0) {
            throw new BestResultsNotFound(search);
        }

        Searchable bestMatch = null;
        int maxCount = -1;

        for (Searchable item : items) {
            String term = item.getSearchTerm();
            int count = countSubstringOccurances(term, search);

            if (count > maxCount) {
                maxCount = count;
                bestMatch = item;
            }
        }

        if (maxCount == 0) {
            throw new BestResultsNotFound(search);
        }

        return bestMatch;
    }

    private int countSubstringOccurances(String str, String substring) {
        int count = 0;
        int index = 0;
        int substringLength = substring.length();

        if (substringLength == 0) return 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += substringLength;
        }
        return count;
    }
}
