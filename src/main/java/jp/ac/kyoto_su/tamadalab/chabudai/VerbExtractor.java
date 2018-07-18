package jp.ac.kyoto_su.tamadalab.chabudai;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class VerbExtractor{
    private MethodPool pool = new MethodPool();
    private VerbExtractionVisitor visitor = new VerbExtractionVisitor(pool);

    public Stream<Method> extract(Path path){
        CompilationUnit unit = parse(path);
        unit.accept(visitor);
        return pool.stream().peek(method -> method.apply(path));
    }

    private CompilationUnit parse(Path path){
        ASTParser parser = ASTParser.newParser(AST.JLS10);
        parser.setSource(toCharArray(path));
        return (CompilationUnit) parser.createAST(new NullProgressMonitor());
    }

    private char[] toCharArray(Path path){
        try {
            return readFile(path);
        } catch(IOException e) {
            return new char[0];
        }
    }

    private char[] readFile(Path path) throws IOException {
        return Files.lines(path)
            .map(line -> line.toCharArray())
            .reduce(new char[0], (array1, array2) -> mergeArray(array1, array2));
    }

    private char[] mergeArray(char[] array1, char[] array2){
        char[] array = new char[array1.length + array2.length];
        System.arraycopy(array1, 0, array, 0, array1.length);
        System.arraycopy(array2, 0, array, array1.length, array2.length);
        return array;
    }
}
