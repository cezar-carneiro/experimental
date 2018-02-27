package com.oproom.service;

import com.oproom.model.DirectoryInfo;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Cezar Carneiro on 27/02/2018.
 */

public interface IFileSystemService {

    DirectoryInfo getDir(String path) throws Exception;
    File getFile(String path) throws Exception;
    boolean delete(String path) throws Exception;
    void saveFile(String dir, String filename, InputStream is) throws Exception;
    OutputStream createNewFileOutputStream(String dir, String filename) throws Exception;
    boolean createDir(String path) throws Exception;

}
