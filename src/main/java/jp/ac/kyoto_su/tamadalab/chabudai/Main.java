package jp.ac.kyoto_su.tamadalab.chabudai;

import java.io.IOException;
import java.util.Arrays;

public class Main{
    public Main(String[] args){
        Arrays.stream(args)
            .map(dir -> convert(dir))
            .forEach(item -> println(item));
    }

    private void println(Project project) {
        try{
            project.stream()
            .forEach(method -> println(project, method));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void println(Project project, Method method) {
        System.out.printf("%s,%s,%s,%s,%s", project.baseName(), method.path(),
                method.className(), method.methodName(),
                method.returnType());
        method.arguments().forEach(type -> System.out.printf(",%s", type));
        System.out.println();
    }

    private Project convert(String targetDir){
        return new Project(targetDir);
    }

    public static void main(String[] args){
        new Main(args);
    }
}
