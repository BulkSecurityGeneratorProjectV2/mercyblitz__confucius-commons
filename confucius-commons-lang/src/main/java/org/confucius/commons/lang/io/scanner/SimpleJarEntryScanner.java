/**
 *
 */
package org.confucius.commons.lang.io.scanner;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.confucius.commons.lang.util.jar.JarUtil;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Simple {@link JarEntry} Scanner
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see SimpleJarEntryScanner
 * @since 1.0.0
 */
public class SimpleJarEntryScanner {

    /**
     * Singleton
     */
    public static final SimpleJarEntryScanner INSTANCE = new SimpleJarEntryScanner();

    protected SimpleJarEntryScanner() {

    }

    /**
     * @param jarURL
     *         {@link URL} of {@link JarFile} or {@link JarEntry}
     * @param recursive
     *         recursive
     * @return Read-only {@link Set}
     * @throws NullPointerException
     *         If argument <code>null</code>
     * @throws IllegalArgumentException
     *         <ul> <li>{@link JarUtil#resolveRelativePath(URL)}
     * @throws IOException
     *         <ul> <li>{@link JarUtil#toJarFile(URL)}
     * @since 1.0.0
     */
    @Nonnull
    public Set<JarEntry> scan(URL jarURL, final boolean recursive) throws NullPointerException, IllegalArgumentException, IOException {
        return this.scan(jarURL, recursive, null);
    }

    /**
     * @param jarURL
     *         {@link URL} of {@link JarFile} or {@link JarEntry}
     * @param recursive
     *         recursive
     * @param jarEntryFilter
     *         {@link JarUtil.JarEntryFilter}
     * @return Read-only {@link Set}
     * @throws NullPointerException
     *         If argument <code>null</code>
     * @throws IllegalArgumentException
     *         {@link JarUtil#resolveJarAbsolutePath(URL)}
     * @throws IOException
     *         {@link JarUtil#toJarFile(URL)}
     * @see JarUtil.JarEntryFilter
     * @since 1.0.0
     */
    @Nonnull
    public Set<JarEntry> scan(URL jarURL, final boolean recursive, JarUtil.JarEntryFilter jarEntryFilter) throws NullPointerException, IllegalArgumentException, IOException {
        String relativePath = JarUtil.resolveRelativePath(jarURL);
        JarFile jarFile = JarUtil.toJarFile(jarURL);
        return scan(jarFile, relativePath, recursive, jarEntryFilter);
    }


    /**
     * @param jarFile
     * @param recursive
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public Set<JarEntry> scan(JarFile jarFile, final boolean recursive) throws NullPointerException, IllegalArgumentException, IOException {
        return scan(jarFile, recursive, null);
    }

    /**
     * @param jarFile
     * @param recursive
     * @param jarEntryFilter
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public Set<JarEntry> scan(JarFile jarFile, final boolean recursive, JarUtil.JarEntryFilter jarEntryFilter) throws NullPointerException, IllegalArgumentException, IOException {
        return scan(jarFile, StringUtils.EMPTY, recursive, jarEntryFilter);
    }

    protected Set<JarEntry> scan(JarFile jarFile, String relativePath, final boolean recursive, JarUtil.JarEntryFilter jarEntryFilter) throws NullPointerException, IllegalArgumentException, IOException {
        Set<JarEntry> jarEntriesSet = Sets.newLinkedHashSet();
        List<JarEntry> jarEntriesList = JarUtil.filter(jarFile, jarEntryFilter);

        for (JarEntry jarEntry : jarEntriesList) {
            String jarEntryName = jarEntry.getName();

            boolean accept = false;
            if (recursive) {
                accept = jarEntryName.startsWith(relativePath);
            } else {
                if (jarEntry.isDirectory()) {
                    accept = jarEntryName.equals(relativePath);
                } else {
                    int beginIndex = jarEntryName.indexOf(relativePath);
                    if (beginIndex == 0) {
                        accept = jarEntryName.indexOf("/", relativePath.length()) < 0;
                    }
                }
            }
            if (accept) {
                jarEntriesSet.add(jarEntry);
            }
        }
        return Collections.unmodifiableSet(jarEntriesSet);
    }
}