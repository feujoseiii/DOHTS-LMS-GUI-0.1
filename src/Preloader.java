import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class Preloader extends JFrame{

    //swing components variables
    private JPanel preloaderPanel;
    private JProgressBar progressBar;
    private JLabel checkingDescription;

    //file management variables
    private static String workingDirectory;
    private ArrayList<File> fileDependenciesDir = new ArrayList<>();
    private ArrayList<String> fileDependenciesErrors = new ArrayList<>();

    // add file dependencies below with file name and file extension, set to = {} to remove dependencies
    private static final String []fileNames = {};

    public Preloader(){
        this.setTitle("DOHTS LMS GUI 0.1");
        createView();
        pack();
        setVisible(true);
    }

    private void createView(){
        setContentPane(preloaderPanel);
        setPreferredSize(new Dimension(720,480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        try{ UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");}
        catch(Exception e){ e.printStackTrace();}

        Preloader preloaderPage = new Preloader();
        workingDirectory = System.getProperty("user.dir"); // gets the working directory upon execution
        if(preloaderPage.isDependenciesChecked(workingDirectory)){
            //no missing file display dashboard
        }else{
            //missing file
        }
    }

    private boolean isDependenciesChecked(String dir) {
        if(fileNames.length > 0){
            int filesCount = 100 / fileNames.length;
            for(String fileName : fileNames){
                fileDependenciesDir.add(new File(dir + "/" + fileName));
            }
            for(File dependency : fileDependenciesDir){
                checkingDescription.setText("Checking " + dependency.getAbsolutePath());
                if(!dependency.exists()){
                    //if file doesn't exist
                    fileDependenciesErrors.add(dependency.getAbsolutePath());
                }else{
                    progressBar.setValue(progressBar.getValue() + filesCount);
                }
            }
            if(fileDependenciesErrors.size() > 0){
                return false; //there are some files missing
            }else{
                return true; // no missing files in the dependencies. display dashboard
            }
        }else{
            progressBar.setValue(100);
            return true; // no dependencies so display dashboard
        }
    }

}
