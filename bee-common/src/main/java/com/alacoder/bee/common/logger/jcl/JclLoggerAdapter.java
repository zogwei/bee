package com.alacoder.bee.common.logger.jcl;

import java.io.File;

import org.apache.commons.logging.LogFactory;

import com.alacoder.bee.common.logger.Level;
import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerAdapter;

public class JclLoggerAdapter implements LoggerAdapter {

	public Logger getLogger(String key) {
		return new JclLogger(LogFactory.getLog(key));
	}

    public Logger getLogger(Class<?> key) {
        return new JclLogger(LogFactory.getLog(key));
    }

    private Level level;
    
    private File file;

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
