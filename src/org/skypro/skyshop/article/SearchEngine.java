package org.skypro.skyshop.article;

import org.skypro.skyshop.exception.BestResultsNotFound;
import org.skypro.skyshop.product.Product;

import java.util.*;

public class SearchEngine {
    private final Map<String, Searchable> items = new TreeMap<>();

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> result = new TreeMap<>();

        for (Map.Entry<String, Searchable> entry : items.entrySet()) {
            if (entry.getKey().toLowerCase().contains(query.toLowerCase())) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }


    public void add(Searchable item) {
        items.put(item.getSearchTerm(), item);
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
