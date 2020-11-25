/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// Bug khai bao ois trước oos lỗi
package client.controllers;
import Models.com.Pair;
import Models.com.Request;
import Models.com.User;
import java.net.Socket;
import java.sql.*;
import client.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
/**
 *
 * @author cuongnv
 */
public class Controller implements Runnable {
    private Socket socketClient;
    private String serverHost="localhost";
    private Connection con;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int serverPort=8888;
    private LoginView loginView;
    private Game game;
    private Map<String, Pair<User,Integer>> listPlayer = new HashMap<>();
    private User myAccount;
    
    public Controller(){
        loginView = new LoginView();
        loginView.setVisible(true);
        loginView.addListentBtnLogin(new listentBtnLogin());
        openConnection(serverHost, serverPort);
        Thread threadUpdate = new Thread(new updatePlayerOnline());
        threadUpdate.setDaemon(true);
        threadUpdate.start();
    }
    public synchronized Map<String, Pair<User, Integer>> getlistPlayer(){
        return this.listPlayer;
    }
    public void run(){
        while(true){
            try {
                
                Request respond =(Request)ois.readObject();
                if(respond.getObject() == null) continue;
                switch(respond.getRequestName()){
                    case "login":
                       
                        handleLogin(respond);
                        break;
                    case "sendListPlayer":
                        handleListPlayerOnline(respond);
                        
                        break; 
                    case "challange":
                        acceptInvite(respond);
                        break;
                }
            } catch (IOException ex) {
                //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void acceptInvite(Request res){
        String ip = (String) res.getObject();
        System.out.println("User co ip "+ip+" muon thach dau");
        //showMessage(listPlayer.get(ip).getKey().getUserName()+" muốn thách đấu bạn,bạn có muốn chiến không" , "accept",);
        
    }
    public void getUserOnline(){
        
        Request req = new Request("getListPlayer");
        send(req);
    }
    public void handleLogin(Request res){
        try{
        if(res.getObject() instanceof User){
            myAccount =(User) res.getObject();
            loginView.dispose();
            game = new Game();
            game.setActionListener(new ListentBtnPlayer());
            game.showMyAccount(myAccount);
            getUserOnline();
        }
        else loginView.showMessage("Đăng nhập thất bại");
        }
        catch(Exception ex){
            
        }
    }
    public class listentBtnLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) { 
            String s = null;
            
            User user = loginView.getUser();      
            send(new Request("login",(Object) user));
        }
    }
    public class updatePlayerOnline implements Runnable{
        public updatePlayerOnline(){
            //openConnection(serverHost, serverPort);
        }
        public void run(){
            
            while(true){
                try {
                    sleep(10000);
                    getUserOnline();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //xu ly su kien khi click vao nguoi choi muon thach dau
    public class ListentBtnPlayer implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            JButton btn = (JButton) ae.getSource();
            
            String[] sp = btn.getText().split(" ");
            System.out.println(myAccount.getUserName());
            String[] str = sp[1].split(":");
            String ip = str[1];
            System.out.println(ip);
            if(myAccount.getUserName().equals(sp[0])){
                showMessage("Bạn không thể thách đấu với chính mình", "close",null);
            }
            else showMessage("Bạn có muốn gửi lời thách đấu tới "+ sp[0],"challange",ip);
            System.out.println(btn.getText());
        }
    }
    public void showMessage(String content, String action, String ip){
        MessageDialog m = new MessageDialog(content);
        m.addListenerBtnCancel(new ListenActionCancel(m));
        m.addListentBtnOk(new ListenActionOk(m,action,ip));
        m.setVisible(true);
    }
    public class ListenActionOk implements ActionListener{
        private MessageDialog m ;
        private String action;
        private String ip;
        private User user;
        public ListenActionOk(MessageDialog m, String action, String ip){
            this.m = m;
            this.action = action;
            this.ip = ip;
        }
        public ListenActionOk(MessageDialog m, String action, User user){
            this.m = m;
            this.user = user;
            this.action = action;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            switch(this.action){
                case "close":
                    this.m.dispose();
                    break;
                case "challange":
                    sendMatch(this.ip);
                    this.m.dispose();
                    break;
                case "accept":
                    System.out.println("chap nhan dau voi "+this.user.getUserName());
                    break;
                    
            }
        }
    }
    public void sendMatch(String ip){
        Request res = new Request("match", (Object)ip);
        send(res);
    }
    public class ListenActionCancel implements ActionListener{
        private MessageDialog m ;
        public ListenActionCancel(MessageDialog m){
            this.m = m;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            m.dispose();
        }   
    }
    public void handleListPlayerOnline(Request res){
        try{
            if(res.getObject() == null) return;
            Map<String, Pair<User, Integer>> listPlayer=getlistPlayer();
            listPlayer =(Map<String, Pair<User,Integer>>) res.getObject();
            for(Map.Entry<String, Pair<User, Integer>> p : listPlayer.entrySet()){
                System.out.println(p.getKey()+" "+p.getValue().getKey().getUserName()+" "+p.getValue().getValue()+" "+p.getValue().getKey().getScore());
            }
            game.showListPlayer(listPlayer); 
            
        }
        catch(Exception ex){
            return;
        }
    }
    public void showListPlayer(){ 
        game.showListPlayer(listPlayer);
    }
    public String receive(){
        String res=null;
        try {
            
            res = (String) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    public void closeConnection(){
        try {
            System.out.println("close!");
            socketClient.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void openConnection(String serverHost, int port){
        try {
            //System.out.println("helllllo");
            socketClient = new Socket(serverHost, port);
            this.oos = new ObjectOutputStream(socketClient.getOutputStream());
            this.ois = new ObjectInputStream(socketClient.getInputStream());
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void send(Request request){
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
