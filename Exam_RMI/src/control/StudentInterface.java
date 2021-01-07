package control;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Student;

/**
 *
 * @author The Godfather
 */
public interface StudentInterface extends Remote{
    public List<Student> search(String keyword) throws RemoteException;
    public boolean editStudent(int id, Student student) throws RemoteException;
}
