/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataloader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; 
/**
 *
 * @author vinayak
 */


public class filterBox extends javax.swing.JFrame implements ActionListener{
JPanel myJpanel,buttonpanel,contentPane;
JScrollPane pane;
int index;
jdbcconn sqlcon;
ArrayList <Search_Panel>items;
JButton bttn,subbttn,col;
columnselect coll;
public String queryEngine(String table)
{
    String q=coll.getString();
    String qry="SELECT "+q+" FROM "+table +" WHERE CAR_ID IN (";
    
    for(int i=0;i<index;i++)
 {
 qry=qry+items.get(i).getQuery(table);
 if(i!=index-1)qry=qry+" AND CAR_ID IN(";
 }
      for(int i=0;i<index;i++)
 {
 qry=qry+")";
 }
     // System.out.println(qry);
return qry;
}
    /**
     * Creates new form filterBox
     * @param e
     */
@Override
public void actionPerformed(ActionEvent e)
{
    System.out.println(queryEngine("CAR_DATA"));
try{String eve=e.getActionCommand();
if(eve.equals("ADD"))addEle();
else if(eve.equals("SELECT COLUMNS"))coll.setVisible(true);
else if(eve.equals("SUBMIT")){
this.setVisible(false);
TablePane tem=new TablePane(queryEngine("CAR_DATA"),this,sqlcon);    

}
else {
//System.out.println(e.getSource());
deleteEle(Integer.parseInt(eve));
this.setVisible(false);
this.setVisible(true);
this.revalidate();
}

}
catch(Exception ee){
System.out.println(ee);
}
}
 public void setAllValuesc(){
 index=0;
 items =new ArrayList<Search_Panel>();
 
 
 
 
 }
 
 public void addEle(){
 Search_Panel tem=new Search_Panel(index++);
 tem.bttn.addActionListener(this);
  
 items.add(tem);
 myJpanel.removeAll();
 myJpanel.add(buttonpanel);
 for(int i=0;i<index;i++)
 {
 items.get(i).index=i;
 items.get(i).bttn.setActionCommand(Integer.toString(i));
 myJpanel.add(items.get(i));
 items.get(i).validat();
 }
 myJpanel.revalidate();
 }
 public void deleteEle(int in){
 
 items.remove(in);
 index--;
 myJpanel.removeAll();
 myJpanel.validate();
 myJpanel.add(buttonpanel);
 for(int i=0;i<index;i++)
 {
 items.get(i).index=i;
 myJpanel.add(items.get(i));
 items.get(i).bttn.setActionCommand(Integer.toString(i));
 items.get(i).validat();
 }
 myJpanel.revalidate();
 }
 
    public filterBox(jdbcconn a) {
        initComponents();
        sqlcon=a;
        coll =new columnselect();
        setAllValuesc();
        col=new JButton("SELECT COLUMNS");
        col.addActionListener(this);
        bttn=new JButton("ADD");
        bttn.addActionListener(this);
        subbttn=new JButton("SUBMIT");
        subbttn.addActionListener(this);
        buttonpanel= new JPanel();
        buttonpanel.add(bttn);
        buttonpanel.add(col);
        buttonpanel.add(subbttn);
        
        myJpanel = new JPanel();
        myJpanel.add(buttonpanel);
        
        JScrollPane scrollPane = new JScrollPane(myJpanel);
        myJpanel.setLayout(new GridLayout(50,1));
       
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 0, 700,600);
         //panel.add(new Search_Panel(0));
        //panel.add(new Search_Panel(1));
        
        contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(700,600));
        contentPane.add(scrollPane);
        
        this.setContentPane(contentPane);
        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(0, 0, 700,600);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(266, 266, 266)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
