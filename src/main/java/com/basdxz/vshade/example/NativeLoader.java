/*
 * All Rights Reserved
 *
 * Copyright (c) 2021 basdxz
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.basdxz.vshade.example;

import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NativeLoader {
    public static final String NATIVES_DIR = "natives";

    public static void loadLWJGL() {
        val os = System.getProperty("os.name").toLowerCase();
        if (os.contains("windows")) {
            unpackNativeLib("lwjgl64.dll");
        } else if (os.contains("linux")) {
            unpackNativeLib("liblwjgl64.so");
        } else {
            throw new IllegalStateException("Unsupported OS");
        }
        configLWJGL();
    }

    private static void configLWJGL() {
        System.setProperty("org.lwjgl.librarypath", System.getProperty("user.dir") + "/" + NATIVES_DIR);
    }

    private static void unpackAndLoadNativeLib(String libFileName) {
        loadUnpackedLibrary(unpackNativeLib(libFileName));
    }

    private static File unpackNativeLib(String libFileName) {
        var unpackedLibFile = unpackedLibFile(libFileName);
        if (unpackedLibExists(unpackedLibFile)) {
            if (unpackedLibraryHashCheck(packedLibInputStream(libFileName), unpackedLibFile)) {
                return unpackedLibFile;
            } else {
                if (!unpackedLibFile.delete())
                    throw new RuntimeException("Failed to delete: " + unpackedLibFile.getAbsolutePath());
            }
        }
        unpackLibrary(packedLibInputStream(libFileName), unpackedLibFile);
        if (!unpackedLibraryHashCheck(packedLibInputStream(libFileName), unpackedLibFile))
            throw new RuntimeException("Failed to unpack: " + unpackedLibFile.getAbsolutePath());
        return unpackedLibFile;
    }

    @SneakyThrows
    private static InputStream packedLibInputStream(String libFileName) {
        return NativeLoader.class.getResourceAsStream("/" + libFileName);
    }

    private static File unpackedLibFile(String libFileName) {
        return new File(String.format("%s%s%s", NATIVES_DIR, File.separator, libFileName));
    }

    private static boolean unpackedLibExists(File unpackedLibrary) {
        return unpackedLibrary.isFile();
    }

    @SneakyThrows
    private static boolean unpackedLibraryHashCheck(InputStream packedLibInputStream, File unpackedLibFile) {
        return DigestUtils.sha256Hex(packedLibInputStream).equals(
                DigestUtils.sha256Hex(Files.newInputStream(unpackedLibFile.toPath())));
    }

    @SneakyThrows
    private static void unpackLibrary(InputStream packedLibInputStream, File unpackedLibFile) {
        FileUtils.copyInputStreamToFile(packedLibInputStream, unpackedLibFile);
    }

    private static void loadUnpackedLibrary(File unpackedLibrary) {
        System.load(unpackedLibrary.getAbsolutePath());
    }
}
