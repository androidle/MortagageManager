package com.example.helloplugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class HelloPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("=====hellopulugin==============="+ project.getName());
        project.getTasks().create("helloPlugin",
                task -> System.out.println("=====myHello==execute========" + task.getName()));
    }
}