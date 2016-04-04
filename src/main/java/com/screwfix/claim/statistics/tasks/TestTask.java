package com.screwfix.claim.statistics.tasks;

import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

import java.io.PrintWriter;

// TODO: test
public class TestTask extends Task {

    public TestTask() {
        super("test");
    }

    @Override
    public void execute(ImmutableMultimap<String, String> immutableMultimap, PrintWriter printWriter) throws Exception {
        System.out.println("Do something interesting...");
    }
}
