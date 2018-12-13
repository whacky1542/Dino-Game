package dinogame2;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DinoGame2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        //Setup for obstacle class
        Obstacle ob = new Obstacle();
        
        //Play Button
        Button pBtn = new Button();
        pBtn.setText("Play");
        
        //Quit Button
        Button qBtn = new Button();
        qBtn.setText("Quit");
        qBtn.setTranslateX(-470);
        qBtn.setTranslateY(280);
        
        //Main Menu Button
        Button mBtn = new Button();
        mBtn.setText("Main Menu");
        mBtn.setTranslateX(-455);
        mBtn.setTranslateY(280);
        
        //Main Menu Button 2
        Button mBtn2 = new Button();
        mBtn2.setText("Main Menu");
        mBtn2.setTranslateX(-455);
        mBtn2.setTranslateY(280);
        
        //Score
        Text score = new Text(0, 0, Integer.toString(0));
        //score.setTranslateX(-490);
        score.setTranslateY(-290);
        Timeline scoreUpdate = new Timeline(new KeyFrame(Duration.seconds(0.01), new EventHandler<ActionEvent>() {
            int time = 0;
            @Override
            public void handle(ActionEvent event) {
                time += 1;
            	score.setText(Integer.toString(time));
            }
        }));
        scoreUpdate.setCycleCount(Timeline.INDEFINITE);

        
        //Ground Image
        Image ground = new Image(DinoGame2.class.getResourceAsStream("Ground.png"));
        //Image ground = new Image("/Ground.png");
        ImageView iView = new ImageView();
        iView.setImage(ground);
        iView.setTranslateX(500);
        Path groundPath = new Path();
        MoveTo groundOrigin = new MoveTo(1500, 300);
        groundPath.getElements().add(groundOrigin);
        LineTo groundEnd = new LineTo(500, 300);
        groundPath.getElements().add(groundEnd);
        PathTransition groundAnimation = new PathTransition();
        groundAnimation.setNode(iView);
        groundAnimation.setPath(groundPath);
        groundAnimation.setDuration(Duration.seconds(1.5));
        groundAnimation.setCycleCount(Animation.INDEFINITE);
        groundAnimation.setInterpolator(Interpolator.LINEAR);
        groundAnimation.play();
        
        //BoxMan
//        ImageView imageView = new ImageView();
//        List<Image> images = new ArrayList<>();
//        Image boxMan1 = new Image(DinoGame2.class.getResourceAsStream("BoxMan1.png"));
//        Image boxMan2 = new Image(DinoGame2.class.getResourceAsStream("BoxMan2.png"));
//        Image boxMan1 = new Image("/BoxMan1.png");
//        Image boxMan2 = new Image("/BoxMan2.png");
//        images.add(0, boxMan1);
//        images.add(1, boxMan2);
//        Timeline timeline = new Timeline();
//        timeline.setCycleCount(images.size());
//        timeline.setAutoReverse(false);
//        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new KeyValue (imageView.translateXProperty(), 25)));
//        timeline.play();
        
        //BoxMan Temp
        //Image boxMan = new Image("/BoxMan1.png");
        Image boxMan = new Image(DinoGame2.class.getResourceAsStream("BoxMan1.png"));
        ImageView iView2 = new ImageView();
        iView2.setImage(boxMan);
        iView2.setTranslateX(-350);
        iView2.setTranslateY(70);
        //Jump Animation
        Path jumpPath = new Path();
        MoveTo boxManOrigin = new MoveTo(-312, 105);
        jumpPath.getElements().add(boxManOrigin);
        LineTo jumpHeight = new LineTo(-312, 105 - 100);
        jumpPath.getElements().add(jumpHeight);
        PathTransition jumpAnimation = new PathTransition();
        jumpAnimation.setNode(iView2);
        jumpAnimation.setPath(jumpPath);
        jumpAnimation.setDuration(Duration.seconds(.25));
        jumpAnimation.setCycleCount(1);
        jumpAnimation.setInterpolator(Interpolator.EASE_OUT);
        //Fall Animation
        Path fallPath = new Path();
        MoveTo boxManOrigin2 = new MoveTo(-312, 105 - 100);
        fallPath.getElements().add(boxManOrigin2);
        LineTo fallHeight = new LineTo(-312, 105);
        fallPath.getElements().add(fallHeight);
        PathTransition fallAnimation = new PathTransition();
        fallAnimation.setNode(iView2);
        fallAnimation.setPath(fallPath);
        fallAnimation.setDuration(Duration.seconds(.25));
        fallAnimation.setCycleCount(1);
        fallAnimation.setInterpolator(Interpolator.EASE_IN);
        
        //Speed modifier
