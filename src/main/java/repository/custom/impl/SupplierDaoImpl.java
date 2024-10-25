package repository.custom.impl;

import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import repository.custom.SupplierDao;
import util.HibernateUtil;

import java.util.List;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public boolean save(SupplierEntity supplierEntity) {
        System.out.println("Repository : " + supplierEntity);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        Session session = HibernateUtil.getSession();
        ObservableList<SupplierEntity> supplierEntityObservableList = FXCollections.observableArrayList();

        try {
            session.beginTransaction();
            List<SupplierEntity> suppliers = session.createQuery("FROM SupplierEntity", SupplierEntity.class).list();
            supplierEntityObservableList.addAll(suppliers);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return supplierEntityObservableList;
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            session.update(supplierEntity);
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

            SupplierEntity supplierEntity = session.get(SupplierEntity.class, Integer.valueOf(id));

            if (supplierEntity != null) {
                session.delete(supplierEntity);
                session.getTransaction().commit();
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR,"Supplier with ID" + id + "not found.").show();
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
    public SupplierEntity search(String id) {
        Session session = HibernateUtil.getSession();
        SupplierEntity supplier = null;

        try {
            session.beginTransaction();
            supplier = session.get(SupplierEntity.class, Integer.valueOf(id));

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return supplier;
    }
}
