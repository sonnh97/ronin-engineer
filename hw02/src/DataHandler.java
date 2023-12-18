public interface DataHandler<K, V> {
    V getData(K key);
}
