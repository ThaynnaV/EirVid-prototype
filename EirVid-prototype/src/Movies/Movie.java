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
    
    public Movie(){}
    
    public Movie(int id, String title){
        this.id= id;
        this.title = title;
    }
    
    @Override
    public int getMovieId(){
        return this.id;
    }
    
    @Override
    public String getMovieTitle(){
        return this.title;
    }
            
}
