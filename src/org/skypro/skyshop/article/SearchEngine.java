package org.skypro.skyshop.article;

import org.skypro.skyshop.exception.BestResultsNotFound;
import org.skypro.skyshop.product.Product;

import java.util.*;

public class SearchEngine {
    private final Set<Product> products = new HashSet<>();
    private final Set<Article> articles = new HashSet<>();
    private final Set<Product> items = new HashSet<>();

    public Set<Searchable> search(String query) {
        String lowerQuery = query.toLowerCase();
        Comparator<Searchable> comparator = (o1, o2) -> {
            int lengthCompare = Integer.compare(o2.getName().length(), o1.getName().length());
            if (lengthCompare != 0) {
                return lengthCompare;//
            }
            return o1.getName().compareTo(o2.getName());
        };

        Set<Searchable> result = new TreeSet<>(comparator);

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(lowerQuery)) {
                result.add(product);
            }
        }
        for (Article article : articles) {
            if (article.getName().toLowerCase().contains(lowerQuery)) {
                result.add(article);
            }
        }
        return result;
    }

    public void add(Product product) {
        products.add(product);
    }

    public void add(Article article) {
        articles.add(article);
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
