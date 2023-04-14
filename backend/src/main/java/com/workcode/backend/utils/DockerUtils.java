package com.workcode.backend.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.LogContainerCmd;
import com.github.dockerjava.api.model.Frame;

@Component
public class DockerUtils {
    private static Logger myLogger = LoggerFactory.getLogger(DockerUtils.class);

    @Autowired
    private DockerClient dockerClient;

    public List<String> getDockerLogs(String containerId) {
        final List<String> logs = new ArrayList<>();
        int lastLogTime = (int) (System.currentTimeMillis() / 1000);

        LogContainerCmd logContainerCmd = dockerClient.logContainerCmd(containerId);
        logContainerCmd.withStdOut(true).withStdErr(true);
        logContainerCmd.withSince(lastLogTime); // UNIX timestamp (integer) to filter logs. Specifying a timestamp will
                                                // only output log-entries since that timestamp.
        // logContainerCmd.withTail(4); // get only the last 4 log entries

        logContainerCmd.withTimestamps(true);

        try {
            logContainerCmd.exec(new ResultCallback.Adapter<>() {
                @Override
                public void onNext(Frame object) {
                    logs.add(object.toString());
                }
            }).awaitCompletion();
            // logContainerCmd.exec(new LogContainerResultCallback() {
            // @Override
            // public void onNext(Frame item) {
            // logs.add(item.toString());
            // }
            // }).awaitCompletion();
        } catch (InterruptedException e) {
            myLogger.error("Interrupted Exception!" + e.getMessage());
        }

        lastLogTime = (int) (System.currentTimeMillis() / 1000) + 5; // assumes at least a 5 second wait between calls
                                                                     // to getDockerLogs

        return logs;
    }
}
