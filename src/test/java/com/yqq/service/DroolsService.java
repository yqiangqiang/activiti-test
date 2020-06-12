package com.yqq.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author yanqiangqiang
 * @date 2020/6/12 10:40
 */
public class DroolsService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println(execution.getVariable("reason"));

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
