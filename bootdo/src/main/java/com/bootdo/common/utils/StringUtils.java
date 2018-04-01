package com.bootdo.common.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

import java.io.StringWriter;

/**
 * @author yangyt
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    public static String formatHtml(String str){
        try {
            Document document = DocumentHelper.parseText(str);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("utf-8");
            StringWriter writer = new StringWriter();
            HTMLWriter htmlWriter = new HTMLWriter(writer, format);
            htmlWriter.write(document);
            htmlWriter.close();
            writer.toString();
        }catch (Exception e) {
            e.printStackTrace();

        }
        return "";
    }
}
