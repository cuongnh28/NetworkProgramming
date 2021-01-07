/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author HoangHung
 */
public class ClientObj {
    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        IProduct productImpl=(IProduct) Naming.lookup("rmi://localhost/Product");
        Product product=productImpl.getProduct("B17DCCN284");
        System.out.println(product);
        product.setName("Nguyen Hoang Hung");
        product.setImportPrice(100);
        product.setExportPrice(150);
        productImpl.insertProduct(product);
        
    }
}
