package jp.ac.kyoto_su.tamadalab.chabudai;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;

public class Method {
    private SimpleName className;
    private SimpleName methodName;
    private List<Type> signatures = new ArrayList<>();
    private Path path;

    public Method(SimpleName className, MethodDeclaration md) {
        this.className = className;
        this.methodName = md.getName();
        signatures.add(md.getReturnType2());
        for(Object type: md.parameters()) {
            signatures.add(((SingleVariableDeclaration) type).getType());
        }
    }

    void apply(Path path) {
        this.path = path;
    }

    public String path() {
        Optional<Path> sourcePath = Optional.ofNullable(this.path);
        return sourcePath.map(path -> path.toString())
                .orElse("");
    }

    public String className() {
        return className.getFullyQualifiedName();
    }

    public String methodName() {
        return methodName.getIdentifier();
    }

    public String returnType() {
        Optional<Type> returnType = Optional.ofNullable(signatures.get(0));
        return returnType.map(item -> item.toString())
                .orElse("");
    }

    public Stream<String> arguments() {
        return signatures.stream()
                .skip(1)
                .map(type -> type.toString());
    }
}
