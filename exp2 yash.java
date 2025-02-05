class Video {
    private String title;
    private boolean checkedOut;
    private double rating;
    private int ratingCount;

    public Video(String title) {
        this.title = title;
        this.checkedOut = false;
        this.rating = 0.0;
        this.ratingCount = 0;
    }

    public void checkOut() {
        this.checkedOut = true;
    }

    public void returnVideo() {
        this.checkedOut = false;
    }

    public void receiveRating(int rating) {
        this.rating = (this.rating * this.ratingCount + rating) / (++this.ratingCount);
    }

    public String getTitle() {
        return title;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public double getRating() {
        return rating;
    }
}

class VideoStore {
    private Video[] inventory;
    private int count;

    public VideoStore() {
        inventory = new Video[10];
        count = 0;
    }

    public void addVideo(String title) {
        if (count < inventory.length) {
            inventory[count++] = new Video(title);
        }
    }

    public void checkOut(String title) {
        for (int i = 0; i < count; i++) {
            if (inventory[i].getTitle().equals(title) && !inventory[i].isCheckedOut()) {
                inventory[i].checkOut();
                break;
            }
        }
    }

    public void returnVideo(String title) {
        for (int i = 0; i < count; i++) {
            if (inventory[i].getTitle().equals(title) && inventory[i].isCheckedOut()) {
                inventory[i].returnVideo();
                break;
            }
        }
    }

    public void receiveRating(String title, int rating) {
        for (int i = 0; i < count; i++) {
            if (inventory[i].getTitle().equals(title)) {
                inventory[i].receiveRating(rating);
                break;
            }
        }
    }

    public void listInventory() {
        for (int i = 0; i < count; i++) {
            System.out.println("Title: " + inventory[i].getTitle() + ", Checked Out: " + inventory[i].isCheckedOut() + ", Rating: " + inventory[i].getRating());
        }
    }
}

public class VideoStoreLauncher {
    public static void main(String[] args) {
        VideoStore store = new VideoStore();
        store.addVideo("The Matrix");
        store.addVideo("Godfather II");
        store.addVideo("Star Wars Episode IV: A New Hope");

        store.receiveRating("The Matrix", 5);
        store.receiveRating("The Matrix", 4);
        store.receiveRating("Godfather II", 5);
        store.receiveRating("Godfather II", 4);
        store.receiveRating("Star Wars Episode IV: A New Hope", 5);

        store.checkOut("The Matrix");
        store.checkOut("Godfather II");
        store.returnVideo("The Matrix");

        System.out.println("Inventory after renting out 'Godfather II':");
        store.listInventory();
    }
}