import java.awt.*;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
class editor extends JFrame implements ActionListener{
//Creating Required Objects.
    JFrame f;
    JTextArea t;
    JMenuBar menu;
    JMenu file,edit;
    JMenuItem f1,f2,f3,f4,e1,e2,e3,close;
    editor()
    {
        // initialising the name of the editor
        f = new JFrame("Bheemesh's Notepad");
      //  f.show();
        // setting overall theme of the editor
        try{
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookandFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e)
        {

        }
        //Setting  Text component
        t = new JTextArea();
        //Setting  MenuBar
         menu = new JMenuBar();
         //Setting File Menu
        file = new JMenu("File");
        // File Menu Items
        f1 = new JMenuItem("New");
        f2 = new JMenuItem("Open");
        f3 = new JMenuItem("Save");
        f4 = new JMenuItem("Print");
        //Action On Clicking File Menu Items.
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f4.addActionListener(this);
        // Adding File Menu Items To The File Menu
        file.add(f1);
        file.add(f2);
        file.add(f3);
        file.add(f4);
        // Setting Edit Menu
        edit = new JMenu("Edit");
        // Edit Menu Items
        e1 = new JMenuItem("Cut");
        e2 = new JMenuItem("Copy");
        e3 = new JMenuItem("Paste");
        // Action On Clicking Edit Menu Items
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);
        //Adding Edit Menu Items To The Edit Menu
        edit.add(e1);
        edit.add(e2);
        edit.add(e3);
        // Setting Close Item
        close = new JMenuItem("Close");
        //Action On Clicking Close Menu Item
        close.addActionListener(this);
        // Adding File And Edit Menu To Menu Bar Along With Close Item
        menu.add(file);
        menu.add(edit);
        menu.add(close);
        // Adding MenuBar,TextArea To JFrame And Setting The Size of JFrame Window
        f.setJMenuBar(menu);
        f.add(t);
        f.setSize(500,500);
        f.show();//* Testing

    }

public void actionPerformed(ActionEvent e)
{
    // These Method Says What Action Performed By User
    String s = e.getActionCommand();
    if(s.equals("New"))
    {
       t.setText("");
    }
    else if(s.equals("Open")) {
        JFileChooser j = new JFileChooser("D:");
        //Inoking The OpenDialog Box With An Integer
        int r = j.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            // Set The Label To The Path Of The Selected File Locations
            File fi = new File(j.getSelectedFile().getAbsolutePath());
            // String To Copy The Data from The Chosen File
            try {
                String s1 = "", s2 = "";
                FileReader fr = new FileReader(fi);
                BufferedReader br = new BufferedReader(fr);
                s2 = br.readLine();
                while ((s1 = br.readLine()) != null) {
                    s2 = s2 + '\n' + s1;
                }
                t.setText(s2);
            } catch (Exception et) {
                JOptionPane.showMessageDialog(f, et.getMessage());
            }
        }
    }
    else if(s.equals("Save"))
    {
        JFileChooser j = new JFileChooser("D:");
        int r = j.showSaveDialog(null);
        if(r == JFileChooser.APPROVE_OPTION)
        {
            File fi = new File(j.getSelectedFile().getAbsolutePath());
            try{
                FileWriter fw = new FileWriter(fi,false);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(t.getText());
                bw.flush();
                bw.close();
            }
            catch(Exception et)
            {
                JOptionPane.showMessageDialog(f,et.getMessage());
            }
        }

    }
    else if(s.equals("Print"))
    {

        try{
            t.print();
        }
        catch(PrinterException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    else if(s.equals("Cut"))
    {
        t.cut();
    }
    else if(s.equals("Copy"))
    {
       t.copy();
    }
    else if(s.equals("Paste"))
    {
      t.paste();
    }
    else if(s.equals("Close"))
    {
       f.setVisible(false);
    }

}
    public static void main(String[] args) {
       editor e = new editor();

    }

}
