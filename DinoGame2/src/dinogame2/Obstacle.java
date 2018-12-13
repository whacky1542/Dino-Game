package dinogame2;

import javafx.scene.image.Image;

public class Obstacle {
    private Image randomImage;
    private double offset;
    private Image crystal1 = new Image(DinoGame2.class.getResourceAsStream("CrystalSmall.png"));
    private Image crystal2 = new Image(DinoGame2.class.getResourceAsStream("CrystalMedium.png"));
    private Image crystal3 = new Image(DinoGame2.class.getResourceAsStream("CrystalLarge.png"));
    //public Image crystal1 = new Image("/Crystal1.png");
    //public Image crystal2 = new Image("/Crystal2.png");
    //public Image crystal3 = new Image("/Crystal3.png");
    
    public Obstacle() {
        this.randomImage = crystal1;
        this.offset = 0.0;
    }
    
    public Image getRandomImage() {
        int randomInt = ((int) (Math.random() * 3));
        if (randomInt == 1)
            randomImage = crystal1;
        else if (randomInt == 2)
            randomImage = crystal2;
        else
            randomImage = crystal3;
        System.out.println("randomImage is crystal" + randomInt);
        return this.randomImage;
    }
    
    public double getOffset() {
        offset = Math.random() * 1000;
        return this.offset;
    }
}
