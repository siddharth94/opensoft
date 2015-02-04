package search;

import static com.sun.xml.internal.bind.v2.util.EditDistance.editDistance;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import org.json.simple.parser.JSONParser;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Populator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        //dummy file populator
        JSONObject tmp1 = new JSONObject();
        JSONObject tmp2 = new JSONObject();
        JSONObject tmp3 = new JSONObject();
        JSONObject tmp4 = new JSONObject();
        JSONObject tmp5 = new JSONObject();
        JSONObject tmp6 = new JSONObject();
        JSONObject tmp7 = new JSONObject();
        JSONObject tmp8 = new JSONObject();
        JSONArray data = new JSONArray();
        
//        tmp1.put("file_name", "chit");
//        tmp1.put("id", "2");
//        tmp1.put("location", "/home/sidgupta/Downloads/Chit.mp4");
//        tmp1.put("size", 12320968);
//        tmp1.put("modified", 1);
//        data.add(tmp1);

        tmp2.put("file_name", "chitti kalai");
        tmp2.put("id", "1");
        tmp2.put("location", "/home/sidgupta/Downloads/Chit.mp4");
        tmp2.put("size", 12320968);
        tmp2.put("modified", 1);
        data.add(tmp2);
        
        tmp3.put("file_name", "kalai");
        tmp3.put("id", "3");
        tmp3.put("location", "/home/sidgupta/Downloads/Chit.mp4");
        tmp3.put("size", 12320968);
        tmp3.put("modified", 0);
        data.add(tmp3);

        tmp4.put("file_name", "chittorgarh");
        tmp4.put("id", "4");
        tmp4.put("location", "/home/sidgupta/Downloads/Chit.mp4");
        tmp4.put("size", 12320968);
        tmp4.put("modified", 0);
        data.add(tmp4);

//        tmp5.put("file_name", "chitsssss");
//        tmp5.put("id", "5");
//        tmp5.put("location", "/home/sidgupta/Downloads/Chit.mp4");
//        tmp5.put("size", 12320968);
//        tmp5.put("modified", 0);
//        data.add(tmp5);

        tmp6.put("file_name", "chitti");
        tmp6.put("id", "6");
        tmp6.put("location", "/home/sidgupta/Downloads/Chit.mp4");
        tmp6.put("size", 12320968);
        tmp6.put("modified", 1);
        data.add(tmp6);

        tmp8.put("file_name", "chitti");
        tmp8.put("id", "6");
        tmp8.put("location", "/home/sidgupta/Downloads/Chit.mp4");
        tmp8.put("size", 12320968);
        tmp8.put("modified", 0);
        data.add(tmp8);

        tmp7.put("file_name", "honululu");
        tmp7.put("id", "7");
        tmp7.put("location", "/home/sidgupta/Downloads/Chit.mp4");
        tmp7.put("size", 12320968);
        tmp7.put("modified", 0);
        data.add(tmp7);

//        String jo = "toGet.js";
        String jo = "local.js";
//        String jo = "server.js";
//        String jo = "hits.js";
//        HashMap <Integer, ArrayList<Object> > tmp = new HashMap();
//        
        try (
            OutputStream file = new FileOutputStream(jo);
            OutputStream buffer = new BufferedOutputStream(file);
            ObjectOutput output = new ObjectOutputStream(buffer);
          ){
//            System.out.println(tmp.toJSONString());
            output.writeObject(data);
          }  
          catch(IOException ex){
            throw ex;
          }
            
            //Create empty files
            
  /*          JSONArray temp = new JSONArray();
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
*/
        
    }
    
}
