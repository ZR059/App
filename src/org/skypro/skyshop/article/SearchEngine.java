package org.skypro.skyshop.article;

public class SearchEngine {

    public Searchable[] items;
    private int size;

    public SearchEngine(int capacity){
        this.items = new Searchable[capacity];
        this.size = 0;
    }

    public Searchable[] search (String query) {
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

    public void add(Searchable item){
        if(size >= items.length){
            throw new IllegalStateException("Больше места нет!");
        }
        items[size++] = item;
    }
}
