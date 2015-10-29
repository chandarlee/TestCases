// IRemoteService.aidl
package com.netease.lcd.lcdtestcases.cases.binder;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int calc(in int a, in int b);
}
