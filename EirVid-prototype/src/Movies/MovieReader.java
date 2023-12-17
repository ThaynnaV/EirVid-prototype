/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

import DatabaseManagment.Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 2021345
 */
public class MovieReader {
    private final Movies movies;
    private final Database db;
    
    public MovieReader(Movies movies, Database db) {
        this.movies = movies;
        this.db = db;
    }
    
   public boolean readMoviesFromCSV(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line
            int counter = 0;
            br.readLine();
            while ((line = br.readLine()) != null) {
                counter++;
                List<String> values = this.parseCSVLine(line);
        
                String title = values.get(7); // title is 7
                // Create a new Movie using the attributes
                Movie newMovie = new Movie(counter, title);
                newMovie.setOriginalLanguage(values.get(0));
                newMovie.setOriginalTitle(values.get(1));
                                
                newMovie.setOverview(values.get(2));
                newMovie.setPopularity(Double.parseDouble(values.get(3)));
                newMovie.setReleaseDate(values.get(4));
                newMovie.setRuntime(Integer.parseInt(values.get(5)));
                newMovie.setTagline(values.get(6));
                newMovie.setVoteAverage(Double.parseDouble(values.get(8)));
                newMovie.setVoteCount(Integer.parseInt(values.get(9)));
                newMovie.setPrice(Double.parseDouble(values.get(10)));

                // Add the movie to the Movies class
                movies.getMoviesList().add(newMovie);

                // Insert the movie into the database using a PreparedStatement
                try {
                    String insertQuery = "INSERT IGNORE INTO movie (title, original_language, original_title, overview, popularity, " +
                        "release_date, runtime, tagline, vote_average, vote_count, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    
                    try (PreparedStatement preparedStatement = this.db.getConn().prepareStatement(insertQuery)) {
                        preparedStatement.setString(1, newMovie.getMovieTitle());
                        preparedStatement.setString(2, newMovie.getOriginalLanguage());
                        preparedStatement.setString(3, newMovie.getOriginalTitle());
                        preparedStatement.setString(4, newMovie.getOverview());
                        preparedStatement.setDouble(5, newMovie.getPopularity());
                        preparedStatement.setString(6, newMovie.getReleaseDate()); 
                        preparedStatement.setInt(7, newMovie.getRuntime());
                        preparedStatement.setString(8, newMovie.getTagline());
                        preparedStatement.setDouble(9, newMovie.getVoteAverage());
                        preparedStatement.setInt(10, newMovie.getVoteCount());
                        preparedStatement.setDouble(11, newMovie.getPrice());
                        preparedStatement.executeUpdate();
                    }
                   
                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                    return false;
                }
            }

            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    private int generateMovieId() {
        // Implement your logic to generate a unique movie ID programmatically
        // You may use a counter, random number, or any other strategy based on your requirements
        // For simplicity, I'll use a counter in this example
        return movies.getMoviesList().size() + 1;
    }
    
    // References: https://stackoverflow.com/questions/66478881/java-reading-a-csv-files-line-and-storing-it-into-a-list-of-lists
    //Reference Stack overflow: https://stackoverflow.com/questions/7800494/parse-csv-with-double-quote-in-some-cases
    private List<String> parseCSVLine(String line) {
        List<String> values = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder currentValue = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                values.add(currentValue.toString());
                currentValue = new StringBuilder();
            } else {
                currentValue.append(c);
            }
        }

        values.add(currentValue.toString()); // Add the last value

        return values;
    }

}
