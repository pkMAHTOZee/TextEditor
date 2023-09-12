import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit,theme;
    JMenuItem newFile,openFile,saveFile; //file menu items
    JMenuItem cut,copy,paste,selectAll,close; //edit menu items
    JMenuItem darkTheme, moonLight;
    JTextArea textArea;
    TextEditor() {
        frame = new JFrame();
        //logo image
        Image img = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mahto\\OneDrive\\Pictures\\logo.jpg");
        frame.setIconImage(img);

        menuBar = new JMenuBar();

        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");
        theme = new JMenu("theme");

        // initializing file menu items
        newFile = new JMenuItem("New window");
        openFile = new JMenuItem("Open file");
        saveFile = new JMenuItem("Save file");

        // adding action listener to file items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //adding file items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initializing edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select all");
        close = new JMenuItem("Close");
        // adding action listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //adding edit menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //initializing theme menu
        darkTheme = new JMenuItem("Dark Theme");
        moonLight = new JMenuItem("Moonlight");

        //adding action listener to theme menu
        darkTheme.addActionListener(this);
        moonLight.addActionListener(this);
        //adding theme menu items to theme menu
        theme.add(darkTheme);
        theme.add(moonLight);


        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(theme);

        frame.setJMenuBar(menuBar);
        //frame.add(textArea);
        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add text area pane
        panel.add(textArea,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to farme
        frame.add(panel);
        frame.setTitle("Text Editor (by Praveen)");
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        //Edit menu functionalities
        if(actionEvent.getSource()==cut)
        {
            textArea.cut();
        }
        if(actionEvent.getSource()==copy)
        {
            textArea.copy();
        }
        if(actionEvent.getSource()==paste)
        {
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll)
        {
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close)
        {
            System.exit(0);
        }

        //theme menu functionalities
        if(actionEvent.getSource()==darkTheme)
        {
            textArea.setBackground(Color.darkGray);
            textArea.setForeground(Color.white);
        }
        if(actionEvent.getSource()==moonLight)
        {
            textArea.setBackground(new Color(107,169,255));
            textArea.setForeground(Color.black);
        }
        //File menu functionalities
        if(actionEvent.getSource()==openFile)
        {
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "",output="";
                    //read content of file line by line
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch(IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            //get choose option
            int chooseOption = fileChooser.showSaveDialog(null);
            //check if clicked on save button
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //create a new file with chosen directory file
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTexEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}

