public class Book {

    Long ID;
    Long score;

    public Book(Long ID, Long score) {
        this.ID = ID;
        this.score = score;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
