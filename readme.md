
# Author: Adam Golec
## Project start: June 6 2026

## Description
This is a note taking application written in Java.

Users can create, modify, organize, and manage notes.

This project is currenly being developed as an exercise to explore
Java application architecture, persistance, eventually javaFX, and Object-Oriented design.

## Learning Goals

- Practice OOP design
- Learn Layered Application Architecture
- Explore Repository and Service Patterns
- Learn File Persistence and JSON serialization
- Build a JavaFX UI
- Practice unit testing.

## Current features

- Create notes
- Modify Notes
  - Title
  - Body
  - Store in running memory.

## Planned Features

- JavaFX GUI
- JSON Persistance
- Note Tags
- Search Functionality
- Folder Organization
- Copy and Move notes

## Architecture

model/
    Note

repository/
    NoteRepository

service/
    NoteService

## Model

Represents the application data

Example:
    - Note

### Repository

Responsible for storing and retrieving notes.

Example:
    - NoteService

## Note:
<p>Saved data is not guaranteed to be compatible across development versions.
Delete the save file if deserialization fails after pulling new changes, or making 
changes to any serialized class during your development. </p>