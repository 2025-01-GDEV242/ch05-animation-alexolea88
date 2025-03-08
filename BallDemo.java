import java.awt.Color;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @author Alejandro Olea
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
    /**
     * Draws a box and places one or more balls inside it.
     * @param numBalls
     */
    public void boxBounce(int numBalls) 
    {
        // Ensure the number of balls is valid (between 5 and 30)
        if (numBalls < 5 || numBalls > 30) 
        {
            System.out.println("Number of balls must be between 5 and 30.");
            return;
        }

        // Create the canvas for drawing
        Canvas myCanvas = new Canvas("Box Bounce", 600, 500);
        myCanvas.setBackgroundColor(Color.WHITE); // Set background to white
        myCanvas.setVisible(true); // Make sure the canvas is visible

        // Set the boundaries for the box (the walls)
        int leftWall = 50, rightWall = 550, topWall = 50, bottomWall = 400;

        // Create the list of balls
        ArrayList<BoxBall> balls = new ArrayList<>();
        for (int i = 0; i < numBalls; i++) 
        {
            balls.add(new BoxBall(leftWall, rightWall, topWall, bottomWall, myCanvas));
        }

        // Start the animation loop with a maximum time for demo
        long startTime = System.currentTimeMillis();
        long maxDuration = 10000; // Stop after 10 seconds (in milliseconds)

        while (true) 
        {
            myCanvas.wait(50); // Delay to create animation effect (50 milliseconds)

            // Check if the animation has been running for more than the max duration
            if (System.currentTimeMillis() - startTime > maxDuration) 
            {
                break; // Stop the animation if max duration is exceeded
            }

            // Redraw the box (walls) each frame to ensure they stay visible
            myCanvas.setForegroundColor(Color.BLACK);
            myCanvas.fillRectangle(leftWall, topWall, rightWall - leftWall, bottomWall - topWall);

            // Move each ball
            for (BoxBall ball : balls) 
            {
                ball.move();
            }
        }
    }
}
