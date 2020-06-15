package com.yqq;

import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.form.TaskFormDataImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanqiangqiang
 * @date 2020/6/15 11:34
 */
public class variableTest {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();
    TaskService taskService = processEngine.getTaskService();
    IdentityService identityService = processEngine.getIdentityService();


    String pi_1_Id = "75001";
    String pi_2_Id = "75005";
    String deploymentId = "72501";

    @Test
    public void deploy(){
        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("activiti/leaveRequest.bpmn20.xml")
                .addClasspathResource("activiti/leaveRequest.bpmn20.xml")
                .addClasspathResource("activiti/leave-request.png")
                .addClasspathResource("rule/product.drl").deploy();
        System.out.println("deploymentId:"+deployment.getId());//72501

    }

    @Test
    public void start() {
        ProcessInstance processInstance1 = runtimeService.startProcessInstanceByKey("leaveRequest");
        ProcessInstance processInstance2 = runtimeService.startProcessInstanceByKey("leaveRequest");
        System.out.println("processInstance1 Id:"+processInstance1.getId());//75001
        System.out.println("processInstance2 Id:"+processInstance2.getId());//75005
    }

    @Test
    public void setVariable(){

        runtimeService.setVariable(pi_1_Id,"myVar","hello var");
        runtimeService.setVariable(pi_2_Id,"myVar","hello var_2");


    }

    @Test
    public void getVariable() {
        System.out.println("var1:   "+runtimeService.getVariable(pi_1_Id,"myVar"));
        System.out.println("var2:   "+runtimeService.getVariable(pi_2_Id,"myVar"));
    }




}
