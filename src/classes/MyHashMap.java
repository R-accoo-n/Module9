package classes;

import java.util.Objects;

public class MyHashMap<K, V> {
    private static final int CAPACITY = 16;
    private MyMapBucket<K, V>[] bucket;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        this.bucket = new MyMapBucket[CAPACITY];
    }

    private int getHash(K key) {
        return key.hashCode() % CAPACITY;
    }

    private MyKeyValueEntry<K, V> getEntry(K key) {
        int hash = getHash(key);
        for (int i = 0; i < bucket[hash].getEntries().size(); i++) {
            MyKeyValueEntry<K, V> myKeyValueEntry = bucket[hash].getEntries().get(i);
            if(myKeyValueEntry.getKey().equals(key)) {
                return myKeyValueEntry;
            }
        }
        return null;
    }


    public void put(K key, V value) {
        if(containsKey(key)) {
            MyKeyValueEntry<K, V> entry = getEntry(key);
            assert entry != null;
            entry.setValue(value);
        } else {
            int hash = getHash(key);
            if(bucket[hash] == null) {
                bucket[hash] = new MyMapBucket<>();
            }
            bucket[hash].addEntry(new MyKeyValueEntry<>(key, value));
            size++;
        }
    }

    public void remove(K key) {
        if(containsKey(key)) {
            int hash = getHash(key);
            bucket[hash].removeEntry(getEntry(key));
            size--;
        }
    }

    public void clear(){
        this.bucket = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        return containsKey(key) ? (V) Objects.requireNonNull(getEntry(key)).getValue() : null;
    }

    public boolean containsKey(K key) {
        int hash = getHash(key);
        return !(Objects.isNull(bucket[hash]) || Objects.isNull(getEntry(key)));
    }

    private static class MyMapBucket<K, V> {
        private final MyLinkedList<MyKeyValueEntry<K, V>> entries;

        public MyMapBucket() {
            entries = new MyLinkedList<>();
        }

        public MyLinkedList<MyKeyValueEntry<K, V>> getEntries() {
            return entries;
        }

        public void addEntry(MyKeyValueEntry<K, V> entry) {
            this.entries.add(entry);
        }

        public void removeEntry(MyKeyValueEntry<K, V> entry) {
            this.entries.remove(entry);
        }
    }

    private static class MyKeyValueEntry<K, V> {
        private final K key;
        private V value;

        public MyKeyValueEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MyKeyValueEntry<?, ?> that)) return false;
            return getKey().equals(that.getKey()) && getValue().equals(that.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getKey(), getValue());
        }
    }
}
