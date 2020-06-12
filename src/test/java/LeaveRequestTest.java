import com.yqq.beans.Leave;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeaveRequestTest {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    public static void main(String[] args) {

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();

        //发布流程
        Deployment deployment = repositoryService.createDeployment()
//                .addClasspathResource("activiti/leaveRequest.bpmn20.xml")
                .addClasspathResource("activiti/ppleave.bpmn")
                .addClasspathResource("activiti/leave-request.png")
                .addClasspathResource("rule/product.drl").deploy();

        System.out.println("发布ID："+deployment.getId());

//        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leaveRequest");//启动流程
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave");//启动流程
        Map<String, Object> vars = new HashMap<>();
        vars.put("leave", new Leave("张三", 1));

        List<Task> taskList = taskService.createTaskQuery().processInstanceId(pi.getId()).list();
        for (Task task : taskList) {
            System.out.println("正在处理任务：  "+task.getId()+" : "+task.getName());
            taskService.complete(task.getId(),vars);
        }



    }

    @Test
    /**
     * 发布流程
     */
    public void deployProcess() {

    }

    //启动流程
    public void startProcess() {

    }

    //设置变量
    public void setVariables() {

    }

    //获取变量
    public void getVariables() {


    }

    //获取历史变量
    public void getHisVariables() {

    }

    //查看查看流程历史记录
    //查看一次流程一共经历多少个活动
    //查看历史任务
    //查看历史流程变量

}
