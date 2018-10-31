/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author franklin
 */
public class Counter {

    private AtomicInteger counter;

    public Counter() {
        this.counter = new AtomicInteger(0);
    }

    public int getAndIncrement() {
        return this.counter.getAndIncrement();
    }

    public int get() {
        return this.counter.get();
    }
}
