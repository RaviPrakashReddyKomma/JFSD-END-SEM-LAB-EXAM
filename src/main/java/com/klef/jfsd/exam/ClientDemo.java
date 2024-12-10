package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Set up Hibernate session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Vehicle.class)
                                .addAnnotatedClass(Car.class)
                                .addAnnotatedClass(Truck.class)
                                .buildSessionFactory();

        // Create session
        Session session = factory.getCurrentSession();

        try {
            // Create Vehicle, Car, and Truck objects
            Vehicle vehicle = new Vehicle();
            vehicle.setName("Generic Vehicle");
            vehicle.setType("Transport");
            vehicle.setMaxSpeed(80);
            vehicle.setColor("White");

            Car car = new Car();
            car.setName("Sedan");
            car.setType("Car");
            car.setMaxSpeed(150);
            car.setColor("Red");
            car.setNumberOfDoors(4);

            Truck truck = new Truck();
            truck.setName("Dump Truck");
            truck.setType("Truck");
            truck.setMaxSpeed(60);
            truck.setColor("Blue");
            truck.setLoadCapacity(2000);

            // Start a transaction
            session.beginTransaction();

            // Save the objects
            session.save(vehicle);
            session.save(car);
            session.save(truck);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Objects saved!");
        } finally {
            factory.close();
        }
    }
}
