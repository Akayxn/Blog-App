package org.golb;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static FileWriter fileWriter;
    static FileReader fileReader;
    static BufferedReader reader;
    static BufferedWriter writer;
    static String filePath = "src/main/java/org/golb/blogfiles/";
    static File file;

    public static void main(String[] args) {
        boolean exitRequested = false;
        do {
            try {
                int userChoice;
                userUi();
                System.out.print("Enter what you want to do:");
                userChoice = scanner.nextInt();
                scanner.nextLine(); //taking the next space created by nextInt();

                switch (userChoice) {
                    case 1:
                        writeBlog();
                        break;
                    case 2:
                        readBlog();
                        break;
                    case 3:
                        updateBlog();
                        break;
                    case 4:
                        deleteBlog();
                        break;
                    case 5:
                        exitRequested = true;
                        System.out.println("Blog App Closed!");
                        break;
                    default:
                        System.out.println("Enter a correct option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid input.");
                scanner.nextLine();
            }
        } while (!exitRequested);
    }

    private static void writeBlog() {

        System.out.print("Write a Title for your Blog:");
        String blogTitle = scanner.nextLine().strip();

        file = new File(filePath+blogTitle);
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
           writer.newLine(); //gives two extra line breaks
            System.out.print("Enter your blog contents (Type blog.finish to post your content):");
            while(true){
                userInput = scanner.nextLine();
                if(userInput.equals("blog.finish")){
                    break;
                }
                writer.write(userInput);
                writer.newLine();
            }
            System.out.println("Successfully posted a blog with the title " + blogTitle);
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static void readBlog() {
        File file = new File(filePath);

        //calls the namelist from the listBlogs() while also executing its task.
        ArrayList<String> fileNameList = listBlogs();
        if(fileNameList.isEmpty()){
            System.out.println("No Blogs to read currently.");
            return;
        }
        System.out.print("Enter the name of the blog you want to read:");
        String blogTitle = scanner.nextLine().strip();



        try{
            String line;
            if(fileNameList.contains(blogTitle)){
                //Making a Absolute File Path to open the file.
                String fullFilePath = filePath + blogTitle;
                reader = new BufferedReader(new FileReader(fullFilePath));
                while((line =reader.readLine())!= null){
                    System.out.println(line);
                }
                reader.close();
            }
            else{
                System.out.println("Blog with that title doesn't exists! Maybe check the spelling?");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with reading the blog!");
        }


    }

    private static void updateBlog() {

       //calls the namelist from the listBlogs() while also executing its task.
        ArrayList<String> fileNameList = listBlogs();

        if(fileNameList.isEmpty()){
            System.out.println("No Blogs to read currently.");
            return;
        }

        String userInput;
        System.out.print("Enter the name of the blog you want to edit:");
        String blogTitle = scanner.nextLine().strip();
        file = new File(filePath+blogTitle);

        try {
            if (fileNameList.contains(blogTitle)) {
                System.out.println("Enter the contents you want to add (Type blog.finish to post):");
                writer = new BufferedWriter(new FileWriter(file,true));
                while (true) {
                    userInput = scanner.nextLine();
                    if (userInput.equals("blog.finish")) {
                        break;
                    }
                    writer.write(userInput);
                    writer.newLine();
                }
                System.out.println("Successfully edited a blog with the title " + blogTitle);
                writer.close();
            }
            else{
                System.out.println("Blog with that Title doesn't exist! Maybe check the name?");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void deleteBlog() {

        //calls the namelist from the listBlogs() while also executing its task.
        ArrayList<String> fileNameList = listBlogs();

        if(fileNameList.isEmpty()){
            System.out.println("No Blogs to read currently.");
            return;
        }

        System.out.print("Enter the name of the blog you want to delete:");
        String blogTitle = scanner.nextLine().strip();
        file = new File(filePath+blogTitle);
        try{
            if(fileNameList.contains(blogTitle)){
                if(file.delete()){
                    System.out.println("Blog named " + blogTitle + " removed successfully.");
                }
            }
            else{
                System.out.println("Blog with that name doesn't exist! Maybe check the spelling?");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the file!");
        }

    }

    private static ArrayList<String> listBlogs(){
        file = new File(filePath);

        String[] files = file.list(); // getting an array of the files in the directory
        //basically both the statement somewhat means the something but its checking if the directory has no files.
        if(files == null || files.length == 0){
            return new ArrayList<>();
        }
        // making a arraylist to send it to the readBlog() to  compare user value with the existing filename.
        ArrayList<String> fileNameList = new ArrayList<>();
        System.out.println("==========List of Blogs:==========");
        int i = 1;
        for(String fileName : files){
            System.out.println(i + ". " + fileName);
            fileNameList.add(fileName);
            i++;
        }
        System.out.println("================================");
        return fileNameList;
    }



    private static void userUi(){
        System.out.println("=============USER_MENU===============");
        System.out.println("1. Write a Blog.");
        System.out.println("2. Read a Blog.");
        System.out.println("3. Update a Blog.");
        System.out.println("4. Delete a Blog.");
        System.out.println("5. Exit the app");
        System.out.println("=====================================");
    }
}