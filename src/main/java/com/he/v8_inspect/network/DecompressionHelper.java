package com.he.v8_inspect.network;

import com.he.v8_inspect.console.CLog;
import com.he.v8_inspect.protocol.module.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.InflaterOutputStream;
import javax.annotation.Nullable;

public class DecompressionHelper {
    public static InputStream teeInputWithDecompression(NetworkPeerManager networkPeerManager, String str, InputStream inputStream, OutputStream outputStream, @Nullable String str2, ResponseHandler responseHandler) throws IOException {
        CountingOutputStream countingOutputStream;
        if (str2 != null) {
            boolean equals = "gzip".equals(str2);
            boolean equals2 = "deflate".equals(str2);
            if (equals || equals2) {
                countingOutputStream = new CountingOutputStream(outputStream);
                if (equals) {
                    outputStream = GunzippingOutputStream.create(countingOutputStream);
                } else if (equals2) {
                    outputStream = new InflaterOutputStream(countingOutputStream);
                }
                return new ResponseHandlingInputStream(inputStream, str, outputStream, countingOutputStream, networkPeerManager, responseHandler);
            }
            Console.MessageLevel messageLevel = Console.MessageLevel.WARNING;
            Console.MessageSource messageSource = Console.MessageSource.NETWORK;
            CLog.writeToConsole(networkPeerManager, messageLevel, messageSource, "Unsupported Content-Encoding in response for request #" + str + ": " + str2);
        }
        countingOutputStream = null;
        return new ResponseHandlingInputStream(inputStream, str, outputStream, countingOutputStream, networkPeerManager, responseHandler);
    }
}
