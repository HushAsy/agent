package org.hhs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestRun implements Runnable {
    public void run() {
        System.out.println("hello");
    }
}
