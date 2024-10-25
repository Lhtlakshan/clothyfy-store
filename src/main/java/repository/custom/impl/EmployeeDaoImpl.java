package repository.custom.impl;

import entity.EmployeeEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import repository.custom.EmployeeDao;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employeeEntity) {
        System.out.println("Repository : " + employeeEntity);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(employeeEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        ObservableList<EmployeeEntity> employeeList = FXCollections.observableArrayList();

        try {
            session.beginTransaction();
            List<EmployeeEntity> employees = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class).list();
            employeeList.addAll(employees);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employeeList;
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(employeeEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, Integer.valueOf(id));

            if (employeeEntity != null) {
                session.delete(employeeEntity);
                session.getTransaction().commit();
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR,"Employee with ID" + id + "not found.").show();
                return false;
            }
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public EmployeeEntity search(String id) {
        Session session = HibernateUtil.getSession();
        EmployeeEntity employee = null;

        try {
            session.beginTransaction();
            employee = session.get(EmployeeEntity.class, Integer.valueOf(id));

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return employee;
    }


}
