package jp.ac.kyoto_su.tamadalab.chabudai;

import java.nio.file.Path;
import java.util.stream.Stream;

public class FileTreeWalker {
    public Stream<Path> walk(Path base){
        return Stream.of(base);
    }
}
