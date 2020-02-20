public class Book implements Comparable{

    Long ID;
    Long score;

    public Book(String ID, String score) {
        this.ID = Long.parseLong(ID);
        this.score = Long.parseLong(score);
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

    @Override
    public int compareTo(Object comparestu) {
        Long compareage=((Book)comparestu).getScore();
        return (int) (compareage-this.score);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", score=" + score +
                '}';
    }
}
