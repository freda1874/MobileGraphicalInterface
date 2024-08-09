 
# **Musician Search Android App**

## **Project Overview**

an Android app that allows users to search for their favorite musicians and explore their top tracks directly from their mobile devices. 
This app enables users to retrieve a list of 50 hit songs from any artist and provides detailed information about each track. 
Users can listen to these songs, create personalized collections by saving them to their list, and manage their collection with full CRUD capabilities.

## **Features**

- **Search for Musicians**: Users can search for any musician by typing their name into the app, retrieving a list of 50 hit songs by the artist.
  ![Android1](https://github.com/user-attachments/assets/479688ef-8aa8-4419-996c-5ec142074427)

- **Detailed Song Information**: Each song in the list includes detailed information such as title, album, release date, and duration.
  ![Android2](https://github.com/user-attachments/assets/f742ad77-9805-4895-bb26-a1886deb58e0)

- **Listen to Tracks**: Users can listen to the songs directly within the app.
  ![Android2](https://github.com/user-attachments/assets/c8d16602-7cae-4b9c-9782-96add1097590)

- **Personalized Collections**: Users can save their favorite songs to a personalized list and manage the list with CRUD operations.
- ![Android3](https://github.com/user-attachments/assets/4eb4513d-1018-44dd-930e-b8f0b7fa01ea)

  ![Android4](https://github.com/user-attachments/assets/31288019-a11d-4871-9399-d9ef32a951c9)

  - **Create**: Add songs to your collection.
  - **Read**: View and listen to songs in your collection.
  - **Update**: Edit details or reorder songs in your collection.
  - **Delete**: Remove songs from your collection.

## **Technologies Used**

- **Frontend**:
  - **Java**: The primary programming language used for developing the Android app.
  - **Android Studio**: The integrated development environment (IDE) used to code and design the app interface.

- **Backend**:
  - **DeerSong API**: Used to fetch song data, including the top 50 hits for any given artist.
  - **MySQL**: Database used to store user data and personalized song collections.

## **Setup Instructions**

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/musician-search-android-app.git
   ```
2. **Open in Android Studio**:
   - Launch Android Studio.
   - Open the project by navigating to the cloned directory.
   
3. **Set up the API keys**:
   - Obtain an API key from DeerSong API.
   - Add your API key to the project by placing it in the appropriate configuration file (e.g., `strings.xml`).

4. **Run the application**:
   - Connect an Android device or use an emulator.
   - Click on the "Run" button in Android Studio to build and deploy the app.
 

This README provides a clear and concise overview of the project, making it easy for others to understand and get involved with your Android app development.
