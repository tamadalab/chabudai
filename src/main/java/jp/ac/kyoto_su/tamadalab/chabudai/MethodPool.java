package jp.ac.kyoto_su.tamadalab.chabudai;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MethodPool{
    private List<Method> methods = new ArrayList<>();

    public void store(Method method){
        methods.add(method);
    }
    
    public Stream<Method> stream(){
        return methods.stream();
    }
}
