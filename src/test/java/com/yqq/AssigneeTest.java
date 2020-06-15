package com.yqq;

import org.activiti.engine.*;
import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanqiangqiang
 * @date 2020/6/15 16:28
 */
public class AssigneeTest {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();
    TaskService taskService = processEngine.getTaskService();
    IdentityService identityService = processEngine.getIdentityService();

    String deploymentId = "97501";
    String pi_1_Id = "92501";
    String pi_2_Id = "92505";


    @Test
    public void deploy(){
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("activiti/leaveRequest.bpmn20.xml")
                .addClasspathResource("activiti/leave-request.png")
                .addClasspathResource("rule/product.drl").deploy();
        System.out.println("deploymentId:"+deployment.getId());//72501

    }

    @Test
    public void start() {

        Group group = identityService.createGroupQuery().groupId("goup_id_1").singleResult();

        Map<String,Object> map = new HashMap();
        map.put("execute_group","goup_id_1");

        ProcessInstance processInstance1 = runtimeService.startProcessInstanceByKey("leaveRequest",map);//开始前需要设定参数，否则启动异常
        ProcessInstance processInstance2 = runtimeService.startProcessInstanceByKey("leaveRequest",map);
        System.out.println("processInstance1 Id:"+processInstance1.getId());//
        System.out.println("processInstance2 Id:"+processInstance2.getId());//
    }

    @Test
    public void createUserAndGroup() {

//        User user = identityService.newUser("user_id_1");
//        user.setFirstName("user1");
//        identityService.saveUser(user);
//        user = identityService.newUser("user_id_2");
//        user.setFirstName("user2");
//        identityService.saveUser(user);

        Group group = identityService.newGroup("goup_id_1");
        group.setName("测试组");
        group.setType("test");
        identityService.saveGroup(group);

        identityService.createMembership("user_id_1","goup_id_1");
        identityService.createMembership("user_id_2","goup_id_1");

    }

    @Test
    public void setAssignee(){




//        List list = taskService.createTaskQuery().taskCandidateUser("goup_id_1").list();//根据候选人查询任务
        List list1 = taskService.createTaskQuery().taskCandidateGroup("goup_id_1").list();//根据候选组查询任务

    }


}
