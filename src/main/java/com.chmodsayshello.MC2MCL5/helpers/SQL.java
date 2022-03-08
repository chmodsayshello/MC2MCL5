package com.chmodsayshello.MC2MCL5.helpers;

import java.io.File;

public class SQL {
    public boolean connected = false;
    public long saved_nodes = 0;

    public SQL(File out) throws Exception {
        if(!connected){
            //SQLITE connection here
        }
        saved_nodes++;
        System.out.print("Saved Nodes: "+Long.toString(saved_nodes));
    }

}
