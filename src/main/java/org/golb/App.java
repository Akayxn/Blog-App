package org.golb;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static FileWriter fileWriter;
    static FileReader fileReader;
    static BufferedReader reader;
    static BufferedWriter writer;
    static String filePath = "src/main/java/org/golb/blogfiles/";

    public static void main(String[] args) {
        do{
            try{
                int userChoice;
                userUi();
                System.out.print("Enter what you want to do:");
                userChoice = scanner.nextInt();
                scanner.nextLine(); //taking the next space created by nextInt();

                switch (userChoice){
                    case 1:
                        writeBlog();
                        break;
                    case 2:
                        readBlog();
                        break;
                    case 3:
                        editBlog();
                        break;
                    case 4:
                        deleteBlog();
                        break;
                    case 5:
                        System.out.println("Blog App Closed!");
                        System.exit(444);
                    default:
                        System.out.println("Enter a correct option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input.");
                scanner.nextLine();
            }
        }while(true);
    }

    private static void writeBlog() {
        System.out.print("Write a Title for your Blog:");
        String blogTitle = scanner.nextLine().strip();
        System.out.print("Enter your name:");
        String userName = scanner.nextLine().strip();
        File file = new File(filePath+blogTitle);

        try{
            String userInput  = "";
           if(file.createNewFile()){
               System.out.println("Created Blog with the Title " + blogTitle );
           }
           else{
               System.out.println("Blog with that title already exists.");
               return;
           }
           writer = new BufferedWriter(new FileWriter(file,true));

           writer.write("Title:" + blogTitle); //creating a header in the file.
           writer.newLine();
            System.out.print("Enter your blog contents (Type blog.finish to post your content):");
            while(true){
                userInput = scanner.nextLine();
                if(userInput.equals("blog.finish")){
                    break;
                }
                writer.write(userInput);
                writer.newLine();
            }
            writer.write("By: " + userName);
            writer.newLine();
            System.out.println("Successfully posted a blog with the title " + blogTitle);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private static void readBlog() {
    }

    private static void editBlog() {
    }


    private static void deleteBlog() {
    }





    private static void userUi(){
        System.out.println("==================================");
        System.out.println("1. Write a Blog.");
        System.out.println("2. Read a Blog.");
        System.out.println("3. Update a Blog.");
        System.out.println("4. Delete a Blog.");
        System.out.println("5. Exit the app");
        System.out.println("==================================");
    }
}