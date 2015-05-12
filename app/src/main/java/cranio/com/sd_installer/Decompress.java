package cranio.com.sd_installer;

//  This is the class that help to Decompress a file with it has a password
//  We use a open library for this "zip4j"

import android.util.Log;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.File;
import java.io.IOException;

public class Decompress {

    private String _zipFile;
    private String _location;
    private String password;

    public Decompress(String zipFile, String location, String pass)
    {

        _zipFile = zipFile;
        _location = location;
        password = pass;
        _dirChecker("");

    }

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

    private void _dirChecker(String dir)
    {
        File f = new File(_location + dir);
        if(!f.isDirectory()) {
            f.mkdirs();
        }
    }
}