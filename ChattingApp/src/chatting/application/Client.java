package chatting.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client extends JFrame  implements ActionListener {
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea ta;

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;



    Client(){
        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3.png"));
        Image i2 = i1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 =new JLabel(i3);
        l1.setBounds(8,17,30,30);
        p1.add(l1);
        l1.addMouseListener(new MouseAdapter() {  //   Back-Button = exit ( IMPLEMENTATION )
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/2.png"));
        Image i5 = i4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 =new JLabel(i6);
        l2.setBounds(50,5,60,60);
        p1.add(l2);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 =new JLabel(i9);
        l5.setBounds(300,20,30,30);
        p1.add(l5);

        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel l6 =new JLabel(i12);
        l6.setBounds(360,20,35,35);
        p1.add(l6);

        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel l7 =new JLabel(i15);
        l7.setBounds(415,23,13,25);
        p1.add(l7);


//-----------------------------     Active Status           ------------------------------------------
        JLabel l3 = new JLabel("Bunty");
        l3.setBounds(115 , 20, 100 ,20);
        l3.setFont(new Font("SAN-SERIF", Font.BOLD,18));
        l3.setForeground(Color.white);
        p1.add(l3);

        JLabel l4 = new JLabel("Active Now");
        l4.setBounds(115 , 40, 100 ,20);
        l4.setFont(new Font("SAN-SERIF", Font.PLAIN,12));
        l4.setForeground(Color.white);
        p1.add(l4);

        //TEXT AREA-------------------

        ta = new JTextArea();
        ta.setBounds(5,75,440,570);
        ta.setFont(new Font("SAN-SERIF", Font.PLAIN,16));
        ta.setEditable(false);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        add(ta);


        //Text-Field to Enter Message
        t1 = new JTextField();
        t1.setBounds(5,650,310,40);
        t1.setFont(new Font("SAN-SERIF", Font.PLAIN,16));
        add(t1);
        //Button for sending Messages
        b1 = new JButton("Send");
        b1.setBounds(320,650,120,40);
        b1.setBackground(new Color(7,94,84));
        b1.setForeground(Color.white);
        b1.setFont(new Font("SAN-SERIF", Font.PLAIN,14));
        b1.addActionListener(this);
        add(b1);


        getContentPane().setBackground(Color.white);
        setLayout(null);
        setSize(450,700);
        setLocation(1100,200);
        setUndecorated(true);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {
        try{
            String out = t1.getText();
            ta.setText(ta.getText()+"\n" + out);
            dout.writeUTF(out);
            t1.setText(" ");
        }catch (Exception a){

        }

    }

    public static void main(String[] args) {
        new Client().setVisible(true);
        String msginput = "";
        try{
            s = new Socket("127.0.0.1", 6001);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());


            msginput = din.readUTF();
            ta.setText(ta.getText()+ "\n"+msginput);



        }catch(Exception e){

        }

    }
}



