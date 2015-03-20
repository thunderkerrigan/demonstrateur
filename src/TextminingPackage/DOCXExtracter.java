/**

Copyright 2012 Joseph Lewis <joehms22@gmail.com> | <joseph@josephlewis.net>

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

* Redistributions of source code must retain the above copyright
  notice, this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above
  copyright notice, this list of conditions and the following disclaimer
  in the documentation and/or other materials provided with the
  distribution.
* Neither the name of the  nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

This software recieves a docx file from the command line, and returns the text 
from its path.

**/
package TextminingPackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class DOCXExtracter {
    private static String convertStreamToString(java.io.InputStream is) {
        try(Scanner s = new java.util.Scanner(is).useDelimiter("\\A")) {
            return s.hasNext() ? s.next() : "";
        }
    }

    public static String extractFromPath(String path) throws IOException
    {
        ZipFile zipFile = new ZipFile(path);
        String ret = extractFromFile(zipFile);
        zipFile.close();
        return ret;
    }

    public static String extractFromFile(ZipFile f) throws IOException
    {
        ZipEntry e = f.getEntry("word/document.xml");
        InputStream is = f.getInputStream(e);
        String contents = convertStreamToString(is);
        is.close();
        return contents.replaceAll("(?s)\\<.*?\\>", " ");
    }

    public static void main(String[] args)
    {
        for(int i = 0; i < args.length; i++)
            try {
                System.out.println(extractFromPath(args[i]));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}