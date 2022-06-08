import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class functions {

 public static void pwd(String p){
  System.out.println(p);
 }

 public static String cd(String pth){
      return pth;
 }

 public void ls(String path) {
  String [] Lists;
  Path p = Paths.get(path);
  File file = new File(p+" ");
  Lists = file.list();
  int count = 0;
  for (String List : Lists)
  {
   if(count < 2)
   {
    System.out.print(List + " ");
    count ++;
   }
   else
   {
    count = 0;
    System.out.println(List + " ");
   }
  }
  System.out.println("\n");
 }

 public static String date()
 {
  Date thisdate = new Date();
  SimpleDateFormat dateform = new SimpleDateFormat("MM/dd/Y HH:mm:ss");
  return dateform.format(thisdate);
 }

 public static void cp(File source, File destination)
         throws IOException
 {
  byte[] buffer = new byte[1024];
  FileInputStream fis = new FileInputStream(source);
  FileOutputStream fos = new FileOutputStream(destination);

  int read;
  while((read = fis.read(buffer)) != -1)
  {
   fos.write(buffer, 0, read);
  }
  fos.close();
  fis.close();
 }
 public static void clear() {
  try {
   Robot robbie = new Robot();
   robbie.keyPress(16);
   robbie.keyPress(121);
   robbie.keyRelease(16);
   robbie.keyRelease(121);

  } catch (AWTException ex) {
  }
  /*function clear : it is optimized for working on intellij ide
   * for eclipse change 16 -> 17 & 121->122
   * we found this function
   * new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
   * but it does not work properly with us
   */
 }


}




