/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bahanag;


import static Bahanag.WatchDirRecursively.cast;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


import java.io.IOException;
import java.net.*;
import java.net.InetAddress;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.LinkOption;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nick Bahanag
 */
@ManagedBean
@RequestScoped
public class TabConstr {

    private List<Line> Mylist;

    /**
     * Creates a new instance of TabConstr
     */
    private  WatchService watcher;
    private  Map<WatchKey,Path> keys;
    private  boolean recursive;
    private boolean trace = false;

    @SuppressWarnings("unchecked")
    static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }
    
    
    
    
    
    public TabConstr() {
    }

    public List<Line> getMylist() {
        
        Mylist = new ArrayList<Line>();


        BufferedReader br = null;

        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader("C:\\Users\\nkondock\\Desktop\\log_nick.txt"));
int i=0;
            while ((sCurrentLine = br.readLine()) != null) {
                StringTokenizer token= new StringTokenizer(sCurrentLine, ";");
                Line c = new Line();
          
                while (token.hasMoreTokens()){
                    
                    String afficheur=token.nextToken();
                    System.out.println(afficheur);
                    c.setId(i);
                    c.setTime(afficheur);
                    afficheur=token.nextToken();
                    c.setEvent(afficheur);
                    afficheur=token.nextToken();
                    c.setResource(afficheur);
                    afficheur=token.nextToken();
                    c.setUser_name(afficheur);
                    afficheur=token.nextToken();
                    c.setMachine_name(afficheur);
                    
                    
                   
                     
                }
             Mylist.add(c);
                
              i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



//        for (int i = 0; i < 10; i++) {
//            Line c = new Line(i, new Date(), "message" + i, "color" + i);
//            Mylist.add(c);
//        }


        return Mylist;
    }
    
    
    public void demonNick() {
    
  System.out.println("démon qui scrute la clé usb");
    }
    
    
    
private void register(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        if (trace) {
            Path prev = keys.get(key);
            if (prev == null) {
                System.out.format("register: %s\n", dir);
            } else {
                if (!dir.equals(prev)) {
                    System.out.format("update: %s -> %s\n", prev, dir);
                }
            }
        }
        keys.put(key, dir);
    }

    /**
     * Register the given directory, and all its sub-directories, with the
     * WatchService.
     */
    private void registerAll(final Path start) throws IOException {
        // register directory and sub-directories
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                throws IOException
            {
                register(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Creates a WatchService and registers the given directory
     */
    TabConstr(Path dir, boolean recursive) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<WatchKey,Path>();
        this.recursive = recursive;

        if (recursive) {
            System.out.format("Scanning %s ...\n", dir);
            registerAll(dir);
            System.out.println("Done.");
        } else {
            register(dir);
        }

        // enable trace after initial registration
        this.trace = true;
    }

    /**
     * Process all events for keys queued to the watcher
     */
    void processEvents() {
        for (;;) {

            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                // TBD - provide example of how OVERFLOW event is handled
                if (kind == OVERFLOW) {
                    continue;
                }

                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path child = dir.resolve(name);

                // print out event
                
                
                String evenement=event.kind().name();
                String Ressource=String.valueOf(child);        
                String user = System.getProperty("user.name") ;
                
                java.net.InetAddress localMachine = null;
                try {
                    localMachine = java.net.InetAddress.getLocalHost();
                } catch (UnknownHostException ex) {
                    Logger.getLogger(WatchDirRecursively.class.getName()).log(Level.SEVERE, null, ex);
                }
//                System.out.println("Hostname of local machine: " + localMachine.getHostName());
                
                String Machaine=new Date ()+";"+evenement+";"+Ressource+";"+user+";"+localMachine.getHostName();
                
                System.out.println(Machaine);
//                String userWindows = System.getenv("USERNAME");
                
//                System.out.print(new Date ());
//                System.out.print(";");                 
//                System.out.format("%s: %s\n", event.kind().name(), child);
//                System.out.print(";");
//                System.out.print(user);
//                System.out.print(";");
//                System.out.println("Username using environment variable in windows : "  +userWindows);





                // if directory is created, and watching recursively, then
                // register it and its sub-directories
                if (recursive && (kind == ENTRY_CREATE)) {
                    try {
                        if (Files.isDirectory(child,LinkOption.NOFOLLOW_LINKS)) {
                            registerAll(child);
                        }
                    } catch (IOException x) {
                        // ignore to keep sample readbale
                    }
                }
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
    }

    static void usage() {
        System.err.println("usage: java WatchDir [-r] dir");
        System.exit(-1);
    }

    public  static void nickRecursif(String[] args) throws IOException {
        // parse arguments
        
        
        if (args.length == 0 || args.length > 2)
            usage();
        boolean recursive = false;
        int dirArg = 0;
        if (args[0].equals("-r")) {
            if (args.length < 2)
                usage();
            recursive = true;
            dirArg++;
        }

        // register directory and process its events
        Path dir = Paths.get(args[dirArg]);
        new WatchDirRecursively(dir, recursive).processEvents();
    }
    
    
    public static void main (String[] args) throws IOException{
        String tab[] = new String[2];
        
        tab[0]="-r";

        tab[1]="E:";
   
        nickRecursif(tab);
        

    }
    
    public void journaliser()throws IOException{
        String tab[] = new String[2];
        tab[0]="-r";

        tab[1]="E:";
   
        nickRecursif(tab);
    }    



}
        
