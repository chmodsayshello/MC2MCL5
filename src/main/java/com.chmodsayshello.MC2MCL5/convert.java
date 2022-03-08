package com.chmodsayshello.MC2MCL5;

import net.querz.mca.Chunk;
import net.querz.mca.MCAFile;
import net.querz.mca.MCAUtil;


import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

public class convert {
    public convert(File file, String Name, File out) throws IOException { //I only want the filename as a Sting to make it easier for myself
        System.out.println("Converted: ");
        System.out.println(file.toString());
        String split_name = Name;
        split_name= split_name.replaceAll(".mca", "");
        split_name= split_name.replaceAll("r.", "");
        split_name= split_name.replace(".", "§"); //because java doesn't like to split dots, i replace it first
        System.out.println(split_name);
        String[] split_name_list = split_name.split("§");

        System.out.println("NAME SPLIT:");
        System.out.println(split_name_list[0]);

        int max_X;
        int min_X;
        int max_Z;
        int min_Z;


        if(Integer.parseInt(split_name_list[0]) >= 0){
            max_X = Integer.parseInt(split_name_list[0])*32*16;
            min_X = Integer.parseInt(split_name_list[0]);
            min_X = min_X -1;
            min_X = min_X *32*16;
            min_X ++;
        } else{                                                     //The maths has to be inverted if the int is negative
            max_X = Integer.parseInt(split_name_list[0])*32*16;
            min_X = Integer.parseInt(split_name_list[0]);
            min_X++;
            min_X = min_X *32*16;
            min_X = min_X -1;
        }

        if(Integer.parseInt(split_name_list[1]) >= 0){
            max_Z = Integer.parseInt(split_name_list[1])*32*16;
            min_Z = Integer.parseInt(split_name_list[1]);
            min_Z = min_Z -1;
            min_Z = min_Z *32*16;
            min_Z ++;
        }else{
            max_Z = Integer.parseInt(split_name_list[1])*32*16;
            min_Z = Integer.parseInt(split_name_list[1]);
            min_Z++;
            min_Z = min_X*32*16;
            min_Z = min_Z -1;
        }


        MCAFile mcaFile;
        mcaFile = MCAUtil.read(file);


        int chunkX;
        int chunkZ;
        String chunkXstr;
        String chunkZstr;


        System.out.println("MInX: " + Integer.toString(min_X));
        System.out.println("MaxX: " + Integer.toString(max_X));
        System.out.println("MinZ: "+Integer.toString(min_Z));
        System.out.println("MaxZ: "+Integer.toString(max_Z));

        for (int x = min_X; x < max_X+1; x++){
            for (int z = min_Z; z < max_Z+1; z++){
                for (int y = 0; y < 64; y++){
                    chunkX = x /16;
                    chunkZ = z/16;
                    chunkXstr = Integer.toString(chunkX);
                    chunkZstr = Integer.toString(chunkZ);
                    chunkXstr = chunkXstr.replaceAll("\\.\\d+$", "");
                    chunkZstr = chunkZstr.replaceAll("\\.\\d+$", "");
                    chunkX = Integer.parseInt(chunkXstr);
                    chunkZ = Integer.parseInt(chunkZstr);

                    Chunk chunk = mcaFile.getChunk(chunkX, chunkZ);

                    System.out.println("");
                    System.out.print(x);
                    System.out.print(" ");
                    System.out.print(y);
                    System.out.print(" ");
                    System.out.print(z);
                    System.out.print(" ");
                    System.out.println(" ");

                    JSONObject block = new JSONObject(chunk.getBlockStateAt(x, y, z).valueToString());
                    block = new JSONObject(block.get("Name").toString());
                    String block_string = block.get("value").toString();
                    System.out.println(block_string);
                }
            }
        }
    }
}
