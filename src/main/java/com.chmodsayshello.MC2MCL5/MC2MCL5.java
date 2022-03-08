package com.chmodsayshello.MC2MCL5;

import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;


public class MC2MCL5 {
    public static void main(String[]args) throws IOException{
        String Name = new String();


        JFrame frame = new JFrame("Select Minecraft World save");

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(frame);
        File file = null;
        if (option == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            System.out.println("File Selected: " + file.getName());
            Name = file.getName();
            System.out.println("Path;");
            System.out.println(file);
            JOptionPane.showMessageDialog(new JFrame(), file, "Info", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Open command canceled");
            JOptionPane.showMessageDialog(new JFrame(), "Something went wrong :(, are you sure you selected a Minecraft world?", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        File out = new File(System.getProperty("user.dir")+"/"+file.getName()+"/map.sqlite");
        out.mkdirs();

        int question = JOptionPane.showConfirmDialog(new JFrame(), "Converting, this can take a veeeeeeery long time, when its done another dialoge will pop up, if you want to stay informed about the progress run this programm in the console, it logs into it, conversion starts after you press ok now! Do you want to continue?");
        if (question > 0){
            JOptionPane.showMessageDialog(new JOptionPane(), "Cancelled, absolutley no changes were done");
            System.exit(0);
        }


        System.out.println("Output dir:");
        System.out.println(out);

        System.setProperty("user.dir", file.toString());

        if(!Files.isDirectory(Paths.get(System.getProperty("user.dir")+"/region"))){
            JOptionPane.showMessageDialog(new JFrame(), "Something went wrong :(, are you sure you selected a Minecraft world?", "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        File region_dir = new File(file.toString()+"/region");
        String regions[] = region_dir.list();

        for(int i=0; i<regions.length; i++){ //This is the most important piece of code in this java class
            System.out.println("starting conversion: ");
            System.out.println(regions[i]);
            new convert(new File(region_dir.toString() +"/"+ regions[i]), regions[i], out);
        }


        System.out.println("DONE!");
        System.out.print("Saved nodes: ");
        JOptionPane.showMessageDialog(new JFrame(), "Done, you should be able to play the world now if you put it in the world folder, it is located at: " + out.toString(), "DONE!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
  
    }
}
