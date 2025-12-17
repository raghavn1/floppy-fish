import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ControllerClass {
   public ControllerClass(SubPanel sub, JButton signup, JButton login){
    signup.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
            sub.signupshow();
        }
    });

    login.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
            sub.loginshow();
        }
    });
   } 
}