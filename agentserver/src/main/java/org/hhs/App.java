package org.hhs;

import org.hhs.server.AgentServer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        AgentServer agentServer = new AgentServer();
        agentServer.initServer();
    }
}
