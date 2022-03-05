package com.fleet.activiti.modeler5.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fleet.activiti.modeler5.entity.ActivitiModel;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("model")
public class ModelerController {

    private static final Logger logger = LoggerFactory.getLogger(ModelerController.class);

    @Resource
    ObjectMapper objectMapper;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    /**
     * 创建一个空模型
     */
    @RequestMapping(value = "/create")
    public String create(@RequestBody ActivitiModel activitiModel) {
        logger.info("创建一个空模型：name:{},key:{},description:{}", activitiModel.getName(), activitiModel.getKey(), activitiModel.getDescription());
        try {
            String key = StringUtils.defaultString(activitiModel.getKey());
            String description = StringUtils.defaultString(activitiModel.getDescription());

            ObjectNode metaInfo = objectMapper.createObjectNode();
            metaInfo.put(ModelDataJsonConstants.MODEL_NAME, activitiModel.getName());
            metaInfo.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            metaInfo.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);

            // 初始化一个空模型
            Model model = repositoryService.newModel();
            model.setName(activitiModel.getName());
            model.setKey(key);
            model.setMetaInfo(metaInfo.toString());
            repositoryService.saveModel(model);

            String id = model.getId();

            ObjectNode editorSourceInfo = objectMapper.createObjectNode();
            editorSourceInfo.put("id", "canvas");
            editorSourceInfo.put("resourceId", "canvas");

            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorSourceInfo.set("stencilset", stencilSetNode);
            repositoryService.addModelEditorSource(id, editorSourceInfo.toString().getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "成功";
    }

    /**
     * 获取所有模型
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Model> list() {
        return repositoryService.createModelQuery().list();
    }

    /**
     * 发布模型为流程定义
     */
    @RequestMapping("/deploy")
    @ResponseBody
    public Object deploy(String modelId) throws Exception {
        // 获取模型
        // RepositoryService repositoryService =
        // processEngine.getRepositoryService();
        Model modelData = repositoryService.getModel(modelId);
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if (bytes == null) {
            return "模型数据为空，请先设计流程并成功保存，再进行发布。";
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if (model.getProcesses().size() == 0) {
            return "数据模型不符要求，请至少设计一条主线流程。";
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        // 发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
        return "成功";
    }

    /**
     * 启动流程
     */
    @RequestMapping("/start")
    @ResponseBody
    public Object startProcess(String keyName) {
        ProcessInstance process = runtimeService.startProcessInstanceByKey(keyName);
        return process.getId() + " : " + process.getProcessDefinitionId();
    }

    /**
     * 提交任务
     */
    @RequestMapping("/run")
    @ResponseBody
    public Object run(String processInstanceId) {
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        // log.info("task {} find ",task.getId());
        taskService.complete(task.getId());
        return "成功";
    }
}
