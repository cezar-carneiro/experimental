package com.oproom.service;

import android.content.Context;

import com.oproom.model.DirectoryInfo;
import com.oproom.model.FileEntryInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Cezar Carneiro on 24/1/2018.
 */

public class FileSystemService extends BaseService {

    public FileSystemService(Context context) {
        super(context);
    }

    public DirectoryInfo getDir(String path) throws Exception {
        if(path == null) {
            path = mContext.getApplicationInfo().dataDir;
        }
        File file = new File(path);
        if(!file.exists()){
            throw new Exception(path + " doesn't exist.");
        }
        if (!file.canRead()){
            throw new Exception("Can't read " + path);
        }
        if (!file.isDirectory()){
            throw new Exception(path + " is not a directory.");
        }

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setName(file.getName());
        directoryInfo.setAbsolutePath(file.getAbsolutePath());
        directoryInfo.setLength(file.length());

        File[] files = file.listFiles();
        FileEntryInfo[] entries = new FileEntryInfo[files.length];
        for (int i = 0; i < files.length; i++) {
            entries[i] = new FileEntryInfo();
            entries[i].setName(files[i].getName());
            entries[i].setLength(files[i].length());
            entries[i].setLastModified(files[i].lastModified());
            entries[i].setDir(files[i].isDirectory());
        }
        directoryInfo.setChildren(entries);

        return directoryInfo;
    }

    public File getFile(String path) throws Exception {
        File file = new File(path);
        if(!file.exists()){
            throw new Exception(path + " doesn't exist.");
        }
        if (!file.canRead()){
            throw new Exception("Can't read " + path);
        }
        if (!file.isFile()){
            throw new Exception(path + " is not a file.");
        }

        return file;
    }

    public boolean delete(String path) throws Exception {
        File file = new File(path);
        if(!file.exists()){
            throw new Exception(path + " doesn't exist.");
        }
        if (!file.canWrite()){
            throw new Exception("Can't delete " + path);
        }

        return file.delete();
    }

    public void saveFile(String dir, String filename, InputStream is) throws Exception {
        OutputStream fos = createNewFileOutputStream(dir, filename);
        int read = -1;
        byte[] buff = new byte[4096];
        while ((read = is.read(buff)) != -1){
            fos.write(buff, 0, read);
        }
        is.close();
        fos.flush();
        fos.flush();
        fos.close();
    }

    public OutputStream createNewFileOutputStream(String dir, String filename) throws Exception {
        File file = new File(dir);
        if(!file.exists() && !file.mkdirs()){
            throw new Exception(dir + " is invalid.");
        }
        if (!file.canWrite()){
            throw new Exception("Can't write to " + dir);
        }

        FileOutputStream fos = new FileOutputStream(new File(dir, filename));
        return fos;
    }

    public boolean createDir(String path) throws Exception {
        File dir = new File(path);
        if(dir.exists()) {
            if(dir.isFile()){
                throw new Exception(path + " already exists as a file.");
            }
            throw new Exception(path + " already exists.");
        }
        return dir.mkdirs();
    }
}
