package rtl.sod.corp.sche.whmg.appointment.infraestructure.adapters.http.rest.telemetry;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.*;
import java.util.HashMap;
import java.util.Map;


import java.io.*;
import java.util.*;
import javax.management.*;
import javax.management.remote.*;
import com.sun.tools.attach.*;

@ApplicationScoped
public class JmxInspectorImpl implements JmxInspector {

    private static final String JMX_HOST = "127.0.0.1";

    private JMXConnector jmxConnector;
    private MBeanServerConnection remoteConnection;

    @PostConstruct
    public void initializeJMXConnector() {
        try {
        	

        	String pid = ManagementFactory.getRuntimeMXBean().getName();
        	int indexOf = pid.indexOf('@');
        	if (indexOf > 0) {
        		pid = pid.substring(0, indexOf);
        	}
        	
        	final String CONNECTOR_ADDRESS =
        			 "com.sun.management.jmxremote.localConnectorAddress";
        			 
        			// attach to the target application
        			VirtualMachine vm = null;
					try {
						vm = VirtualMachine.attach(pid);
					} catch (AttachNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			 
        			// get the connector address
        			String connectorAddress =
        			    vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);
        			 
        			// no connector address, so we start the JMX agent
        			if (connectorAddress == null) {
        			   String agent = vm.getSystemProperties().getProperty("java.home") +
        			       File.separator + "lib" + File.separator + "management-agent.jar";
        			   try {
						vm.loadAgent(agent);
					} catch (AgentLoadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (AgentInitializationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
        			 
        			   // agent is started, get the connector address
        			   connectorAddress =
        			       vm.getAgentProperties().getProperty(CONNECTOR_ADDRESS);
        			}
        			 
        			
            //String managementPort = System.getProperty("com.sun.management.jmxremote.port");
            //JMXServiceURL jmxServiceURL = new JMXServiceURL("rmi", "", 0, "/jndi/rmi://" + JMX_HOST + ":" + managementPort + "/jmxrmi");
           
            
            JMXServiceURL serviceURL = new JMXServiceURL(connectorAddress);
            jmxConnector = JMXConnectorFactory.newJMXConnector(serviceURL, new HashMap<>());
             
            
            
            jmxConnector.connect();
            remoteConnection = jmxConnector.getMBeanServerConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getProcessCPU() {
        try {
            // No direct method in the operating system class to get the CPU load
            double cpuUsage = (double) getAttributeValue("java.lang:type=OperatingSystem", "ProcessCpuLoad");
            return (int) (cpuUsage * 100);
        } catch (Exception e) {
            return -1;
        }
    }

    public Map<String, Long> getHeapMemory() {
        Map<String, Long> heapMemory = new HashMap<>();
        try {
            MemoryMXBean memoryMXBean = ManagementFactory.newPlatformMXBeanProxy(
                    remoteConnection,
                    ManagementFactory.MEMORY_MXBEAN_NAME,
                    MemoryMXBean.class);
            MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
            heapMemory.put("used", heapMemoryUsage.getUsed() / 1024);
            heapMemory.put("committed", heapMemoryUsage.getCommitted() / 1024);
            heapMemory.put("init", heapMemoryUsage.getInit() / 1024);
            heapMemory.put("max", heapMemoryUsage.getMax() / 1024);
            return heapMemory;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return heapMemory;
    }

    public Map<String, Integer> getThreadDetails() {
        Map<String, Integer> threadDetails = new HashMap<>();
        try {
            ThreadMXBean threadsMXBean = ManagementFactory.newPlatformMXBeanProxy(
                    remoteConnection,
                    ManagementFactory.THREAD_MXBEAN_NAME,
                    ThreadMXBean.class);
            threadDetails.put("threads", threadsMXBean.getThreadCount());
            threadDetails.put("threads.peak", threadsMXBean.getPeakThreadCount());
            threadDetails.put("threads.daemon", threadsMXBean.getDaemonThreadCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return threadDetails;
    }

    public ThreadInfo[] getThreadInfo() {
        ThreadInfo[] info = new ThreadInfo[0];
        try {
            ThreadMXBean threadsMXBean = ManagementFactory.newPlatformMXBeanProxy(
                    remoteConnection,
                    ManagementFactory.THREAD_MXBEAN_NAME,
                    ThreadMXBean.class);
            info = threadsMXBean.getThreadInfo(threadsMXBean.getAllThreadIds());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return info;
    }

    public Map<String, Long> getLoadedClassesInfo() {
        Map<String, Long> loadedClassesInfo = new HashMap<>();
        try {
            ClassLoadingMXBean classLoadingMXBean = ManagementFactory.newPlatformMXBeanProxy(
                    remoteConnection,
                    ManagementFactory.CLASS_LOADING_MXBEAN_NAME,
                    ClassLoadingMXBean.class);
            loadedClassesInfo.put("classes", classLoadingMXBean.getTotalLoadedClassCount());
            loadedClassesInfo.put("classes.loaded", (long) classLoadingMXBean.getLoadedClassCount());
            loadedClassesInfo.put("classes.unloaded", classLoadingMXBean.getUnloadedClassCount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loadedClassesInfo;
    }

    public Map<String, Long> getGCInfo() {
        Map<String, Long> gcInfo = new HashMap<>();
        try {
            // In the Management Factory you find only one GC count and time. We need two (scavenge and marksweep)
            gcInfo.put("ps_scavenge.count", (Long) getAttributeValue("java.lang:type=GarbageCollector,name=PS Scavenge", "CollectionCount"));
            gcInfo.put("ps_scavenge.time", (Long) getAttributeValue("java.lang:type=GarbageCollector,name=PS Scavenge", "CollectionTime"));
            gcInfo.put("ps_marksweep.count", (Long) getAttributeValue("java.lang:type=GarbageCollector,name=PS MarkSweep", "CollectionCount"));
            gcInfo.put("ps_marksweep.time", (Long) getAttributeValue("java.lang:type=GarbageCollector,name=PS MarkSweep", "CollectionTime"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gcInfo;
    }

    private Object getAttributeValue(String objectName, String attributeName) throws Exception {
        return jmxConnector.getMBeanServerConnection().getAttribute(new ObjectName(objectName), attributeName);
    }

    @PreDestroy
    public void closeJMXConnector() {
        try {
            if (jmxConnector != null) {
                jmxConnector.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}