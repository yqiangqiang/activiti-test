import com.alibaba.fastjson.JSONObject;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;


public class MyProcessTest {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    //发布流程
    @Test
    public void deploy(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deployment = processEngine.getRepositoryService().createDeployment()
                .addClasspathResource("activiti/my-process.bpmn20.xml").deploy();

        System.out.println("id:"+deployment.getId());
    }


    //查询流程定义`
    @Test
    public void queryProcessDefinition() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> pList = processEngine.getRepositoryService().createProcessDefinitionQuery().orderByProcessDefinitionId().asc().list();
        pList.forEach(v -> {
            System.out.print("id:"+v.getId());
            System.out.print("name:"+v.getName());
            System.out.print("key:"+v.getKey());
            System.out.println("version:"+v.getVersion());
        });

    }

    //开启流程
    @Test
    public void startProcess() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.createProcessInstanceQuery().orderByProcessInstanceId()
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("my-process");
        System.out.println("pid:" + pi.getId() + "activitiId:" + pi.getActivityId());

        List<Task> taskList = processEngine.getTaskService().createTaskQuery().processInstanceId(pi.getId()).list();
        System.out.println(taskList.size());
    }

    //查看我的任务
    @Test
    public void queryMyTask() {


//        String assingnee = "张三";
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskAssignee(assingnee).list();
//        tasks.forEach(v->{
//            System.out.println("taskId:"+v.getId()+" ,taskname:"+v.getName());
//        });

    }
    //办理任务
    @Test
    public void complete(){
        String taskId = "604";
        processEngine.getTaskService().complete(taskId);

    }

    @Test
    public void queryProcessState(){
        String processInstanceId = "601";
        ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(processInstanceId)//按照流程ID查询
                .singleResult();//返回唯一结果
        if (pi != null) {
            System.out.println("当前流程在："+pi.getActivityId());
        }else {
            System.out.println("流程已经结束!");
        }
    }


}
