import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class LoginPage extends JPanel {


    private MainPanel mainPanel;

    private HashMap<String, String> emailaddress = new HashMap<>();

    private String currentUser;
    private int userHighScore;

       
    public LoginPage(MainPanel mainPanel) throws FileNotFoundException{
        this.mainPanel = mainPanel;
   
        this.setPreferredSize(new Dimension(300,300));
        //defining panel
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);
       //labels and text fields
        JLabel email  = new JLabel("Email: ");
        JLabel password = new JLabel("Password: ");
        JTextField enterEmail = new JTextField(15);
        JTextField enterPassword = new JTextField(15);

        JButton submit = new JButton("Submit");

        JLabel title = new JLabel("Login");
        title.setFont(new Font("SansSerif", Font.BOLD, 40));

        JLabel info = new JLabel();
        info.setFont(new Font("SansSerif", Font.ITALIC, 20));

            

        //Adding contraints
        springLayout.putConstraint(SpringLayout.WEST, title, 335, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, title, 30, SpringLayout.NORTH, this);
        
        springLayout.putConstraint(SpringLayout.WEST, email,200 , SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, email,250 , SpringLayout.NORTH,this);
        springLayout.putConstraint(SpringLayout.WEST, enterEmail,300, SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, enterEmail,250 , SpringLayout.NORTH,this);

        springLayout.putConstraint(SpringLayout.WEST, password,200 , SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, password,300 , SpringLayout.NORTH,this);
        springLayout.putConstraint(SpringLayout.WEST, enterPassword,300, SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, enterPassword,300 , SpringLayout.NORTH,this);

        springLayout.putConstraint(SpringLayout.WEST, submit,350, SpringLayout.WEST,this);
        springLayout.putConstraint(SpringLayout.NORTH, submit,350 , SpringLayout.NORTH,this);

        springLayout.putConstraint(SpringLayout.WEST, info, 310, SpringLayout.WEST, this);
        springLayout.putConstraint(SpringLayout.NORTH, info, 400,SpringLayout.NORTH, this);





            

        submit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){

                String emailfield = enterEmail.getText().trim();
                String password = enterPassword.getText().trim();
        
                try{
                    String content = "Content.txt";
                    File file = new File(content);
                    Scanner input = new Scanner(file); 
            
                    while(input.hasNextLine()){
                        String info = input.nextLine();
                        //System.out.println(info);
                        String[] list = info.split(",");
                        String email = list[0].trim();
                        String pass = list[1].trim();
                        int highScore = Integer.parseInt(list[2].trim());

                        emailaddress.put(email, pass);

                        if (email.equals(emailfield)) {
                            userHighScore = highScore;
                        }

                        list = null;
                    }
                    
                    input.close();
                    
                } catch(FileNotFoundException a){
                        System.out.println("FILE NOT FOUND");
                }
        
        
                if(emailaddress.containsKey(emailfield)) {
                    System.out.println("USING USER email");
                    String p = (String) emailaddress.get(emailfield);
                    p = p.trim();
                    if(password.equals(p)){
      
                        currentUser = emailfield;
                        mainPanel.loginSuccessful(currentUser, userHighScore);

                    }else{
                        System.out.println("INCORRECT PASSWORD");
                        info.setText("Incorrect password");
                    }
            
                } else {
                    System.out.println("EMAIL NOT FOUND");
                    info.setText("Email not found");
                }
        
            }
        });


            //adding to make them appear
            this.add(title);
            this.add(submit);
            this.add(email);
            this.add(enterEmail);
            this.add(password);
            this.add(enterPassword);
            this.add(info);
        
            this.setBackground(new Color(164, 125, 171));
            
    }
}
