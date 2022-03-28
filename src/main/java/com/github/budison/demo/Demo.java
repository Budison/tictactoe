package com.github.budison.demo;

import com.github.budison.ArgsReader;

/**
 * @author Kevin Nowak
 */
public class Demo {
    public Demo() {
    }
    public Demo(ArgsReader argsReader) {
        System.out.println("Demo Mode with arguments");
    }

    public void run() {
        System.out.println("Running Demo Mode");
    }
}
