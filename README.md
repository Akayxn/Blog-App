# Simple Blog Application

A Java console application that allows users to create, read, update, and delete blog posts through a simple command-line interface.

## Features

- **Create Blogs**: Write and save blog posts with unique titles
- **Read Blogs**: View existing blog posts
- **Update Blogs**: Add content to existing blog posts
- **Delete Blogs**: Remove unwanted blog posts
- **List All Blogs**: View a list of all available blog posts

## How to Use

1. Run the application
2. Choose from the menu options:
   - Option 1: Write a new blog
   - Option 2: Read an existing blog
   - Option 3: Update a blog
   - Option 4: Delete a blog
   - Option 5: Exit the application

## Technical Details

- Written in Java
- Uses file I/O operations to store blog posts as text files
- Blog posts are stored in the `src/main/java/org/golb/blogfiles/` directory

## Getting Started

```
git clone [your-repository-url]
cd [repository-name]
javac org/golb/App.java
java org.golb.App
```

## Usage Notes

- To finish writing or updating a blog post, type `blog.finish`
- Each blog post must have a unique title
