/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Movies;

/**
 *
 * @author Alany 2021345
 */
public interface MovieInterface {
    int getMovieId();    
    String getMovieTitle();
    String getOriginalLanguage();
    String getOriginalTitle();
    String getOverview();
    double getPopularity();
    String getReleaseDate();
    int getRuntime();
    String getTagline();
    double getVoteAverage();
    int getVoteCount();
    double getPrice();
    void setOriginalTitle(String originalTitle);
    void setOriginalLanguage(String originalLanguage);
    void setOverview(String overview);
    void setPopularity(double popularity);
    void setReleaseDate(String releaseDate);
    void setRuntime(int runtime);
    void setTagline(String tagline);
    void setVoteAverage(double voteAverage);
    void setVoteCount(int voteCount);
    void setPrice(double price);
}
