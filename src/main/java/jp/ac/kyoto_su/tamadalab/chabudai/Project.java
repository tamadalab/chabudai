package jp.ac.kyoto_su.tamadalab.chabudai;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Project {
    private VerbExtractor extractor = new VerbExtractor();
    private String baseName;

    public Project(String baseName) {
        this.baseName = baseName;
    }

    public String baseName() {
        return baseName;
    }

    public Stream<Method> stream() throws IOException {
        return listUp(baseName)
                .flatMap(file -> extractor.extract(file));
    }

    private Stream<Path> listUp(String path) throws IOException {
        List<Path> list = new ArrayList<>();
        Files.walkFileTree(Paths.get(path), new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(file.toString().endsWith(".java"))
                    list.add(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
        return list.stream();
    }
}