//        double speed = 1;
//        Timeline speedModifier = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//            	speed -= 0.01;
//            }
//        }));
//        speedModifier.setCycleCount(Timeline.INDEFINITE);
//        speedModifier.play();
        
        //Crystal Generation
        ImageView iView3 = new ImageView();
        Image crystal1 = new Image(DinoGame2.class.getResourceAsStream("CrystalSmall.png"));
        //Image crystal1 = new Image("/Crystal1.png");
        iView3.setImage(crystal1);
        iView3.setTranslateX(1000);
        Path crystalPath = new Path();
        MoveTo crystalOrigin = new MoveTo(1000, 105);
        crystalPath.getElements().add(crystalOrigin);
        LineTo crystalEnd = new LineTo(-1000, 105);
        crystalPath.getElements().add(crystalEnd);
        PathTransition crystalAnimation = new PathTransition();
        crystalAnimation.setNode(iView3);
        crystalAnimation.setPath(crystalPath);
        crystalAnimation.setDuration(Duration.seconds(3));
        crystalAnimation.setCycleCount(1);
        crystalAnimation.setInterpolator(Interpolator.LINEAR);
        crystalAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                iView3.setImage(ob.getRandomImage());
                crystalOrigin.setX(1000 + ob.getOffset());
                System.out.println("Obstacle placed at " + crystalOrigin.getX());
                crystalAnimation.play();
            }
        });
        
        //Player Collision Box
        Rectangle collider = new Rectangle(30, 40);
        collider.setFill(Color.TRANSPARENT);
        //Crystal Collision Box
        Rectangle collider2 = new Rectangle(40, 40);
        collider2.setFill(Color.TRANSPARENT);
        
        //Game Over image
        ImageView iViewLose = new ImageView();
        Image gameOver = new Image(DinoGame2.class.getResourceAsStream("GameOver.png"));
        //Image gameOver = new Image("/GameOver.png");
        iViewLose.setImage(gameOver);
        
        //Layouts
        StackPane root = new StackPane();
        root.getChildren().addAll(pBtn, qBtn);
        StackPane root2 = new StackPane();
        root2.getChildren().addAll(iView, score, iView2, iView3, collider, collider2, mBtn);
        StackPane root3 = new StackPane();
        root3.getChildren().addAll(iViewLose, mBtn2);
        
        //Scenes
        Scene scene = new Scene(root, 1000, 600);
        Scene scene2 = new Scene(root2, 1000, 600);
        Scene scene3 = new Scene(root3, 1000, 600);
        
        //Stage
        primaryStage.setTitle("Dino Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Timeline
        Timeline crystalGenerator = new Timeline(new KeyFrame(Duration.seconds(3.1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            }
        }));
        crystalGenerator.setCycleCount(Timeline.INDEFINITE);
        //Idea from: https://stackoverflow.com/questions/9966136/javafx-periodic-background-task by Sergey Grinev
        Timeline colliderUpdate = new Timeline(new KeyFrame(Duration.seconds(0.005), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	collider.setTranslateX(iView2.getTranslateX());
                collider.setTranslateY(iView2.getTranslateY());
                collider2.setTranslateX(iView3.getTranslateX());
                collider2.setTranslateY(iView3.getTranslateY());
                //Help from: https://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx by jewelsea
                if (collider.getBoundsInParent().intersects(collider2.getBoundsInParent())) {
                    primaryStage.setScene(scene3);
                    crystalGenerator.stop();
                    crystalAnimation.stop();
                    scoreUpdate.stop();
                }
            }
        }));
        colliderUpdate.setCycleCount(Timeline.INDEFINITE);
        colliderUpdate.play();
        
        //Events
        pBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);
                crystalGenerator.play();
            }
        });
        qBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//              File file = new File(".");
