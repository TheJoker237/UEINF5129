/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bahanag;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 * @author Nick Bahanag
 */
public class Find extends SimpleFileVisitor<Path> {
    
    /** Main program.
     * @param args Command line arguments - directories to search.
     */
    public static void main(final String... args) throws IOException {
        final FileVisitor<Path> fileVisitor = new Find();
        for (final String arg : args.length > 0 ? args : new String[] {"."}) {
            final Path root = Paths.get(arg);
            Files.walkFileTree(root, fileVisitor);
        }
    }

    /** {@inheritDoc} */
    public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) {
        if (".svn".equals(dir.getFileName().toString())) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        System.out.println(dir);
        return FileVisitResult.CONTINUE;
    }

    /** {@inheritDoc} */
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) {
        System.out.println(file);
        return FileVisitResult.CONTINUE;
    }
}
