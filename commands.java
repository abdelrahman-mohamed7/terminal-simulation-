import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class commands {
    public static void main(String []args) throws IOException{
        Scanner In = new Scanner(System.in);
        functions obj = new functions();
        Path path = Paths.get("M:\\");
        String p = path.toString();
        while(true) {
            System.out.print("User@User-virtual-machine:~$");
            String ch = In.nextLine();
            String str[] = ch.split(" ");
            int ind = str.length;
            if (str[0].equals("pwd")) {
                System.out.println(p);
            }
            else if (str[0].equals("cd")) {
                if (str[1].startsWith("\\")) {
                    p = obj.cd(str[1]);
                    path = Paths.get(str[1]);
                }
                else if (str[1].equals("..")) {
                    path = path.getParent();
                    p = path.toString();
                }
                else {
                    str[1] = "\\" + str[1];
                    File tmp = new File(p + str[1]);
                    if (tmp.isDirectory()) {
                        p += obj.cd(str[1]);
                        path = Paths.get(p);
                    } else {
                        System.out.println("bash: cd:" + str[1] + ": No such directory");
                    }
                }
            }
            else if (str[0].equals("mkdir")) {
                String dirname = str[1];
                File dir = new File(p + ("\\" + dirname));
                dir.mkdir();
            }

            else if (str[0].equals("rmdir") || str[0].equals("rm")) {
                File dir = new File(p + ("\\" + str[1]));
                dir.delete();
            }
            else if (str[0].equals("ls")) {
                obj.ls(p);
            }
            else if (str[0].equals("more")) {
                try {
                    path = Paths.get(p + ("\\" + str[1]));
                    Scanner myReader = new Scanner(path);
                    while (myReader.hasNextLine()) {
                        System.out.print(myReader.nextLine());
                        String t = In.nextLine();
                        for (int i = 0 ; i <3 && myReader.hasNextLine() ;i++)
                            System.out.println(myReader.nextLine());
                    }
                    myReader.close(); }
                catch (Exception e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }

            else if (str[0].equals("mv")) {
                String er = str[1];
                String pn = p + str[2];
                try{
                String t[] = pn.split(".");
                Path mv = Files.move(Paths.get(p + ("\\" + er)), Paths.get(pn));}
                catch (Exception e){
                    Path mv = Files.move(Paths.get(p + ("\\" + er)), Paths.get(pn+("\\" + er)));}
            }

            else if (str[0].equals("date") && ind == 1) {
                System.out.println(obj.date());
            }

            else if (str[0].equals("cp")) {
                FileInputStream instream = null;
                FileOutputStream outstream = null;

                try {
                    File infile = new File(p + ("\\" + str[1]));
                    File outfile = new File(p + ("\\" + str[2]));

                    instream = new FileInputStream(infile);
                    outstream = new FileOutputStream(outfile);

                    byte[] buffer = new byte[1024];

                    int length;
                    while ((length = instream.read(buffer)) > 0) {
                        outstream.write(buffer, 0, length);
                    }

                    instream.close();
                    outstream.close();

                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
            else if (str[0].equals("cat")) {
                for (int i = 1; i < str.length; i++) {
                    try {
                        path = Paths.get(p + ("\\" + str[i]));
                        Scanner myReader = new Scanner(path);
                        while (myReader.hasNextLine()) {
                            System.out.println(myReader.nextLine());

                        }
                        myReader.close();
                    } catch (Exception e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();

                    }
                }
            }
            else if (str[0].equals("clear") && ind == 1) {
                obj.clear();
            }
            else if (ind == 1)
                System.out.println(str[0] + ": command not found");

            else if (str[1].equals(">")) {
                if (str[0].equals("date"))
                    str[0] = obj.date();
                File tmpDir = new File(p);
                boolean exists = tmpDir.exists();
                if (!exists) System.out.println("there no such a directory");

                if (!tmpDir.isDirectory()) System.out.println("  is a directory");

                File N = new File(p + str[2]);
                exists = N.exists();
                if (N.exists() && N.isFile()) {
                    new FileWriter((p + ("\\" + str[2])), false).close();

                    try {
                        FileWriter NE = new FileWriter((p + ("\\" + str[2])));

                        NE.write(str[0]);
                        NE.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }

                }
                else {
                    try {
                        FileWriter P = new FileWriter((p + ("\\" + str[2])));
                        P.write(str[0]);
                        P.close();
                    } catch (IOException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }


                }
            }
            else if (str[1].equals(">>")) {
                if (str[0].equals("date"))
                    str[0] = obj.date();
                File tmpDir = new File(p);
                boolean exists = tmpDir.exists();
                if (!exists) System.out.println("there no such a directory");

                if (!tmpDir.isDirectory()) System.out.println(" is a directory");

                // test to see if a file exists
                File N = new File(p + str[2]);
                exists = N.exists();
                if (N.exists() && N.isFile()) {
                    try (FileWriter fw = new FileWriter((p + ("\\" + str[2])), true);
                         BufferedWriter bw = new BufferedWriter(fw);
                         PrintWriter out = new PrintWriter(bw)) {
                        out.println(str[0]);
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                }
                else {
                    new FileWriter((p + ("\\" + str[2])), true).close();
                    try (FileWriter fw = new FileWriter((p + ("\\" + str[2])), true);
                         BufferedWriter bw = new BufferedWriter(fw);
                         PrintWriter out = new PrintWriter(bw)) {
                        out.println(str[0]);

                    } catch (IOException e) {
                        System.out.println(e);
                    }


                }
            }

        }


    }



    }

