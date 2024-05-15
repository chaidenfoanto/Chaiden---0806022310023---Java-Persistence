package com.example;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTest {

    public static Scanner scanner = new Scanner(System.in);
    public static Configuration configuration = new Configuration().configure();
    public static SessionFactory sessionFactory = configuration.buildSessionFactory();
    public static Session session = sessionFactory.openSession();

    public static String InputText(String value) {
        try {
            System.out.print(value);
            String input = toTitle(scanner.nextLine().trim());

            if (input.matches("\\d+")) {
                throw new IllegalArgumentException("Input cannot contain only numbers.");
            }

            if (!input.matches("^[a-zA-Z\\s]*$")){
                System.out.println("Input should only contain alphabets.");
                return InputText(value);
            } 

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                return InputText(value);
            }
            else{
                return input;
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("\n----------Error Detected : " + ex.getMessage()+"----------\n");
            return InputText(value);
        } catch (Exception ex) {
            System.out.println("\n----------Error Detected : " + ex.getMessage()+"----------\n");
            return InputText(value);
        }
        
    }
    public static String toTitle(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
    
        StringBuilder converted = new StringBuilder();
    
        boolean convertNext = true;
        for (char chr : text.toCharArray()) {
            if (Character.isSpaceChar(chr)) {
                convertNext = true;
            } else if (convertNext) {
                chr = Character.toTitleCase(chr);
                convertNext = false;
            } else {
                chr = Character.toLowerCase(chr);
            }
            converted.append(chr);
        }
    
        return converted.toString();
    }

    public static int InputInt(String value) {
        try {
            System.out.print(value);
            String inputString = scanner.nextLine().trim();
            if (inputString.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                return InputInt(value);
            }

            if (inputString.contains(".") || inputString.contains(",")) {
                System.out.println("Invalid input. Please input integers only.");
                return InputInt(value);
            }

            Integer input = Integer.parseInt(inputString);

            if (input < 0) {
                System.out.println("Input cannot be less than or equal to 0. Please try again.");
                return InputInt(value);
            }
            else{
                return input;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please input integers only.");
            return InputInt(value);
        } catch (Exception ex) {
            System.out.println("\n----------Error Detected : " + ex.getMessage()+"----------\n");
            return InputInt(value);
        }
    }

    public static void displayStudents() {
        // Data contoh mahasiswa
        List<Students> getresult = session.createQuery("select s from Students s", Students.class).list();

        // Menampilkan header
        StringBuilder separatorTop = new StringBuilder();
        StringBuilder teksJudul = new StringBuilder();
        String[] header = { "ID", "Name", "Age","Major" };
        int[] columnLengths = new int[header.length];


        for (Students student : getresult) {
            String[] data = { String.valueOf(student.getId()),student.getName(), String.valueOf(student.getAge()), student.getMajor() };

            for (int i = 0; i < data.length; i++) {
                columnLengths[i] = Math.max(columnLengths[i], data[i].length());
                if (columnLengths[i] < header[i].length()) {
                    columnLengths[i] = header[i].length();
                }
            }
        }

        int totalLength = 0;
        for (int length : columnLengths) {
            totalLength += length;
        }
        totalLength += 3 * columnLengths.length + 1;

        String judul = "Student List";
        int judulLength = totalLength - judul.length() - 2;

        for (int i = 0; i < (judulLength / 2); i++) {
            teksJudul.append(" ");
        }

        for (int i = 0; i < totalLength; i++) {
            separatorTop.append("=");
        }

        System.out.println(separatorTop.toString());

        System.out.println("|" + teksJudul.toString() + judul + teksJudul.toString() + "|");

        System.out.println(separatorTop.toString());

        for (int i = 0; i < header.length; i++) {
            System.out.print("| " + String.format("%-" + (columnLengths[i] + 1) + "s", header[i]));
        }
        System.out.println("|");

        System.out.println(separatorTop.toString());

        for (Students student : getresult) {
            String[] data = { String.valueOf(student.getId()),student.getName(), String.valueOf(student.getAge()), student.getMajor() };

            for (int i = 0; i < data.length; i++) {
                System.out.print("| " + String.format("%-" + (columnLengths[i] + 1) + "s", data[i]));
            }
            System.out.println("|");
        }

        System.out.println(separatorTop.toString());
    }

    public static void main(String[] args) {
        Transaction tx = null;
        try {
            boolean exit = true;
            while (exit) {
                int choice = InputInt("Choose your action!\n1. Insert Data\n2. Update Data\n3. Delete Data\n4. View Data\n5. Exit\nChoice [1-5] : ");
                switch (choice) {
                    case 1:
                        System.out.println("\n---------- Insert Data ----------\n\n");
                        tx = session.beginTransaction();
                        Students newStudent = new Students();
                        newStudent.setName(InputText("Insert student's name\t: "));
                        newStudent.setAge(InputInt("Insert student's age\t: "));
                        newStudent.setMajor(InputText("Insert student's major\t: "));
                        session.save(newStudent);
                        tx.commit();
                        System.out.println("\n---------- DATA SUCCESSFULLY ADDED ----------\n");
                        break;
                    case 2:
                        System.out.println("\n---------- Update Data ----------\n\n");
                        tx = session.beginTransaction();
                        displayStudents();
                        session.getTransaction().commit();
                        Long updateId = Long.parseLong(String.valueOf(InputInt("Student's ID that you want to update\t: ")));
                        Students studentToUpdate = session.get(Students.class, updateId);
                        if (studentToUpdate != null) {
                            tx = session.beginTransaction();
                            System.out.println("Student's data you want to update\t: " + studentToUpdate);
                            studentToUpdate.setName(InputText("Insert -NEW- student's name\t: "));
                            studentToUpdate.setAge(InputInt("Insert -NEW- student's age\t: "));
                            studentToUpdate.setMajor(InputText("Insert -NEW- student's major\t: "));
                            session.update(studentToUpdate);
                            tx.commit();
                            System.out.println("\n---------- DATA SUCCESSFULLY UPDATED ----------\n");
                        } else {
                            System.out.println("Student's ID was not found!");
                        }
                        break;
                    case 3:
                        System.out.println("\n---------- Delete Data ----------\n\n");
                        tx = session.beginTransaction();
                        displayStudents();
                        session.getTransaction().commit();
                        Long deleteId = Long.parseLong(String.valueOf(InputInt("Student's ID that you want to delete\t: ")));
                        Students studentToDelete = session.get(Students.class, deleteId);
                        if (studentToDelete != null) {
                            tx = session.beginTransaction();
                            System.out.println("Student's data you want to update\t: " + studentToDelete);
                            session.delete(studentToDelete);
                            tx.commit();
                            System.out.println("\n---------- DATA SUCCESSFULLY DELETED ----------\n");
                        } else {
                            System.out.println("Student's ID was not found!");
                        }
                        break;
                    case 4:
                        System.out.println("\n---------- View Data ----------\n\n");
                        tx = session.beginTransaction();
                        displayStudents();
                        session.getTransaction().commit();
                        break;
                    case 5:
                        exit = false;
                        break;
                    default:
                        System.out.println("Invalid Choice!");
                }
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}

