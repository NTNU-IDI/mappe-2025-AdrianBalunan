import java.util.ArrayList;

/**
 * AuthorRegistry class contains a list of authors and methods to manage them.
 * @author Adrian Balunan
 * @version 1.0
 */

public class authorRegistry {
    /* ArrayList that stores authors of the author class */
    private ArrayList<author> Authors;

    /** 
     * Constructor for the authorRegistry class 
     */
    public authorRegistry(){
        Authors = new ArrayList<>();
    }

    /** 
     * Method to add an author to the ArrayList Authors
     * @param f input author
    */
    public void addAuthor(author f) {
        Authors.add(f);
    }

    /**
     * Getter-method, returns the list of authors
     * @return list of authors
     */
    public ArrayList<author> getAuthors() {
        return Authors;
    }

    /**
     * Search function, searches for a spesific author by given id
     * @param id given id
     * @return the author with matching id, null if not found
     */
    public author getAuthorByID(int id){
        author foundAuthor = Authors.stream()
            .filter(x -> x.getAuthor_Id() == id)
            .findFirst()
            .orElse(null);
        return foundAuthor;
    }

    /**
     * Delete function, deletes an author by given id
     * @param inputId given id
     */
    public void DeleteByID(int inputId){
        Authors.removeIf(x -> x.getAuthor_Id() == inputId);
    }

    /**
     * See all function, prints all authors in the registry
     */
    public void seeAll() {
        System.out.println("List of Authors:");
        Authors.forEach(x -> System.out.println("-  " + x.toString()));
        System.out.println("");
    }
}
