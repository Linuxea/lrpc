package com.linuxea.lrpc.common.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class ObjectPool<T> {

    private final Integer coreSize;
    private final ArrayBlockingQueue<T> pool;
    private AtomicInteger counter;

    public ObjectPool(Integer coreSize) {
        this.coreSize = coreSize;
        this.pool = new ArrayBlockingQueue<>(coreSize);
        counter = new AtomicInteger(0);
    }

    public T get(Supplier<T> tSupplier) {

        synchronized (this) {
            if (counter.get() < coreSize) {
                T t = tSupplier.get();
                this.counter.incrementAndGet();
                return t;
            }

            try {
                return pool.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean returnPool(T t) {
        return this.pool.add(t);
    }

}
