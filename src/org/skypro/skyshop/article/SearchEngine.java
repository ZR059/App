package org.skypro.skyshop.article;

import org.skypro.skyshop.exception.BestResultsNotFound;
import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items;

    public SearchEngine() {
        this.items = new ArrayList<>();
    }

    public List<Searchable> search(String query) {

        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }
        List<Searchable> results = new ArrayList<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    public void add(Searchable item) {
        if (item == null) {
            throw new IllegalStateException("Нельзя добавить пустой элемент!");
        }
        items.add(item);
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
