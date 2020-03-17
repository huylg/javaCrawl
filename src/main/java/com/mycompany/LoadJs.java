/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

import static com.mycompany.LoadHtml.hostUrl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author LENOVO
 */
public class LoadJs extends Thread{
    public String strUrl;
    public String result;
    static List<String> filePahths = new ArrayList<String>();  
   Element e;
   Document doc;
   File file;
     void WriteFile() throws IOException
    {
        String filePathReturn = result;
                if (filePathReturn != null) {
                    e.attr("src", filePathReturn);
                    FileWriter fileWriter1 = new FileWriter(file);
                    fileWriter1.write(doc.toString());
                    fileWriter1.close();
                }
    }
    public LoadJs(String strUrl,Element e,Document doc,File file)
    {
        this.strUrl = new String(strUrl);
        this.e =e;
        this.doc=doc;
        this.file=file;
    }
    
    @Override
    public void run() {
         URL url;

        try {
        url = new URL(strUrl);
            if (hostUrl.getHost().compareTo(url.getHost()) != 0)// link child
            {
                result = null;
                WriteFile();
                return ;
            }

        } catch (MalformedURLException e) {
            
            if(strUrl.isEmpty())
            {
                result = null;
                try {
                    WriteFile();
                } catch (IOException ex) {
                    Logger.getLogger(LoadJs.class.getName()).log(Level.SEVERE, null, ex);
                }
                return ;
            }
            if (strUrl.charAt(0) != '/') {
                strUrl = LoadHtml.strHostUrl + "/" + strUrl;
            } else {
                strUrl = LoadHtml.strHostUrl + strUrl;
            }

        } catch (IOException ex) {
            Logger.getLogger(LoadJs.class.getName()).log(Level.SEVERE, null, ex);
        }

        String filePath = strUrl.split("//")[1];

        if (filePath.charAt(filePath.length() - 1) == '\\' || filePath.charAt(filePath.length() - 1) == '/') {
            filePath = filePath.substring(0, filePath.length() - 1);
        }

        String[] lines = filePath.split("/");
        filePath = filePath.replaceAll("[:*?<>\"|]", "");
        String folderPath = "";

        for (int i = 0; i < lines.length - 1; i++) {
            folderPath += lines[i] + "/";
        }

        File Folder = new File(folderPath);

        if (!Folder.exists()) {
            Folder.mkdirs();
        }

        File file = new File(filePath + ".js");

        synchronized(this)
        {
        if (filePahths.indexOf(file.getAbsoluteFile())!=-1) {
            result = file.getAbsolutePath();
             try {
                 WriteFile();
             } catch (IOException ex) {
                 Logger.getLogger(LoadJs.class.getName()).log(Level.SEVERE, null, ex);
             }
            return ;
        }
        filePahths.add(file.getAbsolutePath());
        }
        String fromFile = strUrl;
        String toFile = file.getAbsolutePath();

        try {
            
            
            
           
            URL website = new URL(fromFile);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(toFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            System.out.println(toFile);
        
        } catch (IOException ex) {
            result = null;
             try {
                 WriteFile();
             } catch (IOException ex1) {
                 Logger.getLogger(LoadJs.class.getName()).log(Level.SEVERE, null, ex1);
             }
            return;
        }
        
        result = file.getAbsolutePath();
        try {
            WriteFile();
        } catch (IOException ex) {
            Logger.getLogger(LoadJs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }
}
