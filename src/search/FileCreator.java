/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;

/**
 *
 * @author sidgupta
 */
public class FileCreator {
    
    FileCreator() throws IOException
    {
        JSONArray temp = new JSONArray();
        HashMap <Integer, ArrayList <Object> > temphash = new HashMap <Integer, ArrayList <Object> >();

        try (
            OutputStream file = new FileOutputStream("hits.js");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
          ){
            output.writeObject(temphash);
          }  
          catch(IOException ex){
            throw ex;
          }
        try (
            OutputStream file = new FileOutputStream("local.js");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
          ){
            output.writeObject(temp);
          }  
          catch(IOException ex){
            throw ex;
          }
        try (
            OutputStream file = new FileOutputStream("server.js");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
          ){
            output.writeObject(temp);
          }  
          catch(IOException ex){
            throw ex;
          }

        try (
            OutputStream file = new FileOutputStream("toGet.js");
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
          ){
            output.writeObject(temp);
          }  
          catch(IOException ex){
            throw ex;
          }        
    }
    
}
