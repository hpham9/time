package time;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.*;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.unihd.dbs.heideltime.standalone.*;
import de.unihd.dbs.uima.annotator.heideltime.resources.*;
import de.unihd.dbs.heideltime.standalone.components.JCasFactory;
import de.unihd.dbs.heideltime.standalone.components.ResultFormatter;
import de.unihd.dbs.heideltime.standalone.components.PartOfSpeechTagger;
import de.unihd.dbs.heideltime.standalone.components.impl.AllLanguagesTokenizerWrapper;
import de.unihd.dbs.heideltime.standalone.components.impl.HunPosTaggerWrapper;
import de.unihd.dbs.heideltime.standalone.components.impl.IntervalTaggerWrapper;
import de.unihd.dbs.heideltime.standalone.components.impl.JCasFactoryImpl;
import de.unihd.dbs.heideltime.standalone.components.impl.JVnTextProWrapper;
import de.unihd.dbs.heideltime.standalone.components.impl.StanfordPOSTaggerWrapper;
import de.unihd.dbs.heideltime.standalone.components.impl.TimeMLResultFormatter;
import de.unihd.dbs.heideltime.standalone.components.impl.TreeTaggerWrapper;
import de.unihd.dbs.heideltime.standalone.components.impl.UimaContextImpl;
import de.unihd.dbs.heideltime.standalone.components.impl.XMIResultFormatter;
import de.unihd.dbs.heideltime.standalone.exceptions.DocumentCreationTimeMissingException;
import de.unihd.dbs.uima.annotator.heideltime.HeidelTime;
import de.unihd.dbs.uima.annotator.heideltime.resources.Language;
import de.unihd.dbs.uima.annotator.heideltime.resources.ResourceScanner;
import de.unihd.dbs.uima.annotator.intervaltagger.IntervalTagger;
import de.unihd.dbs.uima.types.heideltime.Dct;

import de.unihd.dbs.heideltime.standalone.DocumentType;
import de.unihd.dbs.heideltime.standalone.OutputType;
import de.unihd.dbs.heideltime.standalone.POSTagger;
import de.unihd.dbs.uima.types.heideltime.Timex3;
import de.unihd.dbs.uima.annotator.heideltime.resources.Language;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;
//import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.util.Calendar;
import java.util.Date;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class recognition {

    private JFrame Recognition;
    private JTextField DataPath;
    private JTextField ConfigPath;
    private JButton DataButton;
    private JButton ConfigButton;
    private File file;
    private String fullPath;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	recognition window = new recognition();
                    window.Recognition.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public recognition() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        Recognition = new JFrame();
        Recognition.setBounds(100, 100, 801, 600);
        Recognition.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout springLayout = new SpringLayout();
        Recognition.getContentPane().setLayout(springLayout);
        
        JButton Execute = new JButton("Execute");
        Execute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, Execute, -85, SpringLayout.SOUTH, Recognition.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, Execute, -530, SpringLayout.EAST, Recognition.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, Execute, -38, SpringLayout.SOUTH, Recognition.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, Execute, -309, SpringLayout.EAST, Recognition.getContentPane());
        Recognition.getContentPane().add(Execute);
        
        DataPath = new JTextField();
        DataPath.setText("Insert data file location here");
        springLayout.putConstraint(SpringLayout.WEST, DataPath, -321, SpringLayout.EAST, Execute);
        Recognition.getContentPane().add(DataPath);
        DataPath.setColumns(10);
        
        ConfigPath = new JTextField();
        ConfigPath.setText("Insert config.props location here");
        ConfigPath.setToolTipText("");
        springLayout.putConstraint(SpringLayout.WEST, ConfigPath, 153, SpringLayout.WEST, Recognition.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, DataPath, 0, SpringLayout.EAST, ConfigPath);
        Recognition.getContentPane().add(ConfigPath);
        ConfigPath.setColumns(10);
        
        DataButton = new JButton("Find");
        DataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, DataPath, -1, SpringLayout.NORTH, DataButton);
        springLayout.putConstraint(SpringLayout.EAST, DataButton, -99, SpringLayout.EAST, Recognition.getContentPane());
        Recognition.getContentPane().add(DataButton);
        
        ConfigButton = new JButton("Find");
        ConfigButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        springLayout.putConstraint(SpringLayout.NORTH, ConfigButton, 249, SpringLayout.NORTH, Recognition.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, DataButton, -53, SpringLayout.NORTH, ConfigButton);
        springLayout.putConstraint(SpringLayout.EAST, ConfigPath, -47, SpringLayout.WEST, ConfigButton);
        springLayout.putConstraint(SpringLayout.NORTH, ConfigPath, 1, SpringLayout.NORTH, ConfigButton);
        springLayout.putConstraint(SpringLayout.EAST, ConfigButton, 0, SpringLayout.EAST, DataButton);
        Recognition.getContentPane().add(ConfigButton);
        
        
        final JFileChooser fileChooser = new JFileChooser();
        
        DataButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int result = fileChooser.showOpenDialog(Recognition);
                if (result==JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    File filepath = fileChooser.getSelectedFile();
                    String fullPath2 = filepath.getAbsolutePath();
                    DataPath.setText(fullPath2);
                }
            }
            
        });
        
        ConfigButton.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int result = fileChooser.showOpenDialog(Recognition);
                if (result==JFileChooser.APPROVE_OPTION) {
                    File file2 = fileChooser.getSelectedFile();
                    File filepath = fileChooser.getSelectedFile();
                    fullPath = filepath.getAbsolutePath();
                    ConfigPath.setText(fullPath);
                }
            }
            
        });
        
        
        
        Execute.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
                    
                    
                   String config = fullPath;
                    
                	String sline;
                	Date date = new Date();
                	
                	HeidelTimeStandalone heidelTime = new HeidelTimeStandalone(Language.ENGLISH,
                            DocumentType.COLLOQUIAL,
                            OutputType.TIMEML,
                            config,
                            POSTagger.TREETAGGER, true);
                	
                    try {
                    	BufferedReader sc = new BufferedReader(new FileReader(file));
                    	StringBuilder sb = new StringBuilder();
                        String line = sc.readLine();

                        while (line != null) {
                            sb.append(line);
                            sb.append("\n");
                            line = sc.readLine(); 
                        }
                            sline = sb.toString();
                            sc.close();
                            
                            String in = heidelTime.process(sline, date);

                        PrintWriter pr = new PrintWriter(new File("result.xml"));
                        
                        pr.println(in);
                        
                    	pr.close();
                    	

                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                
                
                
            }
        });
        
        
    }
}


