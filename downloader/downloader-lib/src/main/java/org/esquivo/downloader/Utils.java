/**
 * 
 */
package org.esquivo.downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author woo
 * 
 */
public final class Utils {
	private static final Logger LOG = LoggerFactory.getLogger(HCDownloader.class);
	
	private final static int BUFFER_SIZE = 4096;

	private Utils() {

	}

	public static File writeToTempFile(InputStream in) throws IOException {
		File tempFile = File.createTempFile("urldownloader-", null);
		
		writeToFile(in, tempFile);
		
		return tempFile;
	}
	
	public static void writeToFile(InputStream in, File file) throws IOException {
	    OutputStream out = new FileOutputStream(file);
		try {
			if (LOG.isDebugEnabled()) {
				LOG.info("Writing to file : " + file.getAbsolutePath());
			}

			IOUtils.copyLarge(in, out, new byte[BUFFER_SIZE]);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}
}
