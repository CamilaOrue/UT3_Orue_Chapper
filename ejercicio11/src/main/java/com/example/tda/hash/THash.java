package com.example.tda.hash;

abstract public class THash<K, V> {
    protected TNodoHash<K, V>[] hashTable;

    @SuppressWarnings("unchecked")
    public THash(int elementosEsperados) {
        hashTable = new TNodoHash[calcularCapacidadOptima(elementosEsperados)];
    }

    public V buscar(K clave) {
        return buscar(clave, new Report());
    }

    public abstract V buscar(K clave, Report report);

    public boolean delete(K clave) {
        return delete(clave, new Report());
    }

    public abstract boolean delete(K clave, Report report);

    public boolean insertar(K clave, V valor) {
        return insertar(clave, valor, new Report());
    }

    public abstract boolean insertar(K clave, V valor, Report report);

    protected abstract int functionHashing(K valor);

    public abstract boolean esVacio();

    public abstract void vaciar();

    protected abstract int calcularCapacidadOptima(int elementosEsperados);

    protected abstract boolean redimensionar();

    public abstract Iterable<Entry<K, V>> entries();

    public abstract Iterable<K> keys();

    public abstract Iterable<V> values();
}
