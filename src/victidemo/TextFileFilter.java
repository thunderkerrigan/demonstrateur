/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package victidemo;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Thunderkerrigan
 */
public class TextFileFilter extends FileFilter{

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
        return true;
    }

    String extension = this.getFileExtension(f);
    if (extension != null) {
            return extension.equals("txt") ||
                    extension.equals("pdf") ||
                    extension.equals("docx") ||
                    extension.equals("doc");
    }

    return false;
}

    @Override
    public String getDescription() {
        return "Texte";
    }
    

    private String getFileExtension(File f) {
        String fileName = f.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
