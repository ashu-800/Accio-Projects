import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener{
      JFrame frame;
      JTextArea textArea;
      JMenuBar menuBar;
      JMenu file,edit;
      JMenuItem newFile, openFile, saveFile;
      JMenuItem cut,copy,paste,selectAll,close;
       TextEditor(){

        frame= new JFrame();
        textArea = new JTextArea();

        menuBar= new JMenuBar();

        file= new JMenu("File");
        edit= new JMenu("Edit");

        newFile= new JMenuItem("New File");
        openFile= new JMenuItem("Open File");
        saveFile= new JMenuItem("Save File");

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        cut= new JMenuItem("Cut");
        copy= new JMenuItem("Copy");
        paste= new JMenuItem("Paste");
        selectAll= new JMenuItem("Select All");
        close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);


        menuBar.add(file);
        menuBar.add(edit);


        frame.setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        frame.add(panel);


        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

       }
    @Override
    public void actionPerformed(ActionEvent event) {
           if(event.getSource()==cut){
                textArea.cut();
            }
            if(event.getSource()==copy){
                textArea.copy();
            }
            if(event.getSource()==paste){
                textArea.paste();
            }
            if(event.getSource()==selectAll){
                textArea.selectAll();
            }
            if(event.getSource()==close){
                System.exit(0);
            }

            if(event.getSource()==openFile){
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseOption=fileChooser.showOpenDialog(null);
                if(chooseOption==JFileChooser.APPROVE_OPTION){
                    File file= fileChooser.getSelectedFile();
                    String filePath = file.getPath();

                        try  {
                            FileReader fileReader = new FileReader(filePath);
                            BufferedReader bufferedReader = new BufferedReader(fileReader);
                            String line = ""; String output="";

                            while((line = bufferedReader.readLine())!=null){
                                output+=line+"/n";
                            }
                            textArea.setText(output);
                        }
                        catch (IOException ioException){
                            ioException.printStackTrace();
                        }
                }
            }

            if(event.getSource()==saveFile){
                JFileChooser fileChooser = new JFileChooser("C:");
                int chooseOption = fileChooser.showSaveDialog(null);
                if(chooseOption==JFileChooser.APPROVE_OPTION){
                    File newfile = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                    try{
                        FileWriter fileWriter = new FileWriter(newfile);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                    }
                    catch (IOException ioException){
                        ioException.printStackTrace();
                    }
                }
            }

            if(event.getSource()==newFile){
                TextEditor newTextEditor = new TextEditor();
            }



    }


    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

    }
}