//              for(String fileNames : file.list()) System.out.println(fileNames);
	            try {
	      			//From https://stackoverflow.com/questions/20039980/java-replace-line-in-text-file by Micheal Yaworski
	      			BufferedReader file = new BufferedReader(new FileReader("HighScore.txt"));
	      			String line;
	      			StringBuffer inputBuffer = new StringBuffer();
	      			while ((line = file.readLine()) != null) {
	      				inputBuffer.append(line);
	      				inputBuffer.append('\n');
	      			}
	      			String inputStr = inputBuffer.toString();
	      			String delims = " ";
	      			String scores[] = inputStr.split(delims);
	      			file.close();
	      			System.out.println(inputStr);
//	      			for (int i = 0; i < scores.length; i++) {
//	      				System.out.println(scores[i]);
//	      				if (Integer.parseInt(scores[i]) < Integer.parseInt(score.getText())) {
//	      					System.out.println("NEW HIGH SCORE!");
//	      					System.out.println(score.getText());
//	      					inputStr = score.getText() + inputStr.substring(inputStr.indexOf(scores[i])+1);
//	      					String myName = "domanokz";
//	      					String newName = myName.substring(0,4)+'x'+myName.substring(5);
//	      					System.out.println(newName);
//	      					System.out.println(inputStr);
//	      					i = 3;
//	      				}
//	      			}
	      			if (Integer.parseInt(inputStr.substring(inputStr.indexOf("a")+1, inputStr.indexOf("b")-1)) < Integer.parseInt(score.getText())) {
      					System.out.println("NEW HIGH SCORE!");
      					System.out.println(score.getText());
      					inputStr = "a" + score.getText() + " b" + inputStr.substring(inputStr.indexOf("a")+1, inputStr.indexOf("b")-1) + " c" + inputStr.substring(inputStr.indexOf("b")+1, inputStr.indexOf("c")-1) + "z";
      					System.out.println(inputStr);
      				} else if (Integer.parseInt(inputStr.substring(inputStr.indexOf("b")+1, inputStr.indexOf("c")-1)) < Integer.parseInt(score.getText())) {
      					System.out.println("NEW HIGH SCORE!");
      					System.out.println(score.getText());
      					inputStr = inputStr.substring(inputStr.indexOf("a"), inputStr.indexOf("b")+1) + score.getText() + " c" + inputStr.substring(inputStr.indexOf("b")+1, inputStr.indexOf("c")-1) + "z";
      					System.out.println(inputStr);
      				} else if (Integer.parseInt(inputStr.substring(inputStr.indexOf("c")+1, inputStr.indexOf("z"))) < Integer.parseInt(score.getText())) {
      					System.out.println("NEW HIGH SCORE!");
      					System.out.println(score.getText());
      					inputStr = inputStr.substring(inputStr.indexOf("a"), inputStr.indexOf("c")+1) + score.getText() + "z";
      					System.out.println(inputStr);
      				}
	      			FileOutputStream fileOut = new FileOutputStream("HighScore.txt");
	      			fileOut.write(inputStr.getBytes());
	      			fileOut.close();
	      		} catch (FileNotFoundException e) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                primaryStage.close();
            }
        });
        mBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });
        mBtn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);
            }
        });
        scene2.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent ke) {
        		if(ke.getCode().equals(KeyCode.UP)) {
                            crystalAnimation.play();
                            scoreUpdate.play();
                            jumpAnimation.play();
                            jumpAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                            fallAnimation.play();
                                    }
                            });
        		}
        	}
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
