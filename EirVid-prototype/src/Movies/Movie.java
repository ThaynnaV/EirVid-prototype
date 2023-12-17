/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Movies;

/**
 *
 * @author Alany 2021345
 */
public class Movie implements MovieInterface {
    private int id;
    private String title;
    private String originalTitle;
    private String originalLanguage;
    private String overview;
    private double popularity;
    private String releaseDate;
    private int runtime;
    private String tagline;
    private double voteAverage;
    private int voteCount;
    private double price;
    
    public Movie(){}
    public Movie(int id, String title){
        this.id = id;
        this.title = title;
    }
    public Movie(int id, String title, String originalTitle, String originalLanguage, String overview, double popularity,
                 String releaseDate, int runtime, String tagline, double voteAverage, int voteCount, double price) {
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.originalLanguage = originalLanguage;
        this.overview = overview;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.tagline = tagline;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.price = price;
    }
     // Getters and setters for the new attributes

    @Override
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    @Override
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }
    
    @Override
    public String getOriginalTitle() {
        return originalTitle;
    }

    @Override
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Override
    public String getOverview() {
        return overview;
    }

    @Override
    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public double getPopularity() {
        return popularity;
    }

    @Override
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int getRuntime() {
        return runtime;
    }

    @Override
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @Override
    public String getTagline() {
        return tagline;
    }

    @Override
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    @Override
    public double getVoteAverage() {
        return voteAverage;
    }

    @Override
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    // Existing methods for id and title

    @Override
    public int getMovieId() {
        return this.id;
    }

    @Override
    public String getMovieTitle() {
        return this.title;
    }
    
     // New method to print all information
    public void printMovieInfo() {
        System.out.println("Movie ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Original Title: " + originalTitle);
        System.out.println("Original Language: " + originalLanguage);
        System.out.println("Overview: " + overview);
        System.out.println("Popularity: " + popularity);
        System.out.println("Release Date: " + releaseDate);
        System.out.println("Runtime: " + runtime);
        System.out.println("Tagline: " + tagline);
        System.out.println("Vote Average: " + voteAverage);
        System.out.println("Vote Count: " + voteCount);
        System.out.println("Price: " + price);
    }
            
}
