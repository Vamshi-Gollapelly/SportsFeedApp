# SportsFeedApp

A simple Sports News Feed app built for Android using Java as part of a university assignment.

## What the app does

The app shows sports news stories in a feed-style layout. Users can browse 
top stories, filter news by sport, tap on a story to read more, and bookmark 
their favourite stories to read later.

## Screens

- Home Screen - shows featured matches at the top in a horizontal scroll, 
  and a grid of latest news below
- Detail Screen - shows the full story with an image, title, description, 
  and related stories at the bottom
- Bookmarks Screen - shows all the stories the user has saved

## Features

- Horizontal scrolling row for featured matches
- 2 column news grid
- Search bar to filter news by sport category like Football, Basketball or Cricket
- Tap any story to open the full detail view
- Bookmark button to save favourite stories
- Bookmarks are stored locally on the device
- Bottom navigation to switch between Home and Bookmarks

## How it was built

- Language: Java
- Single Activity with multiple Fragments for navigation
- RecyclerView for displaying news lists
- Room Database to store bookmarks
- Navigation Component for moving between screens
- All news data is hardcoded as dummy data for demonstration purposes

## How to run

1. Clone or download this repository
2. Open it in Android Studio
3. Run it on an emulator or physical Android device
