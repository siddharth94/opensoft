/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import static com.sun.xml.internal.bind.v2.util.EditDistance.editDistance;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
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
import javax.swing.JSlider;
import javax.swing.JTextField;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class hits{
    public int id;
    public int hits;
    public String location;
}

/**
 *
 * @author sidgupta
 */
public class Home extends javax.swing.JFrame {
    
    /**
     * Creates new form Home
     */
    public Home() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jButton1 = new javax.swing.JButton();
        search_query = new javax.swing.JFormattedTextField();
        search_scroll = new javax.swing.JScrollPane();
        search_page = new javax.swing.JPanel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        search_query.setText("Try searching something");

        javax.swing.GroupLayout search_pageLayout = new javax.swing.GroupLayout(search_page);
        search_page.setLayout(search_pageLayout);
        search_pageLayout.setHorizontalGroup(
            search_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        search_pageLayout.setVerticalGroup(
            search_pageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        search_scroll.setViewportView(search_page);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(search_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(search_query, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(search_query, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        final String query = search_query.getText();
        search_page.removeAll();

        try 
        {
            //search server.js for query
            ArrayList<Pair> foundList = search_local(query);

            for (int i=0; i < foundList.size() ;i++)
            {
                final JSONObject n = (JSONObject) foundList.get(i).first;
                JButton b = new JButton((String) n.get("file_name"));
                JButton del = new JButton("X");
                del.setForeground(Color.red);
                del.addMouseListener(new java.awt.event.MouseAdapter() {
                    
                });
                
                if (n.get("from").equals("server"))
                    b.setBackground(Color.red);
                else b.setBackground(Color.green);
                
                b.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        try
                        {
                            //increase hits in HashMap
                            String hFileName = "hits.js";
                            HashMap<Integer, ArrayList<Object> > hits = new HashMap();
                            File hitFile = new File(hFileName);
                            if (hitFile.exists())
                            {
                                try(
                                    InputStream file = new FileInputStream(hFileName);
                                    InputStream buffer = new BufferedInputStream(file);
                                    ObjectInput input = new ObjectInputStream (buffer);
                                  ){
                                    //deserialize the json array
                                    hits = (HashMap)input.readObject();
                                   }
                            }
                            
                            //Add to toGetList
                            if (hits.containsKey(Integer.parseInt((String) n.get("id"))))
                            {
                                int ie = Integer.parseInt((String) n.get("id"));
                                ArrayList <Object> tmp = hits.get(ie);
                                int newval = (int) tmp.get(0) + editDistance(query, (String) n.get("file_name"));
                                tmp.remove(0);
                                tmp.add(0, newval);
                                hits.put(ie, tmp);
                            }
                            else
                            {
                                int ie = Integer.parseInt((String) n.get("id"));
                                ArrayList <Object> tmp = new ArrayList<>();
                                tmp.add(editDistance(query, (String) n.get("file_name")));
                                tmp.add((String) n.get("location"));
                                hits.put(ie, tmp);
                            }
                            
                            //write back to serialized object
                            try (
                                OutputStream file = new FileOutputStream(hFileName);
                                OutputStream buffer = new BufferedOutputStream(file);
                                ObjectOutput output = new ObjectOutputStream(buffer);
                              ){
                                output.writeObject(hits);
                              }  
                              catch(IOException ex){
                                throw ex;
                              }
            
                            boolean executed = false;
                            
                            //check if present in local list
                            if (n.get("from").equals("local"))
                            {
                                System.out.println("in if");
                                System.out.println((int)n.get("modified"));
                                if ((int)n.get("modified") == 1)
                                {
                                    String msg = "Modified version available on server. Download new file?";
                                    ask_to_download(n,msg);
                                }
                                String command = "gnome-open "+n.get("location");
                                Process p;
                                p = Runtime.getRuntime().exec(command);
                            }
                            else
                            {
                                String message = "File not available locally! Add to download list?";
                                ask_to_download(n,message);
                            }


                        }catch(Exception e)
                        {
                            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }

                    private void ask_to_download(JSONObject n, String msg) throws IOException, ClassNotFoundException {
                        System.out.println("in function");
                        JFrame msgf = new JFrame();
                        JPanel pan = new JPanel();
                        GridLayout pra = new GridLayout(3,1);
                        pra.setHgap(50);
                        pra.setVgap(10);
                        pan.setLayout(pra);
                        msgf.setContentPane(new JPanel());
                        msgf.setSize(400, 300);
                        msgf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        JPanel message = new JPanel();
                        JSlider slider = new JSlider(0,10,5);
                        slider.setPaintTicks(true);
                        slider.setMajorTickSpacing(1);
                        pan.add(new JLabel(msg));
                        pan.add(slider);
                        pan.add(new JLabel("How urgent is it to get this file?"));
                        pan.repaint();
                        pan.revalidate();
                        pan.validate();
                        message.add(pan);

                        int response = JOptionPane.showConfirmDialog(msgf, message, "Default made dialog", JOptionPane.YES_NO_OPTION);
                        if (response == 0)
                        {
                            //add to toget list
                            String toGetfile = "toGet.js";
                            File get = new File(toGetfile);
                            JSONArray toGet = null;
                            if (get.exists())
                            {
                                try(
                                    InputStream file = new FileInputStream(get);
                                    InputStream buffer = new BufferedInputStream(file);
                                    ObjectInput input = new ObjectInputStream (buffer);
                                  ){
                                    //deserialize the json array
                                    toGet = (JSONArray)input.readObject();
                                   }
                            }

                            //Add priority
                            JSONObject notInLocal = new JSONObject();
                            notInLocal.put("file_name", (String) n.get("file_name"));
                            notInLocal.put("id", (String) n.get("id"));
                            int priority = slider.getValue();
                            notInLocal.put("priority",priority);
                            notInLocal.put("size", n.get("size"));
                            notInLocal.put("category", n.get("cateogry"));
                            notInLocal.put("extension", n.get("extension"));
                            System.out.println(notInLocal.toString());
                            toGet.add(notInLocal);
                            try (
                                OutputStream file = new FileOutputStream(toGetfile);
                                OutputStream buffer = new BufferedOutputStream(file);
                                ObjectOutput output = new ObjectOutputStream(buffer);
                              ){
                                output.writeObject(toGet);
                              }  
                              catch(IOException ex){
                                throw ex;
                              }
                        }
                    }
                });
                
                GridLayout pra = new GridLayout(0,2);
                pra.setHgap(50);
                pra.setVgap(10);
                search_page.setSize(10, 500);
                search_page.setLayout(pra);
                search_page.add(b);
                search_page.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                search_page.revalidate();
                search_page.validate();
                search_page.repaint();
            }
        } catch (ClassNotFoundException | IOException | ParseException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        File tmp = new File("check");
        if (!tmp.exists()){
            FileCreator t = new FileCreator();
            System.out.println("file rewritten");
            tmp.createNewFile();
        }
       
           
        
        try {
            //Call Thread to start parallel thread.
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JPanel search_page;
    private javax.swing.JFormattedTextField search_query;
    private javax.swing.JScrollPane search_scroll;
    // End of variables declaration//GEN-END:variables

    private ArrayList<Pair> search_local(String query) throws IOException, ParseException, ClassNotFoundException, Exception {
        
        //String constants
        String serverFileName = "server.js";
        String localFileName = "local.js";
        
        //get localjson array
        JSONArray localList = getJsonArray(localFileName);
        
        //check if json array present
        JSONArray serverList = getJsonArray(serverFileName);
                
        //search for query in file names using edit distance
        ArrayList<Pair> retList = new ArrayList<Pair>();
        ArrayList<Integer> localIdList = new ArrayList<>();
        
        for (int i=0; i<localList.size(); i++)
        {
            JSONObject tmpObj = (JSONObject) localList.get(i);
//            System.out.println(tmpObj.toString());
            int perMatch = editDistance(query, (String) tmpObj.get("file_name"));
            perMatch = query.length() - perMatch;
            if (perMatch >= (query.length()/2))
            {
                localIdList.add(Integer.parseInt((String) tmpObj.get("id")));
                tmpObj.put("from", "local");
                Pair tmp = new Pair(tmpObj, perMatch);
                retList.add(tmp);
                System.out.println(tmpObj.toString());
            }
        }
        
        Collections.sort(localIdList);
        
        for (int i=0; i<serverList.size(); i++)
        {
            JSONObject n = (JSONObject) serverList.get(i);
            int perMatch = editDistance(query, (String) n.get("file_name"));
            perMatch = query.length() - perMatch;
            if (perMatch >= (query.length()/2))
            {
                if (Collections.binarySearch(localIdList, Integer.parseInt((String) n.get("id"))) < 0)
                {
                    n.put("from", "server");
                    Pair tmp = new Pair(n, perMatch);
                    retList.add(tmp);
                }
            }
        }

        Comparator comparePair = new Comparator <Pair>() {

            @Override
            public int compare(Pair o1, Pair o2) {
                return ((int)(o1.second) - (int)(o2.second));
            }
        };
        
        Collections.sort(retList, comparePair);
        
        return retList;
    }

    private FileReader getfile(String filename) {
        try
        {
            FileReader file = new FileReader(filename);
            return file;
        }catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private JSONArray getJsonArray(String fileName) throws Exception {
        //returns jsonArray stored in fileName
        File f = new File(fileName);
        JSONArray toReturn = null;
        if (f.exists())
        {
            try(
                InputStream file = new FileInputStream(fileName);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
              ){
                //deserialize the json array
                toReturn = (JSONArray)input.readObject();
               }
            catch(Exception e)
            {
                throw e;
            }
        }
        return toReturn;
    }

    public class Pair<F, S> {
        public F first;
        public S second;
        
        Pair (){
            this.first = null;
            this.second = null;
        }

        public Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }
        
        public <A, B> Pair <A, B> create(A a, B b) {
            return new Pair<A, B>(a, b);
    }
        
    }
}