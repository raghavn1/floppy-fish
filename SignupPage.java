import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.Color;
import java.awt.Font;

public class SignupPage extends JPanel{
    
    public SignupPage(){
        
        this.setPreferredSize(new Dimension(400,400 ));
        
      
        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        //making labels and names
        JLabel email = new JLabel("Email: ");
        JLabel password = new JLabel("New Password: ");
        JLabel confirmation = new JLabel("Confirm Password: ");
        JLabel error = new JLabel("");

        JTextField insertEmail = new JTextField(15);
        JTextField insertPassword = new JTextField(15);
        JTextField confirmPassword = new JTextField(15);

        JButton submit = new JButton("Submit");
        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("SansSerif", Font.BOLD, 40));

        JLabel info = new JLabel();
        info.setFont(new Font("SansSerif", Font.ITALIC, 20));


        //setting up constraints
        layout.putConstraint(SpringLayout.WEST, title, 325, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, title, 30, SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.NORTH, email, 250,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, email, 200,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, insertEmail, 250, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, insertEmail, 370,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, password, 300,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, password, 200,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, insertPassword, 300, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, insertPassword, 370,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, confirmation, 350,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, confirmation, 200,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, confirmPassword, 350, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, confirmPassword, 370,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH,error, 400, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, error, 300,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH,submit , 450, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, submit, 330,SpringLayout.WEST, this);

        layout.putConstraint(SpringLayout.NORTH, info, 500, SpringLayout.NORTH,this);
        layout.putConstraint(SpringLayout.WEST, info, 250,SpringLayout.WEST, this);


        //button functionality
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String userEmail = insertEmail.getText();
                String userPassword = insertPassword.getText();
                String confirm = confirmPassword.getText();
            
                if(confirm.trim().equals(userPassword.trim()) == false){
                    error.setText("both passwords must match");
                } else{
                    String information = userEmail.trim() + "," + userPassword.trim() + ",0";
                    String content = "Content.txt";
                    info.setText("Account Created! Please Login.");
                    try{
                        FileWriter writer  = new FileWriter(content,true);
                        writer.write(information+"\n");
                        writer.close();
                        System.out.println("Data has been added to file: "+ information);
                    }catch(IOException f){
                        System.out.println("Error");
                    }


                }
                
            }
             
        });

        this.add(title);
        this.add(email);
        this.add(insertEmail);
        this.add(password);
        this.add(insertPassword);
        this.add(confirmation);
        this.add(confirmPassword);
        this.add(submit);
        this.add(error);
        this.add(info);
         

        this.setBackground(new Color(164, 125, 171));


    }
    
}