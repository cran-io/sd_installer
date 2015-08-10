package cran.io.com.sdinstaller;

//  This is the class that we use to descompress the zip files that are encrypted
//  for these class we use a java library

import android.util.Log;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;

public class Decompress {

//  Here we declare the strings that we are going to use

    private String _zipFile;
    private String _location;
    private String password;

//  This is the constructor method that sets the string for the class

    public Decompress(String zipFile, String location, String pass)
    {

        _zipFile = zipFile;
        _location = location;
        password = pass;
        _dirChecker("");

    }

//  With these method we unzip the encrypted file

    public boolean unzip() throws IOException
    {
        try
        {
            ZipFile zipFile = new ZipFile(_zipFile);
            if (zipFile.isEncrypted())
            {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(_location);
            return true;
        }
        catch (ZipException e)
        {
            return false;
        }
    }

//  This method is use to check te location of the file that we are going to unzip

    private void _dirChecker(String dir)
    {
        File f = new File(_location + dir);
        if(!f.isDirectory()) {
            f.mkdirs();
        }
    }
}
