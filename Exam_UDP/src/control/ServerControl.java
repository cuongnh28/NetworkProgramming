/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

/**
 *
 * @author The Godfather
 */
public class ServerControl {

    private DatagramPacket receivePacket, sendPacket;
    private DatagramSocket myServer;
    private int port = 6666;
    private Dao dao;

    public ServerControl() {
        try {
            myServer = new DatagramSocket(port);
        } catch (SocketException ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        dao = new Dao();
        while (true) {
            listening();
        }
    }

    public void listening() {
        Object o = receiveData();
        if (o instanceof String) {
            String keyword = (String) o;
            List<Student> list = dao.search(keyword);
            sendData(list);
        } else {
            Student std = (Student) o;
            boolean check = dao.updateStudent(std);
            sendData(check);
        }
    }

    public void sendData(Object o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(o);
            oos.flush();

            byte[] sendData = baos.toByteArray();
            InetAddress IPAddres = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            sendPacket = new DatagramPacket(sendData, sendData.length, IPAddres, clientPort);
            myServer.send(sendPacket);
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object receiveData() {
        byte[] receiveData = new byte[1024];
        Object o = new Object();
        try {
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            myServer.receive(receivePacket);
            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            o = ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ServerControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return o;
    }
}
