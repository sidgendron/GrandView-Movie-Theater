package org.champlain.prog2.grandviewmovietheater.Model;
/**
 * Represents a movie available at GMV
 *
 * Managers can add new movies and update existing movie information.
 *
 * @author Keren
 */
public class Movie {

    /**
     * The unique ID assigned to the movie.
     */
    private final int aMovieId;

    /**
     * The title of the movie.
     */
    private String aTitle;

    /**
     * The genre of the movie.
     */
    private String aGenre;

    /**
     * The language of the movie.
     */
    private String aLanguage;

    /**
     * The duration of the movie in minutes.
     */
    private int aMovieLength;

    /**
     * A short description of the movie.
     */
    private String aDescription;

    /**
     * The file path of the movie poster.
     */
    private String aPosterPath;

    /**
     * Creates a movie with the provided information.
     *
     * @param pMovieId the movie's unique ID
     * @param pTitle the movie's title
     * @param pGenre the movie's genre
     * @param pLanguage the movie's language
     * @param pMovieLength the movie's duration in minutes
     * @param pDescription the movie's description
     * @param pPosterPath the movie poster's file path
     * @throws IllegalArgumentException if any required value is invalid
     */
    public Movie(int pMovieId, String pTitle, String pGenre,
                 String pLanguage, int pMovieLength,
                 String pDescription, String pPosterPath) {

        if (pMovieId <= 0) {
            throw new IllegalArgumentException(
                    "Movie ID must be greater than 0.");
        }

        this.aMovieId = pMovieId;
        this.setTitle(pTitle);
        this.setGenre(pGenre);
        this.setLanguage(pLanguage);
        this.setMovieLength(pMovieLength);
        this.setDescription(pDescription);
        this.setPosterPath(pPosterPath);
    }

    /**
     * Gets the movie's unique ID.
     *
     * @return the movie ID
     */
    public int getMovieId() {
        return this.aMovieId;
    }

    /**
     * Gets the movie's title.
     *
     * @return the movie title
     */
    public String getTitle() {
        return this.aTitle;
    }

    /**
     * Updates the movie's title.
     *
     * @param pTitle the new movie title
     * @throws IllegalArgumentException if the title is null or blank
     */
    public void setTitle(String pTitle) {
        if (pTitle == null || pTitle.isBlank()) {
            throw new IllegalArgumentException(
                    "Movie title cannot be empty.");
        }

        this.aTitle = pTitle.trim();
    }

    /**
     * Gets the movie's genre.
     *
     * @return the movie genre
     */
    public String getGenre() {
        return this.aGenre;
    }

    /**
     * Updates the movie's genre.
     *
     * @param pGenre the new movie genre
     * @throws IllegalArgumentException if the genre is null or blank
     */
    public void setGenre(String pGenre) {
        if (pGenre == null || pGenre.isBlank()) {
            throw new IllegalArgumentException(
                    "Movie genre cannot be empty.");
        }

        this.aGenre = pGenre.trim();
    }

    /**
     * Gets the movie's language.
     *
     * @return the movie language
     */
    public String getLanguage() {
        return this.aLanguage;
    }

    /**
     * Updates the movie's language.
     *
     * @param pLanguage the new movie language
     * @throws IllegalArgumentException if the language is null or blank
     */
    public void setLanguage(String pLanguage) {
        if (pLanguage == null || pLanguage.isBlank()) {
            throw new IllegalArgumentException(
                    "Movie language cannot be empty.");
        }

        this.aLanguage = pLanguage.trim();
    }

    /**
     * Gets the movie's duration in minutes.
     *
     * @return the movie duration in minutes
     */
    public int getMovieLength() {
        return this.aMovieLength;
    }

    /**
     * Updates the movie's duration in minutes.
     *
     * @param pMovieLength the new duration in minutes
     * @throws IllegalArgumentException if the duration is not positive
     */
    public void setMovieLength(int pMovieLength) {
        if (pMovieLength <= 0) {
            throw new IllegalArgumentException(
                    "Movie length must be greater than 0.");
        }

        this.aMovieLength = pMovieLength;
    }

    /**
     * Gets the movie's description.
     *
     * @return the movie description
     */
    public String getDescription() {
        return this.aDescription;
    }

    /**
     * Updates the movie's description.
     *
     * @param pDescription the new movie description
     * @throws IllegalArgumentException if the description is null or blank
     */
    public void setDescription(String pDescription) {
        if (pDescription == null || pDescription.isBlank()) {
            throw new IllegalArgumentException(
                    "Movie description cannot be empty.");
        }

        this.aDescription = pDescription.trim();
    }

    /**
     * Gets the movie poster's file path.
     *
     * @return the poster file path
     */
    public String getPosterPath() {
        return this.aPosterPath;
    }

    /**
     * Updates the movie poster's file path.
     *
     * @param pPosterPath the new movie poster's file path
     * @throws IllegalArgumentException if the path is null or blank
     */
    public void setPosterPath(String pPosterPath) {
        if (pPosterPath == null || pPosterPath.isBlank()) {
            throw new IllegalArgumentException(
                    "Poster path cannot be empty.Choose an image from this device.");
        }

        this.aPosterPath = pPosterPath.trim();
    }
}

