# 目的#
 
实验activiti与SpringBoot的集成
	
# 步骤 #

**STEP1.Gradle中引用依赖的jar**
	
	compile("org.activiti:spring-boot-starter-basic:5.17.0")
	compile("com.h2database:h2:1.4.185")

**STEP2.编写resources/processes/xxx.bpmn20.xml**
	
	<?xml version="1.0" encoding="UTF-8"?>
	<definitions
        xmlns="http://www.omg.org/spec/BPMN/20100524/MODEL"
        xmlns:activiti="http://activiti.org/bpmn"
        targetNamespace="Examples">

	    <process id="WF1" name="WF1">
	        <startEvent id="start" />
	        <sequenceFlow id="flow1" sourceRef="start" targetRef="step1" />
	        <serviceTask id="step1" activiti:expression="#{step1.run()}" />
	        <sequenceFlow id="flow2" sourceRef="step1" targetRef="step2" />
	        <serviceTask id="step2" activiti:expression="#{step2.run()}" />
	        <sequenceFlow id="flow3" sourceRef="step2" targetRef="end" />
	        <endEvent id="end" />
	    </process>
	</definitions>
	
	TODO:验证路径、文件名有特殊潜规则
	
**STEP3.编写subprovider/steps**
	
**STEP4.在MainProvider中调用WF1**
	
	RuntimeService oRuntimeService = IOCUtils.getInstance().getBean(RuntimeService.class);
	oRuntimeService.startProcessInstanceByKey("WF1");

# reference #
	
	https://www.activiti.org/userguide/index.html#springExpressions
	https://spring.io/blog/2015/03/08/getting-started-with-activiti-and-spring-boot
	
#其它参考和备份#

**安装Activiti Designer**
	
	http://wiselyman.iteye.com/blog/2285223
	https://spring.io/blog/2015/03/08/getting-started-with-activiti-and-spring-boot
	http://blog.csdn.net/u013355724/article/details/51915540
	http://blog.csdn.net/javahighness/article/details/47753293

#SpringBatch bakup#
	
	说明1：本来是实验SpringBatch，但是SpringBatch不满足现在的应用场景，类似后台定时批处理
	
**关于DataSource的错误**

If you want an embedded database please put a supported one on the classpath
	
	http://stackoverflow.com/questions/24074749/spring-boot-cannot-determine-embedded-database-driver-class-for-database-type