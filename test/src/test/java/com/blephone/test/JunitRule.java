package com.blephone.test;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class JunitRule implements TestRule {
    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String methodName = description.getMethodName();
                System.out.println(methodName + " 测试方法开始");
                base.evaluate();
                System.out.println(methodName + " 测试方法结束");
            }
        };
    }
}
