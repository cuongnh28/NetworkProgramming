/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author HoangHung
 */
public class ProductImpl extends UnicastRemoteObject implements IProduct{
    public  ProductImpl() throws RemoteException{
    super();
    }
    @Override
    public Product getProduct(String studentCode) throws RemoteException {
        Product product=new Product(100,studentCode,null,0,0,null);
        return product;
    }

    @Override
    public boolean insertProduct(Product product) throws RemoteException {
        if(product.getName().length()<8|| product.getName().length()>20) return false;
        if(product.getImportPrice()>product.getExportPrice()) return false;
        
        System.out.println(product);
        return true;
    }
    
}
