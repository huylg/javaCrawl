/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Document doc = Jsoup.connect(url).get();
 *
 * @author LENOVO
 */
public class LoadHtml {

    static String strHostUrl;
    static URL hostUrl;
static String loadImg(String strUrl) throws IOException, IOException {
        URL url;

        try {
            url = new URL(strUrl);
            if (hostUrl.getHost().compareTo(url.getHost()) != 0)// link child
            {
                return null;
            }

        } catch (MalformedURLException e) {
            if (strUrl.charAt(0) != '/') {
                strUrl = strHostUrl + "/" + strUrl;
            } else {
                strUrl = strHostUrl + strUrl;
            }
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

        File file = new File(filePath);

        if (file.exists()) {
            return file.getAbsolutePath();
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

        } catch (MalformedURLException e) {
            return null;
        } catch (FileNotFoundException e) {
            return null;
        }

        return file.getAbsolutePath();

    }


    static String loadJs(String strUrl) throws IOException, IOException {
        URL url;

        try {
            url = new URL(strUrl);
            if (hostUrl.getHost().compareTo(url.getHost()) != 0)// link child
            {
                return null;
            }

        } catch (MalformedURLException e) {
            if (strUrl.charAt(0) != '/') {
                strUrl = strHostUrl + "/" + strUrl;
            } else {
                strUrl = strHostUrl + strUrl;
            }
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

        if (file.exists()) {
            return file.getAbsolutePath();
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

        } catch (MalformedURLException e) {
            return null;
        } catch (FileNotFoundException e) {
            return null;
        }

        return file.getAbsolutePath();

    }

    static String loadCss(String strUrl) throws IOException, IOException {
        URL url;

        try {
            url = new URL(strUrl);
            if (hostUrl.getHost().compareTo(url.getHost()) != 0)// link child
            {
                return null;
            }

        } catch (MalformedURLException e) {
            if (strUrl.charAt(0) != '/') {
                strUrl = strHostUrl + "/" + strUrl;
            } else {
                strUrl = strHostUrl + strUrl;
            }

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

        File file = new File(filePath + ".css");

        if (file.exists()) {
            return file.getAbsolutePath();
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

        } catch (MalformedURLException e) {
            return null;
        } catch (FileNotFoundException e) {
            return null;
        }

        return file.getAbsolutePath();

    }

    static String loadHtml(String strUrl) throws IOException, InterruptedException {

        URL url;

        try {
            url = new URL(strUrl);

            if (hostUrl.getHost().compareTo(url.getHost()) != 0)// link child
            {
                return null;
            }

        } catch (MalformedURLException e)// Url is not valid
        {
            if (strUrl.indexOf(":") != -1) {
                return null;
            }

            if (strUrl.compareTo("#") == 0) {
                return "#";
            }

       

            if (strUrl == null || strUrl.isEmpty()) {
                return null;
            }

            if (strUrl.charAt(0) != '/') {
                strUrl = strHostUrl + "/" + strUrl;
            } else {
                strUrl = strHostUrl + strUrl;
            }
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

        File file = new File(filePath + ".html");

        if (file.exists()) {
            return file.getAbsolutePath();
        }
        Document doc;
        try {
            doc = Jsoup.connect(strUrl).get();
        } catch (IOException e) {

            return null;
        }

        FileWriter fileWriter = new FileWriter(file);

        doc.getElementsByTag("base").attr("href", "");

        fileWriter.write(doc.toString());

        fileWriter.close();
        System.out.println(file.getAbsoluteFile());
        for (Element e : doc.getAllElements()) {

            if (e.tagName().compareTo("a") == 0 && e.hasAttr("href")) {
                String filePathReturn = loadHtml(e.attr("href"));
                if (filePathReturn != null) {

                    if (filePathReturn.compareTo("#") == 0) {
                        filePathReturn = file.getAbsolutePath();
                    }
                    e.attr("href", filePathReturn);
                    FileWriter fileWriter1 = new FileWriter(file);
                    fileWriter1.write(doc.toString());

                    fileWriter.close();

                }
            } else if (e.tagName().compareTo("link") == 0 && e.attr("type").compareTo("text/css") == 0) {

                LoadCss loadCss = new LoadCss(e.attr("href"),e,doc,file);
                loadCss.start();
              
                

            } else if (e.tagName().compareTo("script") == 0 && e.attr("type").compareTo("text/javascript") == 0 && e.hasAttr("src")) {
                LoadJs loadJs = new LoadJs(e.attr("src"),e,doc,file);
                loadJs.start();
            }
            else if(e.tagName().compareTo("img")==0 && e.hasAttr("src"))
            {
                LoadImg loadImg = new LoadImg(e.attr("src"),e,doc,file);
                loadImg.start();
            }
        }

        return file.getAbsolutePath();
    }

    public static void main(String arg[]) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        //strHostUrl=scanner.nextLine();
        strHostUrl = scanner.nextLine();
        
        try {
            if(strHostUrl.endsWith("/"))
                strHostUrl = strHostUrl.substring(0, strHostUrl.length()-1);
            hostUrl = new URL(strHostUrl);
            loadHtml(strHostUrl);
        } catch (MalformedURLException e) {
            System.err.println("Url is not valid");

        }

    }

}
